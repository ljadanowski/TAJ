package com.projekt3;
import com.projekt3.pages.Contact;
import com.projekt3.pages.Home;
import com.projekt3.pages.Regulamin;

import org.jbehave.web.selenium.WebDriverProvider;

public class Pages 
{
private WebDriverProvider driverProvider;
	
	//Pages
	private Home home;
	private Contact contact;
	private Regulamin regulamin;
	// ...
	
	public Pages(WebDriverProvider driverProvider) 
	{
		super();
		this.driverProvider = driverProvider;
	}
	
	public Home home() 
	{
		if (home == null) {
			home = new Home(driverProvider);
		}
		return home;
	}
	
	public Contact contact() 
	{
		if (contact == null) {
			contact = new Contact(driverProvider);
		}
		return contact;
	}
	
	public Regulamin regulamin() 
	{
		if (regulamin == null) {
			regulamin = new Regulamin(driverProvider);
		}
		return regulamin;
	}
	
}
