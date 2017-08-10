package hash;

import java.util.Arrays;

import miscFunctions.Misc;

public class SHA2 extends HashFunction
{

	// Constants
	public static final int SHA224 = 0, SHA256 = 1, SHA384 = 2, SHA512 = 3;

	private final int[] BLOCKSIZE =
	{ 64, 128 };

	int[] constants512 =
	{ 0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5, 0xd807aa98,
			0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174, 0xe49b69c1, 0xefbe4786,
			0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da, 0x983e5152, 0xa831c66d, 0xb00327c8,
			0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967, 0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13,
			0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85, 0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819,
			0xd6990624, 0xf40e3585, 0x106aa070, 0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a,
			0x5b9cca4f, 0x682e6ff3, 0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7,
			0xc67178f2 };

	long[] constants1024 =
	{ 0x428a2f98d728ae22L, 0x7137449123ef65cdL, 0xb5c0fbcfec4d3b2fL, 0xe9b5dba58189dbbcL, 0x3956c25bf348b538L,
			0x59f111f1b605d019L, 0x923f82a4af194f9bL, 0xab1c5ed5da6d8118L, 0xd807aa98a3030242L, 0x12835b0145706fbeL,
			0x243185be4ee4b28cL, 0x550c7dc3d5ffb4e2L, 0x72be5d74f27b896fL, 0x80deb1fe3b1696b1L, 0x9bdc06a725c71235L,
			0xc19bf174cf692694L, 0xe49b69c19ef14ad2L, 0xefbe4786384f25e3L, 0x0fc19dc68b8cd5b5L, 0x240ca1cc77ac9c65L,
			0x2de92c6f592b0275L, 0x4a7484aa6ea6e483L, 0x5cb0a9dcbd41fbd4L, 0x76f988da831153b5L, 0x983e5152ee66dfabL,
			0xa831c66d2db43210L, 0xb00327c898fb213fL, 0xbf597fc7beef0ee4L, 0xc6e00bf33da88fc2L, 0xd5a79147930aa725L,
			0x06ca6351e003826fL, 0x142929670a0e6e70L, 0x27b70a8546d22ffcL, 0x2e1b21385c26c926L, 0x4d2c6dfc5ac42aedL,
			0x53380d139d95b3dfL, 0x650a73548baf63deL, 0x766a0abb3c77b2a8L, 0x81c2c92e47edaee6L, 0x92722c851482353bL,
			0xa2bfe8a14cf10364L, 0xa81a664bbc423001L, 0xc24b8b70d0f89791L, 0xc76c51a30654be30L, 0xd192e819d6ef5218L,
			0xd69906245565a910L, 0xf40e35855771202aL, 0x106aa07032bbd1b8L, 0x19a4c116b8d2d0c8L, 0x1e376c085141ab53L,
			0x2748774cdf8eeb99L, 0x34b0bcb5e19b48a8L, 0x391c0cb3c5c95a63L, 0x4ed8aa4ae3418acbL, 0x5b9cca4f7763e373L,
			0x682e6ff3d6b2b8a3L, 0x748f82ee5defb2fcL, 0x78a5636f43172f60L, 0x84c87814a1f0ab72L, 0x8cc702081a6439ecL,
			0x90befffa23631e28L, 0xa4506cebde82bde9L, 0xbef9a3f7b2c67915L, 0xc67178f2e372532bL, 0xca273eceea26619cL,
			0xd186b8c721c0c207L, 0xeada7dd6cde0eb1eL, 0xf57d4f7fee6ed178L, 0x06f067aa72176fbaL, 0x0a637dc5a2c898a6L,
			0x113f9804bef90daeL, 0x1b710b35131c471bL, 0x28db77f523047d84L, 0x32caab7b40c72493L, 0x3c9ebe0a15c9bebcL,
			0x431d67c49c100d4cL, 0x4cc5d4becb3e42b6L, 0x597f299cfc657e2al, 0x5fcb6fab3ad6faecL, 0x6c44198c4a475817L };

