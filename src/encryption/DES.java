package encryption;

import java.util.Arrays;

import miscFunctions.Misc;

public class DES extends CryptionFunction
{

	private final int[] initialPermutation =
	{ 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32,
			24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63,
			55, 47, 39, 31, 23, 15, 7 };

	private final int[] reversePermutation =
	{ 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53,
			21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33,
			1, 41, 9, 49, 17, 57, 25 };

	private final int[] fPermutation =
	{ 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4,
			25 };

	private final int[] E =
	{ 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21,
			22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };

	private final int[][] S =
	{
			{ 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7, 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3,
					8, 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0, 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10,
					0, 6, 13 },
			{ 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11,
					5, 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0,
					5, 14, 9 },
			{ 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8, 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15,
					1, 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7, 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11,
					5, 2, 12 },
			{ 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15, 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14,
					9, 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4, 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12,
					7, 2, 14 },
			{ 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9, 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8,
					6, 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14, 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9,
					10, 4, 5, 3 },
			{ 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11, 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3,
					8, 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6, 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6,
					0, 8, 13 },
			{ 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1, 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8,
					6, 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2, 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14,
					2, 3, 12 },
			{ 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7, 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9,
					2, 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8, 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3,
					5, 6, 11 } };

	private final int[][] PC_1 =
	{
			{ 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44,
					36 },
			{ 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12,
					4 } };

	private final int[] PC_2 =
	{ 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55,
			30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };

	private final int[] leftShift =
	{ 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };

	public DES(byte[] key, StringBuilder output, int verbose)
	{
		super(key, output, verbose);

		// Print key
		this.writeOutput("Key", QUIET);
		this.writeOutput(key, QUIET);
		this.writeOutput("", QUIET);

		this.roundKey = this.keySchedule(key);

	}

	@Override
	public byte[] encryption(byte[] input)
	{
		// Print input
		this.writeOutput("Input", QUIET);
		this.writeOutput(input, QUIET);
		this.writeOutput("", QUIET);

		// Perform initial Permutation
		input = this.permutate(input, initialPermutation);

		// Print permutated input
		this.writeOutput("After permutation (L,R)", INFORMATIVE);
		this.writeOutput(input, INFORMATIVE);
		this.writeOutput("", INFORMATIVE);

		// Split input into L and R
		byte[] L, R;
		L = Arrays.copyOf(input, 4);
		R = Arrays.copyOfRange(input, 4, 8);

		// Perform rounds
		for (int x = 0; x < 16; ++x)
		{
			this.writeOutput("Round " + (x + 1), INFORMATIVE);

			byte[] temp = R.clone();
			this.writeOutput("f(R, K" + (x + 1) + ")", INFORMATIVE);
			byte[] fR = this.f(R, this.roundKey[x]);

			// Print f(r, K)
			this.writeOutput(fR, INFORMATIVE);
			this.writeOutput("", INFORMATIVE);

			for (int y = 0; y < 4; ++y)
			{
				R[y] = (byte) (fR[y] ^ L[y]);
			}

			L = temp;

			// Print new L and R
			this.writeOutput("L", INFORMATIVE);
			this.writeOutput(L, INFORMATIVE);
			this.writeOutput("", INFORMATIVE);
			this.writeOutput("R", INFORMATIVE);
			this.writeOutput(R, INFORMATIVE);
			this.writeOutput("", INFORMATIVE);

		}

		// Put L and R back together
		System.arraycopy(R, 0, input, 0, 4);
		System.arraycopy(L, 0, input, 4, 4);

		// Perform inverse initial permutation
		input = this.permutate(input, reversePermutation);

		// Print output
		this.writeOutput("Output", QUIET);
		this.writeOutput(input, QUIET);

		return input;

	}

