import java.util.Hashtable;

/*
 * Zad. 3. Napisz klas� LiczbaRzymska, kt�ra b�dzie posiada�a jedno prywatne pole zawieraj�ce liczb� 
 * (zainicjalizowane w konstruktorze) i metod� toString(), kt�ra b�dzie zwraca�a t� sam� liczb� zapisan� 
 * w rzymskim systemie zapisywania liczb. 
Przed implementacj� metody toString() zastan�w si� jak powinna zachowa� si� ta klasa w r�nych przypadkach 
np. gdy w konstruktorze podano liczb� ujemn�. 
Zaimplementuj klas� testuj�c� i odpowiednie przypadki testowe. W tym momencie testy oczywi�cie zako�cz� si� niepowodzeniem. 
Zaimplementuj metod� toString() i uruchom ponownie testy. 

http://www.diveintopython.net/unit_testing/romantest.html
 */
public class LiczbaRzymska 
{
	private String str_liczba = "";
	private int liczba;
	
	public LiczbaRzymska(int liczba)
	{
		this.liczba = liczba;
	}
	
	public String toString()
	{
		if(liczba<0)
		{
			throw new ArithmeticException("Liczba ujemna");
		}
		else if(liczba==0)
		{
			throw new ArithmeticException("Liczba rowna zero");
		}
		else if(liczba>4000)
		{
			throw new ArithmeticException("Zbyt duza liczba");
		}
		else 
		{
			//String str_liczba = "";
			Hashtable<Integer, String> h = new Hashtable<Integer, String>();
			h.put(1, "I");
			h.put(4, "IV");
			h.put(5, "V");
			h.put(9, "IX");
			h.put(10, "X");
			h.put(40, "XL");
			h.put(50, "L");
			h.put(90, "XC");
			h.put(100, "C");
			h.put(400, "CD");
			h.put(500, "D");
			h.put(900, "CM");
			h.put(1000, "M");

			int liczby[] = {1,4,5,9,10,40,50,90,100,400,500,900,1000};
			int dlugosc = liczby.length;
			
			for(int i=dlugosc-1; i>=0; i--)
			{
				if(liczba-liczby[i]>=0) 
				{
					str_liczba += h.get(liczby[i]);
					liczba-=liczby[i];
					i = dlugosc;
				}	
			}		
		}
		return str_liczba;
	}
	public static void main(String[] args)
	{
		System.out.println(new LiczbaRzymska(0).toString());
	}
}
