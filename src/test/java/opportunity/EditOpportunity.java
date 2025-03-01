package opportunity;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditOpportunity {

	// TODO Auto-generated method stub

	// Create WebDriver interface and FirefoxDriver,ChromeDriver,Edge Driver classes
	// will implement the WebDriver interface and WebDriver focus on manipulating
	// browser.
	// Create global variable URL.
	public static WebDriver driver;
	public static String URL = "https://login.salesforce.com";
	public static String browser; // It can be chrome,firefox,edge,safari or opera

	public static String invokeBrowser(String browser) {

		switch (browser.toLowerCase()) {
		case "chrome":
			System.out.println("User Option is " + browser + " so invoking Chrome Browser");
			ChromeOptions ceoption = new ChromeOptions();
			ceoption.addArguments("--disable-notifications");
			driver = new ChromeDriver(ceoption);
			break;

		case "firefox":
			System.out.println("User Option is " + browser + " so invoking Firefox Browser");
			FirefoxOptions fxoption = new FirefoxOptions();
			fxoption.addArguments("--disable-notifications");
			driver = new FirefoxDriver(fxoption);
			break;

		case "edge":
			System.out.println("User Option is " + browser + " so invoking Edge Browser");
			EdgeOptions eeoption = new EdgeOptions();
			eeoption.addArguments("--disable-notifications");
			driver = new EdgeDriver(eeoption);
			break;

		default:
			System.out.println("User Option is invalid " + browser + " so invoking default Chrome Browser");
			driver = new ChromeDriver();
			break;

		}
		return browser;
	}

	public static void browserSettings() {
		// Maximize the Browser
		driver.manage().window().maximize();
		// Delete all the cookies
		driver.manage().deleteAllCookies();
	}

	public static void navigateURL() {
		// Navigate to the URL
		driver.get(URL);
		// Wait for the page to load
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		// Wait for the element to be located till the time we specified then throw
		// NoSuchElement Exception
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	public static void login(String uName, String pass) {
		// Login to https://login.salesforce.com using username and password
		WebElement username, password, login;
		username = driver.findElement(By.id("username"));
		username.sendKeys(uName);
		password = driver.findElement(By.id("password"));
		password.sendKeys(pass);
		login = driver.findElement(By.id("Login"));
		login.submit();
	}

	public static void clickAppLauncherthenSalesandEditSalesOpportunity(String name,String description) throws Exception {
		WebElement AppLauncher, ViewAll, Sales, Opportunity,Search,ActionsBox,Edit,Stage,
		PerceptionAnalysis,CloseDate,DeliveryStatus,InProgress,Description,Save;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		// click on App Launcher menu
		AppLauncher = driver.findElement(By.xpath("//button[contains(@class,'AppLauncherHeader')]"));
		wait.until(ExpectedConditions.visibilityOf(AppLauncher));
		AppLauncher.click();
		// click view all and then click sales from app launcher
		ViewAll = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
		ViewAll.click();
		Sales = driver.findElement(By.xpath("//p[text()='Sales']"));
		Sales.click();
		Thread.sleep(5000);
		// click on opportunity tab
		Opportunity = driver.findElement(By.xpath("//a[@title='Opportunities']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Opportunity);
		Search=driver.findElement(By.xpath("//input[@name='Opportunity-search-input']"));
		Search.sendKeys("Salesforce Automation by "+name);
		Search.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		ActionsBox=driver.findElement(By.xpath("//a[contains(@class,'slds-button slds-button--icon-x-small slds-button--icon-border-filled')]"));
		js.executeScript("arguments[0].click();", ActionsBox);
		Edit=driver.findElement(By.xpath("//a[@title='Edit']"));
		Edit.click();
		//Change CloseDate to tomorrow date
		String pattern = "dd/MM/yyyy"; // Define your desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1); // Add one day to today
        Date tomorrow = calendar.getTime();
        String tomorrowDate = dateFormat.format(tomorrow);
        CloseDate = driver.findElement(By.xpath("//input[@name='CloseDate']"));
        CloseDate.clear();
		CloseDate.sendKeys(tomorrowDate);
		Stage=driver.findElement(By.xpath("(//button[@class='slds-combobox__input slds-input_faux fix-slds-input_faux slds-combobox__input-value'])[3]"));
		Stage.click();
		PerceptionAnalysis=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Perception Analysis']"));
		js.executeScript("arguments[0].click();", PerceptionAnalysis);
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//textarea")));
		DeliveryStatus=driver.findElement(By.xpath("(//button[@class='slds-combobox__input slds-input_faux fix-slds-input_faux slds-combobox__input-value'])[5]"));
		DeliveryStatus.click();
		InProgress=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='In progress']"));
		js.executeScript("arguments[0].click();", InProgress);
		Description=driver.findElement(By.xpath("//textarea"));
		Description.sendKeys(description);
		// click save and verify opportunity name
		Save = driver.findElement(By.xpath("//button[@name='SaveEdit']"));
		Save.click();
		WebElement VerifyStage=driver.findElement(By.xpath("//span[text()='Perception Analysis']"));
		String resulttext = VerifyStage.getText();
		resulttext.trim();
		if(resulttext.contains("Perception Analysis"))
		{
			System.out.println("Both Stage Name are Same : "+resulttext);
		}
		
	  }

	public static void closeBrowser() {
		// Close the Browser
		driver.quit();
		// driver.close(); It will close the current focussed tab or window only
		// not close the browser driver
		// we don't know whether website has multiple tabs or windows present or not
		// so quit is used
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		invokeBrowser("chrome");
		browserSettings();
		navigateURL();
		login("raja@credosystemz.sandbox", "wobble1960");
		clickAppLauncherthenSalesandEditSalesOpportunity("Abhishek","SalesForce");
		// closeBrowser();
	}

}
