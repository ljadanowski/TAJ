import java.util.Hashtable;
import java.util.Random;


public class Gra implements Psikus 
{
	Random generator;
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

	public Integer hultajchochla(Integer liczba) throws NieudanyPsikusException 
	{
		String tekst = liczba.toString();
		int wygenerowana1, wygenerowana2;
		if( (tekst.charAt(0) == '-' && tekst.length() == 2) || tekst.length() == 1) 
			throw new NieudanyPsikusException();
		else 
		{
			if(tekst.charAt(0) == '-') 
			{
				StringBuilder a = new StringBuilder(tekst.substring(1));
				generator = new Random();
				do {
					wygenerowana1 = generator.nextInt(tekst.length());
					wygenerowana2 = generator.nextInt(tekst.length());
				} while(wygenerowana1 == wygenerowana2);
				
				char temp = tekst.charAt(wygenerowana2);
				a.setCharAt(wygenerowana2, tekst.charAt(wygenerowana1));
				a.setCharAt(wygenerowana1, temp);	
				return (-1)*Integer.parseInt(a.toString());
			}
			else
			{
				StringBuilder a = new StringBuilder(tekst);
				generator = new Random();
				do {
					wygenerowana1 = generator.nextInt(tekst.length());
					wygenerowana2 = generator.nextInt(tekst.length());
				} while(wygenerowana1 == wygenerowana2);
				char temp = tekst.charAt(wygenerowana2);
				a.setCharAt(wygenerowana2, tekst.charAt(wygenerowana1));
				a.setCharAt(wygenerowana1, temp);	
				return Integer.parseInt(a.toString());
			}
		}
	}

	public Integer nieksztaltek(Integer liczba) 
	{
		String tekst = liczba.toString();
		Hashtable<String, String> zamiana = new Hashtable<String, String>();
		generator = new Random();
		
		int wygenerowana = generator.nextInt(tekst.length());
		zamiana.put("3", "8");
		zamiana.put("7", "1");
		zamiana.put("6", "9");
		String klucze[] = {"3", "7", "6"};

		for(String i : klucze)
		{
			if(tekst.charAt(wygenerowana) == i.charAt(0))
			{
				String nowy = tekst.replace(tekst.charAt(wygenerowana), zamiana.get(i).charAt(0));
				return Integer.parseInt(nowy);
			}
		}
		return liczba;
	}
	
	public static void main(String[] args)
	{
		Gra gra = new Gra();
		System.out.println(gra.nieksztaltek(3));
	}
}
