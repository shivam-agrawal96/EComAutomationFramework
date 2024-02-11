package ecommerce.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ecommerce.abstractcomponents.AbstractComponents;

public class ThankYouPage extends AbstractComponents {
	
	WebDriver driver;
	
	public ThankYouPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
	WebElement ThankyouText;

	
	public String getThankYouMessage() throws InterruptedException
	{
		VisibilityOfElement(ThankyouText);
		return ThankyouText.getText();
	}
	

}
