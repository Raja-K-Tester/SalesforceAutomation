package cases;

import java.time.Duration;
import java.util.List;

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

public class CreateNewCaseWithoutMandatoryFields {

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
	
	public static void clickAppLauncherthenCasesCreateNewCaseWithoutMandatoryFields(String firstname,String lastname,String subject,String description) throws Exception
	{
		WebElement AppLauncher, ViewAll,Cases,New,ContactName,
		NewContactName,FirstName,LastName,SaveContact,StatusOptions,Status,
		Subject,Description,Save,CloseErrorDialog;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		// click on App Launcher menu
		AppLauncher = driver.findElement(By.xpath("//button[contains(@class,'AppLauncherHeader')]"));
		wait.until(ExpectedConditions.visibilityOf(AppLauncher));
		AppLauncher.click();
		// click view all and then click cases from app launcher
		ViewAll = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
		ViewAll.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",
				driver.findElement(By.xpath("//a[@data-label='Cases']")));
		Cases=driver.findElement(By.xpath("//a[@data-label='Cases']"));
		js.executeScript("arguments[0].click();", Cases);
		Thread.sleep(5000);
		New=driver.findElement(By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Case.NewCase']"));
		New.click();
		Thread.sleep(5000);
		//CaseOriginOption=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Case.Origin']//button"));
		//CaseOriginOption.click();
		//CaseOrigin=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Email']"));
		//CaseOrigin.click();
		ContactName=driver.findElement(By.xpath("//input[@placeholder='Search Contacts...']"));
		ContactName.click();
		NewContactName=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='actionCreateNew']"));
		wait.until(ExpectedConditions.visibilityOf(NewContactName));
		NewContactName.click();
		FirstName=driver.findElement(By.xpath("//input[@placeholder='First Name']"));
		FirstName.sendKeys(firstname);
		LastName=driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
		LastName.sendKeys(lastname);
		SaveContact=driver.findElement(By.xpath("//button[@title='Save']"));
		SaveContact.click();
		Thread.sleep(5000);
		StatusOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Case.Status']//button[@role='combobox']"));
		StatusOptions.click();
		Status=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='']"));
		Status.click();
		Subject=driver.findElement(By.xpath("//input[@name='Subject']"));
		Subject.sendKeys(subject);
		Description=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Case.Description']//textarea"));
		Description.sendKeys(description);
		Save = driver.findElement(By.xpath("//button[@name='SaveEdit']"));
		Save.click();
		Thread.sleep(3000);
		CloseErrorDialog=driver.findElement(By.xpath("//button[@title='Close error dialog']"));
		Thread.sleep(3000);
		CloseErrorDialog.click();
		List<WebElement> VerifyMultipleAlerts = driver.findElements(By.xpath("//div[@class='slds-form-element__help']"));
		for (WebElement alert : VerifyMultipleAlerts) {
			String alertmessage = alert.getText();
			System.out.println(alertmessage);
		}
		
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		invokeBrowser("chrome");
		browserSettings();
		navigateURL();
		//login("raja@credosystemz.sandbox", "wobble1960");
		login("krish.wobble@multiplier.sandbox", "Wobble1960$");
		clickAppLauncherthenCasesCreateNewCaseWithoutMandatoryFields("Naveen","Elumalai","Testing","Automation testing");
		// closeBrowser();
	}

}
