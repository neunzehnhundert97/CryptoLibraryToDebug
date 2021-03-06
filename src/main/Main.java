package main;

import miscFunctions.*;

import com.sun.org.apache.xml.internal.security.utils.DigesterOutputStream;

import hash.*;

public class Main
{
	private static final int HELP_WIDTH = 18;

	public static void main(String[] args)
	{

		if (args.length == 0)
		{
			@SuppressWarnings("unused")
			InteractiveIO iio = new InteractiveIO();
		}
		else
		{
			doAlgorithm(args);
		}
	}

	public static void doAlgorithm(String[] args)
	{
		String algorithm = null;
		String input = null;
		int verboseLevel = -1;
		String key = null;
		boolean de_en_cryption = false; // false = decryption --- true = encryption
		boolean allHashSet = false;

		for (int i = 0; i < args.length; i++)
		{
			switch (args[i])
			{
			case "-a":
			case "-algorithm":
				algorithm = args[++i];
				break;
			case "-i":
			case "-input":
				input = args[i + 1];

				if (input == null)
				{
					input = "";
				}

				// check for input string with blanks
				for (int x = i + 2; x < args.length; x++)
				{
					if (!args[x].startsWith("-"))
					{
						input = input + " " + args[x];
					}
					else
					{
						break;
					}
				}

				break;
			case "-v":
			case "-verbose":
				verboseLevel = Integer.valueOf(args[++i]);
				break;
			case "-k":
			case "-key":
				key = args[++i];
				break;
			case "-d":
			case "-decrypt":
				de_en_cryption = false;
				break;
			case "-e":
			case "-encrypt":
				de_en_cryption = true;
				break;
			case "-h":
			case "-help":
				helpfunction_short();
				System.exit(0);
				break;
			case "-IO":
			case "-io":
			case "-Interactive":
				@SuppressWarnings("unused")
				InteractiveIO iio = new InteractiveIO();
			case "-allHash":
			case "-allhash":
				allHashSet = true;
			}
		}

		Misc.printHeadLine(null);

		// check mandatory inputs
		if (algorithm == null && allHashSet == false)
		{
			System.out.println("ERROR: Please enter an algorithm after '-algorithm' or '-a' You choosed " + algorithm
					+ " as an algorithm.");
			Misc.throwError(-100);
		}

		if (input == null)
		{
			input = "";
		}

		if (algorithm == null)
		{
			algorithm = "";
		}

		// check input conformity
		if (verboseLevel == -1)
		{
			System.out.println(
					"INFO: Default verbose level is 0. You can set a higher verbose level with\n'-verbose' or 'v'");
			verboseLevel = 0;
		}
		if (verboseLevel < 0 || verboseLevel > 3)
		{
			System.out.println("ERROR: Please enter a possible verbose level. You choosed " + verboseLevel
					+ "as an verbose level. Possible levels are 0, 1, 2, 3.");
			Misc.throwError(-100);
		}

		if (algorithm.startsWith("-"))
		{
			System.out.println("ERROR: Please enter an algorithm after '-algorithm' or '-a' You choosed " + algorithm
					+ " as an algorithm.");
			Misc.throwError(-100);
		}

		StringBuilder output = new StringBuilder();

		if (allHashSet)
		{
			try
			{
				Misc.printHeadLine("SHA-224");
				(new SHA2(SHA2.SHA224, output, verboseLevel)).digest(input.getBytes());
				System.out.println(output);
				output.delete(0, output.length());
				Misc.printHeadLine("SHA-256");
				(new SHA2(SHA2.SHA256, output, verboseLevel)).digest(input.getBytes());
				System.out.println(output);
				output.delete(0, output.length());
				Misc.printHeadLine("SHA-256");
				(new SHA2(SHA2.SHA384, output, verboseLevel)).digest(input.getBytes());
				System.out.println(output);
				output.delete(0, output.length());
				Misc.printHeadLine("SHA-256");
				(new SHA2(SHA2.SHA512, output, verboseLevel)).digest(input.getBytes());
				System.out.println(output);
				output.delete(0, output.length());
				Misc.printHeadLine("SHA-1");
				(new SHA1(output, verboseLevel)).digest(input.getBytes());
				System.out.println(output);
				output.delete(0, output.length());
				Misc.printHeadLine("MD5");
				(new MD5(output, verboseLevel)).digest(input.getBytes());
				System.out.println(output);
				output.delete(0, output.length());
				System.exit(0);
			}
			catch (Exception e)
			{
				Misc.throwError(-101);
			}
		}

		// now start the algorithms

		try
		{
			switch (algorithm.toLowerCase())
			{
			case "sha224":
				Misc.printHeadLine("SHA-224");
				(new SHA2(SHA2.SHA224, output, verboseLevel)).digest(input.getBytes());
				break;
			case "sha256":
				Misc.printHeadLine("SHA-256");
				(new SHA2(SHA2.SHA256, output, verboseLevel)).digest(input.getBytes());
				break;
			case "sha384":
				Misc.printHeadLine("SHA-384");
				(new SHA2(SHA2.SHA384, output, verboseLevel)).digest(input.getBytes());
				break;
			case "sha512":
				Misc.printHeadLine("SHA-512");
				(new SHA2(SHA2.SHA512, output, verboseLevel)).digest(input.getBytes());
				break;
			case "sha1":
				Misc.printHeadLine("SHA-1");
				(new SHA1(output, verboseLevel)).digest(input.getBytes());
				break;
			case "md5":
				Misc.printHeadLine("MD5");
				(new MD5(output, verboseLevel)).digest(input.getBytes());
				break;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Misc.throwError(-102);
		}
		finally
		{
			System.out.println(output);
		}
		System.exit(0);
	}

	private static void helpfunction_short()
	{
		Misc.printHeadLine("HELP - SHORTCUTS");
		System.out.printf("%1$-" + HELP_WIDTH + "s %2$s", "-a [ALGORITHM]",
				"Set the algorithm which should be executed.\n");
		System.out.printf("%1$-" + HELP_WIDTH + "s %2$s", "-d", "Let the algorithm decrypt.\n");
		System.out.printf("%1$-" + HELP_WIDTH + "s %2$s", "-e", "let the algorithm encrypt.\n");
		System.out.printf("%1$-" + HELP_WIDTH + "s %2$s", "-h", "Opens the help Page with short commands.\n");
		System.out.printf("%1$-" + HELP_WIDTH + "s %2$s", "-i [INPUT]",
				"Give the algorithm its input. In Cryptographic algorithms input is the plaintext.\n");
		System.out.printf("%1$-" + HELP_WIDTH + "s %2$s", "-IO",
				"Enter the interactive IO mode. (You will enter theis mode when you start the programm without arguments.\n");
		System.out.printf("%1$-" + HELP_WIDTH + "s %2$s", "-k [KEY]", "Give the cryptographic algorithm the key.\n");
		System.out.printf("%1$-" + HELP_WIDTH + "s %2$s", "-v [LEVEL]", "Set the verbose Level.\n");
		System.out.printf("%1$-" + HELP_WIDTH + "s %2$s", "", "Level [0]: Quiet - Only output of algorithm.\n");
		System.out.printf("%1$-" + HELP_WIDTH + "s %2$s", "",
				"Level [1]: Informative  - Prints basic intermediate Values.\n");
		System.out.printf("%1$-" + HELP_WIDTH + "s %2$s", "",
				"Level [2]: Excessive - Prints all intermediate values of this algorithm.\n");
		System.out.printf("%1$-" + HELP_WIDTH + "s %2$s", "",
				"level [3]: Inheritive - Prints all intermediate values of this and called algorithms.\n");
	}
}