	@Override
	public byte[] decryption(byte[] input)
	{
		// Print input
		this.writeOutput("Input", QUIET);
		this.writeOutput(input, QUIET);
		this.writeOutput("", QUIET);

		// Perform initial Permutation
		input = this.permutate(input, initialPermutation);

		// Print permutated input
		this.writeOutput("After permutation (L,R)", INFORMATIVE);
		this.writeOutput(input, INFORMATIVE);
		this.writeOutput("", INFORMATIVE);

		// Split input into L and R
		byte[] L, R;
		L = Arrays.copyOf(input, 4);
		R = Arrays.copyOfRange(input, 4, 8);

		// Perform rounds
		for (int x = 15; x >= 0; --x)
		{
			this.writeOutput("Round " + (x + 1), INFORMATIVE);

			byte[] temp = R.clone();
			this.writeOutput("f(R, K" + (x + 1) + ")", INFORMATIVE);
			byte[] fR = this.f(R, this.roundKey[x]);

			// Print f(r, K)
			this.writeOutput(fR, INFORMATIVE);
			this.writeOutput("", INFORMATIVE);

			for (int y = 0; y < 4; ++y)
			{
				R[y] = (byte) (fR[y] ^ L[y]);
			}

			L = temp;

			// Print new L and R
			this.writeOutput("L", INFORMATIVE);
			this.writeOutput(L, INFORMATIVE);
			this.writeOutput("", INFORMATIVE);
			this.writeOutput("R", INFORMATIVE);
			this.writeOutput(R, INFORMATIVE);
			this.writeOutput("", INFORMATIVE);

		}

		// Put L and R back together
		System.arraycopy(R, 0, input, 0, 4);
		System.arraycopy(L, 0, input, 4, 4);

		// Perform inverse initial permutation
		input = this.permutate(input, reversePermutation);

		// Print output
		this.writeOutput("Output", QUIET);
		this.writeOutput(input, QUIET);

		return input;

	}

	@Override
	public byte[][] keySchedule(byte[] key)
	{
		roundKey = new byte[16][];

		this.writeOutput("Key schedule", INFORMATIVE);
		this.writeOutput("", INFORMATIVE);

		byte[] C, D;
		C = this.permutate(key, PC_1[0]);
		D = this.permutate(key, PC_1[1]);

		this.writeOutput("Initial key permutation", EXCESSIVE);
		this.writeOutput("C:", EXCESSIVE);
		this.writeOutput(C, EXCESSIVE);
		this.writeOutput("D:", EXCESSIVE);
		this.writeOutput(D, EXCESSIVE);

		// Generate roundkeys
		for (int x = 0; x < 16; ++x)
		{
			// Turn blocks to bin strings
			String sC = Misc.byteToBinaryString(C).substring(0, 28);
			String sD = Misc.byteToBinaryString(D).substring(0, 28);
			;

			// Rotate left
			if (leftShift[x] == 1)
			{
				sC = sC.substring(1) + sC.charAt(0);
				sD = sD.substring(1) + sD.charAt(0);
			}
			else
			{
				sC = sC.substring(2) + sC.charAt(0) + sC.charAt(1);
				sD = sD.substring(2) + sD.charAt(0) + sD.charAt(1);
			}

			C = Misc.binStringToByteArray(sC);
			D = Misc.binStringToByteArray(sD);

			byte[] K = Misc.binStringToByteArray(sC + sD);
			roundKey[x] = this.permutate(K, PC_2);

			// Print roundkey
			this.writeOutput("K" + (x + 1), INFORMATIVE);
			this.writeOutput(roundKey[x], INFORMATIVE);
			this.writeOutput("", INFORMATIVE);

		}

		return roundKey;
	}

	private byte[] f(byte[] R, byte[] K)
	{
		// E-Function
		byte[] E = this.permutate(R, this.E);
		this.writeOutput("", EXCESSIVE);
		this.writeOutput("After E permutation", EXCESSIVE);
		this.writeOutput(E, EXCESSIVE);
		this.writeOutput("", EXCESSIVE);

		// XOR
		for (int x = 0; x < E.length; ++x)
		{
			E[x] ^= K[x];
		}
		
		this.writeOutput("XORed with key", EXCESSIVE);
		this.writeOutput(E, EXCESSIVE);
		this.writeOutput("", EXCESSIVE);

		// S-Boxes
		E = this.S(E);
		
		this.writeOutput("Substituted", EXCESSIVE);
		this.writeOutput(E, EXCESSIVE);
		this.writeOutput("", EXCESSIVE);

		// Permutation
		E = this.permutate(E, fPermutation);
		
		this.writeOutput("Permutated", EXCESSIVE);
		this.writeOutput("", EXCESSIVE);

		return E;
	}

	private byte[] S(byte[] B)
	{
		byte[] out = new byte[4];

		// Convert to binary String for better accessing
		String sB = Misc.byteToBinaryString(B);

		// Iterate over the String in 6bit steps
		for (int x = 0; x < 8; ++x)
		{
			String subS = sB.substring(6 * x, 6 * x + 6);
			int row, column;
			row = Integer.parseInt(subS.charAt(0) + "" + subS.charAt(5), 2);
			column = Integer.parseInt(subS.substring(1, 5), 2);
			int entry = S[x][row * 16 + column];

			if (x % 2 == 0)
				out[x / 2] = (byte) (entry << 4);
			else
				out[x / 2] |= entry;
		}

		return out;

	}

	public byte[] testPermutation(byte[] array)
	{
		return this.permutate(this.permutate(array, initialPermutation), this.reversePermutation);
	}

}
