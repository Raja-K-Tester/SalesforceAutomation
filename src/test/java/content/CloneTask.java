package content;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CloneTask {

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

	public static void clickAppLauncherthenContentCloneTask(String listname,String listapiname) throws Exception {
		WebElement AppLauncher,ViewAll,Content,ViewAllTasks,SelectListDisplay,Table,TasksDropDown,
		OpenTasks,Settings,Clone,ListName,ListAPIName,SelectListViewRadioButton,Save,Subject,
		Input,SaveSubject,Name,Delete,SearchContact,SelectContact,SaveName,CloseFilter,
		VerifyOpenTasks,VerifySubject,VerifyName;
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		// click on App Launcher menu
		AppLauncher = driver.findElement(By.xpath("//button[contains(@class,'AppLauncherHeader')]"));
		wait.until(ExpectedConditions.visibilityOf(AppLauncher));
		Thread.sleep(3000);
		AppLauncher.click();
		Thread.sleep(3000);
		// click view all and then click Contents from app launcher
		ViewAll = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
		wait.until(ExpectedConditions.visibilityOf(ViewAll));
		ViewAll.click();
		Thread.sleep(5000);
		Content=driver.findElement(By.xpath("//p[text()='Content']"));
		Content.click();
		Thread.sleep(3000);
		ViewAllTasks=driver.findElement(By.xpath("//div[contains(@class,'TodayTask')]//a"));
		Actions action=new Actions(driver);
		action.scrollToElement(ViewAllTasks);
		ViewAllTasks.click();
		SelectListDisplay=driver.findElement(By.xpath("//button[@title='Select list display']"));
		wait.until(ExpectedConditions.visibilityOf(SelectListDisplay));
		SelectListDisplay.click();
		Table=driver.findElement(By.xpath("//li[@title='Display as table']/a"));
		wait.until(ExpectedConditions.visibilityOf(Table));
		Table.click();
		Thread.sleep(3000);
		TasksDropDown=driver.findElement(By.xpath("//button[@title='Select a List View: Tasks']"));
		TasksDropDown.click();
		OpenTasks=driver.findElement(By.xpath("//a/span[text()='Open Tasks']"));
		OpenTasks.click();
		Settings=driver.findElement(By.xpath("//button[@title='List View Controls']"));
		Settings.click();
		Clone=driver.findElement(By.xpath("//li[contains(@class,'Clone')]//a"));
		wait.until(ExpectedConditions.visibilityOf(Clone));
		Clone.click();
		ListName=driver.findElement(By.xpath("//input[@name='title']"));
		ListName.clear();
		ListName.sendKeys(listname);
		ListAPIName=driver.findElement(By.xpath("//input[@name='developerName']"));
		ListAPIName.clear();
		ListAPIName.sendKeys(listapiname);
		SelectListViewRadioButton=driver.findElement(By.xpath("//span[text()='All users can see this list view']/.."));
		SelectListViewRadioButton.click();
		Save=driver.findElement(By.xpath("//button[contains(@class,'confirm')]"));
		Save.click();
		Thread.sleep(5000);
		CloseFilter=driver.findElement(By.xpath("//button[@class='slds-button slds-button_icon slds-panel__close slds-button_icon-bare']"));
		CloseFilter.click();
		Subject=driver.findElement(By.xpath("//span[@class='slds-grid slds-grid--align-spread forceInlineEditCell']"));
		Actions actions=new Actions(driver);
		actions.moveToElement(Subject).doubleClick().perform();
		Input=driver.findElement(By.xpath("//input[@class='slds-combobox__input slds-input slds-combobox__input-value']"));
		wait.until(ExpectedConditions.visibilityOf(Input));
		Input.click();
		for(int i=5;i>=1;i--)
		{
			Input.sendKeys(Keys.BACK_SPACE);
		}
		
		Input.sendKeys("map");
		driver.findElement(By.xpath("//input[@name='Task-search-input']")).click();
		SaveSubject=driver.findElement(By.xpath("//button[contains(@class,'save')]"));
		SaveSubject.click();
		Thread.sleep(5000);
		Name=driver.findElement(By.xpath("//span[text()='Edit Name: Item ']/.."));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Name);
		Delete=driver.findElement(By.xpath("//span[@class='deleteIcon']"));
		Delete.click();
		SearchContact=driver.findElement(By.xpath("//input[@placeholder='Search Contacts...']"));
		SearchContact.click();
		SelectContact=driver.findElement(By.xpath("//div[@title='map']"));
		SelectContact.click();
		SaveName=driver.findElement(By.xpath("//button[contains(@class,'save')]"));
		SaveName.click();
		Thread.sleep(3000);
		TasksDropDown.click();
		VerifyOpenTasks=driver.findElement(By.xpath("//a/span[text()='Open Tasks']"));
		VerifyOpenTasks.click();
		VerifySubject=driver.findElement(By.xpath("//a[@title='map']"));
		wait.until(ExpectedConditions.visibilityOf(VerifySubject));
		String subject = VerifySubject.getText();
		System.out.println("Subject Verified : "+subject);
		VerifyName=driver.findElement(By.xpath("(//a[@title='map'])[2]"));
		String name = VerifyName.getText();
		System.out.println("Name Verified : "+name);
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
		clickAppLauncherthenContentCloneTask("New open tasks", "New_open_tasks");
	}

}
