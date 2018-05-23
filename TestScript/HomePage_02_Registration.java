package com.aditya.homePage;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aditya.testBase.TestBase;
import com.aditya.testBase.TestEnvironment;
import com.aditya.testData.JsonReader;
import com.aditya.utility.AppConfig;
import com.aditya.utility.DropDownHelper;

public class HomePage_02_Registration extends TestEnvironment 
{	
	@DataProvider(name="registration data")
	public Object[][] passData() throws IOException, ParseException
	{
		//return JsonReader.getJSONdata(AppConfig.getJsonPath()+"Registration.json", "Registration Data",3);
		return JsonReader.getdata(AppConfig.getJsonPath()+"Registration.json", "Registration Data",2 , 12);
	}
	
	@Test(dataProvider = "registration data")
	public void userRegistration(String FirstName,String LastName, String phone, String email,String address,
			                     String city, String state, String postalCode, String country, String userId,
			                     String pwd, String confirmPwd) throws Exception
	{ 
		TestBase.loadPropertiesFile();
		TestBase.driver.navigate().to(AppConfig.getURL());
	    
		WebElement register = TestBase.getWebElement("RegisterButton");
	    JavascriptExecutor js = (JavascriptExecutor)TestBase.driver;
		js.executeScript("arguments[0].setAttribute('target','_self');",register); register.click(); 
	    
	    
	    TestBase.getWebElement("FName").sendKeys(FirstName);
	    TestBase.getWebElement("LName").sendKeys(LastName);
	    TestBase.getWebElement("Phone").sendKeys(phone);
	    TestBase.getWebElement("Email").sendKeys(email);
	    TestBase.getWebElement("Address").sendKeys(address);
	    TestBase.getWebElement("City").sendKeys(city);
	    TestBase.getWebElement("State").sendKeys(state);
	    TestBase.getWebElement("PostalCode").sendKeys(postalCode);
	    DropDownHelper.SelectUsingVisibleText(TestBase.getWebElement("Country"), country);
	    TestBase.getWebElement("UserID").sendKeys(userId);
	    TestBase.getWebElement("Password").sendKeys(pwd);
	    TestBase.getWebElement("ConfirmPassword").sendKeys(confirmPwd);
	    TestBase.getWebElement("Submit").click();
	    
	    Thread.sleep(2000);
	}
}
