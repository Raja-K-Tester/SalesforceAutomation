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

public class CreateNewTask {

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
	
	public static void clickAppLauncherthenContentCreateNewTask(String name,String prodname) throws Exception
	{
		WebElement AppLauncher,ViewAll,Content,ViewAllTasks,Actions,NewTask,AccountName,
		SelectAccountName,SubjectOptions,SelectSubject,PriorityOptions,SelectPriority,
		StatusOptions,SetStatus,NameImage,SelectContactOption,SearchContacts,SelectContactName,
		RelatesToImage,ClickProducts,SearchProducts,CreateNewProduct,ProductName,SaveProduct,
		SaveTask,SaveMessage;
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
		Actions=driver.findElement(By.xpath("//li//a[contains(@class,'slds-button slds-button--icon')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Actions);
		NewTask = driver.findElement(By.xpath("//a[@data-target-selection-name='sfdc:StandardButton.Task.NewTask']"));
		NewTask.click();
		Thread.sleep(5000);
		AccountName=driver.findElement(By.xpath("//span[@class='deleteIcon']"));
		AccountName.click();
		SelectAccountName=driver.findElement(By.xpath("//div[@title='"+name+"']"));
		SelectAccountName.click();
		SubjectOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.Subject']//input"));
		SubjectOptions.click();
		SelectSubject=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Email']"));
		SelectSubject.click();
		PriorityOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.Priority']//a"));
		PriorityOptions.click();
		SelectPriority=driver.findElement(By.xpath("//a[@title='High']"));
		SelectPriority.click();
		StatusOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.Status']//a"));
		StatusOptions.click();
		SetStatus=driver.findElement(By.cssSelector("a[title^='In']"));
		SetStatus.click();
		NameImage=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.WhoId']//a[contains(@aria-label,'Name')]"));
		NameImage.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Contacts']")));
		SelectContactOption=driver.findElement(By.xpath("//a[@title='Contacts']"));
		SelectContactOption.click();
		SearchContacts=driver.findElement(By.cssSelector("input[title$='Contacts']"));
		SearchContacts.click();
		SelectContactName=driver.findElement(By.xpath("//input[contains(@title,'Contacts')]/..//a"));
		SelectContactName.click();
		RelatesToImage=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.WhatId']//a[contains(@aria-label,'Related')]"));
		RelatesToImage.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Products']")));
		ClickProducts=driver.findElement(By.xpath("//a[@title='Products']"));
		js.executeScript("arguments[0].scrollIntoView();",ClickProducts);
		ClickProducts.click();
		SearchProducts=driver.findElement(By.xpath("//input[@title='Search Products']"));
		SearchProducts.click();
		CreateNewProduct=driver.findElement(By.xpath("//span[text()='New Product']/.."));
		CreateNewProduct.click();
		ProductName=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Product2.Name']//input"));
		wait.until(ExpectedConditions.visibilityOf(ProductName));
		ProductName.sendKeys(prodname);
		SaveProduct=driver.findElement(By.xpath("//div[@class='forceModalActionContainer--footerAction forceModalActionContainer']//button[@title='Save']"));
		SaveProduct.click();
		Thread.sleep(5000);
		SaveTask=driver.findElement(By.xpath("//div[@class='actionsContainer']//button[@title='Save']"));
		wait.until(ExpectedConditions.visibilityOf(SaveTask));
		SaveTask.click();
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
		clickAppLauncherthenContentCreateNewTask("Raja K","SalesForceAutomation");
	}

}
