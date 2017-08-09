package miscFunctions;

import main.Main;

public abstract class Function
{
	// Constants
	public static final int QUIET = 0, INFORMATIVE = 1, EXCESSIVE = 2, INHERITIVE = 3;

	// Attributes
	protected byte[] input;

	protected int verbose;

	public Function(byte[] input, int verboseLevel)
	{
		this.input = input;
		this.verbose = verboseLevel;

		// Print input
		this.writeOutput(QUIET, "Input (" + input.length + " Bytes)");
		this.writeOutput(input, QUIET);
	}

	protected void writeOutput(int verboseLevel, Object... objects)
	{
		if (verboseLevel <= this.verbose)
		{
			for (Object o : objects)
			{
				Main.output += o;
			}
			Main.output += '\n';
		}
	}

	protected void writeOutput(byte[] array, int verboseLevel)
	{
		if (verboseLevel <= this.verbose)
		{
			for (int x = 0; x < array.length; ++x)
			{
				Main.output += String.format("%02X", array[x]);
				if (x % 64 == 63)
					Main.output += "\n\n";
				else if (x % 16 == 15)
					Main.output += "\n";
				else if (x % 4 == 3)
					Main.output += " ";
			}
		}
	}
}
