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
		
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection(""));
		
		//moga tez istniec takie dziwne sytuacje z kropkami jak ponizej:
		//dlatego potrzebny jest warunek nowy.charAt(nowy.length() - 1) == '.'
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
		
		//Zakldam tez ze nie moze byc duzych liter w nazwie
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("Jan.kowalski.pl"));
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("jan.Kowalski.pl"));
		
		//na potrzeby funckji send (adres serwera przynajmniej 4-znakowy)
		assertEquals(ConnectionStatus.FAILURE, msi.checkConnection("w.pl"));
	}
	
	@Test
	public void sendtest()
	{
		try {
			//przypadek graniczny ale prawidlowy		
			assertEquals(SendingStatus.SENT, msi.send("wp.pl", "abc"));
			assertEquals(SendingStatus.SENT, msi.send("kowalski.wp.pl", "abcdef"));
		} catch (MalformedRecipientException e) {
			System.err.println("ADRES NIE MA PRZYNAJMNIEJ 4 ZNAKOW LUB KOMUNIKAT NIE MA PRZYNAJMNIEJ 3-ZNAKOW"
					+ "LUB NIE MA PODANEGO PRAWIDLOWEGO SERWERA");
			e.printStackTrace();
		}
	}
	
	@Test(expected=MalformedRecipientException.class)
	public void sendtestWithException() throws MalformedRecipientException
	{
		//bledny komunikat
		assertEquals(SendingStatus.SENDING_ERROR, msi.send("kowalski.wp.pl", ""));
		assertEquals(SendingStatus.SENDING_ERROR, msi.send("kowalski.wp.pl", "ab"));
		
		assertEquals(SendingStatus.SENDING_ERROR, msi.send("", ""));
		
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
		
		assertEquals(SendingStatus.SENDING_ERROR, msi.send("Jan.kowalski.pl", "def"));
		assertEquals(SendingStatus.SENDING_ERROR, msi.send("jan.Kowalski.pl", "def"));
		
		//na potrzeby funckji send (adres serwera przynajmniej 4-znakowy)
		assertEquals(SendingStatus.SENDING_ERROR, msi.send("w.pl", "def"));
	}
}
