package task;

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

public class CreateTask {
	
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

	public static void clickAppLauncherthenTasksCreateNewTask(String subject, String firstname,
			String lastname) throws Exception {
		WebElement AppLauncher, ViewAll, Tasks, Actions, NewTask, ContactName, 
		NewContactName, FirstName, LastName, SaveContact,
		StatusOptions, Status, Subject, Save,SaveMessage,DisplayOption,TableView;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		// click on App Launcher menu
		AppLauncher = driver.findElement(By.xpath("//button[contains(@class,'AppLauncherHeader')]"));
		wait.until(ExpectedConditions.visibilityOf(AppLauncher));
		AppLauncher.click();
		// click view all and then click tasks from app launcher
		ViewAll = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
		ViewAll.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//a[@data-label='Tasks']")));
		Tasks = driver.findElement(By.xpath("//a[@data-label='Tasks']"));
		js.executeScript("arguments[0].click();", Tasks);
		Thread.sleep(5000);
		Actions=driver.findElement(By.xpath("//a[contains(@class,'slds-button slds-button--icon')]"));
		js.executeScript("arguments[0].click();", Actions);
		NewTask = driver.findElement(By.xpath("//a[@data-target-selection-name='sfdc:StandardButton.Task.NewTask']"));
		NewTask.click();
		Thread.sleep(5000);
		Subject = driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.Subject']//input"));
		Subject.sendKeys(subject);
		ContactName = driver.findElement(By.xpath("//input[@placeholder='Search Contacts...']"));
		ContactName.click();
		NewContactName = driver.findElement(By.xpath("//span[@title='New Contact']/.."));
		wait.until(ExpectedConditions.visibilityOf(NewContactName));
		NewContactName.click();
		FirstName = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
		FirstName.sendKeys(firstname);
		LastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
		LastName.sendKeys(lastname);
		SaveContact = driver.findElement(By.xpath("(//button[@title='Save']//span[@dir='ltr'])[2]"));
		//js.executeScript("arguments[0].click();", SaveContact);
		SaveContact.click();
		Thread.sleep(5000);
		StatusOptions = driver.findElement(By
				.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.Status']//a"));
		StatusOptions.click();
		Status = driver.findElement(By.xpath("//a[@title='Waiting on someone else']"));
		Status.click();
		Save = driver.findElement(By.xpath("//button[@title='Save']"));
		Save.click();
		Thread.sleep(5000);
		SaveMessage=driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
		String messagetext = SaveMessage.getText();
		System.out.println(messagetext);
		DisplayOption=driver.findElement(By.xpath("//button[@title='Select list display']"));
		DisplayOption.click();
		TableView=driver.findElement(By.xpath("//li[@title='Display as table']/a"));
		TableView.click();
		Thread.sleep(5000);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		invokeBrowser("chrome");
		browserSettings();
		navigateURL();
		// login("raja@credosystemz.sandbox", "wobble1960");
		login("krish.wobble@multiplier.sandbox", "Wobble1960$");
		clickAppLauncherthenTasksCreateNewTask("Bootcamp","Sarath", "M");
		// closeBrowser();
	}

}
