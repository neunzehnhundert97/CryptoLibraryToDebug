package hash;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.Test;

public class SHA2_Test
{

	@Test
	public final void testSHA224() throws NoSuchAlgorithmException
	{
		System.out.println("Test SHA224");
		MessageDigest shaCor = MessageDigest.getInstance("SHA-224");
		
		for (int size = 0; size < 500; ++size)
		{
			System.out.println("Size: "+size);
			byte[] input = new byte[size];
			for (int x = 0; x < 2; ++x)
			{
				//System.out.print(x+" ");
				for(int i = 0;i<size;++i)
				{
					input[i] = (byte) (Math.random()*Byte.MAX_VALUE);
				}
				
				assertArrayEquals(shaCor.digest(input), (new SHA2(input, SHA2.SHA224, 0).digest()));
				
			}
		}
	}
	
	@Test
	public final void testSHA256() throws NoSuchAlgorithmException
	{
		System.out.println("Test SHA256");
		MessageDigest shaCor = MessageDigest.getInstance("SHA-256");
		
		for (int size = 0; size < 500; ++size)
		{
			System.out.println("Size: "+size);
			byte[] input = new byte[size];
			for (int x = 0; x < 2; ++x)
			{
				//System.out.print(x+" ");
				for(int i = 0;i<size;++i)
				{
					input[i] = (byte) (Math.random()*Byte.MAX_VALUE);
				}
				
				assertArrayEquals(shaCor.digest(input), (new SHA2(input, SHA2.SHA256, 0).digest()));
				
			}
		}
	}
	
	@Test
	public final void testSHA384() throws NoSuchAlgorithmException
	{
		System.out.println("Test SHA384");
		MessageDigest shaCor = MessageDigest.getInstance("SHA-384");
		
		for (int size = 0; size < 500; ++size)
		{
			System.out.println("Size: "+size);
			byte[] input = new byte[size];
			for (int x = 0; x < 2; ++x)
			{
				//System.out.print(x+" ");
				for(int i = 0;i<size;++i)
				{
					input[i] = (byte) (Math.random()*Byte.MAX_VALUE);
				}
				
				assertArrayEquals(shaCor.digest(input), (new SHA2(input, SHA2.SHA384, 0).digest()));
				
			}
		}
	}
	
	@Test
	public final void testSHA512() throws NoSuchAlgorithmException
	{
		System.out.println("Test SHA512");
		MessageDigest shaCor = MessageDigest.getInstance("SHA-512");
		
		for (int size = 0; size < 500; ++size)
		{
			System.out.println("Size: "+size);
			byte[] input = new byte[size];
			for (int x = 0; x < 2; ++x)
			{
				//System.out.print(x+" ");
				for(int i = 0;i<size;++i)
				{
					input[i] = (byte) (Math.random()*Byte.MAX_VALUE);
				}
				
				assertArrayEquals(shaCor.digest(input), (new SHA2(input, SHA2.SHA512, 0).digest()));
				
			}
		}
	}

}
