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
		assertEquals(1, myApp.testConnection(".pl"));
		assertEquals(1, myApp.testConnection("jan.kowalski.de"));		
		assertEquals(1, myApp.testConnection("wp.ru"));
		assertEquals(1, myApp.testConnection("jan.kowalski"));
		assertEquals(1, myApp.testConnection("kowalski"));
		assertEquals(1, myApp.testConnection("kowalski.")); 
		
		//Zakldam tez ze nie moze byc duzych liter w nazwie
		
		//na potrzeby funkcji send (adres serwera przynajmniej 4-znakowy)
		assertEquals(1, myApp.testConnection("w.pl"));
		
		assertEquals(1, myApp.testConnection(""));				
	}
	
	@Test
	public void sendMessagetest() 
	{	
//		assertEquals(2, myApp.sendMessage("", ""));
//		//bledny komunikat
//		assertEquals(2, myApp.sendMessage("kowalski.wp.pl", ""));
//		assertEquals(2, myApp.sendMessage("kowalski.wp.pl", "ab"));
//								
//		//tak naprawde jezeli sie nie polaczy to nic nie wysle...
//		assertEquals(2, myApp.sendMessage(".pl", "def"));
//		assertEquals(2, myApp.sendMessage("jan.kowalski.de", "def"));
//		assertEquals(2, myApp.sendMessage("wp.ru", "def"));
//		assertEquals(2, myApp.sendMessage("jan.kowalski", "def"));
//		assertEquals(2, myApp.sendMessage("kowalski.", "def")); 
//		
//		//na potrzeby funkcji send (adres serwera przynajmniej 4-znakowy)
//		assertEquals(2, myApp.sendMessage("w.pl", "def"));
	}
	
	class MessageServiceHandler implements InvocationHandler 
	{
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
		{
			if("checkConnection".equals(method.getName()))
			{
				//serwer z koncowka ".pl" i majacy dlugosc 4 badz wiecej liter
				if(args[0].toString().endsWith(".pl") && args[0].toString().length() >= 4)
					return ConnectionStatus.SUCCESS;
				else return ConnectionStatus.FAILURE;
			}
			else if("send".equals(method.getName()))
			{
				//adres serwera tez musi byc prawidlowy i dodatkowo dlugosc wiadomosci >= 3
				if(args[0].toString().endsWith(".pl") && args[0].toString().length() >= 4 && args[1].toString().length() >= 3)
					return SendingStatus.SENT;
				else 			
					throw new MalformedRecipientException();
			}
			return null;
		}
	}
}
