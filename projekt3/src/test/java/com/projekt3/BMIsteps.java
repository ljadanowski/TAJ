package com.projekt3;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class BMIsteps 
{
	private final Pages pages;
	
	public BMIsteps(Pages pages) {
		this.pages = pages;
	}
	
	@Given("user is on Home page")
	public void userIsOnHomePage()
	{        
        pages.home().open();        
    }
	
	@When("user sets parameters mass $a and height $b")
	public void userSetParametersCalculator(String a, String b)
	{
		//wypelnienie pol waga i wzrost
	   pages.home().findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]/div[2]/form/div[1]/input[1]")).sendKeys(a);
	   pages.home().findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]/div[2]/form/div[1]/input[3]")).sendKeys(b);
	   //klik na przyciksu Oblicz BMI
	   pages.home().findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]/div[2]/form/div[2]/input")).click();
	}
	
	@Then("I give $result")
	public void counted(double result)
	{
		WebElement element = pages.home().findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]/div[2]/div/p[1]/span"));
		double actual = Double.parseDouble(element.getText());
		assertEquals(result, actual, 0);
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
    
    @When("user not set parameters")
    public void notSetParameters()
    {
    	pages.home().findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]/div[2]/form/div[2]/input")).click();
    }
    @Then("get warning")
    public void getWarning()
    {
    	pages.home().manage().window();
    }
    //zobaczyc jak w c#, dodac jeszcze pare z wykorzystaniem getCurrentUrl itd.
}	
