package hash;

import miscFunctions.Function;

public abstract class HashFunction extends Function
{

	// Constructor
	public HashFunction(byte[] input, int verbose)
	{
		// Call constructor of superclass
		super(input, verbose);
	}

	// Abstract digest method
	public abstract byte[] digest();

}
