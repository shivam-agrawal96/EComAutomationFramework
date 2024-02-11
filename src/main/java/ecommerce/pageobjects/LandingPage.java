package ecommerce.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement pswd;
	
	@FindBy(css="#login")
	WebElement loginbutton;
	
	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement errorMessage;

	
	public ProductSelectionPage login(String useremail , String password)
	{
		email.sendKeys(useremail);
		pswd.sendKeys(password);
		loginbutton.click();
		ProductSelectionPage productsPage = new ProductSelectionPage(driver);
		return productsPage;
	}
	
	public void gotoURL(String URL)
	{
		driver.get(URL);
	}
	
	public String getErrorMessage()
	{
		return errorMessage.getText();
	}


}
