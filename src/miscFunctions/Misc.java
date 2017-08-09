package miscFunctions;

public class Misc
{

	public static void printHeadLine(String s)
	{
		System.out.println();
		
		if (s != null)
		{
			int sLength = s.length() + 2;
			for (int i = 0; i < 40 - (sLength / 2); i++)
			{
				System.out.print("-");
			}
			System.out.print(" " + s + " ");
			for (int i = 0; i < 40 - (sLength / 2); i++)
			{
				System.out.print("-");
			}
		}else {
			for (int i = 0; i < 80; i++) {
				System.out.print("-");
			}
		}

		System.out.println();
	}

	public static void printLogo()
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
