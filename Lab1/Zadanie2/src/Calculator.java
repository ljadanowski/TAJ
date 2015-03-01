import java.math.BigDecimal;


public class Calculator {

	public double add(double a, double b)
	{
		return (BigDecimal.valueOf(a).add(BigDecimal.valueOf(b))).doubleValue();
	}
	
	public double sub(double a, double b)
	{
		return (BigDecimal.valueOf(a).subtract(BigDecimal.valueOf(b))).doubleValue();
	}
	
	public double multi(double a, double b)
	{
		return (BigDecimal.valueOf(a).multiply(BigDecimal.valueOf(b))).doubleValue();
	}
	
	public double div(double a, double b)
	{
		return (BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b))).doubleValue();
	}
	
	public boolean greater(double a, double b)
	{
		if(BigDecimal.valueOf(a).compareTo(BigDecimal.valueOf(b)) == 1) return true;
		else return false;
	}
}
