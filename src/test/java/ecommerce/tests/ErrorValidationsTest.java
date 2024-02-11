package ecommerce.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ecommerce.basetest.BaseTestecom;
import ecommerce.pageobjects.LandingPage;

public class ErrorValidationsTest extends BaseTestecom {
	
	String email = "shivam.agrawal@gmail.com";
	String password = "Imsa@2001";
	String product = "ADIDAS ORIGINAL";
	String ThankyouMessage = "THANKYOU FOR THE ORDER.";
	String URL = "https://rahulshettyacademy.com/client";
	String Country = "India";
	String loginValidationMessage = "Incorrect email or password.";

	@Test(groups = {"errorValidations"})
	public void loginValidation() throws InterruptedException, IOException
	{
		// TODO Auto-generated method stub

		LandingPage lp = launchApplication(URL);
		lp.login("shivam.agrawal@gmail.com", "Isa@2001");
		Assert.assertTrue(loginValidationMessage.equalsIgnoreCase(lp.getErrorMessage()));
//		Assert.assertTrue(false);
		
	}

}
