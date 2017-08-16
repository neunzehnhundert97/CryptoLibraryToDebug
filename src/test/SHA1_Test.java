package test;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import hash.SHA1;

public class SHA1_Test
{

	@Test
	public final void testDigest() throws NoSuchAlgorithmException
	{
		System.out.println("Test SHA-1");
		MessageDigest shaCor = MessageDigest.getInstance("SHA-1");
		SHA1 shaTest = new SHA1(null, -1);

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

				assertArrayEquals(shaCor.digest(input), (shaTest.digest(input)));

			}
		}
	}

}
