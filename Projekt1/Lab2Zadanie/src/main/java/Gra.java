import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
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
		String nowy = "";
		Hashtable<Character, Character> zamiana = new Hashtable<Character, Character>();
		generator = new Random();

		zamiana.put('3', '8');
		zamiana.put('7', '1');
		zamiana.put('6', '9');
		char klucze[] = {'3', '7', '6'};
		
		if((liczba.toString().length() == 1) || (liczba.toString().charAt(0) == '-' && liczba.toString().length() == 2)) 
			return liczba;
		else 
		{
			int wygenerowana = generator.nextInt(zamiana.size());
			char wylosowana_litera = klucze[wygenerowana];
			for(int i=0; i<tekst.length(); i++)
			{
				if(tekst.charAt(i) == wylosowana_litera)
				{
					nowy = tekst.replace(tekst.charAt(i), zamiana.get(wylosowana_litera));
					if(nowy.charAt(0) == '-') 
					{
						nowy = nowy.substring(1);
						return -1 * Integer.parseInt(nowy);
					}
					else return Integer.parseInt(nowy);
				}	
			}
		}
		
		if(nowy.isEmpty()) return liczba;
		else return  Integer.parseInt(nowy);
	}
	
	public static void main(String[] args)
	{
		
	}
}
