import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertNull;

public class Cyfrokradtest 
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
}
