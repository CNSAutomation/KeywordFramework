package methods;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KeyWordExample {
	static WebDriver driver;
	static WebDriverWait wait;

	public void open_Browser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver","C:/BrowserDriver/geckodriver-v0.13.0-win64/geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:/BrowserDriver/chromedriver_win32/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						"D:/Jars/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
	}

	public void enter_URL(String URL) {
		driver.navigate().to(URL);
	}

	public By locatorValue(String locatorTpye, String value) throws InterruptedException {
		By by;
		switch (locatorTpye) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "css":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			Thread.sleep(2000);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		case "innertext":
			by = By.xpath("//*[text()='"+value+"']");
			break;
		default:
			by = null;
			break;
		}
		return by;
	}

	public void enter_Text(String locatorType, String value, String text) throws InterruptedException {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			System.out.println("Enter Text :-"+text);
			element.sendKeys(text);
			Thread.sleep(5000);
			
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
	}

	public void click_On_Link(String locatorType, String value) throws InterruptedException {
		try {
			System.out.println("Now click on value :-"+value);
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.click();
			Thread.sleep(5000);
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
	}

	public void click_On_Button(String locatorType, String value) throws InterruptedException {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.click();
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to perform click" + e);
		}
	}
	public void select_List(String locatorType,String value, String option) throws Exception
		{
	    	try{
	    		By locator;
	    		locator = locatorValue(locatorType, value);
	    		WebElement element = driver.findElement(locator);
				element.click();
	    		Select select = new Select(driver.findElement(locator));
	    		select.selectByVisibleText(option);
	    		Thread.sleep(3000);
	    	}catch(NoSuchElementException e){
	    		System.err.format("No Element Found to perform click" + e);
	    	}
	    }
	
	public void close_Browser() {
		driver.quit();
	}

}
