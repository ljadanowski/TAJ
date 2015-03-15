import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CalculatorTest 
{	
	Calculator calculator;
	
	@Before
	public void setUp() throws Exception 
	{
		calculator = new Calculator();
	}

	@After
	public void tearDown() throws Exception 
	{
		calculator = null;
	}

	@Test
	public void testAdd() 
	{
		assertEquals(5.3, calculator.add(2.1, 3.2), 0);
		assertEquals(1.1, calculator.add(-2.2, 3.3), 0);
		assertEquals(-5.0, calculator.add(-2.0,-3.0), 0);
		assertEquals(5.321, calculator.add(2.101, 3.22), 0);
		//To nie powinno byc rowne
		assertNotEquals(-5.00001, calculator.add(-2.0, -3.0), 0);
	}
	
	@Test
	public void testSub()
	{
		assertEquals(-1.0, calculator.sub(2.0, 3.0), 0);
		//Mozna tez podawac w nastepujacy sposob:
		assertEquals(-5, calculator.sub(-2, 3), 0);
		assertEquals(1, calculator.sub(-2, -3), 0);
		//Roznosc:
		assertNotEquals(1.000000001, calculator.sub(-2, -3), 0);
	}
	
	@Test
	public void testMulti()
	{
		assertEquals(6.72, calculator.multi(2.1, 3.2), 0);
		assertEquals(-6.72, calculator.multi(-2.1, 3.2), 0);
		assertEquals(6.72, calculator.multi(-2.1, -3.2), 0);
	}
	
	@Test
	public void testDiv()
	{
		assertEquals(0.5, calculator.div(1.0,2.0), 0);
		assertEquals(0.1, calculator.div(1.0, 10.0), 0);
		assertEquals(2, calculator.div(6.0, 3.0), 0);
		assertEquals(-2.0, calculator.div(6.0, -3.0), 0);
		assertEquals(-2.0, calculator.div(-6.0, 3.0), 0);
		assertEquals(2.0, calculator.div(-6.0, -3.0), 0);
		assertEquals(-0.2, calculator.div(1.0, -5.0), 0);
		assertEquals(0.2, calculator.div(-1.0, -5.0), 0);
		assertEquals(-1.0, calculator.div(5.0, -5.0), 0);
		assertEquals(-0.5, calculator.div(1.1,-2.2), 0);
	}
	
	@Test
	public void testGreater()
	{
		assertFalse(calculator.greater(120, 121));
		assertFalse(calculator.greater(120.99999999999, 121));
		assertFalse(calculator.greater(0.0005, 0.0005));
		assertFalse(calculator.greater(0.0005, 0.00051));
		assertFalse(calculator.greater(-0.00051, 0.0005));
		
		assertTrue(calculator.greater(1234567890, 123456789));
		assertTrue(calculator.greater(121, 120.9999999999999));
		assertTrue(calculator.greater(0.00051, 0.0005));
		assertTrue(calculator.greater(0.0005, -0.00051));
	}
	
	//obsluzenie wyjatku dzielenia przez 0
	@Test(expected = ArithmeticException.class)
	public void testDivByZero()
	{
		calculator.div(8.2, 0);
		calculator.div(5.2, 0.0);
		calculator.div(-5.2, 0.0);
	}
}
