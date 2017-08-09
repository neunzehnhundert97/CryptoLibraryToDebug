package hash;

public class SHA2 extends HashFunction
{

	// Constants
	public static final int SHA224 = 0, SHA256 = 1, SHA384 = 2, SHA512 = 3;

	private final int[] BLOCKSIZE =
	{ 64, 128 };

	// Attributes
	private int version;

	// Constructor
	public SHA2(byte[] input, int version, int verbose)
	{
		// Call constructor of superclass
		super(input, verbose);

		// Set Attributes
		this.version = version;
	}

	@Override
	public byte[] digest()
	{
		// Padding
		this.padding();

		return null;
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
		if (length % blockSize > blockSize - 1 - blockSize / 8)
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
		
		//Set the input to be the padded one
		input = paddedInput;

	}
}
