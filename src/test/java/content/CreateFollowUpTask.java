package content;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateFollowUpTask {

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

	public static void clickAppLauncherthenContentCreateNewFollowUpTask(String name,String contactname,String productname) throws Exception
	{
		WebElement AppLauncher,ViewAll,Content,ViewAllTasks,SelectListDisplay,Table,
		ShowActions,CreateFollowUpTask,AccountName,SelectAccountName,SubjectOptions,
		SelectSubject,Name,ContactName,RelatesTo,ProductName,PriorityOptions,Priority,
		StatusOptions,Status,Save,SaveMessage;
		
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
		ShowActions=driver.findElement(By.xpath("//a[contains(@class,'rowActionsPlaceHolder')]"));
		ShowActions.click();
		CreateFollowUpTask=driver.findElement(By.xpath("//a[@data-target-selection-name='sfdc:StandardButton.Task.FollowupEvent']"));
		wait.until(ExpectedConditions.visibilityOf(CreateFollowUpTask));
		CreateFollowUpTask.click();
		Thread.sleep(5000);
		AccountName=driver.findElement(By.xpath("//span[@class='deleteIcon']"));
		AccountName.click();
		SelectAccountName=driver.findElement(By.xpath("//div[@title='"+name+"']"));
		SelectAccountName.click();
		SubjectOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Event.Subject']//input"));
		SubjectOptions.click();
		SelectSubject=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Call']"));
		SelectSubject.click();
		JavascriptExecutor js=(JavascriptExecutor) driver;
		PriorityOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.Priority']//a"));
		js.executeScript("arguments[0].scrollIntoView();", PriorityOptions);
		PriorityOptions.click();
		Priority=driver.findElement(By.xpath("//a[@title='High']"));
		Priority.click();
		StatusOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.Status']//a"));
		js.executeScript("arguments[0].scrollIntoView();", StatusOptions);
		StatusOptions.click();
		Status=driver.findElement(By.xpath("//a[@title='In Progress']"));
		Status.click();
		Name=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Event.WhoId']//a[@class='deleteAction']"));
		Name.click();
		ContactName=driver.findElement(By.xpath("//div[@title='"+contactname+"']"));
		ContactName.click();
		RelatesTo=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Event.WhatId']//a[@class='deleteAction']"));
		RelatesTo.click();
		ProductName=driver.findElement(By.xpath("//div[@title='"+productname+"']"));
		ProductName.click();
		Save=driver.findElement(By.xpath("//button[@title='Save']"));
        Save.click();
        SaveMessage=driver
				.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
		String resulttext = SaveMessage.getText();
		resulttext.trim();
		System.out.println(resulttext);
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
		clickAppLauncherthenContentCreateNewFollowUpTask("Raja K","Raja","SalesForceAutomation");
	}

}
