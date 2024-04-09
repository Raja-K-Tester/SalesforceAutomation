package campaign;

import java.time.Duration;
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

public class DeleteLeadForCampaign {

	// Create WebDriver interface and FirefoxDriver,ChromeDriver,Edge Driver classes
	// will implement the WebDriver interface and WebDriver focus on manipulating browser.
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

	public static void clickAppLauncherthenSalesDeleteContact(String name,String firstname,String lastname) throws Exception {
		WebElement AppLauncher, ViewAll, Sales, Campaigns, CampaignsLink,
		LeadsLink,ListView,Search,Actions,DeleteOption,DeleteButton,Message,ViewAllLink,
		Filters,FilterTypeContact,Apply;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		// click on App Launcher menu
		AppLauncher = driver.findElement(By.xpath("//button[contains(@class,'AppLauncherHeader')]"));
		wait.until(ExpectedConditions.visibilityOf(AppLauncher));
		Thread.sleep(3000);
		AppLauncher.click();
		Thread.sleep(3000);
		// click view all and then click Service Territories from app launcher
		ViewAll = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
		wait.until(ExpectedConditions.visibilityOf(ViewAll));
		ViewAll.click();
		Thread.sleep(5000);
		Sales = driver.findElement(By.xpath("//p[text()='Sales']"));
		Sales.click();
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		LeadsLink=driver.findElement(By.xpath("//a[@title='Leads']"));
		js.executeScript("arguments[0].click();", LeadsLink);
		Thread.sleep(3000);
		ListView=driver.findElement(By.xpath("//button[contains(@class,'ListView')]"));
		ListView.click();
		Search=driver.findElement(By.xpath("//input[@placeholder='Search this list...']"));
		Search.sendKeys(firstname+" "+lastname);
		Search.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		Actions=driver.findElement(By.xpath("//a[contains(@class,'rowActionsPlaceHolder')]"));
		Actions.click();
		DeleteOption=driver.findElement(By.xpath("//a[@data-target-selection-name='sfdc:StandardButton.Lead.Delete']"));
		DeleteOption.click();
		DeleteButton = driver.findElement(By.xpath("//button[@title='Delete']"));
		DeleteButton.click();
		Message = driver
				.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
		String resulttext = Message.getText();
		resulttext.trim();
		System.out.println(resulttext);
		Thread.sleep(5000);
		Search.clear();
		Search.sendKeys(firstname+" "+lastname);
		Search.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		Campaigns = driver.findElement(By.xpath("//a[@title='Campaigns']"));
		js.executeScript("arguments[0].click();", Campaigns);
		Thread.sleep(5000);
		CampaignsLink = driver.findElement(By.xpath("//a[@title='"+name+"']"));
		CampaignsLink.click();
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,500)");
		ViewAllLink=driver.findElement(By.xpath("(//span[@class='view-all-label'])[2]"));
		js.executeScript("arguments[0].click();", ViewAllLink);
		Thread.sleep(3000);
		Filters=driver.findElement(By.xpath("//button[@title='Show quick filters']"));
		Filters.click();
		FilterTypeContact=driver.findElement(By.xpath("(//label[@class='slds-checkbox__label']/span[@class='slds-checkbox_faux'])[3]"));
		FilterTypeContact.click();
		Apply=driver.findElement(By.xpath("//button[@title='Apply']"));
		Apply.click();
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
		clickAppLauncherthenSalesDeleteContact("Bootcamp","Raja","Krishnan");
		// closeBrowser();
	}

}
