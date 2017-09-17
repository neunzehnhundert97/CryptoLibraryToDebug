package hash;

import miscFunctions.Misc;

public class SHA1 extends HashFunction
{

	// Constants
	private final int[] constants =
	{ 0x5A827999, 0x6ED9EBA1, 0x8F1BBCDC, 0xCA62C1D6 };

	public SHA1(StringBuilder output, int verbose)
	{
		super(output, verbose);
	}

	@Override
	public byte[] digest(byte[] input)
	{
		this.input = input;

		// Print input
		this.writeOutput("Input (" + input.length + " Bytes)", QUIET);
		this.writeOutput(input, QUIET);

		// Padding
		this.padding();

		return Misc.getBytes(this.process());
	}

	private int[] process()
	{
		// Initialize
		int[] state =
		{ 0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476, 0xC3D2E1F0 };
		int[] working = new int[80];
		int[] input = Misc.concatToInteger(this.input);

		// Iterate over all blocks
		for (int i = 0; i < input.length * 4 / 64; ++i)
		{
			writeOutput("-----------------------------------", INFORMATIVE);
			writeOutput("Block " + (i + 1), INFORMATIVE);
			writeOutput("", INFORMATIVE);

			// Set up working
			writeOutput("Working in detail:", EXCESSIVE);
			System.arraycopy(input, i * 16, working, 0, 16);

			for (int t = 16; t < 80; ++t)
			{
				// Compute working
				// Note that the rotation is done at the end, not directly to one of the values!
				working[t] = Integer.rotateLeft(working[t - 3] ^ working[t - 8] ^ working[t - 14] ^ working[t - 16], 1);
				writeOutput("W[" + t + "]", EXCESSIVE);
				writeOutput(String.format("(%08X ^ %08X ^ %08X ^ %08X) rotate left by 1", working[t - 3],
						working[t - 8], working[t - 14], working[t - 16]), EXCESSIVE);
				writeOutput(String.format("=(%08X) rotate left by 1",
						working[t - 3] ^ working[t - 8] ^ working[t - 14] ^ working[t - 16]), EXCESSIVE);
				writeOutput(String.format("=%08X\n", working[t]), EXCESSIVE);
			}

			writeOutput("Working:", INFORMATIVE);
			writeOutput(working, INFORMATIVE);

			int A = state[0], B = state[1], C = state[2], D = state[3], E = state[4];

			// Perform main hash loop
			for (int t = 0; t < 80; ++t)
			{
				writeOutput("Round " + t, INFORMATIVE);

				int tmp1, tmp2;
				tmp1 = Integer.rotateLeft(A, 5);
				tmp2 = this.f(t, B, C, D);

				int TEMP = tmp1 + tmp2 + E + working[t] + constants[t / 20];

				writeOutput("TEMP:", EXCESSIVE);
				writeOutput(
						String.format("%08X + %08X + %08X + %08X + %08X", tmp1, tmp2, E, working[t], constants[t / 20]),
						EXCESSIVE);
				writeOutput(String.format("=%08X\n", TEMP), EXCESSIVE);

				// Write values to variables
				E = D;
				D = C;
				C = Integer.rotateLeft(B, 30);
				B = A;
				A = TEMP;

				int[] alphabetic =
				{ A, B, C, D, E };
				writeOutput("Working variables", INFORMATIVE);
				writeOutput(alphabetic, INFORMATIVE);
				writeOutput("", INFORMATIVE);
			}

			// Write values back to state
			state[0] += A;
			state[1] += B;
			state[2] += C;
			state[3] += D;
			state[4] += E;

			// Test, if it is not the final round
			if (i + 1 < input.length * 4 / 64)
			{
				writeOutput("State", INFORMATIVE);
				writeOutput(state, INFORMATIVE);

			}
			else
			{
				writeOutput("Digest", QUIET);
				writeOutput(state, QUIET);
				break;
			}
		}

		return state;
	}

	private void padding()
	{
		// Define local variables
		int length = input.length;
		int blockSize = 64;
		int blocks = (int) Math.ceil(length * 1.0 / blockSize);

		// Check, if padding will add another block
		// For Blocks of 512, the input may not exceed 64 - 8 = 56 Bytes
		if (length % blockSize > blockSize - 1 - blockSize / 8 || length % blockSize == 0)
		{
			blocks++;
		}

		// Prepare new array
		byte[] paddedInput = new byte[blocks * blockSize];
		System.arraycopy(input, 0, paddedInput, 0, length);

		// Insert 1
		paddedInput[input.length] = (byte) 0x80;

		// Insert length
		// The length is specified as a 64 or 128 bit Integer in the RFC,
		// but Java's array's cannot exceed an Integer
		long bitLength = length * 8;
		for (int x = 0; x < 8; ++x)
		{
			paddedInput[blocks * blockSize - 1 - x] = (byte) ((bitLength) >>> (8 * x));
		}

		// Set the input to be the padded one
		input = paddedInput;

		writeOutput("", INFORMATIVE);
		writeOutput("Padded input (" + input.length + " Bytes)", INFORMATIVE);
		writeOutput(input, INFORMATIVE);

		return;
	}

	private int f(int t, int B, int C, int D)
	{
		switch ((int) (t / 20))
		{
		case 0:
			return (B & C) | ((~B) & D);
		case 2:
			return (B & C) | (B & D) | (C & D);
		case 1:
		case 3:
			return B ^ C ^ D;

		}
		return 0;
	}

}
