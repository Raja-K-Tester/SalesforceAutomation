package accounts;

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

public class EditAccount {

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

	public static void clickAppLauncherthenSalesGotoAccountsandEditAccount(String name,String Billingaddress,String Shippingaddress,String phone) throws Exception {
		WebElement AppLauncher, ViewAll, Sales, Accounts,Search,ActionsBox,Edit,TypeOptions,
		Type,IndustryOptions,Industry,BillingAddress,ShippingAddress,PriorityOptions,Priority,
		SLAOptions,SLA,ActiveOptions,Active,Phone,UpsellOpportunityOptions,UpsellOpportunity,
		Save,VerifyPhone;
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
		// click on accounts tab
		Accounts = driver.findElement(By.xpath("//a[@title='Accounts']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Accounts);
		Search=driver.findElement(By.xpath("//input[@name='Account-search-input']"));
		Search.sendKeys(name);
		Search.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		ActionsBox=driver.findElement(By.xpath("//a[contains(@class,'slds-button slds-button--icon-x-small slds-button--icon-border-filled')]"));
		js.executeScript("arguments[0].click();", ActionsBox);
		Edit=driver.findElement(By.xpath("//a[@title='Edit']"));
		Edit.click();
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.Type']//button")));
		TypeOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.Type']//button"));
		TypeOptions.click();
		Type=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Technology Partner']"));
		Type.click();
		IndustryOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.Industry']//button"));
		IndustryOptions.click();
		Industry=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Healthcare']"));
		Industry.click();
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.BillingAddress']//textarea")));
		BillingAddress=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.BillingAddress']//textarea"));
		BillingAddress.sendKeys(Billingaddress);
		ShippingAddress=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.ShippingAddress']//textarea"));
		ShippingAddress.sendKeys(Shippingaddress);
		PriorityOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.CustomerPriority__c']//button"));
		PriorityOptions.click();
		Priority=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Low']"));
		Priority.click();
		SLAOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.SLA__c']//button"));
		SLAOptions.click();
		SLA=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Silver']"));
		SLA.click();
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.Active__c']//button")));
		ActiveOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.Active__c']//button"));
		ActiveOptions.click();
		Active=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='No']"));
		Active.click();
		js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.Phone']//input")));
		Phone=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.Phone']//input"));
		Phone.sendKeys(phone);
		js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.UpsellOpportunity__c']//button")));
		UpsellOpportunityOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.UpsellOpportunity__c']//button"));
		UpsellOpportunityOptions.click();
		UpsellOpportunity=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='No']"));
		UpsellOpportunity.click();
		// click save and verify opportunity name
		Save = driver.findElement(By.xpath("//button[@name='SaveEdit']"));
		Save.click();
		Thread.sleep(5000);
		VerifyPhone=driver.findElement(By.xpath("//span[@class='forceOutputPhone slds-truncate']"));
		String phonenumber = VerifyPhone.getText();
		phonenumber.strip();
		if(phonenumber.contains(phone))
		{
			System.out.println("Both Phone Number are Same : "+phonenumber);
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
		clickAppLauncherthenSalesGotoAccountsandEditAccount("Abhishek","Plot No.8, Vinayaga Avenue, Rajiv Gandhi Salai, (Omr), Okkiampettai, Sri Sowdeswari Nagar, Chennai, Tamil Nadu 600097",
				"Plot No.8, Vinayaga Avenue, Rajiv Gandhi Salai, (Omr), Okkiampettai, Sri Sowdeswari Nagar, Chennai, Tamil Nadu 600097","9600112302");
		// closeBrowser();
	}

}
