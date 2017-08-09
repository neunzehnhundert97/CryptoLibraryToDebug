package miscFunctions;

public abstract class Function
{
	// Constants
	public static final int QUIET = 0;
	public static final int INFORMATIVE = 1;
	public static final int EXCESSIVE = 2;
	public static final int INHERITIVE = 3;

	// Attributes
	protected byte[] input;
	protected String output;

	protected int verbose;

	public Function(byte[] input, String output, int verboseLevel)
	{
		this.input = input;
		this.output = output;
		this.verbose = verboseLevel;

		// Print input
		this.writeOutput(QUIET, "Input (" + input.length + " Bytes)");
		this.writeOutput(QUIET, input);
	}

	protected void writeOutput(int verboseLevel, Object... objects)
	{
		if (verboseLevel >= this.verbose)
		{
			for (Object o : objects)
			{
				output += o;
			}
			output += '\n';
		}
	}

	protected void writeOutput(int verboseLevel, byte[] array)
	{
		if (verboseLevel >= this.verbose)
		{
			for (int x = 0; x < array.length; ++x)
			{
				output += String.format("%02X", array[x]);
				if (x % 64 == 63)
					output += "\n\n";
				else if (x % 16 == 15)
					output += "\n";
				else if (x % 4 == 3)
					output += " ";
			}
		}
	}
}
