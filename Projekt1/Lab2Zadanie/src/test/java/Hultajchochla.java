import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


public class Hultajchochla 
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
	
	//testowanie wyjatkow
	@Test(expected = NieudanyPsikusException.class)
	public void hultajchochlaWithException() throws NieudanyPsikusException 
	{
		gra.hultajchochla(1);
		gra.hultajchochla(-1);
		gra.hultajchochla(9);
		gra.hultajchochla(-9);
	}
}
