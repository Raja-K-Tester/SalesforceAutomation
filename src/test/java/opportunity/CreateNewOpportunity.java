package opportunity;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

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

public class CreateNewOpportunity {

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

		public static void clickAppLauncherthenSalesandcreateNewSalesOpportunity(String name) throws Exception {
			WebElement AppLauncher, ViewAll, Sales, Opportunity, NewOpportunity, InitialOppName, 
			CloseDate, Stage,NeedAnalysis, Save, GoToDetails, FinalOppName;
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
			// click on opportunity tab
			Opportunity = driver.findElement(By.xpath("//a[@title='Opportunities']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", Opportunity);
			// click on new button
			NewOpportunity = driver.findElement(By.xpath("//a[@title='New']"));
			NewOpportunity.click();
			// enter opportunity name as Sales force automation by your name get the text and store it
			InitialOppName = driver.findElement(By.xpath("//input[@name='Name']"));
			InitialOppName.sendKeys("Salesforce Automation by "+name);
			//String initialname=(String) js.executeScript("return document.getElementById('input-202').innerText");
			Thread.sleep(5000);
			//String initialname = InitialOppName.getText();
			// choose close date as today
			String pattern = "dd/MM/yyyy"; // Define your desired date format
	        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
	        String dateString = dateFormat.format(new Date());
			CloseDate = driver.findElement(By.xpath("//input[@name='CloseDate']"));
			CloseDate.sendKeys(dateString);
			// select stage as need analysis
			Stage = driver.findElement(By.xpath("(//button[@class='slds-combobox__input slds-input_faux fix-slds-input_faux slds-combobox__input-value'])[2]"));
			Stage.click();
			NeedAnalysis = driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Needs Analysis']"));
			js.executeScript("arguments[0].click();", NeedAnalysis);
			// click save and verify opportunity name
			Save = driver.findElement(By.xpath("//button[@name='SaveEdit']"));
			Save.click();
			GoToDetails = driver.findElement(By.xpath("//a[@id='detailTab__item']"));
			GoToDetails.click();
			FinalOppName = driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Opportunity.Name']//lightning-formatted-text"));
			String finalname = FinalOppName.getText();
			if (finalname.contains("Salesforce Automation by "+name)) {
				System.out.println("Both name are same:" + finalname);
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
			clickAppLauncherthenSalesandcreateNewSalesOpportunity("Abhishek");
			//closeBrowser();
		}


}
