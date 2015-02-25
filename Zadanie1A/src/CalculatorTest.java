import junit.framework.TestCase;

public class CalculatorTest extends TestCase{
	
	Calculator calculator;
	
	protected void setUp() throws Exception {
		 calculator = new Calculator();
	}

	protected void tearDown() throws Exception {
		calculator = null;
	}

	public void testAdd()
	{
		assertEquals(5, calculator.add(2, 3));
		assertEquals(1, calculator.add(-2, 3));
		assertEquals(-5, calculator.add(-2, -3));
	}

	public void testSub()
	{
		assertEquals(-1, calculator.sub(2, 3));
		assertEquals(-5, calculator.sub(-2, 3));
		assertEquals(1, calculator.sub(-2, -3));
	}
	
	public void testMulti()
	{
		assertEquals(6, calculator.multi(2, 3));
		assertEquals(-6, calculator.multi(-2, 3));
		assertEquals(6, calculator.multi(-2, -3));
	}
	
	public void testDiv()
	{
		assertEquals(0, calculator.div(2, 3));
		assertEquals(1, calculator.div(5, 3));
		assertEquals(2, calculator.div(6, 3));
		assertEquals(-2, calculator.div(6, -3));
		assertEquals(-1, calculator.div(5, -3));
	}
	//1B
	public void testDivByZero()
	{
		try {
			calculator.div(5,0);
		} catch(ArithmeticException e) {
			fail(e.toString());
		}
	}
}