	// Attributes
	private int version;

	// Constructor
	public SHA2(byte[] input, int version, StringBuilder output, int verbose)
	{
		// Call constructor of superclass
		super(input, output, verbose);

		// Set Attributes
		this.version = version;
	}

	@Override
	public byte[] digest()
	{
		// Padding
		this.padding();

		// Processing
		if (version / 2 == 0)
		{
			// SHA224 and SHA2256 are similar processed
			return Misc.getBytes(this.process512());
		}
		else
		{
			// SHA384 and SHA512 are similar processed
			return Misc.getBytes(this.process1024());
		}
	}

	private int[] process512()
	{
		// Initialize state
		int[] working = new int[64];
		int[] state = new int[8];
		int[] input = Misc.concatToInteger(this.input);

		// SHA224
		if (version == SHA224)
		{
			state[0] = 0xc1059ed8;
			state[1] = 0x367cd507;
			state[2] = 0x3070dd17;
			state[3] = 0xf70e5939;
			state[4] = 0xffc00b31;
			state[5] = 0x68581511;
			state[6] = 0x64f98fa7;
			state[7] = 0xbefa4fa4;
		}
		// SHA256
		else
		{
			state[0] = 0x6a09e667;
			state[1] = 0xbb67ae85;
			state[2] = 0x3c6ef372;
			state[3] = 0xa54ff53a;
			state[4] = 0x510e527f;
			state[5] = 0x9b05688c;
			state[6] = 0x1f83d9ab;
			state[7] = 0x5be0cd19;
		}

		// Iterate over all blocks
		for (int i = 0; i < input.length * 4 / BLOCKSIZE[version / 2]; ++i)
		{
			writeOutput("-----------------------------------", INFORMATIVE);
			writeOutput("Block " + (i + 1), INFORMATIVE);
			writeOutput("", INFORMATIVE);

			// Setup working
			writeOutput("Working in detail:", EXCESSIVE);

			System.arraycopy(input, i * 16, working, 0, 16);

			for (int t = 16; t < 64; ++t)
			{
				int tmp1, tmp2;
				tmp1 = SSIG1(working[t - 2]);
				tmp2 = SSIG0(working[t - 15]);
				working[t] = tmp1 + working[t - 7] + tmp2 + working[t - 16];
				writeOutput("W[" + t + "]", EXCESSIVE);
				writeOutput(String.format("%08X + %08X + %08X + %08X", tmp1, working[t - 2], working[t - 7], tmp2),
						EXCESSIVE);
				writeOutput(String.format("= %08X\n", working[t]), EXCESSIVE);
			}

			writeOutput("Working:", INFORMATIVE);
			writeOutput(working, INFORMATIVE);

			// Perform main hash Loop
			int a = state[0], b = state[1], c = state[2], d = state[3], e = state[4], f = state[5], g = state[6],
					h = state[7];

			for (int t = 0; t < 64; ++t)
			{
				writeOutput("Round " + t, INFORMATIVE);

				int tmp1, tmp2, tmp3, tmp4, T1, T2;
				tmp1 = BSIG1(e);
				tmp2 = CH(e, f, g);
				tmp3 = BSIG0(a);
				tmp4 = MAJ(a, b, c);

				T1 = h + tmp1 + tmp2 + constants512[t] + working[t];
				T2 = tmp3 + tmp4;

				writeOutput("T1:", EXCESSIVE);
				writeOutput(
						String.format("%08X + %08X + %08X + %08X + %08X", h, tmp1, tmp2, constants512[t], working[t]),
						EXCESSIVE);
				writeOutput(String.format("= %08X", T1), EXCESSIVE);

				writeOutput("T2:", EXCESSIVE);
				writeOutput(String.format("%08X + %08X", tmp3, tmp4), EXCESSIVE);
				writeOutput(String.format("= %08X\n", T2), EXCESSIVE);

				// Write values to variables
				h = g;
				g = f;
				f = e;
				e = d + T1;
				d = c;
				c = b;
				b = a;
				a = T1 + T2;

				int[] alphabetic =
				{ a, b, c, d, e, f, g, h };
				writeOutput("Working variables", INFORMATIVE);
				writeOutput(alphabetic, INFORMATIVE);
			}

			// Write value back to state
			state[0] += a;
			state[1] += b;
			state[2] += c;
			state[3] += d;
			state[4] += e;
			state[5] += f;
			state[6] += g;
			state[7] += h;

			// Test, if it is not the final round
			if (i + 1 < input.length * 4 / BLOCKSIZE[version / 2])
			{
				writeOutput("State", INFORMATIVE);
				writeOutput(state, INFORMATIVE);

			}
			else
			{
				if (version == SHA224)
					state = Arrays.copyOf(state, 7);
				writeOutput("Digest", QUIET);
				writeOutput(state, QUIET);
			}
		}

		return state;
	}

