package hash;

import miscFunctions.Misc;

public class MD5 extends HashFunction
{

	// Constants
	private final int[] T =
	{ 0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee, 0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501, 0x698098d8,
			0x8b44f7af, 0xffff5bb1, 0x895cd7be, 0x6b901122, 0xfd987193, 0xa679438e, 0x49b40821, 0xf61e2562, 0xc040b340,
			0x265e5a51, 0xe9b6c7aa, 0xd62f105d, 0x2441453, 0xd8a1e681, 0xe7d3fbc8, 0x21e1cde6, 0xc33707d6, 0xf4d50d87,
			0x455a14ed, 0xa9e3e905, 0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a, 0xfffa3942, 0x8771f681, 0x6d9d6122, 0xfde5380c,
			0xa4beea44, 0x4bdecfa9, 0xf6bb4b60, 0xbebfbc70, 0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x4881d05, 0xd9d4d039,
			0xe6db99e5, 0x1fa27cf8, 0xc4ac5665, 0xf4292244, 0x432aff97, 0xab9423a7, 0xfc93a039, 0x655b59c3, 0x8f0ccc92,
			0xffeff47d, 0x85845dd1, 0x6fa87e4f, 0xfe2ce6e0, 0xa3014314, 0x4e0811a1, 0xf7537e82, 0xbd3af235, 0x2ad7d2bb,
			0xeb86d391 };

	public MD5(byte[] input, StringBuilder output, int verbose)
	{
		super(input, output, verbose);
	}

	@Override
	public byte[] digest()
	{
		// Padding
		this.padding();

		return Misc.getBytes(this.process());
	}

