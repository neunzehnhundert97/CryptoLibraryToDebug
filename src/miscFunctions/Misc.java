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
		}
		else
		{
			for (int i = 0; i < 80; i++)
			{
				System.out.print("-");
			}
		}

		System.out.println();
	}

	public static byte[] getBytes(int[] array)
	{
		byte[] bytes = new byte[array.length * 4];

		for (int x = 0; x < array.length; ++x)
		{
			bytes[4 * x] = (byte) (array[x] >>> 24);
			bytes[4 * x + 1] = (byte) (array[x] >>> 16);
			bytes[4 * x + 2] = (byte) (array[x] >>> 8);
			bytes[4 * x + 3] = (byte) array[x];
		}

		return bytes;
	}

	public static byte[] getBytes(long[] array)
	{
		int[] ints = new int[array.length * 2];

		for (int x = 0; x < array.length; ++x)
		{
			ints[2 * x] = (int) (array[x] >>> 32);
			ints[2 * x + 1] = (int) array[x];
		}

		return getBytes(ints);
	}

	public static int[] concatToInteger(byte[] array)
	{
		int[] ints = new int[array.length / 4];

		for (int x = 0; x < array.length / 4; ++x)
		{
			ints[x] = Byte.toUnsignedInt(array[4 * x]) << 24;
			ints[x] |= Byte.toUnsignedInt(array[4 * x + 1]) << 16;
			ints[x] |= Byte.toUnsignedInt(array[4 * x + 2]) << 8;
			ints[x] |= Byte.toUnsignedInt(array[4 * x + 3]);
		}

		return ints;
	}

	public static long[] concatToLong(byte[] input)
	{
		int[] ints = Misc.concatToInteger(input);
		long[] longs = new long[ints.length / 2];

		for (int x = 0; x < ints.length / 2; ++x)
		{
			longs[x] = Integer.toUnsignedLong(ints[2 * x]) << 32;
			longs[x] |= Integer.toUnsignedLong(ints[2 * x + 1]);
		}

		return longs;
	}
	
	public static void throwError(int errorCode) {
		
		/* FATAL ERROR DEFINITIONS:
		 * ------------------------
		 * -1 :	Fatal ERRER where the program normally not get to. For example a switch where it should be impossible to get into the default 
		 * 		case but you never get to this point normally.
		 * -2 :	FATAL ERROR for debug reasons. We normally use this error to detect bugs in code.
		 * 
		 * -100+ ERROR DEFINITIONS:
		 * ------------------------
		 * -100 :	Error in the input values. Most common reason: Wrong input Syntax. Here a short sys.out should tell you whats going wrong
		 * 			before you get into this error function.
		 */
		
		switch (errorCode) {
		case -1:
			System.out.println("FATAL ERROR! Please report this bug!");break;
		case -2:
			System.out.println("FATAL ERROR FOR DEBUG ONLY!");break;
		case -100:
			System.out.println("ERROR IN INPUT VALUES");break;
		case -101:
			System.out.println("ERROR IN ALL HASH ALGORITHMS");break;
		case -102:
			System.out.println("ERRIR IN SPECIFIC HASH");break;
		}
		System.exit(errorCode);
	}
}
