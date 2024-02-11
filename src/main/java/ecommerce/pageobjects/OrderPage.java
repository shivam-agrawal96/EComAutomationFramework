package ecommerce.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ecommerce.abstractcomponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	WebElement Items;
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderPageItems;
	
	public boolean VerifyOrder(String product)
	{
		VisibilityOfElement(Items);
		boolean match = orderPageItems.stream().anyMatch(s->s.getText().equalsIgnoreCase(product));
		return match;
	}
	

}
