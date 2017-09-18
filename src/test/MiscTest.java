package test;

import org.junit.Test;

public class MiscTest
{

	@Test
	public final void testFormat()
	{
		for(int x = 0;x<1000000;++x)
		{
			String s = "00";
			s = String.format("%-8s", s).replace(' ', '0');
		}
	}
	
	@Test
	public final void testWhile()
	{
		for(int x = 0;x<1000000;++x)
		{
			String s = "00";
			while(s.length()%8!=0)
			{
				s  += 0;
			}
		}
	}

}
