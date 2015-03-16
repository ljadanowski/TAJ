import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertNull;

import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

//Uwaga! Klasa ta zostala dodana dodatkowo zeby pokazac ze ten sie da wywolywac parametryczne testy.
//W Glownej klasie wywolujacej testy mozemy odkomentowywac i zakomentowywac, 
//w ten sposob kontrolujac co sie ma uruchomic. 
//Domyslnie ta klasa nie bedzie uruchamiana, bo by powielalo tylko testy.

@RunWith(Parameterized.class)
public class CyfrokradParameterizedtest 
{
	Gra gra;
	
	private Integer inputNumber;
	private String rexp;
	
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
	
	public CyfrokradParameterizedtest(Integer inputNumber, String rexp) 
	{
		this.inputNumber = inputNumber;
		this.rexp = rexp;
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> parametry() 
	{
	      return Arrays.asList(new Object[][] {
	         {12, "[0-9]"},
	         {-12, "-[0-9]"},
	         {123, "[0-9]{2}"},
	         {-123, "-[0-9]{2}"},
	         {1234, "[0-9]{3}"},
	         {-1234, "-[0-9]{3}"}
	      });
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void cyfrokradtest()
	{
		assertThat(gra.cyfrokrad(inputNumber).toString(), RegexMatcher.matches(rexp));
	}
	
	@Test
	public void cyfrokradnulltest()
	{
		assertNull(gra.cyfrokrad(1));
		assertNull(gra.cyfrokrad(-1));
		assertNull(gra.cyfrokrad(9));
		assertNull(gra.cyfrokrad(-9));
	}
}
