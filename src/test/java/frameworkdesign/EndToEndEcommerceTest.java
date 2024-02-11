package frameworkdesign;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EndToEndEcommerceTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String email = "shivam.agrawal@gmail.com";
		String password = "Imsa@2001";
		String product = "ADIDAS ORIGINAL";
		String ThankyouMessage = "THANKYOU FOR THE ORDER.";
		
		//WebDriver Settings
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		//Login
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.cssSelector("#login")).click();
		
		//Locating the items to add in cart and adding in cart
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".card-body"))));
		List<WebElement> productList = driver.findElements(By.cssSelector(".card-body"));
		WebElement prod = productList.stream().filter(s->s.findElement(By.tagName("b")).
				getText().equals(product)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".btn.w-10.rounded")).click();
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//Verify that item selected is present in cart on the cart page and clicking on checkout
		driver.findElement(By.xpath("(//button[@routerlink='/dashboard/cart'])")).click();
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".cartSection h3"))));
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean itempresent = cartItems.stream().anyMatch(s->s.getText().equalsIgnoreCase(product));
		Assert.assertTrue(itempresent);
		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
		
		//Selecting country on checkout page and proceeding further
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[placeholder='Select Country']"))));
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("India");
		driver.findElement(By.cssSelector("button.ng-star-inserted:nth-child(3)")).click();
		JavascriptExecutor jsexecutor = (JavascriptExecutor)driver; 
		jsexecutor.executeScript("window.scrollBy(0,500);");
		Thread.sleep(2000);
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).doubleClick().build().perform();
		
		//Verifying thank-you message
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".hero-primary"))));
		Assert.assertEquals(ThankyouMessage , driver.findElement(By.cssSelector(".hero-primary")).getText());
		System.out.println(driver.findElement(By.cssSelector(".hero-primary")).getText());
		
		driver.quit();

	}

}
