//import java.util.regex.Matcher;

import org.junit.After;
//import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertNull;

import org.hamcrest.*;
import org.hamcrest.core.Is;


public class GraTest
{
	Gra gra;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
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
	
	@SuppressWarnings("unchecked")
	@Test
	public void cyfrokradtest() 
	{
		assertNull(gra.cyfrokrad(1));
		assertNull(gra.cyfrokrad(-1));
		assertNull(gra.cyfrokrad(9));
		assertNull(gra.cyfrokrad(-9));
		
		assertThat(gra.cyfrokrad(12).toString(), RegexMatcher.matches("[0-9]"));
		assertThat(gra.cyfrokrad(-12).toString(), RegexMatcher.matches("-[0-9]"));
		
		assertThat(gra.cyfrokrad(123).toString(), RegexMatcher.matches("[0-9]{2}"));
		assertThat(gra.cyfrokrad(-123).toString(), RegexMatcher.matches("-[0-9]{2}"));
		
		assertThat(gra.cyfrokrad(1234).toString(), RegexMatcher.matches("[0-9]{3}"));
		assertThat(gra.cyfrokrad(-1234).toString(), RegexMatcher.matches("-[0-9]{3}"));		
	}
	
	@Test(expected = NieudanyPsikusException.class)
	public void hultajchochlaWithException() throws NieudanyPsikusException 
	{
		gra.hultajchochla(1);
		gra.hultajchochla(-1);
		gra.hultajchochla(9);
		gra.hultajchochla(-9);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void hultajchochla()
	{
		try {
			assertThat(gra.hultajchochla(12).toString(), RegexMatcher.matches("21"));
			assertThat(gra.hultajchochla(34).toString(), RegexMatcher.matches("43"));
			assertThat(gra.hultajchochla(82).toString(), RegexMatcher.matches("28"));
			
			//robimy kombinacje wszystkich liczb 3-liczbowych - jest ich 3! = 6
			assertThat(gra.hultajchochla(123).toString(), anyOf(
					RegexMatcher.matches("132"), 
					RegexMatcher.matches("312"), 
					RegexMatcher.matches("321"),
					RegexMatcher.matches("213"),
					RegexMatcher.matches("231")
					));
		} catch (NieudanyPsikusException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void nieksztaltek()
	{
		// 3=>8, 7=>1, 6=>9
		assertThat(gra.nieksztaltek(1).toString(), equalTo("1"));
		assertThat(gra.nieksztaltek(2).toString(), equalTo("2"));
		assertThat(gra.nieksztaltek(3).toString(), equalTo("8"));
		assertThat(gra.nieksztaltek(7).toString(), equalTo("1"));
		assertThat(gra.nieksztaltek(6).toString(), equalTo("9"));
		
		assertThat(gra.nieksztaltek(12).toString(), equalTo("12"));
		assertThat(gra.nieksztaltek(13).toString(), equalTo("18"));
		assertThat(gra.nieksztaltek(17).toString(), equalTo("11"));
		assertThat(gra.nieksztaltek(16).toString(), equalTo("19"));
		
		//assertThat(gra.nieksztaltek(128).toString(), equalTo("123"));
	}
	
}
