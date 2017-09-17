package hash;

import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import miscFunctions.Misc;

public class Keccak extends HashFunction
{

	// Keccak parameters
	private int b, w, l, c, r, d;

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

		// Initialize state
		state = new byte[b / 8];
	}

	@Override
	public byte[] digest(byte[] input)
	{
		this.input = input;

		// Pad
		this.padding();

		// Process
		this.absorb();

		// Squeeze out one output
		return Arrays.copyOf(this.squeeze(), d / 8);
	}

	private void absorb()
	{
		for (int n = 0; n < input.length / (r / 8); ++n)
		{
			// Get current block
			byte[] block = new byte[r / 8];
			System.arraycopy(input, n * (r / 8), block, 0, r / 8);

			// XOR block
			for (int x = 0; x < r / 8; ++x)
			{
				state[x] ^= block[x];
			}

		}
	}

	private byte[] squeeze()
	{
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
			paddedInput[length] = (byte) 0x80;
			paddedInput[length] = 1;
		}

		// Save padded input
		input = paddedInput;
	}

	private void fFunction()
	{

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
		return A;

	}

	private boolean[][][] pi(boolean[][][] A)
	{
		return A;

	}

	private boolean[][][] chi(boolean[][][] A)
	{
		return A;

	}

	private boolean[][][] iota(boolean[][][] A)
	{
		return A;

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

		return new String(bitString).getBytes();
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
