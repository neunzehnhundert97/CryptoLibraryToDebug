package encryption;

import miscFunctions.Function;

public abstract class CryptionFunction extends Function
{
	// Attributes
	private byte[] key;

	public CryptionFunction(byte[] key, StringBuilder output, int verbose)
	{
		super(output, verbose);
		this.key = key;
	}

	public abstract byte[] encryption(byte[] input);

	public abstract byte[] decryption(byte[] input);

}
