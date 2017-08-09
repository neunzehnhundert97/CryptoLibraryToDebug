package main;

import miscFunctions.Misc;

public class Main {

	public static void main(String[] args) {

		if (args.length == 0) {
			InteractiveIO iio = new InteractiveIO();
		} else {
			doAlgorithm(args);
		}
	}

	public static void doAlgorithm(String[] args) {

		String algorithm = null;
		String input = null;
		int verboseLevel = -1;
		String key = null;
		boolean de_en_cryption = false; // false = decryption --- true = encryption 
				
		for (int i = 0; i < args.length; i++) {
			switch(args[i]) {
			case "-a":
			case "-algorithm":
				algorithm = args[++i];
				break;
			case "-i":
			case "-input":
				input = args[++i];
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
				helpfunction();
				System.exit(0);
				break;
			case "-IO":
			case "-io":
			case "-Interactive":
				InteractiveIO iio = new InteractiveIO();
				
			}
		}
		
		//check mandatory inputs
		if(algorithm == null) {
			System.out.println("ERROR: Please enter an algorithm with '-algorithm' or '-a'.");
			System.exit(-1);
		}
		
		//check input conformity
		if (algorithm.charAt(0) == '-') {
			System.out.println("ERROR: Please enter an algorithm after '-algorithm' or '-a' You choosed " + algorithm + " as an algorithm.");
		}
		if (verboseLevel == -1) {
			System.out.println("INFO: Devault verbose level is 0. You can set a higher verbose level with '-verbose' or 'v'");
			verboseLevel = 0;
		}
		if (verboseLevel < 0 || verboseLevel > 3) {
			System.out.println("ERROR: Please enter a possible verbose level. You choosed " + verboseLevel + "as an verbose level. Possible levels are 0, 1, 2, 3.");
			System.exit(-1);
		}
		
		if (algorithm.startsWith("-")) {
			System.out.println("ERROR: Please enter an algorithm after '-algorithm' or '-a' You choosed \" + algorithm + \" as an algorithm.");
		}
		
		
		//now start the algorithms
		switch(algorithm.toLowerCase()) {
		case "sha224":
			Misc.printHeadLine("SHA224");
			//TODO open sha224
			break;
		case "sha256":
			Misc.printHeadLine("SHA256");
			//TODO open sha256
			break;
		case "sha384":
			Misc.printHeadLine("SHA384");
			//TODO open sha384
			break;
		case "sha512":
			Misc.printHeadLine("SHA512");
			//TODO open sha512
			break;
		}
		
		System.exit(0);
	}

	private static void helpfunction() {
		// TODO Auto-generated method stub
		
	}
}
