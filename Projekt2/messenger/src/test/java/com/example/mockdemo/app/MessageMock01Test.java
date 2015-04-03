package com.example.mockdemo.app;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;

public class MessageMock01Test {
	
	// SUT
	private Messenger messenger;
	private MessageService mock;
	
	@Before
	public void setUp() 
	{
		mock = createMock(MessageService.class);
		messenger = new Messenger(mock);
	}
	@Test
	public void connectionNiePoprawnytest() 
	{
		expect(mock.checkConnection("")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		expect(mock.checkConnection("jan.kowalski..pl")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		expect(mock.checkConnection("jan.kowalski...pl")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		expect(mock.checkConnection(".pl")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		expect(mock.checkConnection("jan.kowalski.de")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		expect(mock.checkConnection("wp.ru")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		expect(mock.checkConnection("jan.kowalski")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		expect(mock.checkConnection("kowalski")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		expect(mock.checkConnection("kowalski.")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		expect(mock.checkConnection("jan@kowalski.pl")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		expect(mock.checkConnection("jan&kowalski.pl")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		expect(mock.checkConnection("jan^kowalski.pl")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		expect(mock.checkConnection("jan!kowalski.pl")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		expect(mock.checkConnection("jan[kowalski.pl")).andReturn(ConnectionStatus.FAILURE).anyTimes();
		
		replay(mock);
		
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
		
		verify(mock);
	}
	
	@Test
	public void connectionPoprawnytest() 
	{
		expect(mock.checkConnection("wp.pl")).andReturn(ConnectionStatus.SUCCESS).anyTimes();
		expect(mock.checkConnection("inf.ug.edu.pl")).andReturn(ConnectionStatus.SUCCESS).anyTimes();
		expect(mock.checkConnection("jan.kowalski.pl")).andReturn(ConnectionStatus.SUCCESS).anyTimes();
		expect(mock.checkConnection("jan52.kowalski.pl")).andReturn(ConnectionStatus.SUCCESS).anyTimes();
		
		replay(mock);
		
		assertEquals(0, messenger.testConnection("wp.pl"));
		assertEquals(0, messenger.testConnection("inf.ug.edu.pl"));
		assertEquals(0, messenger.testConnection("jan.kowalski.pl"));   
		assertEquals(0, messenger.testConnection("jan52.kowalski.pl"));  
		
		verify(mock);
	}
	
	@Test
	public void sendMessagePoprawnytest() 
	{

		try {
			expect(mock.send("wp.pl", "abc")).andReturn(SendingStatus.SENT).anyTimes();
			expect(mock.send("kowalski.wp.pl", "abcdef")).andReturn(SendingStatus.SENT).anyTimes();
		} catch (MalformedRecipientException e) {
			e.printStackTrace();
		}
		
		replay(mock);
		
		assertEquals(0, messenger.sendMessage("wp.pl", "abc"));
		assertEquals(0, messenger.sendMessage("kowalski.wp.pl", "abcdef"));
		
		verify(mock);
	}
	
	@Test//(expected=MalformedRecipientException.class)
	public void sendMessageNiePoprawnytest() 
	{	
		try {
			expect(mock.send("", "")).andThrow(new MalformedRecipientException()).anyTimes();
			expect(mock.send("kowalski.wp.pl", "")).andThrow(new MalformedRecipientException()).anyTimes();
			expect(mock.send("kowalski.wp.pl", "ab")).andThrow(new MalformedRecipientException()).anyTimes();
			expect(mock.send("jan.kowalski..pl", "def")).andThrow(new MalformedRecipientException()).anyTimes();
			expect(mock.send("jan.kowalski...pl", "def")).andThrow(new MalformedRecipientException()).anyTimes();
			expect(mock.send(".pl", "def")).andThrow(new MalformedRecipientException()).anyTimes();
			//na potrzeby funkcji send (adres serwera przynajmniej 4-znakowy)
			expect(mock.send("w.pl", "def")).andThrow(new MalformedRecipientException()).anyTimes();
		} catch (MalformedRecipientException e) {
			e.printStackTrace();
		}
		
		replay(mock);
		
		assertEquals(2, messenger.sendMessage("", ""));
		//bledny komunikat
		assertEquals(2, messenger.sendMessage("kowalski.wp.pl", ""));
		assertEquals(2, messenger.sendMessage("kowalski.wp.pl", "ab"));
		assertEquals(2, messenger.sendMessage(".pl", "def"));
		assertEquals(2, messenger.sendMessage("jan.kowalski..pl", "def"));
		assertEquals(2, messenger.sendMessage("jan.kowalski...pl", "def"));
		//na potrzeby funkcji send (adres serwera przynajmniej 4-znakowy)
		assertEquals(2, messenger.sendMessage("w.pl", "def"));
		
		verify(mock);
	}
}
