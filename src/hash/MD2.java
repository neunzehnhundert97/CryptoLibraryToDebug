package hash;

import java.util.Arrays;

import com.sun.rmi.rmid.ExecOptionPermission;

public class MD2 extends HashFunction
{

	private final int S[] =
	{ 41, 46, 67, 201, 162, 216, 124, 1, 61, 54, 84, 161, 236, 240, 6, 19, 98, 167, 5, 243, 192, 199, 115, 140, 152,
			147, 43, 217, 188, 76, 130, 202, 30, 155, 87, 60, 253, 212, 224, 22, 103, 66, 111, 24, 138, 23, 229, 18,
			190, 78, 196, 214, 218, 158, 222, 73, 160, 251, 245, 142, 187, 47, 238, 122, 169, 104, 121, 145, 21, 178, 7,
			63, 148, 194, 16, 137, 11, 34, 95, 33, 128, 127, 93, 154, 90, 144, 50, 39, 53, 62, 204, 231, 191, 247, 151,
			3, 255, 25, 48, 179, 72, 165, 181, 209, 215, 94, 146, 42, 172, 86, 170, 198, 79, 184, 56, 210, 150, 164,
			125, 182, 118, 252, 107, 226, 156, 116, 4, 241, 69, 157, 112, 89, 100, 113, 135, 32, 134, 91, 207, 101, 230,
			45, 168, 2, 27, 96, 37, 173, 174, 176, 185, 246, 28, 70, 97, 105, 52, 64, 126, 15, 85, 71, 163, 35, 221, 81,
			175, 58, 195, 92, 249, 206, 186, 197, 234, 38, 44, 83, 13, 110, 133, 40, 132, 9, 211, 223, 205, 244, 65,
			129, 77, 82, 106, 220, 55, 200, 108, 193, 171, 250, 36, 225, 123, 8, 12, 189, 177, 74, 120, 136, 149, 139,
			227, 99, 232, 109, 233, 203, 213, 254, 59, 0, 29, 57, 242, 239, 183, 14, 102, 88, 208, 228, 166, 119, 114,
			248, 235, 117, 75, 10, 49, 68, 80, 180, 143, 237, 31, 26, 219, 153, 141, 51, 159, 17, 131, 20 };

	public MD2(StringBuilder output, int verbose)
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

		// Perform padding
		this.padding();

		// Process padded input
		return this.process();
	}

	private byte[] process()
	{
		// Define buffer
		byte[] X = new byte[48];

		// Iterate over all 16 byte blocks
		for (int i = 0; i < input.length / 16; ++i)
		{
			this.writeOutput("Block " + (i + 1) + "\n", INFORMATIVE);
			this.writeOutput("Buffer X before", INFORMATIVE);

			// Fill buffer X with the input
			for (int j = 0; j < 16; ++j)
			{
				X[16 + j] = input[i * 16 + j];
				X[32 + j] = (byte) (X[16 + j] ^ X[j]);
			}

			this.writeOutput(X, INFORMATIVE);

			byte t = 0;

			// Perform main hash computation
			for (int j = 0; j < 18; ++j)
			{

				for (int k = 0; k < 48; ++k)
				{
					t = X[k] = (byte) (X[k] ^ this.S[Byte.toUnsignedInt(t)]);
				}

				t = (byte) (t + j);

				this.writeOutput("t after round " + (j + 1), EXCESSIVE);
				this.writeFormatOutput("%02X\n", EXCESSIVE, t);

			}

			this.writeOutput("Buffer X after", INFORMATIVE);
			this.writeOutput(X, INFORMATIVE);
		}

		byte[] digest = Arrays.copyOf(X, 16);

		// Print digest
		writeOutput("Digest", QUIET);
		writeOutput(digest, QUIET);

		return digest;

	}

	private void padding()
	{
		// Determine padding length
		int pad = 16 - input.length % 16;

		// Prepare new array with padding and checksum
		byte[] paddedInput = new byte[input.length + pad];
		System.arraycopy(input, 0, paddedInput, 0, input.length);

		// Fill with i bytes of i
		for (int x = 0; x < pad; ++x)
		{
			paddedInput[input.length + x] = (byte) pad;
		}

		// Compute checksum

		byte[] checksum = new byte[16];
		byte L = 0;

		// Iterate over all blocks of the input
		for (int i = 0; i < paddedInput.length / 16; ++i)
		{
			// Iterate over all bytes
			for (int j = 0; j < 16; ++j)
			{
				byte c = paddedInput[i * 16 + j];
				// Attention XOR
				checksum[j] ^= (byte) this.S[Byte.toUnsignedInt((byte) (c ^ L))];
				L = checksum[j];
			}
		}

		// Save the padded input
		this.input = new byte[paddedInput.length + 16];
		System.arraycopy(paddedInput, 0, input, 0, paddedInput.length);
		System.arraycopy(checksum, 0, input, paddedInput.length, 16);

		writeOutput("", INFORMATIVE);
		writeOutput("Padded input (" + input.length + " Bytes)", INFORMATIVE);
		writeOutput(input, INFORMATIVE);

	}

}
