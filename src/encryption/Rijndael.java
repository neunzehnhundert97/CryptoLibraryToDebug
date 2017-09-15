package encryption;

public class Rijndael extends CryptionFunction
{

	private int version;
	
	public Rijndael(byte[] key, int version, StringBuilder output, int verbose)
	{
		//Call constructor of superclass
		super(key, output, verbose);
		
		//Save version
		this.version = version;
	}

	@Override
	public byte[] encryption(byte[] input)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] decryption(byte[] input)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[][] keySchedule(byte[] key)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
