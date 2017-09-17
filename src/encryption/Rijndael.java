package encryption;

public class Rijndael extends CryptionFunction
{

	private int blockSize;
	
	public Rijndael(byte[] key, int blockSize, StringBuilder output, int verbose)
	{
		//Call constructor of superclass
		super(key, output, verbose);
		
		//Save version
		this.blockSize = blockSize;
	}

	@Override
	public byte[] encryption(byte[] input)
	{
		return null;
	}

	@Override
	public byte[] decryption(byte[] input)
	{
		return null;
	}

	@Override
	public byte[][] keySchedule(byte[] key)
	{
		return null;
	}
}
