package main;

import java.util.Scanner;

import miscFunctions.Misc;

public class InteractiveIO
{
	private Scanner scan;

	public InteractiveIO()
	{
		scan = new Scanner(System.in);

		this.printLogo();
		System.out.println("Welcome to the interactive IO. Here you can choose between different options and\n"
				+ "see what is possible. If you want a fast usage of this programm you can start\n"
				+ "this programm with different Parametes out of the console. For more information\n"
				+ "start the prorgamm with the parameter '-h' or '-help'.");
		controller();
	}

	private void controller()
	{
		String choice = null;
		boolean end = false;

		choice = overview();

		while (!end)
		{
			switch (choice)
			{
			case "quit":
				scan.close();
				System.exit(0);
			case "cryptoAlgorithms":
				choice = this.crypto_Algorithms();
				break;
			case "hashAlgorithms":
				choice = this.hash_Algorithms();
				break;
			case "miscAlgorithms":
				choice = this.misc_Algorithms();
				break;
			case "overview":
				choice = this.overview();
				break;
			case "start_sha_2_224":
			case "start_sha_2_256":
			case "start_sha_2_384":
			case "start_sha_2_512":
				start_algorithms(choice);
				choice = "overview";
			default:
				scan.close();
				System.out.println("ERROR: Something went dramatically wrong. Please report this error.");
				System.exit(-1);
			}
		}
	}

	private void start_algorithms(String choice)
	{
		if (choice.startsWith("start_sha_2_"))
		{
			String yn = null;
			boolean correctInput = false;
			String input = null;
			String verboseLevel = null;
			String[] command = new String[6];

			// we are in the sha_2 family
			System.out.println("Do you want a input? (y/n)");

			while (!correctInput)
			{
				yn = scan.next();

				switch (yn)
				{
				case "y":
					System.out.println("Please enter the input:");

					while (!scan.hasNext())
					{
					}
					input = scan.next();
					correctInput = true;
					break;
				case "n":
					correctInput = true;
					input = "";
					break;
				default:
					yn = null;
					System.out.println("Wrong input. Please try again.");
					break;
				}
			}
			System.out.println("Choose your verbose Level (0-3):");

			correctInput = false;
			while (!correctInput)
			{
				while (!scan.hasNext())
				{
				}
				verboseLevel = scan.next();

				if ((Integer.parseInt(verboseLevel)) >= 0 || (Integer.parseInt(verboseLevel)) <= 3)
				{
					correctInput = true;
				}
				else
				{
					System.out.println("Wrong input. Please try again.");
					verboseLevel = null;
				}
			}

			// now start the algorithm
			command[0] = "-a";
			command[2] = "-v";
			command[3] = verboseLevel;

			switch (choice)
			{
			case "start_sha_2_224":
				command[1] = "sha224";
				break;
			case "start_sha_2_256":
				command[1] = "sha256";
				break;
			case "start_sha_2_384":
				command[1] = "sha384";
				break;
			case "start_sha_2_512":
				command[1] = "sha512";
				break;
			default:
				System.out.println("ERROR: choice cant be resolved.");
				break;
			}

			if (input != null)
			{
				command[4] = "-i";
				command[5] = input;
			}
			else
			{
				command[4] = "";
				command[5] = "";
			}

			Main.doAlgorithm(command);
		}

	}

	private String overview()
	{
		String choice = null;

		System.out.println("Choose between following classes of algorithms:\n" + " c - Cryptographic algorithms\n"
				+ " h - Hash algorithms\n" + " m - Misc algorithms\n" + "Type 'q' to quit this programm");

		while (true)
		{
			choice = scan.next();

			if (choice.length() > 1)
			{
				System.out.println("Wrong input. Please try again.");
				choice = null;
			}

			switch (choice)
			{
			case "c":
				return "cryptoAlgorithms";
			case "h":
				return "hashAlgorithms";
			case "m":
				return "miscAlgorithms";
			case "q":
				return "quit";
			default:
				choice = null;
				System.out.println("Wrong input. Please try again.");
			}
		}
	}

	private String crypto_Algorithms()
	{
		Misc.printHeadLine("CRYPTO ALGORITHMS");

		String choice = null;

		System.out.println("Choose between following crypto algorithms:\n" + "nothing here right now - we are sorry\n"
				+ "type 'b' to get back.");

		while (true)
		{
			choice = scan.next();

			if (choice.length() > 1)
			{
				System.out.println("Wrong input. Please try again.");
				choice = null;
			}

			switch (choice)
			{
			case "b":
				return "overview";
			default:
				choice = null;
				System.out.println("Wrong input. Please try again.");
			}
		}

	}

	private String hash_Algorithms()
	{
		Misc.printHeadLine("HASH ALGORITHMS");

		String choice = null;

		System.out.println("Choose between following hash algorithms:\n" + " 1 - sha224\n" + " 2 - sha256\n"
				+ " 3 - sha384\n" + " 4 - sha512\n" + "type 'b' to get back.");

		while (true)
		{
			choice = scan.next();

			if (choice.length() > 1)
			{
				System.out.println("Wrong input. Please try again.");
				choice = null;
			}

			switch (choice)
			{
			case "1":
				return "start_sha_2_224";
			case "2":
				return "start_sha_2_256";
			case "3":
				return "start_sha_2_384";
			case "4":
				return "start_sha_2_512";
			case "b":
				return "overview";
			default:
				choice = null;
				System.out.println("Wrong input. Please try again.");
			}
		}
	}

	private String misc_Algorithms()
	{
		Misc.printHeadLine("MISC ALGORITHMS");

		String choice = null;

		System.out.println("Choose between following misc algorithms:\n" + "nothing here right now - we are sorry\n"
				+ "type 'b' to get back.");

		while (true)
		{
			choice = scan.next();

			if (choice.length() > 1)
			{
				System.out.println("Wrong input. Please try again.");
				choice = null;
			}

			switch (choice)
			{
			case "b":
				return "overview";
			default:
				choice = null;
				System.out.println("Wrong input. Please try again.");
			}
		}

	}

	private void printLogo()
	{
		System.out.println();
		System.out.println("	   _____                  _        _      _ _                          ");
		System.out.println("	  / ____|                | |      | |    (_) |                         ");
		System.out.println("	 | |     _ __ _   _ _ __ | |_ ___ | |     _| |__  _ __ __ _ _ __ _   _ ");
		System.out.println("	 | |    | '__| | | | '_ \\| __/ _ \\| |    | | '_ \\| '__/ _` | '__| | | |");
		System.out.println("	 | |____| |  | |_| | |_) | || (_) | |____| | |_) | | | (_| | |  | |_| |");
		System.out.println("	  \\_____|_|   \\__, | .__/ \\__\\___/|______|_|_.__/|_|  \\__,_|_|   \\__, |");
		System.out.println("	               __/ | |          _        _____       _            __/ |");
		System.out.println("	              |___/|_|         | |      |  __ \\     | |          |___/ ");
		System.out.println("	                               | |_ ___ | |  | | ___| |__  _   _  __ _ ");
		System.out.println("	                               | __/ _ \\| |  | |/ _ \\ '_ \\| | | |/ _` |");
		System.out.println("	                               | || (_) | |__| |  __/ |_) | |_| | (_| |");
		System.out.println("	                                \\__\\___/|_____/ \\___|_.__/ \\__,_|\\__, |");
		System.out.println("	                                                                  __/ |");
		System.out.println("	                                                                 |___/ ");
		System.out.println("	                                                         by Leon Becker");
		System.out.println("	                                                     and Pascal Gimmler");
		Misc.printHeadLine(null);
	}
}
