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
		this.writeOutput("Input (" + input.length + " Bytes)", QUIET);
		this.writeOutput(input, QUIET);
	}

	protected void writeOutput(String string, int verboseLevel)
	{
		if (verboseLevel <= this.verbose)
		{
			this.intend(verboseLevel);
			Main.output += string;
			Main.output += '\n';
		}
	}

	protected void writeOutput(byte[] array, int verboseLevel)
	{
		if (verboseLevel <= this.verbose)
		{
			this.intend(verboseLevel);
			for (int x = 0; x < array.length; ++x)
			{
				Main.output += String.format("%02X", array[x]);
				if (x % 64 == 63 && x != array.length - 1)
				{
					Main.output += "\n\n";
					this.intend(verboseLevel);
				}
				else if (x % 16 == 15)
				{
					Main.output += "\n";
					this.intend(verboseLevel);
				}
				else if (x % 4 == 3)
					Main.output += " ";
			}

			Main.output += '\n';
		}
	}

	protected void writeOutput(int[] array, int verboseLevel)
	{
		if (verboseLevel <= this.verbose)
		{
			this.intend(verboseLevel);
			for (int x = 0; x < array.length; ++x)
			{
				Main.output += String.format("%08X", array[x]);
				if (x % 16 == 15 && x != array.length - 1)
				{
					Main.output += "\n\n";
					this.intend(verboseLevel);
				}
				else if (x % 4 == 3)
				{
					Main.output += "\n";
					this.intend(verboseLevel);
				}
				else
					Main.output += " ";
			}

			Main.output += '\n';
		}
	}

	protected void writeOutput(long[] array, int verboseLevel)
	{
		if (verboseLevel <= this.verbose)
		{
			this.intend(verboseLevel);
			for (int x = 0; x < array.length; ++x)
			{
				//System.out.println(String.format("%08X %08X", (int) (array[x] >>> 32), (int) array[x])+"\n"+Long.toHexString(array[x]));
				Main.output += String.format("%08X %08X", (int) (array[x] >>> 32), (int) array[x]);
				if (x % 8 == 7 && x != array.length - 1)
				{
					Main.output += "\n\n";
					this.intend(verboseLevel);
				}
				else if (x % 2 == 1)
				{
					Main.output += "\n";
					this.intend(verboseLevel);
				}
				else
				{
					Main.output += " ";
				}
			}

			Main.output += '\n';
		}
	}

	private void intend(int verboseLevel)
	{
		switch (verboseLevel)
		{
		case EXCESSIVE:
			Main.output += "\t";
		case INFORMATIVE:
			Main.output += "\t";
		}
	}
}
