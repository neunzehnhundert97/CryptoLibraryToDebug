package encryption;

import miscFunctions.Function;

public abstract class CryptionFunction extends Function
{
	// Attributes
	private byte[] key;

	public CryptionFunction(byte[] input, byte[] key, int verbose)
	{
		super(input, verbose);
		this.key = key;
	}

	public abstract byte[] encryption();

	public abstract byte[] decryption();

}
