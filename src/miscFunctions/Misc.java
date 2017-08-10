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
			longs[x] = ((long) ints[2 * x]) << 32;
			longs[x] |= ints[2 * x + 1];
		}

		return longs;
	}
}
