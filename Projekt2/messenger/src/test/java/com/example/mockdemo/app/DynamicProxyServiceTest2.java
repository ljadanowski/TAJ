package com.example.mockdemo.app;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.example.mockdemo.app.DynamicProxyServiceTest.MessageServiceHandler;
import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;

public class DynamicProxyServiceTest2 
{
	InvocationHandler ih =  new MessageServiceHandler();
	MessageService ms = (MessageService) Proxy.newProxyInstance(
			MessageService.class.getClassLoader(),
			new Class[] { MessageService.class }, ih);
	
	Messenger myApp = new Messenger(ms);
	
	@Test
	public void connectiontest() 
	{
		assertEquals(0, myApp.testConnection("wp.pl"));
		assertEquals(0, myApp.testConnection("inf.ug.edu.pl"));
		assertEquals(0, myApp.testConnection("jan.kowalski.pl"));   
		assertEquals(0, myApp.testConnection("jan52.kowalski.pl"));  
		assertEquals(0, myApp.testConnection("jan.kowalski.pl"));  
	}
	
	@Test
	public void sendMessagetest() 
	{
		assertEquals(0, myApp.sendMessage("wp.pl", "abc"));
		assertEquals(0, myApp.sendMessage("kowalski.wp.pl", "abcdef"));
	}
	
	class MessageServiceHandler implements InvocationHandler 
	{
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
		{
			if("checkConnection".equals(method.getName()))
			{				
				return ConnectionStatus.SUCCESS;
			}
			else if("send".equals(method.getName()))
			{
				return SendingStatus.SENT;
			}
			return null;
		}
	}
}
