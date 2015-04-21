package com.projekt3.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class Contact extends WebDriverPage 
{

	public Contact(WebDriverProvider driverProvider) 
	{
		super(driverProvider);
	}
	
	public void open() 
	{
		get("http://bmi-online.pl/kontakt");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}
