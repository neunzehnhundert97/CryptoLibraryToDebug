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

	// Converts an array of integers into an array of bytes
	// Example 0x12345678 => 0x12, 0x34, 0x56, 0x78
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

	// Converts an array of longs to an array of bytes
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

	public static void throwError(int errorCode)
	{

		/*
		 * FATAL ERROR DEFINITIONS: ------------------------ -1 : Fatal ERRER where the
		 * program normally not get to. For example a switch where it should be
		 * impossible to get into the default case but you never get to this point
		 * normally. -2 : FATAL ERROR for debug reasons. We normally use this error to
		 * detect bugs in code.
		 * 
		 * -100+ ERROR DEFINITIONS: ------------------------ -100 : Error in the input
		 * values. Most common reason: Wrong input Syntax. Here a short sys.out should
		 * tell you whats going wrong before you get into this error function.
		 */

		switch (errorCode)
		{
		case -1:
			System.out.println("FATAL ERROR! Please report this bug!");
			break;
		case -2:
			System.out.println("FATAL ERROR FOR DEBUG ONLY!");
			break;
		case -100:
			System.out.println("ERROR IN INPUT VALUES");
			break;
		case -101:
			System.out.println("ERROR IN ALL HASH ALGORITHMS");
			break;
		case -102:
			System.out.println("ERROR IN SPECIFIC HASH");
			break;
		}
		System.exit(errorCode);
	}

	// Converts a byte to a binary string as Java doesn't have this functionality
	public static String byteToBinaryString(byte b)
	{
		return String.format("%8s", Integer.toBinaryString(Byte.toUnsignedInt(b))).replace(' ', '0');
	}

	// Converts an array of bytes to a binary string
	public static String byteToBinaryString(byte[] b)
	{
		String s = "";
		for (byte by : b)
		{
			s += Misc.byteToBinaryString(by);
		}
		return s;
	}

	// Converts a binary String to a array of chars
	public static byte[] binStringToByteArray(String s)
	{
		// Pad to whole bytes
		while (s.length() % 8 != 0)
		{
			s += '0';
		}

		byte[] out = new byte[s.length() / 8];

		for (int x = 0; x < out.length; ++x)
		{
			int i = Integer.valueOf(s.substring(x * 8, x * 8 + 8), 2);
			out[x] = (byte) i;
		}

		return out;
	}

	// This function will reverse the bits in each byte independently
	public static byte[] revBytes(byte[] array)
	{
		byte[] out = new byte[array.length];
		
		for(int x = 0;x<array.length;++x)
		{
			out[x] = (byte) (Integer.reverse(array[x])>>>24);
		}
		
		return out;
	}

}
