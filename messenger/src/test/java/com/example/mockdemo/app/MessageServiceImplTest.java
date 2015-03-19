//Testy dla klasy bez u¿ycia dodatkowych technologii 

package com.example.mockdemo.app;
import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageServiceImpl;
import com.example.mockdemo.messenger.SendingStatus;

public class MessageServiceImplTest 
{
	MessageServiceImpl msi;

	@Before
	public void setUp() throws Exception 
	{
		msi = new MessageServiceImpl();
	}

	@After
	public void tearDown() throws Exception 
	{
		msi = null;
	}

	@Test
	public void checkConnectiontest() 
	{
		/*URL, np: wp.pl, inf.ug.edu.pl 
    	Uwaga: po³¹czenie koñczy siê sukcesem tylko dla adresów z poddomeny .pl	*/
		assertEquals(ConnectionStatus.SUCCESS, msi.checkConnection("wp.pl"));
		assertEquals(ConnectionStatus.SUCCESS, msi.checkConnection("inf.ug.edu.pl"));
		assertEquals(ConnectionStatus.SUCCESS, msi.checkConnection("jan.kowalski.pl"));   
		assertEquals(ConnectionStatus.SUCCESS, msi.checkConnection("jan52.kowalski.pl"));  
		assertEquals(ConnectionStatus.SUCCESS, msi.checkConnection("jan.kowalski.pl"));   
		
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan.kowalski..pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan.kowalski...pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection(".pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan.kowalski.de"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("wp.ru"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan.kowalski"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("kowalski"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("kowalski.")); 
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan@kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan&kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan^kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan%kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan$kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan!kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan(kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan)kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan?kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan<kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan>kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan\"kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan{kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan}kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan[kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan]kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan+kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("Jan+kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jAn+kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan+Kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan+koWalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan+kowalski.PL"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan+kowalski.Pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan+kowalski.pL"));
		
		//na potrzeby funckji send (adres serwera przynajmniej 4-znakowy)
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("w.pl"));
	}
	
	@Test
	public void sendtest()
	{
		/* Metoda u¿ywana do wysy³ania komunikatów do serwera.
	     Wymagamy aby adres serwera by³ przynajmniej 4 znakowy a komunikat
	     przynajmniej 3 znakowy, w przeciwnym wypadku metoda wyrzuca
	     MalformedRecipientException.
	     Zwraca statusy:
	     SendingStatus.SENT w przypadku powodzenia operacji
	     SendingStatus.SENDING_ERROR w przypadku b³êdu (przerwane po³¹czenie itp.
	     niezale¿ne b³êdy)
	     */
		try {
			//przypadek graniczny ale prawidlowy
			assertEquals(SendingStatus.SENT, msi.send("wp.pl", "abc"));
			assertEquals(SendingStatus.SENT, msi.send("kowalski.wp.pl", "abcdef"));
			
			//bledny komunikat
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("kowalski.wp.pl", ""));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("kowalski.wp.pl", "ab"));
			
			//tak naprawde jezeli sie nie polaczy to nic nie wysle...
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan.kowalski..pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan.kowalski...pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send(".pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan.kowalski.de", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("wp.ru", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan.kowalski", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("kowalski", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("kowalski.", "def")); 
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan@kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan&kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan^kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan%kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan$kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan!kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan(kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan)kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan?kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan<kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan>kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan\"kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan{kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan}kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan[kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan]kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan+kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("Jan+kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jAn+kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan+Kowalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan+koWalski.pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan+kowalski.PL", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan+kowalski.Pl", "def"));
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan+kowalski.pL", "def"));
			
			//na potrzeby funckji send (adres serwera przynajmniej 4-znakowy)
			assertEquals(SendingStatus.SENDING_ERROR, msi.send("w.pl", "def"));
		} catch (MalformedRecipientException e) {
			System.err.println("ADRES NIE MA PRZYNAJMNIEJ 4 ZNAKOW LUB KOMUNIKAT NIE MA PRZYNAJMNIEJ 3-ZNAKOW");
			e.printStackTrace();
		}
	}

}
