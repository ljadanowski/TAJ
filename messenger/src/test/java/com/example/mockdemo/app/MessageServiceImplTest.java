//Testy dla klasy bez u¿ycia dodatkowych technologii 

package com.example.mockdemo.app;
import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MessageServiceImpl;

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
	}
	
	@Test
	public void sendtest() 
	{
		
	}

}
