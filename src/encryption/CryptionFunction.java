package encryption;

import miscFunctions.Function;

public abstract class CryptionFunction extends Function
{
	// Attributes
	protected byte[] key;
	protected byte[][] roundKey;

	public CryptionFunction(byte[] key, StringBuilder output, int verbose)
	{
		super(output, verbose);
		this.key = key;
	}

	public abstract byte[] encryption(byte[] input);

	public abstract byte[] decryption(byte[] input);
	
	public abstract byte[][] keySchedule(byte[] key);

}
