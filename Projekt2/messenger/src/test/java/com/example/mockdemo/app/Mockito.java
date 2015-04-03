package com.example.mockdemo.app;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;

public class Mockito 
{
	private Messenger messenger;
	private MessageService mock;

	@Before
	public void setUp() throws Exception 
	{
		mock = mock(MessageService.class);
		messenger = new Messenger(mock);
	}

	@Test
	public void connectionNiePoprawnytest()
	{
		when(mock.checkConnection("")).thenReturn(ConnectionStatus.FAILURE);
		when(mock.checkConnection("jan.kowalski..pl")).thenReturn(ConnectionStatus.FAILURE);
		when(mock.checkConnection("jan.kowalski...pl")).thenReturn(ConnectionStatus.FAILURE);
		when(mock.checkConnection(".pl")).thenReturn(ConnectionStatus.FAILURE);
		when(mock.checkConnection("jan.kowalski.de")).thenReturn(ConnectionStatus.FAILURE);
		when(mock.checkConnection("wp.ru")).thenReturn(ConnectionStatus.FAILURE);
		when(mock.checkConnection("jan.kowalski")).thenReturn(ConnectionStatus.FAILURE);
		when(mock.checkConnection("kowalski")).thenReturn(ConnectionStatus.FAILURE);
		when(mock.checkConnection("kowalski.")).thenReturn(ConnectionStatus.FAILURE);
		when(mock.checkConnection("jan@kowalski.pl")).thenReturn(ConnectionStatus.FAILURE);
		when(mock.checkConnection("jan&kowalski.pl")).thenReturn(ConnectionStatus.FAILURE);
		when(mock.checkConnection("jan^kowalski.pl")).thenReturn(ConnectionStatus.FAILURE);
		when(mock.checkConnection("jan!kowalski.pl")).thenReturn(ConnectionStatus.FAILURE);
		when(mock.checkConnection("jan[kowalski.pl")).thenReturn(ConnectionStatus.FAILURE);
		
		assertEquals(1, messenger.testConnection(""));
		assertEquals(1, messenger.testConnection("jan.kowalski..pl"));
		assertEquals(1, messenger.testConnection("jan.kowalski...pl"));
		assertEquals(1, messenger.testConnection(".pl"));
		assertEquals(1, messenger.testConnection("jan.kowalski.de"));		
		assertEquals(1, messenger.testConnection("wp.ru"));
		assertEquals(1, messenger.testConnection("jan.kowalski"));
		assertEquals(1, messenger.testConnection("kowalski"));
		assertEquals(1, messenger.testConnection("kowalski.")); 
		assertEquals(1, messenger.testConnection("jan@kowalski.pl"));
		assertEquals(1, messenger.testConnection("jan&kowalski.pl"));
		assertEquals(1, messenger.testConnection("jan^kowalski.pl"));	
	}
	
	@Test
	public void connectionPoprawnytest() 
	{
		when(mock.checkConnection("wp.pl")).thenReturn(ConnectionStatus.SUCCESS);
		when(mock.checkConnection("inf.ug.edu.pl")).thenReturn(ConnectionStatus.SUCCESS);
		when(mock.checkConnection("jan.kowalski.pl")).thenReturn(ConnectionStatus.SUCCESS);
		when(mock.checkConnection("jan52.kowalski.pl")).thenReturn(ConnectionStatus.SUCCESS);
				
		assertEquals(0, messenger.testConnection("wp.pl"));
		assertEquals(0, messenger.testConnection("inf.ug.edu.pl"));
		assertEquals(0, messenger.testConnection("jan.kowalski.pl"));   
		assertEquals(0, messenger.testConnection("jan52.kowalski.pl"));  
	}
	
	@Test
	public void sendMessagePoprawnytest() 
	{

		try {
			when(mock.send("wp.pl", "abc")).thenReturn(SendingStatus.SENT);
			when(mock.send("kowalski.wp.pl", "abcdef")).thenReturn(SendingStatus.SENT);
		} catch (MalformedRecipientException e) {
			e.printStackTrace();
		}

		assertEquals(0, messenger.sendMessage("wp.pl", "abc"));
		assertEquals(0, messenger.sendMessage("kowalski.wp.pl", "abcdef"));
	}
	
	@Test//(expected=MalformedRecipientException.class)
	public void sendMessageNiePoprawnytest() 
	{	
		try {
			when(mock.send("", "")).thenThrow(new MalformedRecipientException());
			when(mock.send("kowalski.wp.pl", "")).thenThrow(new MalformedRecipientException());
			when(mock.send("kowalski.wp.pl", "ab")).thenThrow(new MalformedRecipientException());
			when(mock.send("jan.kowalski..pl", "def")).thenThrow(new MalformedRecipientException());
			when(mock.send("jan.kowalski...pl", "def")).thenThrow(new MalformedRecipientException());
			when(mock.send(".pl", "def")).thenThrow(new MalformedRecipientException());
			//na potrzeby funkcji send (adres serwera przynajmniej 4-znakowy)
			when(mock.send("w.pl", "def")).thenThrow(new MalformedRecipientException());
		} catch (MalformedRecipientException e) {
			fail("To nie powinno sie zdazyc!");
			e.printStackTrace();
		}
		
		assertEquals(2, messenger.sendMessage("", ""));
		//bledny komunikat
		assertEquals(2, messenger.sendMessage("kowalski.wp.pl", ""));
		assertEquals(2, messenger.sendMessage("kowalski.wp.pl", "ab"));
		assertEquals(2, messenger.sendMessage(".pl", "def"));
		assertEquals(2, messenger.sendMessage("jan.kowalski..pl", "def"));
		assertEquals(2, messenger.sendMessage("jan.kowalski...pl", "def"));
		//na potrzeby funkcji send (adres serwera przynajmniej 4-znakowy)
		assertEquals(2, messenger.sendMessage("w.pl", "def"));
	}
}
