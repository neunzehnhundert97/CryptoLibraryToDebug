package main;

import java.util.Scanner;

import miscFunctions.Misc;

public class InteractiveIO
{
	public InteractiveIO()
	{
		Misc.printLogo();
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
			case "cryptoAlgorithms":
				Misc.printHeadLine("CRYPTO ALGORITHMS");
				System.exit(0);
				break;
			case "hashAlgorithms":
				Misc.printHeadLine("HASH ALGORITHMS");
				System.exit(0);
				break;
			case "miscAlgorithms":
				Misc.printHeadLine("MISC ALGORITHMS");
				System.exit(0);
				break;
			}
		}
	}

	private String overview()
	{
		String choice = null;

		System.out.println("Choose between following classes of algorithms:\n" + " c - Cryptographic algorithms\n"
				+ " h - Hash algorithms\n" + " m - Misc algorithms\n" + "Type 'q' to quit this programm");

		Scanner scan = new Scanner(System.in);

		while (true)
		{

			choice = scan.nextLine();

			if (choice.length() > 1)
			{
				System.out.println("Wrong input. Please try again.");
				choice = null;
			}

			switch (choice)
			{
			case "c":
				scan.close();
				return "cryptoAlgorithms";
			case "h":
				scan.close();
				return "hashAlgorithms";
			case "m":
				scan.close();
				return "miscAlgorithms";
			}

		}
	}
}
