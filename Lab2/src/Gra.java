import java.util.Hashtable;
import java.util.Random;


public class Gra implements Psikus 
{
	Random generator;
	
	@Override
	public Integer cyfrokrad(Integer liczba) 
	{
		if(liczba.toString().charAt(0) == '-')
		{
			if(liczba.toString().substring(1).length() == 1) return null;
			else 
			{
				generator = new Random();
				String tekst = liczba.toString().substring(1);
				int dlugosc = tekst.length();
				StringBuilder sb = new StringBuilder(tekst);
				int wygenerowana = generator.nextInt(dlugosc);
				sb.deleteCharAt(wygenerowana);
				return (-1)*Integer.parseInt(sb.toString());
			}
		}
		else if(liczba.toString().length() == 1) return null;
		else 
		{
			generator = new Random();
			String tekst = liczba.toString();
			int dlugosc = tekst.length();
			StringBuilder sb = new StringBuilder(tekst);
			int wygenerowana = generator.nextInt(dlugosc);
			sb.deleteCharAt(wygenerowana);
			return Integer.parseInt(sb.toString());
		}
	}
	@Override
	public Integer hultajchochla(Integer liczba) throws NieudanyPsikusException 
	{
		String tekst = liczba.toString();
		if(tekst.length() == 1) throw new NieudanyPsikusException();
		else 
		{
			StringBuilder a = new StringBuilder(tekst);
			generator = new Random();
			int wygenerowana1 = generator.nextInt(tekst.length());
			int wygenerowana2 = generator.nextInt(tekst.length());
			char temp = tekst.charAt(wygenerowana2);
			a.setCharAt(wygenerowana2, tekst.charAt(wygenerowana1));
			a.setCharAt(wygenerowana1, temp);	
			return Integer.parseInt(a.toString());
		}
	}
	@Override
	public Integer nieksztaltek(Integer liczba) 
	{
		String tekst = liczba.toString();
		Hashtable<String, String> zamiana = new Hashtable<String, String>();
		generator = new Random();
		
		int wygenerowana = generator.nextInt(3);
		zamiana.put("3", "8");
		zamiana.put("7", "1");
		zamiana.put("6", "9");
		String klucze[] = {"3", "7", "6"};
		
		String b = zamiana.get(klucze[wygenerowana]);
		
		if(tekst.indexOf(klucze[wygenerowana]) == -1) return liczba;
		else return Integer.parseInt(tekst.replace(klucze[wygenerowana], b));
	}
	public static void main(String[] args) 
	{

	}
}
