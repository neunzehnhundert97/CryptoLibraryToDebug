package hash;

import miscFunctions.Misc;

public class MD5 extends HashFunction
{

	public MD5(byte[] input, StringBuilder output, int verbose)
	{
		super(input, output, verbose);
		// TODO Auto-generated constructor stub
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
		//TODO Check endianess
		int[] state =
		{ 0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476 };
		int[] input = Misc.concatToInteger(this.input);
		int[] M = new int[16];
		
		// Iterate over all blocks
		for(int i = 0;i < input.length / 16;++i)
		{
			writeOutput("-----------------------------------", INFORMATIVE);
			writeOutput("Block " + (i + 1), INFORMATIVE);
			writeOutput("", INFORMATIVE);

			// Set up M
			writeOutput("M in detail:", EXCESSIVE);
			System.arraycopy(input, i * 16, M, 0, 16);
		}
		

		return null;
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
		return X & Z | (Y & (~Z));
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
