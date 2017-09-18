package hash;

import java.util.Arrays;

import miscFunctions.Misc;

public class Keccak extends HashFunction
{

	// Keccak parameters
	private int b, w, l, c, r, d, n;

	private boolean firstSqueeze = true;

	private byte[] state;

	public Keccak(int b, int d, StringBuilder output, int verbose)
	{
		// Call constructor of superclass
		super(output, verbose);

		// Save parameters
		this.b = b;
		this.w = b / 25;
		// log_2(b/25)
		this.l = (int) (Math.log10(w) * 3.321928095);
		this.c = 2 * d;
		this.r = b - c;
		this.d = d;
		this.n = 12 + 2 * l;

		// Initialize state
		state = new byte[b / 8];
	}

	@Override
	public byte[] digest(byte[] input)
	{
		this.input = input;

		// Print input
		this.writeOutput("Input (" + input.length + " Bytes)", QUIET);
		this.writeOutputKeccak(input, QUIET);

		// Padding
		this.padding();

		// Process
		this.absorb();

		// Squeeze out one output
		byte[] digest = Arrays.copyOf(this.squeeze(), d / 8);

		writeOutput("Digest", QUIET);
		writeOutputKeccak(digest, QUIET);

		return digest;
	}

	private void absorb()
	{
		for (int n = 0; n < input.length / (r / 8); ++n)
		{
			// Get current block
			byte[] block = new byte[r / 8];
			System.arraycopy(input, n * (r / 8), block, 0, r / 8);

			writeOutput("\n", INFORMATIVE);
			writeOutput("Block " + (n + 1), INFORMATIVE);

			// XOR block
			for (int x = 0; x < r / 8; ++x)
			{
				state[x] ^= block[x];
			}

			writeOutput("XORed block", INFORMATIVE);
			writeOutputKeccak(state, INFORMATIVE);

			this.fFunction();
		}
	}

	private byte[] squeeze()
	{
		if (firstSqueeze)
		{
			// Do Squeezing
			firstSqueeze = false;
			return Arrays.copyOf(state, r / 8);
		}

		return null;

	}

	private void padding()
	{
		// Define local variables
		int length = input.length;
		int blockSize = r;
		int blocks = (int) (Math.floor(length / blockSize) + 1);

		// Prepare new array
		byte[] paddedInput = new byte[blocks * blockSize / 8];
		System.arraycopy(input, 0, paddedInput, 0, length);

		// Check special case when there is only one byte padding
		if (blockSize / 8 - length == 1)
		{
			paddedInput[paddedInput.length - 1] = (byte) 0x81;
		}
		else
		{
			// TODO !!!!!
			paddedInput[length] = (byte) 0b01100000;
			paddedInput[paddedInput.length - 1] = 1;
		}

		// Save padded input
		input = paddedInput;

		writeOutput("", INFORMATIVE);
		writeOutput("Padded input (" + input.length + " Bytes)", INFORMATIVE);
		writeOutputKeccak(input, INFORMATIVE);
	}

	private void fFunction()
	{
		boolean[][][] A = this.bytesToState(state);

		for (int i = 12 + 2 * l - n; i < 12 + 2 * l; ++i)
		{
			writeOutput("", EXCESSIVE);
			writeOutput("f(" + i + ")", EXCESSIVE);

			A = theta(A);
			writeOutput("Theta", EXCESSIVE);
			writeOutputKeccak(this.stateToByte(A), EXCESSIVE);

			A = rho(A);
			writeOutput("", EXCESSIVE);
			writeOutput("Rho", EXCESSIVE);
			writeOutputKeccak(this.stateToByte(A), EXCESSIVE);

			A = pi(A);
			writeOutput("", EXCESSIVE);
			writeOutput("Pi", EXCESSIVE);
			writeOutputKeccak(this.stateToByte(A), EXCESSIVE);

			A = chi(A);
			writeOutput("", EXCESSIVE);
			writeOutput("Chi", EXCESSIVE);
			writeOutputKeccak(this.stateToByte(A), EXCESSIVE);

			A = iota(A, i);
			writeOutput("", EXCESSIVE);
			writeOutput("Iota", EXCESSIVE);
			writeOutputKeccak(this.stateToByte(A), EXCESSIVE);
		}

		state = this.stateToByte(A);
	}

	private boolean[][][] theta(boolean[][][] A)
	{
		// Prepare arrays
		boolean[][][] tempA = new boolean[5][5][w];
		boolean[][] C = new boolean[5][w], D = new boolean[5][w];

		// C Loop
		for (int x = 0; x < 5; ++x)
		{
			for (int z = 0; z < w; ++z)
			{
				C[x][z] = A[x][0][z] ^ A[x][1][z] ^ A[x][2][z] ^ A[x][3][z] ^ A[x][4][z];
			}
		}

		// D Loop
		for (int x = 0; x < 5; ++x)
		{
			for (int z = 0; z < w; ++z)
			{
				// Java's modulo does not work well on negative integers
				D[x][z] = C[(x - 1 + 5) % 5][z] ^ C[(x + 1) % 5][(z - 1 + w) % w];
			}
		}

		// A Loop
		for (int x = 0; x < 5; ++x)
		{
			for (int y = 0; y < 5; ++y)
			{
				for (int z = 0; z < w; ++z)
				{
					tempA[x][y][z] = A[x][y][z] ^ D[x][z];
				}
			}
		}

		return tempA;

	}