	private long[] process1024()
	{
		// Initialize state
		long[] working = new long[80];
		long[] state = new long[8];
		long[] input = Misc.concatToLong(this.input);

		// SHA384
		if (version == SHA384)
		{
			state[0] = 0xcbbb9d5dc1059ed8L;
			state[1] = 0x629a292a367cd507L;
			state[2] = 0x9159015a3070dd17L;
			state[3] = 0x152fecd8f70e5939L;
			state[4] = 0x67332667ffc00b31L;
			state[5] = 0x8eb44a8768581511L;
			state[6] = 0xdb0c2e0d64f98fa7L;
			state[7] = 0x47b5481dbefa4fa4L;
		}
		// SHA512
		else
		{
			state[0] = 0x6a09e667f3bcc908L;
			state[1] = 0xbb67ae8584caa73bL;
			state[2] = 0x3c6ef372fe94f82bL;
			state[3] = 0xa54ff53a5f1d36f1L;
			state[4] = 0x510e527fade682d1L;
			state[5] = 0x9b05688c2b3e6c1fL;
			state[6] = 0x1f83d9abfb41bd6bL;
			state[7] = 0x5be0cd19137e2179L;
		}

		// Iterate over all blocks
		for (int i = 0; i < input.length * 8 / BLOCKSIZE[version / 2]; ++i)
		{
			writeOutput("-----------------------------------", INFORMATIVE);
			writeOutput("Block " + (i + 1), INFORMATIVE);
			writeOutput("", INFORMATIVE);

			// Setup working
			writeOutput("Working in detail:", EXCESSIVE);

			System.arraycopy(input, i * 16, working, 0, 16);

			for (int t = 16; t < 80; ++t)
			{
				long tmp1, tmp2;
				tmp1 = SSIG1(working[t - 2]);
				tmp2 = SSIG0(working[t - 15]);
				working[t] = tmp1 + working[t - 7] + tmp2 + working[t - 16];
				writeOutput("W[" + t + "]", EXCESSIVE);
				writeOutput(String.format("%016X + %016X + %016X + %016X", tmp1, working[t - 2], working[t - 7], tmp2),
						EXCESSIVE);
				writeOutput(String.format("= %016X\n", working[t]), EXCESSIVE);
			}

			writeOutput("Working:", INFORMATIVE);
			writeOutput(working, INFORMATIVE);

			// Perform main hash Loop
			long a = state[0], b = state[1], c = state[2], d = state[3], e = state[4], f = state[5], g = state[6],
					h = state[7];

			for (int t = 0; t < 80; ++t)
			{
				writeOutput("Round " + t, INFORMATIVE);

				long tmp1, tmp2, tmp3, tmp4, T1, T2;
				tmp1 = BSIG1(e);
				tmp2 = CH(e, f, g);
				tmp3 = BSIG0(a);
				tmp4 = MAJ(a, b, c);

				T1 = h + tmp1 + tmp2 + constants1024[t] + working[t];
				T2 = tmp3 + tmp4;

				writeOutput("T1:", EXCESSIVE);
				writeOutput(String.format("%016X + %016X + %016X + %016X + %016X", h, tmp1, tmp2, constants1024[t],
						working[t]), EXCESSIVE);
				writeOutput(String.format("= %016X", T1), EXCESSIVE);

				writeOutput("T2:", EXCESSIVE);
				writeOutput(String.format("%016X + %016X", tmp3, tmp4), EXCESSIVE);
				writeOutput(String.format("= %016X\n", T2), EXCESSIVE);

				// Write values to variables
				h = g;
				g = f;
				f = e;
				e = d + T1;
				d = c;
				c = b;
				b = a;
				a = T1 + T2;

				long[] alphabetic =
				{ a, b, c, d, e, f, g, h };
				writeOutput("Working variables", INFORMATIVE);
				writeOutput(alphabetic, INFORMATIVE);
			}

			// Write value back to state
			state[0] += a;
			state[1] += b;
			state[2] += c;
			state[3] += d;
			state[4] += e;
			state[5] += f;
			state[6] += g;
			state[7] += h;

			// Test, if it is not the final round
			if (i + 1 < input.length * 8 / BLOCKSIZE[version / 2])
			{
				writeOutput("State", INFORMATIVE);
				writeOutput(state, INFORMATIVE);

			}
			else
			{
				if (version == SHA384)
					state = Arrays.copyOf(state, 6);
				writeOutput("Digest", QUIET);
				writeOutput(state, QUIET);
			}
		}

		return state;
	}

