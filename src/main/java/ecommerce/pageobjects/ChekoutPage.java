package ecommerce.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ecommerce.abstractcomponents.AbstractComponents;

public class ChekoutPage extends AbstractComponents {
	
	WebDriver driver;
	
	public ChekoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".card-body")
	List<WebElement> allitems;
	
	By buttonLocator = By.cssSelector(".btn.w-10.rounded");
	
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement countryLoader;
	
	@FindBy(css = "button.ng-star-inserted:nth-child(3)")
	WebElement countrySelected;
	
	@FindBy(css = ".action__submit")
	WebElement chekcoutConfirmation;

	
	public void checkOut(String country) throws InterruptedException
	{
		VisibilityOfElement(countryLoader);
		countryLoader.sendKeys(country);
		countrySelected.click();
		JavascriptExecutor jsexecutor = (JavascriptExecutor)driver; 
		jsexecutor.executeScript("window.scrollBy(0,500);");
		Thread.sleep(500);
	}
	
	public ThankYouPage gotoThankyouPage()
	{
		Actions a = new Actions(driver);
		a.moveToElement(chekcoutConfirmation).doubleClick().build().perform();
		ThankYouPage thankyou = new ThankYouPage(driver);
		return thankyou;
	}
	

}