	private boolean[][][] rho(boolean[][][] A)
	{
		// Prepare arrays
		boolean[][][] tempA = new boolean[5][5][w];

		for (int z = 0; z < w; ++z)
		{
			tempA[0][0][z] = A[0][0][z];
		}

		int x = 1, y = 0;

		for (int t = 0; t < 24; ++t)
		{
			for (int z = 0; z < w; ++z)
			{
				// Adding multiple of w to get a positive value to be reduced
				tempA[x][y][z] = A[x][y][((z - (t + 1) * (t + 2) / 2) + 5 * w) % w];
			}
			int xTemp = x;
			x = y;
			y = (2 * xTemp + 3 * y) % 5;
		}

		return tempA;

	}

	private boolean[][][] pi(boolean[][][] A)
	{
		// Prepare arrays
		boolean[][][] tempA = new boolean[5][5][w];

		for (int x = 0; x < 5; ++x)
		{
			for (int y = 0; y < 5; ++y)
			{
				for (int z = 0; z < w; ++z)
				{
					tempA[x][y][z] = A[(x + 3 * y) % 5][x][z];
				}
			}
		}

		return tempA;

	}

	private boolean[][][] chi(boolean[][][] A)
	{
		// Prepare arrays
		boolean[][][] tempA = new boolean[5][5][w];

		for (int x = 0; x < 5; ++x)
		{
			for (int y = 0; y < 5; ++y)
			{
				for (int z = 0; z < w; ++z)
				{
					tempA[x][y][z] = A[x][y][z] ^ ((A[(x + 1) % 5][y][z] ^ true) & (A[(x + 2) % 5][y][z]));
				}
			}
		}

		return tempA;

	}

	private boolean[][][] iota(boolean[][][] A, int ir)
	{
		// Prepare arrays
		boolean[][][] tempA = A.clone();
		boolean[] RC = new boolean[w];

		for (int x = 0; x <= l; ++x)
		{
			RC[(int) (Math.pow(2, x) - 1)] = this.rc(x + 7 * ir);
		}

		for (int z = 0; z < w; ++z)
		{
			tempA[0][0][z] ^= RC[z];
		}

		return tempA;

	}

	private boolean rc(int t)
	{
		if (t % 255 == 0)
			return true;

		boolean[] RMain =
		{ true, false, false, false, false, false, false, false };

		for (int x = 1; x <= t % 255; ++x)
		{
			boolean[] R = new boolean[9];
			R[0] = false;
			System.arraycopy(RMain, 0, R, 1, 8);
			R[0] ^= R[8];
			R[4] ^= R[8];
			R[5] ^= R[8];
			R[6] ^= R[8];
			System.arraycopy(R, 0, RMain, 0, 8);
		}

		return RMain[0];
	}

	private boolean[][][] bytesToState(byte[] bytes)
	{
		boolean[][][] A = new boolean[5][5][w];
		String bitString = Misc.byteToBinaryString(bytes);

		for (int x = 0; x < 5; ++x)
		{
			for (int y = 0; y < 5; ++y)
			{
				for (int z = 0; z < w; ++z)
				{
					A[x][y][z] = bitString.charAt(w * (5 * y + x) + z) == '1';
				}
			}
		}

		return A;
	}

	private byte[] stateToByte(boolean[][][] A)
	{
		char[] bitString = new char[b];
		Arrays.fill(bitString, '0');

		for (int x = 0; x < 5; ++x)
		{
			for (int y = 0; y < 5; ++y)
			{
				for (int z = 0; z < w; ++z)
				{
					if (A[x][y][z])
					{
						bitString[w * (5 * y + x) + z] = '1';
					}
				}
			}
		}

		return Misc.binStringToByteArray(new String(bitString));
	}

	public static Keccak getSHA3_224(StringBuilder output, int verbose)
	{
		return new Keccak(1600, 224, output, verbose);
	}

	public static Keccak getSHA3_256(StringBuilder output, int verbose)
	{
		return new Keccak(1600, 256, output, verbose);
	}

	public static Keccak getSHA3_384(StringBuilder output, int verbose)
	{
		return new Keccak(1600, 384, output, verbose);
	}

	public static Keccak getSHA3_512(StringBuilder output, int verbose)
	{
		return new Keccak(1600, 512, output, verbose);
	}

}
