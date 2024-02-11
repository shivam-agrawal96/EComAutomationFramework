package ecommerce.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.abstractcomponents.AbstractComponents;

public class ProductSelectionPage extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductSelectionPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".card-body")
	List<WebElement> allitems;
	
	By buttonLocator = By.cssSelector(".btn.w-10.rounded");
	
	@FindBy(css=".card-body")
	WebElement loader1;
	
	@FindBy(id = "toast-container")
	WebElement cartconfirmationbox;
	
	@FindBy(css=".ng-animating")
	WebElement wheelrunner;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartButton;

	
	public void selectItems(String product) throws InterruptedException
	{
		VisibilityOfElement(loader1);
		WebElement prod = allitems.stream().filter(s->s.findElement(By.tagName("b")).
				getText().equals(product)).findFirst().orElse(null);
		prod.findElement(buttonLocator).click();
		VisibilityOfElement(cartconfirmationbox);
		Thread.sleep(2000);
//		InvisibilityofElement(wheelrunner);
	}
	
	public ViewCartPage gotoCartPage()
	{
		cartButton.click();
		ViewCartPage viewcartpage = new ViewCartPage(driver);
		return viewcartpage;
	}
	

}
