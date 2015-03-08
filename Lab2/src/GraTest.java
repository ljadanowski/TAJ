import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;    
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GraTest 
{
	Gra gra;
	@Before
	public void setUp() throws Exception 
	{
		gra = new Gra();
	}

	@After
	public void tearDown() throws Exception 
	{
		gra = null;
	}

	@Test
	public void cyfrokradtest() 
	{
		Pattern p;
		Matcher m;
		boolean b;
		
		assertNull(gra.cyfrokrad(1));
		assertNull(gra.cyfrokrad(-1));
		assertNull(gra.cyfrokrad(9));
		assertNull(gra.cyfrokrad(-9));
		
		Integer liczba = gra.cyfrokrad(12);
		p = Pattern.compile("[0-9]");
		m = p.matcher(liczba.toString());
		b = m.matches();
		assertTrue(b);
		
		liczba = gra.cyfrokrad(-12);
		p = Pattern.compile("-[0-9]");
		m = p.matcher(liczba.toString());
		b = m.matches();
		assertTrue(b);
		
		liczba = gra.cyfrokrad(123);
		p = Pattern.compile("[0-9]{2}");
		m = p.matcher(liczba.toString());
		b = m.matches();
		assertTrue(b);
		
		liczba = gra.cyfrokrad(-123);
		p = Pattern.compile("-[0-9]{2}");
		m = p.matcher(liczba.toString());
		b = m.matches();
		assertTrue(b);
		
		liczba = gra.cyfrokrad(1234);
		p = Pattern.compile("[0-9]{3}");
		m = p.matcher(liczba.toString());
		b = m.matches();
		assertTrue(b);
		
		liczba = gra.cyfrokrad(-1234);
		p = Pattern.compile("-[0-9]{3}");
		m = p.matcher(liczba.toString());
		b = m.matches();
		assertTrue(b);
	}
	
	@Test
	public void hultajchochlatest() 
	{
		Pattern p;
		Matcher m;
		boolean b;
		
		try {
			Integer liczba = gra.hultajchochla(12);
			p = Pattern.compile("[1-2][1-2]");
			m = p.matcher(liczba.toString());
			b = m.matches();
			assertTrue(b);
			
			liczba = gra.hultajchochla(123);
			p = Pattern.compile("[1-3][1-3][1-3]");
			m = p.matcher(liczba.toString());
			b = m.matches();
			assertTrue(b);
			
		} catch (NieudanyPsikusException e) {
			e.printStackTrace();
		}
	}
	@Test(expected=NieudanyPsikusException.class)
	public void hultajchochlatestException() 
	{
		try {
			Integer liczba = gra.hultajchochla(1);
		} catch (NieudanyPsikusException e) {
			//e.printStackTrace();
		}
	}
	
	@Test
	public void nieksztaltek() 
	{
		Pattern p;
		Matcher m;
		boolean b;
		
		Integer liczba = gra.nieksztaltek(1);
		assertEquals(1, liczba.intValue());
		
		liczba = gra.nieksztaltek(2);
		assertEquals(2, liczba.intValue());
		
		liczba = gra.nieksztaltek(12);
		assertEquals(12, liczba.intValue());
		
		liczba = gra.nieksztaltek(123);
		p = Pattern.compile("12[3,8]");
		m = p.matcher(liczba.toString());
		b = m.matches();
		assertTrue(b);
		
		liczba = gra.nieksztaltek(127);
		p = Pattern.compile("12[7,1]");
		m = p.matcher(liczba.toString());
		b = m.matches();
		assertTrue(b);
		
		liczba = gra.nieksztaltek(126);
		p = Pattern.compile("12[6,9]");
		m = p.matcher(liczba.toString());
		b = m.matches();
		assertTrue(b);
		
		liczba = gra.nieksztaltek(376);
		p = Pattern.compile("[3,8][7,6][6,9]");
		m = p.matcher(liczba.toString());
		b = m.matches();
		assertTrue(b);
	}
	
}
