import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class Nieksztaltek 
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
	public void nieksztaltek()
	{
		// 3=>8, 7=>1, 6=>9
		assertThat(gra.nieksztaltek(1).toString(), equalTo("1"));
		assertThat(gra.nieksztaltek(2).toString(), equalTo("2"));
		//zalezy jaka liczbe wylosuje, bo moze wylosowac 3
		assertThat(gra.nieksztaltek(3).toString(), anyOf(
				equalTo("3"), 
				equalTo("8")
		));
		assertThat(gra.nieksztaltek(7).toString(), anyOf(
				equalTo("1"), 
				equalTo("7")
		));		
		assertThat(gra.nieksztaltek(6).toString(), anyOf(
				equalTo("6"), 
				equalTo("9")
		));	
				
		assertThat(gra.nieksztaltek(12).toString(), equalTo("12"));
		assertThat(gra.nieksztaltek(13).toString(), anyOf(
				equalTo("13"), 
				equalTo("18")
		));				
		
		assertThat(gra.nieksztaltek(17).toString(), anyOf(
				equalTo("17"), 
				equalTo("11")
		));	
		
		assertThat(gra.nieksztaltek(18).toString(), anyOf(
				equalTo("18")
		));	
		
		assertThat(gra.nieksztaltek(19).toString(), anyOf(
				equalTo("16"), 
				equalTo("19")
		));	
		
		assertThat(gra.nieksztaltek(123).toString(), anyOf(
				equalTo("123"), 
				equalTo("128")
		));	
		
		assertThat(gra.nieksztaltek(124).toString(), anyOf(
				equalTo("124")
		));	
		
		assertThat(gra.nieksztaltek(125).toString(), anyOf(
				equalTo("125")
		));	
		
		assertThat(gra.nieksztaltek(126).toString(), anyOf(
				equalTo("126"),
				equalTo("129")
		));	
		
		assertThat(gra.nieksztaltek(127).toString(), anyOf(
				equalTo("127"),
				equalTo("121")
		));	
		
		assertThat(gra.nieksztaltek(131).toString(), anyOf(
				equalTo("131"),
				equalTo("181")
		));	
		
		assertThat(gra.nieksztaltek(161).toString(), anyOf(
				equalTo("161"),
				equalTo("191")
		));	
		
		assertThat(gra.nieksztaltek(171).toString(), anyOf(
				equalTo("171"),
				equalTo("111")
		));	
		
		assertThat(gra.nieksztaltek(331).toString(), anyOf(
				equalTo("331"),
				equalTo("881")
		));	
		
		assertThat(gra.nieksztaltek(661).toString(), anyOf(
				equalTo("661"),
				equalTo("991")
		));	
		
		assertThat(gra.nieksztaltek(771).toString(), anyOf(
				equalTo("771"),
				equalTo("111")
		));	
		
		//teraz z dwoma liczbami bioracymi udzial w konwersji
		assertThat(gra.nieksztaltek(1362).toString(), anyOf(
				equalTo("1362"),
				equalTo("1862"),
				equalTo("1392")
		));	
		
		assertThat(gra.nieksztaltek(1372).toString(), anyOf(
				equalTo("1372"),
				equalTo("1872"),
				equalTo("1312")
		));	
		
		assertThat(gra.nieksztaltek(1362).toString(), anyOf(
				equalTo("1362"),
				equalTo("1392"),
				equalTo("1862")
		));	
		
		//teraz z trzema liczbami bioracymi udzial w konwersji
		assertThat(gra.nieksztaltek(13765).toString(), anyOf(
				//gdy 3 zostanie wylosowana
				equalTo("18765"),
				//gdy 7 zostanie wylosowana
				equalTo("13165"),
				//gdy 6 zostanie wylosowana
				equalTo("13795")
		));	
		
		//teraz z trzema liczbami bioracymi udzial w konwersji i powtarzajacymi sie
		
		assertThat(gra.nieksztaltek(3333).toString(), anyOf(
				equalTo("8888"),
				equalTo("3333")
		));	
		
		assertThat(gra.nieksztaltek(7777).toString(), anyOf(
				equalTo("7777"),
				equalTo("1111")
		));	
		
		assertThat(gra.nieksztaltek(6666).toString(), anyOf(
				equalTo("6666"),
				equalTo("9999")
		));	
		
		assertThat(gra.nieksztaltek(1376336973).toString(), anyOf(
				//gdy 3 zostanie wylosowana
				equalTo("1876886978"),
				//gdy 7 zostanie wylosowana
				equalTo("1316336913"),
				//gdy 6 zostanie wylosowana
				equalTo("1379339973")
		));	
		
		//------------------------------------------------------
		//Teraz to samo dla liczb ujemnych:
		
		assertThat(gra.nieksztaltek(-1).toString(), equalTo("-1"));
		assertThat(gra.nieksztaltek(-2).toString(), equalTo("-2"));
		//zalezy jaka liczbe wylosuje, bo moze wylosowac 3
		assertThat(gra.nieksztaltek(-3).toString(), anyOf(
				equalTo("-3"), 
				equalTo("-8")
		));
		assertThat(gra.nieksztaltek(-7).toString(), anyOf(
				equalTo("-1"), 
				equalTo("-7")
		));		
		assertThat(gra.nieksztaltek(-6).toString(), anyOf(
				equalTo("-6"), 
				equalTo("-9")
		));	
				
		assertThat(gra.nieksztaltek(-12).toString(), equalTo("-12"));
		assertThat(gra.nieksztaltek(-13).toString(), anyOf(
				equalTo("-13"), 
				equalTo("-18")
		));				
		
		assertThat(gra.nieksztaltek(-17).toString(), anyOf(
				equalTo("-17"), 
				equalTo("-11")
		));	
		
		assertThat(gra.nieksztaltek(-18).toString(), anyOf(
				equalTo("-18")
		));	
		
		assertThat(gra.nieksztaltek(-19).toString(), anyOf(
				equalTo("-16"), 
				equalTo("-19")
		));	
		
		assertThat(gra.nieksztaltek(-123).toString(), anyOf(
				equalTo("-123"), 
				equalTo("-128")
		));	
		
		assertThat(gra.nieksztaltek(-124).toString(), anyOf(
				equalTo("-124")
		));	
		
		assertThat(gra.nieksztaltek(-125).toString(), anyOf(
				equalTo("-125")
		));	
		
		assertThat(gra.nieksztaltek(-126).toString(), anyOf(
				equalTo("-126"),
				equalTo("-129")
		));	
		
		assertThat(gra.nieksztaltek(-127).toString(), anyOf(
				equalTo("-127"),
				equalTo("-121")
		));	
		
		assertThat(gra.nieksztaltek(-131).toString(), anyOf(
				equalTo("-131"),
				equalTo("-181")
		));	
		
		assertThat(gra.nieksztaltek(-161).toString(), anyOf(
				equalTo("-161"),
				equalTo("-191")
		));	
		
		assertThat(gra.nieksztaltek(-171).toString(), anyOf(
				equalTo("-171"),
				equalTo("-111")
		));	
		
		assertThat(gra.nieksztaltek(-331).toString(), anyOf(
				equalTo("-331"),
				equalTo("-881")
		));	
		
		assertThat(gra.nieksztaltek(-661).toString(), anyOf(
				equalTo("-661"),
				equalTo("-991")
		));	
		
		assertThat(gra.nieksztaltek(-771).toString(), anyOf(
				equalTo("-771"),
				equalTo("-111")
		));	
		
		//teraz z dwoma liczbami bioracymi udzial w konwersji
		assertThat(gra.nieksztaltek(-1362).toString(), anyOf(
				equalTo("-1362"),
				equalTo("-1862"),
				equalTo("-1392")
		));	
		
		assertThat(gra.nieksztaltek(-1372).toString(), anyOf(
				equalTo("-1372"),
				equalTo("-1872"),
				equalTo("-1312")
		));	
		
		assertThat(gra.nieksztaltek(-1372).toString(), anyOf(
				equalTo("-1372"),
				equalTo("-1872"),
				equalTo("-1312")
		));	
		
		//teraz z trzema liczbami bioracymi udzial w konwersji
		assertThat(gra.nieksztaltek(-13765).toString(), anyOf(
				//gdy 3 zostanie wylosowana
				equalTo("-18765"),
				//gdy 7 zostanie wylosowana
				equalTo("-13165"),
				//gdy 6 zostanie wylosowana
				equalTo("-13795")
		));	
		
		//teraz z trzema liczbami bioracymi udzial w konwersji i powtarzajacymi sie
		
		assertThat(gra.nieksztaltek(-3333).toString(), anyOf(
				equalTo("-8888"),
				equalTo("-3333")
		));	
		
		assertThat(gra.nieksztaltek(-7777).toString(), anyOf(
				equalTo("-7777"),
				equalTo("-1111")
		));	
		
		assertThat(gra.nieksztaltek(-6666).toString(), anyOf(
				equalTo("-6666"),
				equalTo("-9999")
		));	
		
		assertThat(gra.nieksztaltek(-1376336973).toString(), anyOf(
				//gdy 3 zostanie wylosowana
				equalTo("-1876886978"),
				//gdy 7 zostanie wylosowana
				equalTo("-1316336913"),
				//gdy 6 zostanie wylosowana
				equalTo("-1379339973")
		));	
	}
}
