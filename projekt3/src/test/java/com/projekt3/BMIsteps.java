package com.projekt3;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class BMIsteps 
{
	private final Pages pages;
	JavascriptExecutor executor;
	
	public BMIsteps(Pages pages) {
		this.pages = pages;
	}
	
	@Given("user is on Home page")
	public void userIsOnHomePage()
	{        
        pages.home().open();        
    }
	
	@When("user sets parameters mass 80 and height 190")
	public void userSetParametersCalculator()
	{        	       
	   pages.home().findElement(By.name("waga")).sendKeys("80");
	   pages.home().findElement(By.name("wzrost")).sendKeys("190");
	   //pages.home().findElement(By.id("Oblicz BMI")).click();
	}
	
    @When("user opens contact link")
    public void userClicksOnContactLink()
    {        
        pages.contact().findElement(By.linkText("Kontakt")).click();
    }
 
    @Then("contact page is shown")
    public void contactPageIsShown()
    {
       assertEquals("Kontakt z autorem BMI-online.PL", pages.home().getTitle());
    }	
    
    @When("user opens rules link")
    public void userClicksOnRegulaminLink()
    {        
        pages.regulamin().findElement(By.linkText("Regulamin")).click();
    }
    
    @Then("rules page is shown")
    public void regulaminPageIsShown()
    {
       assertEquals("Regulamin serwisu internetowego", pages.home().getTitle());
    }
    
}
