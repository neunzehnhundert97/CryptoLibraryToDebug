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
			default:
				scan.close();
				System.out.println("ERROR: Something went dramatically wrong. Please report this error.");
				System.exit(-1);
			}
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
				// scan.close();
				return "cryptoAlgorithms";
			case "h":
				// scan.close();
				return "hashAlgorithms";
			case "m":
				// scan.close();
				return "miscAlgorithms";
			case "q":
				// scan.close();
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
				return "start_sha224";
			case "2":
				return "start_sha256";
			case "3":
				return "start_sha384";
			case "4":
				return "start_sha512";
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
