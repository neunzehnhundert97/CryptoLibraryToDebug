package miscFunctions;

public class Misc {

	public static void printHeadLine(String s)
	{
		int sLength = s.length() + 2;
		System.out.println();
		for(int i = 0; i < 40 - (sLength/2); i++) {
			System.out.print("-");
		}
		System.out.print(" " + s + " ");
		for(int i = 0; i < 40 - (sLength/2); i++) {
			System.out.print("-");
		}
		System.out.println();
		
	}
}
