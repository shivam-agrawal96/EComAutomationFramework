package ecommerce.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ecommerce.abstractcomponents.AbstractComponents;

public class ViewCartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public ViewCartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".card-body")
	List<WebElement> allitems;
	
	@FindBy(xpath="//button[normalize-space()='Checkout']")
	WebElement checkoutPageButton;
	
	@FindBy(css = ".cartSection h3")
	WebElement itemSection;
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> itemsInCart;

	
	public boolean VerifyItems(String product)
	{
		VisibilityOfElement(itemSection);
		boolean itempresent = itemsInCart.stream().anyMatch(s->s.getText().equalsIgnoreCase(product));
		return itempresent;
	}
	
	public ChekoutPage gotoCheckoutPage()
	{
		checkoutPageButton.click();
		ChekoutPage checkout = new ChekoutPage(driver);
		return checkout;
	}
	

}
