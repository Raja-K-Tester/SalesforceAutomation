package salesforceclassic;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
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

public class SalesforceClassicNewAccount {

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

	public static void ClickProfileSwitchtoSalesforceClassicCreateNewAccount(String accountname,String street,
			String city,String zip,String state,String country) throws Exception
	{
		WebElement UserImage,SwitchToSalesforceClassic,AppMenu,Sales,CreateNewButton,
		Account,AccountName,BillingStreet,BillingCity,BillingZip,BillingState,BillingCountry,
		CopyBillingAddresstoShippingAddress,SLAExpirationDate,Save,AccountsTab,VerifyAccountName,
		SwitchToLightningExperience;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Thread.sleep(10000);
		UserImage=driver.findElement(By.xpath("//button[contains(@class,'userProfile')]"));
		wait.until(ExpectedConditions.visibilityOf(UserImage));
		Actions actions=new Actions(driver);
		actions.moveToElement(UserImage).doubleClick().perform();
		SwitchToSalesforceClassic=driver.findElement(By.linkText("Switch to Salesforce Classic"));
		wait.until(ExpectedConditions.visibilityOf(SwitchToSalesforceClassic));
		actions.moveToElement(SwitchToSalesforceClassic).click().perform();
		Thread.sleep(3000);
		AppMenu=driver.findElement(By.xpath("//div[@title='App Menu']"));
		AppMenu.click();
		Sales=driver.findElement(By.linkText("Sales"));
		wait.until(ExpectedConditions.visibilityOf(Sales));
		Sales.click();
		CreateNewButton=driver.findElement(By.id("createNewButton"));
		CreateNewButton.click();
		Account=driver.findElement(By.linkText("Account"));
		wait.until(ExpectedConditions.visibilityOf(Account));
		Account.click();
		AccountName=driver.findElement(By.name("acc2"));
		AccountName.sendKeys("BootCamp Puppeteer_"+accountname);
		BillingStreet=driver.findElement(By.cssSelector("textarea#acc17street"));
		BillingStreet.sendKeys(street);
		BillingCity=driver.findElement(By.cssSelector("input#acc17city"));
		BillingCity.sendKeys(city);
		BillingZip=driver.findElement(By.cssSelector("input#acc17zip"));
		BillingZip.sendKeys(zip);
		BillingState=driver.findElement(By.cssSelector("input#acc17state"));
		BillingState.sendKeys(state);
		BillingCountry=driver.findElement(By.cssSelector("input#acc17country"));
		BillingCountry.sendKeys(country);
		CopyBillingAddresstoShippingAddress=driver.findElement(By.linkText("Copy Billing Address to Shipping Address"));
		CopyBillingAddresstoShippingAddress.click();
		SLAExpirationDate=driver.findElement(By.cssSelector("span.dateInput.dateOnlyInput>input"));
		 // Get the current date
        LocalDate currentDatePlus20 = LocalDate.now().plusDays(20);
        // Define the formatter with the desired pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = currentDatePlus20.format(formatter);
        SLAExpirationDate.sendKeys(date);
        Save=driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@name='save']"));
        Save.click();
		Thread.sleep(4000);
		AccountsTab=driver.findElement(By.xpath("//a[text()='Accounts']"));
		AccountsTab.click();
		Thread.sleep(3000);
		VerifyAccountName=driver.findElement(By.xpath("//a[text()='BootCamp Puppeteer_"+accountname+"']"));
		String verifyaccountname = VerifyAccountName.getText();
		System.out.println("Account Name is Verified : "+verifyaccountname);
		SwitchToLightningExperience=driver.findElement(By.cssSelector("a.switch-to-lightning"));
		SwitchToLightningExperience.click();
		Thread.sleep(10000);
		
	}
	
	public static void logout() throws Exception
	{
		WebElement UserImage,Logout;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		UserImage=driver.findElement(By.xpath("//button[contains(@class,'userProfile')]"));
		wait.until(ExpectedConditions.visibilityOf(UserImage));
		Actions actions=new Actions(driver);
		actions.moveToElement(UserImage).doubleClick().perform();
		Logout=driver.findElement(By.linkText("Log Out"));
		wait.until(ExpectedConditions.visibilityOf(Logout));
		actions.moveToElement(Logout).click().perform();
		Thread.sleep(5000);
		System.out.println(driver.getTitle());
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
		ClickProfileSwitchtoSalesforceClassicCreateNewAccount("Raja", "\r\n"
				+ "Plot No.8, Vinayaga Avenue, Rajiv Gandhi Salai, (OMR), Okkiampettai", "Chennai", "600097", "Tamil Nadu", "India");
		logout();
		closeBrowser();
	}

}
