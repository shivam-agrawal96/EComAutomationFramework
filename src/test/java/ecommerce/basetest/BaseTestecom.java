package ecommerce.basetest;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ecommerce.pageobjects.LandingPage;

public class BaseTestecom {
	
	public WebDriver driver;
	
	public WebDriver driverInitialization() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + 
				"\\src\\main\\java\\ecommerce\\resources\\GlobalData.properties");
		prop.load(fis);
//		System.getProperty("browser")
		String browserName = System.getProperty("Browser")!=null? System.getProperty("Browser"):prop.getProperty("Browser");
		if (browserName.equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver();
		}
		
		else if (browserName.equalsIgnoreCase("Firefox"))
		{
			driver = new FirefoxDriver();
		}
		
		else if (browserName.equalsIgnoreCase("Edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
//	@BeforeMethod
	public LandingPage launchApplication(String URL) throws IOException
	{
		driver = driverInitialization();
		LandingPage lp = new LandingPage(driver);
		lp.gotoURL(URL);
		return lp;
	}
	
	@AfterMethod(alwaysRun = true)
	public void quitter()
	{
		driver.quit();
	}
	
	public List<HashMap<String, String>> jsonDataReader(String filePath) throws IOException
	{
		File file = new File(filePath);
		@SuppressWarnings("deprecation")
		String jsonContent = FileUtils.readFileToString(file);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
	
	public String getScreenshot(String testName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir")+"\\screenshots\\" + testName + ".png"; 
		FileUtils.copyFile(file, new File(filePath)); 
		return filePath;
	}
	
}
