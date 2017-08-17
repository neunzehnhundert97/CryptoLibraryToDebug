package test;

import static org.junit.Assert.assertArrayEquals;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.junit.Test;

import encryption.DES;

public class DES_Test
{

	@Test
	public final void testDES() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher desCor = Cipher.getInstance("DES/ECB/NoPadding");
		byte[] input = new byte[8];
		for (int x = 0; x < 1000; ++x)
		{
			SecretKey key = KeyGenerator.getInstance("DES").generateKey();
			desCor.init(Cipher.ENCRYPT_MODE, key);
			byte[] keyRaw = key.getEncoded();
			DES desTest = new DES(keyRaw, null, -1);
			for (int y = 0; y < 100; y++)
			{
				for (int i = 0; i < 8; ++i)
				{
					input[i] = (byte) (Math.random() * 256);
				}
				
				assertArrayEquals(desCor.doFinal(input), (desTest.encryption(input)));
			}
		}
	}

}
