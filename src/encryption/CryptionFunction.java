package encryption;

import miscFunctions.Function;

public abstract class CryptionFunction extends Function
{
	// Attributes
	private byte[] key;

	public CryptionFunction(byte[] input, byte[] key, String output, int verbose)
	{
		super(input, output, verbose);
		this.key = key;
	}

	public abstract byte[] encryption();

	public abstract byte[] decryption();

}
