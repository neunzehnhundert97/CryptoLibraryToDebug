package test;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.Test;
import hash.SHA2;

public class SHA2_Test
{

	@Test
	public final void testSHA224() throws NoSuchAlgorithmException
	{
		System.out.println("Test SHA224");
		MessageDigest shaCor = MessageDigest.getInstance("SHA-224");

		for (int size = 0; size < 10000; ++size)
		{
			System.out.println("Size: " + size);
			byte[] input = new byte[size];
			for (int x = 0; x < 10; ++x)
			{
				for (int i = 0; i < size; ++i)
				{
					input[i] = (byte) (Math.random() * 256);
				}

				assertArrayEquals(shaCor.digest(input), (new SHA2(SHA2.SHA224, null, -1).digest(input)));

			}
		}
	}

	@Test
	public final void testSHA256() throws NoSuchAlgorithmException
	{
		System.out.println("Test SHA256");
		MessageDigest shaCor = MessageDigest.getInstance("SHA-256");

		for (int size = 0; size < 10000; ++size)
		{
			System.out.println("Size: " + size);
			byte[] input = new byte[size];
			for (int x = 0; x < 10; ++x)
			{
				// System.out.print(x+" ");
				for (int i = 0; i < size; ++i)
				{
					input[i] = (byte) (Math.random() * 256);
				}

				assertArrayEquals(shaCor.digest(input), (new SHA2(SHA2.SHA256, null, -1).digest(input)));

			}
		}
	}

	@Test
	public final void testSHA384() throws NoSuchAlgorithmException
	{
		System.out.println("Test SHA384");
		MessageDigest shaCor = MessageDigest.getInstance("SHA-384");

		for (int size = 0; size < 10000; ++size)
		{
			System.out.println("Size: " + size);
			byte[] input = new byte[size];
			for (int x = 0; x < 10; ++x)
			{
				// System.out.print(x+" ");
				for (int i = 0; i < size; ++i)
				{
					input[i] = (byte) (Math.random() * 256);
				}

				assertArrayEquals(shaCor.digest(input), (new SHA2(SHA2.SHA384, null, -1).digest(input)));

			}
		}
	}

	@Test
	public final void testSHA512() throws NoSuchAlgorithmException
	{
		System.out.println("Test SHA512");
		MessageDigest shaCor = MessageDigest.getInstance("SHA-512");

		for (int size = 0; size < 10000; ++size)
		{
			System.out.println("Size: " + size);
			byte[] input = new byte[size];
			for (int x = 0; x < 10; ++x)
			{
				// System.out.print(x+" ");
				for (int i = 0; i < size; ++i)
				{
					input[i] = (byte) (Math.random() * 256);
				}

				assertArrayEquals(shaCor.digest(input), (new SHA2(SHA2.SHA512, null, -1).digest(input)));

			}
		}
	}

}
