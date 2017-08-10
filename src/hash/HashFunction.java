package hash;

import miscFunctions.Function;

public abstract class HashFunction extends Function
{

	// Constructor
	public HashFunction(byte[] input, StringBuilder output, int verbose)
	{
		// Call constructor of superclass
		super(input, output, verbose);
	}

	// Abstract digest method
	public abstract byte[] digest();

}
