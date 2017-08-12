package hash;

import miscFunctions.Function;

public abstract class HashFunction extends Function
{

	// Constructor
	public HashFunction(StringBuilder output, int verbose)
	{
		// Call constructor of superclass
		super(output, verbose);
	}

	// Abstract digest method
	public abstract byte[] digest(byte[] input);

}
