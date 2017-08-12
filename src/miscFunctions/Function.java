package miscFunctions;

public abstract class Function
{
	// Constants
	public static final int SILENCED = -1, QUIET = 0, INFORMATIVE = 1, EXCESSIVE = 2, INHERITIVE = 3;

	// Attributes
	protected byte[] input;
	protected StringBuilder output;

	protected int verbose;

	// Constructor
	public Function(byte[] input, StringBuilder output, int verboseLevel)
	{
		this.input = input;
		this.output = output;
		this.verbose = verboseLevel;

		// Print input
		this.writeOutput("Input (" + input.length + " Bytes)", QUIET);
		this.writeOutput(input, QUIET);
	}

	protected void writeOutput(String string, int verboseLevel)
	{
		if (verboseLevel <= this.verbose)
		{
			this.intend(verboseLevel);
			output.append(string);
			output.append('\n');
		}
	}
	
	protected void writeFormatOutput(String string, int verboseLevel, Object... args)
	{
		if (verboseLevel <= this.verbose)
		{
			this.intend(verboseLevel);
			output.append(String.format(string, args));
			output.append('\n');
		}
	}

	protected void writeOutput(byte[] array, int verboseLevel)
	{
		if (verboseLevel <= this.verbose)
		{
			this.intend(verboseLevel);
			for (int x = 0; x < array.length; ++x)
			{
				output.append(String.format("%02X", array[x]));
				if (x % 64 == 63 && x != array.length - 1)
				{
					output.append("\n\n");
					this.intend(verboseLevel);
				}
				else if (x % 16 == 15)
				{
					output.append("\n");
					this.intend(verboseLevel);
				}
				else if (x % 4 == 3)
					output.append(" ");
			}

			output.append('\n');
		}
	}

	protected void writeOutput(int[] array, int verboseLevel)
	{
		if (verboseLevel <= this.verbose)
		{
			this.intend(verboseLevel);
			for (int x = 0; x < array.length; ++x)
			{
				output.append(String.format("%08X", array[x]));
				if (x % 16 == 15 && x != array.length - 1)
				{
					output.append("\n\n");
					this.intend(verboseLevel);
				}
				else if (x % 4 == 3)
				{
					output.append("\n");
					this.intend(verboseLevel);
				}
				else
					output.append(" ");
			}

			output.append('\n');
		}
	}

	protected void writeOutput(long[] array, int verboseLevel)
	{
		if (verboseLevel <= this.verbose)
		{
			this.intend(verboseLevel);
			for (int x = 0; x < array.length; ++x)
			{
				output.append(String.format("%08X %08X", (int) (array[x] >>> 32), (int) array[x]));
				if (x % 8 == 7 && x != array.length - 1)
				{
					output.append("\n\n");
					this.intend(verboseLevel);
				}
				else if (x % 2 == 1)
				{
					output.append("\n");
					this.intend(verboseLevel);
				}
				else
				{
					output.append(" ");
				}
			}

			output.append('\n');
		}
	}

	private void intend(int verboseLevel)
	{
		switch (verboseLevel)
		{
		case EXCESSIVE:
			output.append("\t");
		case INFORMATIVE:
			output.append("\t");
		}
	}
}