	private int[] process()
	{
		// Initialize
		int[] state =
		{ 0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476 };
		int[] input = Misc.concatToInteger(this.input);
		int[] X = new int[16];

		// Iterate over all blocks
		for (int i = 0; i < input.length / 16; ++i)
		{
			writeOutput("-----------------------------------", INFORMATIVE);
			writeOutput("Block " + (i + 1), INFORMATIVE);
			writeOutput("", INFORMATIVE);

			// Set up X
			System.arraycopy(input, i * 16, X, 0, 16);

			writeOutput("X:", INFORMATIVE);
			writeOutput(X, INFORMATIVE);

			int A = state[0], B = state[1], C = state[2], D = state[3];

			// Round 1
			A = this.r1(A, B, C, D, X[0], 7, 1);
			D = this.r1(D, A, B, C, X[1], 12, 2);
			C = this.r1(C, D, A, B, X[2], 17, 3);
			B = this.r1(B, C, D, A, X[3], 22, 4);

			A = this.r1(A, B, C, D, X[4], 7, 5);
			D = this.r1(D, A, B, C, X[5], 12, 6);
			C = this.r1(C, D, A, B, X[6], 17, 7);
			B = this.r1(B, C, D, A, X[7], 22, 8);

			A = this.r1(A, B, C, D, X[8], 7, 9);
			D = this.r1(D, A, B, C, X[9], 12, 10);
			C = this.r1(C, D, A, B, X[10], 17, 11);
			B = this.r1(B, C, D, A, X[11], 22, 12);

			A = this.r1(A, B, C, D, X[12], 7, 13);
			D = this.r1(D, A, B, C, X[13], 12, 14);
			C = this.r1(C, D, A, B, X[14], 17, 15);
			B = this.r1(B, C, D, A, X[15], 22, 16);

			// Round 2
			A = this.r2(A, B, C, D, X[1], 5, 17);
			D = this.r2(D, A, B, C, X[6], 9, 18);
			C = this.r2(C, D, A, B, X[11], 14, 19);
			B = this.r2(B, C, D, A, X[0], 20, 20);

			A = this.r2(A, B, C, D, X[5], 5, 21);
			D = this.r2(D, A, B, C, X[10], 9, 22);
			C = this.r2(C, D, A, B, X[15], 14, 23);
			B = this.r2(B, C, D, A, X[4], 20, 24);

			A = this.r2(A, B, C, D, X[9], 5, 25);
			D = this.r2(D, A, B, C, X[14], 9, 26);
			C = this.r2(C, D, A, B, X[3], 14, 27);
			B = this.r2(B, C, D, A, X[8], 20, 28);

			A = this.r2(A, B, C, D, X[13], 5, 29);
			D = this.r2(D, A, B, C, X[2], 9, 30);
			C = this.r2(C, D, A, B, X[7], 14, 31);
			B = this.r2(B, C, D, A, X[12], 20, 32);

			// Round 3
			A = this.r3(A, B, C, D, X[5], 4, 33);
			D = this.r3(D, A, B, C, X[8], 11, 34);
			C = this.r3(C, D, A, B, X[11], 16, 35);
			B = this.r3(B, C, D, A, X[14], 23, 36);

			A = this.r3(A, B, C, D, X[1], 4, 37);
			D = this.r3(D, A, B, C, X[4], 11, 38);
			C = this.r3(C, D, A, B, X[7], 16, 39);
			B = this.r3(B, C, D, A, X[10], 23, 40);

			A = this.r3(A, B, C, D, X[13], 4, 41);
			D = this.r3(D, A, B, C, X[0], 11, 42);
			C = this.r3(C, D, A, B, X[3], 16, 43);
			B = this.r3(B, C, D, A, X[6], 23, 44);

			A = this.r3(A, B, C, D, X[9], 4, 45);
			D = this.r3(D, A, B, C, X[12], 11, 46);
			C = this.r3(C, D, A, B, X[15], 16, 47);
			B = this.r3(B, C, D, A, X[2], 23, 48);

			// Round 4
			A = this.r4(A, B, C, D, X[0], 6, 49);
			D = this.r4(D, A, B, C, X[7], 10, 50);
			C = this.r4(C, D, A, B, X[14], 15, 51);
			B = this.r4(B, C, D, A, X[5], 21, 52);

			A = this.r4(A, B, C, D, X[12], 6, 53);
			D = this.r4(D, A, B, C, X[3], 10, 54);
			C = this.r4(C, D, A, B, X[10], 15, 55);
			B = this.r4(B, C, D, A, X[1], 21, 56);

			A = this.r4(A, B, C, D, X[8], 6, 57);
			D = this.r4(D, A, B, C, X[15], 10, 58);
			C = this.r4(C, D, A, B, X[6], 15, 59);
			B = this.r4(B, C, D, A, X[13], 21, 60);

			A = this.r4(A, B, C, D, X[4], 6, 61);
			D = this.r4(D, A, B, C, X[11], 10, 62);
			C = this.r4(C, D, A, B, X[2], 15, 63);
			B = this.r4(B, C, D, A, X[9], 21, 64);

			// Update state
			state[0] += A;
			state[1] += B;
			state[2] += C;
			state[3] += D;

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

		return new int[]
		{ Integer.reverseBytes(state[0]), Integer.reverseBytes(state[1]), Integer.reverseBytes(state[2]),
				Integer.reverseBytes(state[3]) };
	}

	private int r1(int a, int b, int c, int d, int x, int s, int i)
	{
		return b + Integer.rotateLeft((a + this.F(b, c, d) + x + T[i - 1]), s);
	}

	private int r2(int a, int b, int c, int d, int x, int s, int i)
	{
		return b + Integer.rotateLeft((a + this.G(b, c, d) + x + T[i - 1]), s);
	}

	private int r3(int a, int b, int c, int d, int x, int s, int i)
	{
		return b + Integer.rotateLeft((a + this.H(b, c, d) + x + T[i - 1]), s);
	}

	private int r4(int a, int b, int c, int d, int x, int s, int i)
	{
		return b + Integer.rotateLeft((a + this.I(b, c, d) + x + T[i - 1]), s);
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

	private int F(int X, int Y, int Z)
	{
		return (X & Y) | ((~X) & Z);
	}

	private int G(int X, int Y, int Z)
	{
		return (X & Z) | (Y & (~Z));
	}

	private int H(int X, int Y, int Z)
	{
		return X ^ Y ^ Z;
	}

	private int I(int X, int Y, int Z)
	{
		return Y ^ (X | (~Z));
	}

}
