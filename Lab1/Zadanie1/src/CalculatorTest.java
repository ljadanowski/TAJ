import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CalculatorTest {

	Calculator calculator;
	
	@Before
	public void setUp() throws Exception {
		calculator = new Calculator();
	}

	@After
	public void tearDown() throws Exception {
		calculator = null;
	}
	
	@Test
	public void testAdd()
	{
		assertEquals(5, calculator.add(2, 3));
		assertEquals(1, calculator.add(-2, 3));
		assertEquals(-5, calculator.add(-2, -3));
	}
	
	@Test
	public void testSub()
	{
		assertEquals(-1, calculator.sub(2, 3));
		assertEquals(-5, calculator.sub(-2, 3));
		assertEquals(1, calculator.sub(-2, -3));
	}
	
	@Test
	public void testMulti()
	{
		assertEquals(6, calculator.multi(2, 3));
		assertEquals(-6, calculator.multi(-2, 3));
		assertEquals(6, calculator.multi(-2, -3));
	}
	
	@Test
	public void testDiv()
	{
		assertEquals(0, calculator.div(2, 3));
		assertEquals(1, calculator.div(5, 3));
		assertEquals(2, calculator.div(6, 3));
		assertEquals(-2, calculator.div(6, -3));
		assertEquals(-1, calculator.div(5, -3));
	}
	
	//1B
	@Test(expected = java.lang.ArithmeticException.class)
	public void testDivByZero()
	{
		calculator.div(5,0);
	}
}
