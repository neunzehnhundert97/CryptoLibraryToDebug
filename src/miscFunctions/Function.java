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
	public Function(StringBuilder output, int verboseLevel)
	{
		this.output = output;
		this.verbose = verboseLevel;
	}

	// This method is WIP and should not be expected to be fast and/or efficient
	// Permutation is expected to index from 1 to input.length()
	protected byte[] permutate(byte[] input, int[] permutation)
	{
		if (input.length * 8 != permutation.length)
			Misc.throwError(-2);

		byte[] output = new byte[input.length];

		for (int x = 0; x < input.length; ++x)
		{
			byte b = input[x];
			for (int y = 0; y < 8; ++y)
			{
				if (((b >>> (7 - y)) & 1) == 1)
				{
					int pos = permutation[x * 8 + y] - 1;
					output[pos / 8] |= (1 << (7 - (pos % 8)));
				}
			}
		}

		return output;
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
