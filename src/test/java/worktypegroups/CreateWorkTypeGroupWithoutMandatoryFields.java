package worktypegroups;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class CreateWorkTypeGroupWithoutMandatoryFields {

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
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

	public static void clickAppLauncherthenWorkTypeGroupsCreateWorkTypeGroupWithoutMandatoryFields(String description) throws Exception {
		WebElement AppLauncher, ViewAll, WorkTypeGroups,New, 
		WorkTypeGroupDescription,GroupTypeOption,GroupType,Save,
		CloseErrorDialog,VerifyAlert;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		// click on App Launcher menu
		AppLauncher = driver.findElement(By.xpath("//button[contains(@class,'AppLauncherHeader')]"));
		wait.until(ExpectedConditions.visibilityOf(AppLauncher));
		AppLauncher.click();
		// click view all and then click WorkTypeGroup from app launcher
		ViewAll = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
		ViewAll.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",
				driver.findElement(By.xpath("//a[@data-label='Work Type Groups']")));
		WorkTypeGroups = driver.findElement(By.xpath("//a[@data-label='Work Type Groups']"));
		js.executeScript("arguments[0].click();", WorkTypeGroups);
		Thread.sleep(5000);
		New = driver.findElement(By.xpath("//a[@title='New']"));
		New.click();
		Thread.sleep(3000);
		WorkTypeGroupDescription = driver
				.findElement(By.xpath("//textarea"));
		WorkTypeGroupDescription.sendKeys(description);
		GroupTypeOption=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.WorkTypeGroup.GroupType']//button"));
		GroupTypeOption.click();
		GroupType=driver.findElement(By.xpath("//span[@title='Capacity']/../.."));
		GroupType.click();
		// click save and verify Group name
		Save = driver.findElement(By.xpath("//button[@name='SaveEdit']"));
		Save.click();
		Thread.sleep(3000);
		CloseErrorDialog=driver.findElement(By.xpath("//button[@title='Close error dialog']"));
		CloseErrorDialog.click();
		Thread.sleep(3000);
		VerifyAlert = driver.findElement(By.xpath("//div[@class='slds-form-element__help']"));
		String alertmessage = VerifyAlert.getText();
		System.out.println(alertmessage+" displayed for work type group name ");

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
		clickAppLauncherthenWorkTypeGroupsCreateWorkTypeGroupWithoutMandatoryFields("Automation");
		// closeBrowser();
	}

}