	private void padding()
	{
		// Define local variables
		int length = input.length;
		int blockSize = BLOCKSIZE[version / 2];
		int blocks = (int) Math.ceil(length * 1.0 / blockSize);

		// Check, if padding will add another block
		// For Blocks of 512, the input may not exceed 64 - 8 = 56 Bytes
		// For blocks of 512, the input may not exceed 128 - 16 = 112 Bytes
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

	// Methods for SHA224 and SHA256
	private int CH(int x, int y, int z)
	{
		return (x & y) ^ ((~x) & z);
	}

	private int MAJ(int x, int y, int z)
	{
		return (x & y) ^ (x & z) ^ (y & z);
	}

	private int BSIG0(int x)
	{
		return Integer.rotateRight(x, 2) ^ Integer.rotateRight(x, 13) ^ Integer.rotateRight(x, 22);
	}

	private int BSIG1(int x)
	{
		return Integer.rotateRight(x, 6) ^ Integer.rotateRight(x, 11) ^ Integer.rotateRight(x, 25);
	}

	private int SSIG0(int x)
	{
		return Integer.rotateRight(x, 7) ^ Integer.rotateRight(x, 18) ^ (x >>> 3);
	}

	private int SSIG1(int x)
	{
		return Integer.rotateRight(x, 17) ^ Integer.rotateRight(x, 19) ^ (x >>> 10);
	}

	// Methods for SHA384 and SHA512
	private long CH(long x, long y, long z)
	{
		return (x & y) ^ ((~x) & z);
	}

	private long MAJ(long x, long y, long z)
	{
		return (x & y) ^ (x & z) ^ (y & z);
	}

	private long BSIG0(long x)
	{
		return Long.rotateRight(x, 28) ^ Long.rotateRight(x, 34) ^ Long.rotateRight(x, 39);
	}

	private long BSIG1(long x)
	{
		return Long.rotateRight(x, 14) ^ Long.rotateRight(x, 18) ^ Long.rotateRight(x, 41);
	}

	private long SSIG0(long x)
	{
		return Long.rotateRight(x, 1) ^ Long.rotateRight(x, 8) ^ (x >>> 7);
	}

	private long SSIG1(long x)
	{
		return Long.rotateRight(x, 19) ^ Long.rotateRight(x, 61) ^ (x >>> 6);
	}
}
