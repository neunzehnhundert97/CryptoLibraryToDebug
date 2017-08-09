package main;

public class Main {

	public static void main(String[] args) {

		if (args.length == 0) {
			InteractiveIO iio = new InteractiveIO();
			iio.controller();
		} else {
			doAlgorithm(args);
		}
	}

	public static void doAlgorithm(String[] args) {

		String algorithm = null;
		String input = null;
		int verboseLevel = -1;
				
		for (int i = 0; i < args.length; i++) {
			switch(args[i]) {
			case "-a":
			case "-algorithm":
				algorithm = args[i+1];
				break;
			case "-i":
			case "-input":
				input = args[i+1];
				break;
			case "-v":
			case "-verbose":
				verboseLevel = Integer.valueOf(args[i+1]);
				break;
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
		
		//start the algorithms
		if (algorithm.startsWith("-")) {
			System.out.println("ERROR: Please enter an algorithm after '-algorithm' or '-a' You choosed \" + algorithm + \" as an algorithm.");
		}
	}
}
