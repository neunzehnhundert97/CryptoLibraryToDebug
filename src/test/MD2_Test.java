package test;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import hash.MD2;
import hash.MD5;

public class MD2_Test
{

	@Test
	public final void testDigest() throws NoSuchAlgorithmException
	{
		System.out.println("Test MD2");
		MessageDigest md2Cor = MessageDigest.getInstance("MD2");

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

				assertArrayEquals(md2Cor.digest(input), (new MD2(null, -1).digest(input)));

			}
		}
	}

}
