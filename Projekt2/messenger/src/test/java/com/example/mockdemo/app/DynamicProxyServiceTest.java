package com.example.mockdemo.app;

import static org.junit.Assert.*;

import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.SendingStatus;

import org.junit.Test;

import com.example.mockdemo.messenger.MessageService;

public class DynamicProxyServiceTest 
{
	InvocationHandler ih =  new MessageServiceHandler();
	MessageService ms = (MessageService) Proxy.newProxyInstance(
			MessageService.class.getClassLoader(),
			new Class[] { MessageService.class }, ih);
	
	Messenger myApp = new Messenger(ms);
	
	@Test
	public void checkConnectiontest() 
	{	
		assertEquals(1, myApp.testConnection("jan.kowalski..pl"));
		assertEquals(1, myApp.testConnection("jan.kowalski...pl"));
		assertEquals(1, myApp.testConnection(".pl"));
		assertEquals(1, myApp.testConnection("jan.kowalski.de"));		
		assertEquals(1, myApp.testConnection("wp.ru"));
		assertEquals(1, myApp.testConnection("jan.kowalski"));
		assertEquals(1, myApp.testConnection("kowalski"));
		assertEquals(1, myApp.testConnection("kowalski.")); 
		assertEquals(1, myApp.testConnection("jan@kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan&kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan^kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan%kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan$kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan!kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan(kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan)kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan?kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan<kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan>kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan\"kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan{kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan}kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan[kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan]kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan+kowalski.pl"));
		assertEquals(1, myApp.testConnection("Jan+kowalski.pl"));
		assertEquals(1, myApp.testConnection("jAn+kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan+Kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan+koWalski.pl"));
		assertEquals(1, myApp.testConnection("jan+kowalski.PL"));
		assertEquals(1, myApp.testConnection("jan+kowalski.Pl"));
		assertEquals(1, myApp.testConnection("jan+kowalski.pL"));
		
		//Zakldam tez ze nie moze byc duzych liter w nazwie
		assertEquals(1, myApp.testConnection("Jan.kowalski.pl"));
		assertEquals(1, myApp.testConnection("jan.Kowalski.pl"));
		
		//na potrzeby funkcji send (adres serwera przynajmniej 4-znakowy)
		assertEquals(1, myApp.testConnection("w.pl"));
		
		assertEquals(1, myApp.testConnection(""));				
	}
	
	@Test
	public void sendMessagetest() 
	{	
		assertEquals(2, myApp.sendMessage("", ""));
		//bledny komunikat
		assertEquals(2, myApp.sendMessage("kowalski.wp.pl", ""));
		assertEquals(2, myApp.sendMessage("kowalski.wp.pl", "ab"));
				
		assertEquals(2, myApp.sendMessage("", ""));
				
		//tak naprawde jezeli sie nie polaczy to nic nie wysle...
		assertEquals(2, myApp.sendMessage("jan.kowalski..pl", "def"));
		assertEquals(2, myApp.sendMessage("jan.kowalski...pl", "def"));
		assertEquals(2, myApp.sendMessage(".pl", "def"));
		assertEquals(2, myApp.sendMessage("jan.kowalski.de", "def"));
		assertEquals(2, myApp.sendMessage("wp.ru", "def"));
		assertEquals(2, myApp.sendMessage("jan.kowalski", "def"));
		assertEquals(2, myApp.sendMessage("kowalski", "def"));
		assertEquals(2, myApp.sendMessage("kowalski.", "def")); 
		assertEquals(2, myApp.sendMessage("jan@kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan&kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan^kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan%kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan$kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan!kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan(kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan)kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan?kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan<kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan>kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan\"kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan{kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan}kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan[kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan]kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan+kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("Jan+kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jAn+kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan+Kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan+koWalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan+kowalski.PL", "def"));
		assertEquals(2, myApp.sendMessage("jan+kowalski.Pl", "def"));
		assertEquals(2, myApp.sendMessage("jan+kowalski.pL", "def"));
		
		assertEquals(2, myApp.sendMessage("Jan.kowalski.pl", "def"));
		assertEquals(2, myApp.sendMessage("jan.Kowalski.pl", "def"));
		
		//na potrzeby funckji send (adres serwera przynajmniej 4-znakowy)
		assertEquals(2, myApp.sendMessage("w.pl", "def"));
	}
	
	class MessageServiceHandler implements InvocationHandler 
	{
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
		{
			if("checkConnection".equals(method.getName()))
			{				
				return ConnectionStatus.FAILURE;
			}
			else if("send".equals(method.getName()))
			{
				throw new MalformedRecipientException();
			}
			return null;
		}
	}
}
