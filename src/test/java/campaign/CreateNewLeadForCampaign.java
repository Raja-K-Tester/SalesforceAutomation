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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNewLeadForCampaign {

	// Create WebDriver interface and FirefoxDriver,ChromeDriver,Edge Driver classes
	// will implement the WebDriver interface and WebDriver focus on manipulating
	// browser.
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

	public static void clickAppLauncherthenSalesCampaignsCreateLead(String name,String firstname,String lastname,String company) throws Exception {
		WebElement AppLauncher, ViewAll, Sales, Campaigns, CampaignsLink,NewLeads,
		SearchLeeds,NewLead,SalutationOptions,Salutation,FirstName,LastName,Company,Save,
		Message,Next,Submit,VerifyLeadNameCampaign,LeadsLink,VerifyLeadNameLeads,
		ViewAllLink;
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
		Campaigns = driver.findElement(By.xpath("//a[@title='Campaigns']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Campaigns);
		Thread.sleep(5000);
		CampaignsLink = driver.findElement(By.xpath("//a[@title='"+name+"']"));
		CampaignsLink.click();
		Thread.sleep(8000);
		js.executeScript("window.scrollBy(0,300)");
		NewLeads=driver.findElement(By.xpath("//a[@title='Add Leads']"));
		NewLeads.click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//h2[text()='Add Leads to Campaign']")).click();
		Actions action=new Actions(driver);
		SearchLeeds=driver.findElement(By.xpath("//input[@title='Search Leads']"));
		action.click(SearchLeeds).perform();
		Thread.sleep(3000);
		SearchLeeds.click();
		SearchLeeds.sendKeys(Keys.DOWN);
		NewLead=driver.findElement(By.xpath("//span[@title='New Lead']/.."));
		action.click(NewLead).perform();
		//NewLead.click();
		Thread.sleep(4000);
		SalutationOptions=driver.findElement(By.xpath("//a[@class='select']"));
		SalutationOptions.click();
		Salutation=driver.findElement(By.xpath("//a[@title='Mr.']"));
		Salutation.click();
		FirstName=driver.findElement(By.xpath("//input[@placeholder='First Name']"));
		FirstName.sendKeys(firstname);
		LastName=driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
		LastName.sendKeys(lastname);
		Company=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Lead.Company']//input"));
		Company.sendKeys(company);
		Save=driver.findElement(By.xpath("//button[@title='Save']/span"));
		Save.click();
		Message=driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
		wait.until(ExpectedConditions.visibilityOf(Message));
		String resulttext = Message.getText();
		resulttext.trim();
		System.out.println(resulttext);
		Thread.sleep(5000);
		Next=driver.findElement(By.xpath("//button[text()='Next']"));
		Next.click();
		Thread.sleep(8000);
		Submit=driver.findElement(By.xpath("//button[text()='Submit']"));
		wait.until(ExpectedConditions.visibilityOf(Submit));
		Submit.click();
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,400)");
		ViewAllLink=driver.findElement(By.xpath("(//span[@class='view-all-label'])[2]"));
		js.executeScript("arguments[0].click();", ViewAllLink);
		Thread.sleep(3000);
		VerifyLeadNameCampaign=driver.findElement(By.xpath("//a[@title='"+firstname+" "+lastname+"']"));
		String verifyleadcampaign = VerifyLeadNameCampaign.getText();
		System.out.println("Lead Name in Campaign is : "+verifyleadcampaign);
		LeadsLink=driver.findElement(By.xpath("//a[@title='Leads']"));
		js.executeScript("arguments[0].click();", LeadsLink);
		Thread.sleep(3000);
		VerifyLeadNameLeads=driver.findElement(By.xpath("//span[text()='View Activity: "+firstname+" "+lastname+"']"));
		String verifyleadleads = VerifyLeadNameLeads.getText();
		verifyleadleads=verifyleadleads.substring(15);
		System.out.println("Lead Name in Leads is : "+verifyleadleads);
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
		clickAppLauncherthenSalesCampaignsCreateLead("Bootcamp","Raja","Krishnan","TestLeaf");
		// closeBrowser();
	}

}
