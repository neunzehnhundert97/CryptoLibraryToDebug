package hash;

import miscFunctions.Function;

public abstract class HashFunction extends Function
{

	// Constructor
	public HashFunction(byte[] input, int verbose, String output)
	{
		super(input, output, verbose);
	}
}
