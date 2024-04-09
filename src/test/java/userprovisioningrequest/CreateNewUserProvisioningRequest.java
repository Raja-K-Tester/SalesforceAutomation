package userprovisioningrequest;

import java.time.Duration;
import java.util.List;
import java.util.Set;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNewUserProvisioningRequest {

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

	public static void clickAppLauncherthenViewAllUserProvisioningRequestCreateNewView(String viewname,String viewuniquename) throws Exception {
		WebElement AppLauncher,ViewAll,UserProvisioningRequests,OpenSalesForceClassicLink,
		CreateNewView,ViewName,ViewUniqueName,SelectMyUserProvisioningRequests,FirstField,
		SecondField,AvailableField,SelectedField,Add,VerifyFieldAddedorNot,VisibilitytoAllUsers,
		Save,VerifyHeader;
		
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		UserProvisioningRequests=driver.findElement(By.xpath("//a[@data-label='User Provisioning Requests']"));
		js.executeScript("arguments[0].scrollIntoView();",UserProvisioningRequests);
		js.executeScript("arguments[0].click();",UserProvisioningRequests);
		Thread.sleep(3000);
		OpenSalesForceClassicLink=driver.findElement(By.xpath("//a[@class='uiOutputURL']"));
		OpenSalesForceClassicLink.click();
		Thread.sleep(3000);
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowhandle : windowHandles) {
			String title = driver.getTitle();
			if(title.equalsIgnoreCase("Unsupported Item | Salesforce"))
			{
				driver.switchTo().window(windowhandle);
				System.out.println(driver.getTitle());
			}
		}
		CreateNewView=driver.findElement(By.xpath("//a[text()='Create New View']"));
		wait.until(ExpectedConditions.visibilityOf(CreateNewView));
		CreateNewView.click();
		Thread.sleep(3000);
		ViewName=driver.findElement(By.cssSelector("input#fname"));
		ViewName.sendKeys(viewname);
		ViewUniqueName=driver.findElement(By.id("devname"));
		for(int i=4;i>=1;i--)
		{
			ViewUniqueName.sendKeys(Keys.BACK_SPACE);
		}
		ViewUniqueName.sendKeys(viewuniquename);
		SelectMyUserProvisioningRequests=driver.findElement(By.id("fscope1"));
		SelectMyUserProvisioningRequests.click();
		FirstField=driver.findElement(By.id("fcol1"));
		Select firstfield = new Select(FirstField);
		firstfield.selectByValue("Name");
		SecondField=driver.findElement(By.id("fcol2"));
		Select secondfield=new Select(SecondField);
		List<WebElement> options = secondfield.getOptions();
		for (WebElement option : options) {
			String text = option.getText();
			System.out.println(text);
		}
		int size = options.size();
		System.out.println(size);
		secondfield.selectByValue("CreatedDate");
		AvailableField=driver.findElement(By.id("colselector_select_0"));
		Select availablefield=new Select(AvailableField);
		Add=driver.findElement(By.id("colselector_select_0_right"));
		List<WebElement> availablefieldoptions = availablefield.getOptions();
		for (WebElement availableoption : availablefieldoptions) {
			String option = availableoption.getText();
			System.out.println(option);
		}
		SelectedField=driver.findElement(By.id("colselector_select_1"));
		Select selectedfield=new Select(SelectedField);
		List<WebElement> selectedfieldoptions = selectedfield.getOptions();
		for (WebElement selectedoption : selectedfieldoptions) {
			String option = selectedoption.getText();
			System.out.println(option);
		}
		availablefield.selectByValue("CreatedDate");
		Add.click();
		Thread.sleep(5000);
		VerifyFieldAddedorNot=driver.findElement(By.xpath("//select[@id='colselector_select_1']//option[@value='CreatedDate']"));
		String verifyfieldtext = VerifyFieldAddedorNot.getText();
		System.out.println("Field is added successfully and the name is "+verifyfieldtext);
		VisibilitytoAllUsers=driver.findElement(By.id("fsharefshareall"));
		VisibilitytoAllUsers.click();
		Save=driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@title='Save']"));
		Save.click();
		Thread.sleep(3000);
		VerifyHeader=driver.findElement(By.cssSelector("h1.noSecondHeader.pageType"));
		System.out.println("New User is Created and Verified : "+VerifyHeader.getText());
		
		System.out.println(driver.getTitle());
		for (String windowhandle : windowHandles) {
			if(driver.getTitle().equalsIgnoreCase("Raja ~ Salesforce - Developer Edition"))
			{
			driver.switchTo().window(windowhandle);
			Thread.sleep(5000);
		}
		}
		driver.close();
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
		clickAppLauncherthenViewAllUserProvisioningRequestCreateNewView("Raja","Raja_71");
	}

}
