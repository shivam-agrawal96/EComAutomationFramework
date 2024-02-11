package ecommerce.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecommerce.basetest.BaseTestecom;
import ecommerce.pageobjects.ChekoutPage;
import ecommerce.pageobjects.LandingPage;
import ecommerce.pageobjects.OrderPage;
import ecommerce.pageobjects.ProductSelectionPage;
import ecommerce.pageobjects.ThankYouPage;
import ecommerce.pageobjects.ViewCartPage;

public class SubmitOrderTest extends BaseTestecom {
	
//	String email = "shivam.agrawal@gmail.com";
//	String password = "Imsa@2001";
//	String product = "ADIDAS ORIGINAL";
//	String ThankyouMessage = "THANKYOU FOR THE ORDER.";
//	String URL = "https://rahulshettyacademy.com/client";
//	String Country = "India";

	@Test(dataProvider = "getData", groups = {"purchaseOrder"})
	public void submitOrderTest(HashMap<String,String> input) throws InterruptedException, IOException
	{
		// TODO Auto-generated method stub

		LandingPage lp = launchApplication(input.get("URL"));
		ProductSelectionPage productsPage = lp.login(input.get("email"), input.get("password"));
		
		productsPage.selectItems(input.get("product"));
		ViewCartPage viewcartpage = productsPage.gotoCartPage();
		Assert.assertTrue(viewcartpage.VerifyItems(input.get("product")));

		ChekoutPage checkout = viewcartpage.gotoCheckoutPage();
		checkout.checkOut(input.get("Country"));

		ThankYouPage thankyou = checkout.gotoThankyouPage();
		Assert.assertEquals(input.get("ThankyouMessage") , thankyou.getThankYouMessage());

	}
	
	@Test(dependsOnMethods = {"submitOrderTest"},dataProvider = "getData")
	public void orderVerificationTest(HashMap<String,String> input) throws IOException
	{
		LandingPage lp = launchApplication(input.get("URL"));
		lp.login(input.get("email"), input.get("password"));
		OrderPage op = lp.goToOrdersPage();
		Assert.assertTrue(op.VerifyOrder(input.get("product")));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data	= jsonDataReader(System.getProperty("user.dir")+
				"\\src\\main\\java\\ecommerce\\resources\\testdata.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	 
	
	
	
	
	
	
//	{
//		HashMap<Object,Object> hs = new HashMap<Object,Object>();
//		hs.put("em", "shivam.agrawal@gmail.com");
//		hs.put("pass", "Imsa@2001");
//		hs.put("prod", "ADIDAS ORIGINAL");
//		
//		HashMap<Object,Object> hs1 = new HashMap<Object,Object>();
//		hs1.put("em", "shivam.agrawal@gmail.com");
//		hs1.put("pass", "Imsa@2001");
//		hs1.put("prod", "ZARA COAT 3");
//		
//		Object[][] ob = {{hs}, {hs1}};
//		return ob;
//	}
	
	
//	{
//		Object[][] ob = {{"shivam.agrawal@gmail.com", "Imsa@2001","ADIDAS ORIGINAL"},
//				{"shivam.agrawal@gmail.com", "Imsa@2001", "ZARA COAT 3"}} ;
//		return ob;
//	}

}
