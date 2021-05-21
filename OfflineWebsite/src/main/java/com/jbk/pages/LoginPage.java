package com.jbk.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.jbk.objectRepository.LginPgObjRepo;
import com.jbk.testbase.TestBase;
import com.utility.Utility;

public class LoginPage extends LginPgObjRepo
{
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	//1
	public boolean url()
	{
		String expectedUrl = "file:///C:/Offline%20Website/index.html" ; 
		
		String actualUrl = Utility.getCurrentUrl(driver);
		log.info("Actual Url = " + actualUrl);
		
		if(actualUrl.equals(expectedUrl))
			return true ;
		else
			return false ;
	}
	
	//2
	public boolean title() 
	{ 	
		String expectedTitle = "JavaByKiran | Log in" ; 
		
		String actualTitle = Utility.getTitle(driver);
		log.info("Actual Title = " + actualTitle);
		
		if(actualTitle.equals(expectedTitle))
			return true ;
		else 
			return false ;	
	}
	
	//3
	public boolean logo() 
	{ 	
		if(Utility.displayed(logo)==true)
			return true ;
		else
			return false ;
	}
	
	//4
	public boolean mainHeading() 
	{ 	
		String actualHeading = Utility.getText(mainHeading);
		log.info("Actual Heading = " + actualHeading);
		
		String expectedHeading = "Java By Kiran" ;
		
		if (actualHeading.equals(expectedHeading))
			return true ;
		else
			return false ;
	}
	
	//5
	public boolean subHeading()
	{
		String actualSubHeading = Utility.getText(subHeading);
		log.info("Actual Subheading = " + actualSubHeading);
		
		String expectedSubHeading = "JAVA | SELENIUM | PYTHON" ;
		
		if (actualSubHeading.equals(expectedSubHeading))
			return true ;
		else
			return false ;
	}
	
	//6
	public boolean signInBoxHeading()
	{
		String actualSignInBoxHeading = Utility.getText(signInBoxHeading);
		log.info("Actual Sign-in box heading = " + actualSignInBoxHeading);
		
		String expectedSignInBoxHeading = "Sign in to start your session" ;
		
		if (actualSignInBoxHeading.equals(expectedSignInBoxHeading))
			return true ;
		else
			return false ;
	}
	
	//7
	public boolean placeholders()
	{	
		ArrayList <String>expPlaceholderText = new ArrayList <String>();
		expPlaceholderText.add("Email");
		expPlaceholderText.add("Password");
		
		ArrayList <String>actPlaceholderText = Utility.getPlaceholdersList(placeholdersList);
		log.info("List of Actual Placeholders = " + actPlaceholderText);
		
		if (actPlaceholderText.equals(expPlaceholderText))
			return true ;
		else
			return false ;
	}
	
	//8
	public boolean validLogIn() 
	{
		Utility.sendkeys(uname, "kiran@gmail.com");
		Utility.sendkeys(pass, "123456");
		Utility.click(lgnBtn);
		
		String expectedTitle = "JavaByKiran | Dashboard" ; 
		
		String actualTitle = Utility.getTitle(driver);
		log.info("Actual Title = " + actualTitle);
		
		Utility.click(logout);
		log.info("Clicked Logout Button");
		
		if (actualTitle.equals(expectedTitle))
			return true ;
		else
			return false ;
	}
	
	//9
	public boolean logoutSuccessfullyMessage()
	{
		String actMessage = Utility.getText(successfullyLgOutMsg);
		log.info("Actual Message = " + actMessage);
		
		String expMessage = "Logout successfully" ;
		
		if (actMessage.equals(expMessage))
			return true ;
		else
			return false ;
	}
	
	//10
	public boolean invalidLogIn() 
	{
		Utility.sendkeys(uname, "akshay@gmail.com");
		Utility.sendkeys(pass, "123456789");
		Utility.click(lgnBtn);
		
		String expectedTitle = "JavaByKiran | Log in" ; 
		
		String actualTitle = Utility.getTitle(driver);
		log.info("Actual Title = " + actualTitle);
		
		if (actualTitle.equals(expectedTitle))
			return true ;
		else
			return false ;
	}
	
	//11
	public boolean invalidLogInErrorMessage()
	{
		ArrayList <String>expErrorMessage = new ArrayList <String>();
		expErrorMessage.add("Please enter email as kiran@gmail.com");
		expErrorMessage.add("Please enter password 123456");
		
		ArrayList <String>actErrorMessage = Utility.getListOfElements(ErrorsList);
		log.info("List of Actual Error Messages = " + actErrorMessage);
		
		if (actErrorMessage.equals(expErrorMessage))
			return true ;
		else
			return false ;
	}
	
	//12
	public boolean blankLogInErrorMessage() 
	{
		Utility.clear(uname);
		Utility.clear(pass);
		Utility.click(lgnBtn);
		
		ArrayList <String>expErrorMessage = new ArrayList <String>();
		expErrorMessage.add("Please enter email.");
		expErrorMessage.add("Please enter password.");
		
		ArrayList <String>actErrorMessage = Utility.getListOfElements(ErrorsList);
		log.info("List of Actual Error Messages = " + actErrorMessage);
		
		if (actErrorMessage.equals(expErrorMessage))
			return true ;
		else
			return false ;
	}
	
	//13
	public boolean registerLinkText() 
	{ 
		String actualRegisterLinkText = Utility.getText(registerLink);
		log.info("Actual Register LinkText = " + actualRegisterLinkText);
		
		String expectedRegisterLinkText = "Register a new membership" ;
		
		if (actualRegisterLinkText.equals(expectedRegisterLinkText))
			return true ;
		else
			return false ;
	}
	
	//14
	public boolean registerLink() 
	{ 	
		Utility.click(registerLink);
		log.info("Clicked the Register Link");
	
		String expectedTitle = "JavaByKiran | Registration Page" ; 
		
		String actualTitle = Utility.getTitle(driver);
		log.info("Actual Title = " + actualTitle);
		
		Utility.click(alreadyMemberLink);
		
		if (actualTitle.equals(expectedTitle))
			return true ;
		else
			return false ;
	}
	
	//15
	public void loginWithAllData(HashMap<String,String> hm)
	{	
		Set<String> keys = hm.keySet();
		for(String key :keys)
		{
			Utility.sendkeys(uname, key);
			Utility.sendkeys(pass,hm.get(key));
			Utility.click(lgnBtn);
			
			if(Utility.getTitle(driver).contains("Dashboard"))
			{
				log.info("Valid Data = Username : "+ key + "    Password :" + hm.get(key));
				Utility.click(logout);
			}
			else
			{
				log.info("invalid Data = Username : "+ key + "    Password :" + hm.get(key));
				Utility.clear(uname);
				Utility.clear(pass);
			}
		}
	}
	
	public DashboardPage navigateToDashboardPg() 
	{
		Utility.sendkeys(uname, "kiran@gmail.com");
		Utility.sendkeys(pass, "123456");
		Utility.click(lgnBtn);
		/*driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();*/
		return new DashboardPage(driver);
	}
	
	public RegisterPage navigateToRegisterPg() 
	{
		Utility.click(registerLink);
		return new RegisterPage(driver);
	}
}