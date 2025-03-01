package contentopportunity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

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

public class NewContentOpportunity {
	
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
			
			public static void clickAppLauncherthenContentandcreateNewOpportunity(String name,String amount) throws Exception
			{
				WebElement AppLauncher,ViewAll,Content,ViewAllDeals,OpportunitiesDropdown,
				AllOpportunities,New,InitialOppName,TypeOptions,Type,LeadSourceOptions,
				LeadSource,Amount,CloseDate,Stage,NeedAnalysis,PrimarySourceOptions,
				PrimarySource,Save,SaveMessage,GoToDetails,FinalOppName;
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
				JavascriptExecutor js = (JavascriptExecutor) driver;
				ViewAllDeals=driver.findElement(By.xpath("//a[@aria-label='View All Key Deals']"));
				js.executeScript("arguments[0].scrollIntoView();",ViewAllDeals);
				ViewAllDeals.click();
				Thread.sleep(5000);
				OpportunitiesDropdown=driver.findElement(By.xpath("//button[@title='Select a List View: Opportunities']"));
				OpportunitiesDropdown.click();
				AllOpportunities=driver.findElement(By.xpath("//span[text()='All Opportunities']"));
				wait.until(ExpectedConditions.visibilityOf(AllOpportunities));
				AllOpportunities.click();
				Thread.sleep(3000);
				New=driver.findElement(By.xpath("//a[@title='New']"));
				New.click();
				InitialOppName = driver.findElement(By.xpath("//input[@name='Name']"));
				InitialOppName.sendKeys(name);
				TypeOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Opportunity.Type']//button"));
				js.executeScript("arguments[0].scrollIntoView();",TypeOptions);
				js.executeScript("arguments[0].click();", TypeOptions);
				Type=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='New Customer']"));
				Type.click();
				LeadSourceOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Opportunity.LeadSource']//button"));
				LeadSourceOptions.click();
				LeadSource=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Partner Referral']"));
				LeadSource.click();
				Amount=driver.findElement(By.xpath("//input[@name='Amount']"));
				Amount.sendKeys(amount);
				CloseDate=driver.findElement(By.xpath("//input[@name='CloseDate']"));
				 // Get the current date
		        LocalDate currentDate = LocalDate.now();
		        // Get the year and month information from the current date
		        YearMonth currentYearMonth = YearMonth.from(currentDate);
		        // Add one month to the current year and month
		        YearMonth nextMonth = currentYearMonth.plusMonths(1);
		        // Get the 5th day of the next month
		        LocalDate twentythDayOfNextMonth = nextMonth.atDay(20);
		        // Define the formatter with the desired pattern
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        // Format the date
		        String closedate = twentythDayOfNextMonth.format(formatter);
		        CloseDate.sendKeys(closedate);
		        Stage = driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Opportunity.StageName']//button"));
				Stage.click();
				NeedAnalysis = driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Needs Analysis']"));
				js.executeScript("arguments[0].click();", NeedAnalysis);
				PrimarySourceOptions=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Opportunity.CampaignId']//input"));
				js.executeScript("arguments[0].scrollIntoView();",PrimarySourceOptions);
				PrimarySourceOptions.click();
				PrimarySource=driver.findElement(By.xpath("//span[@title='Bootcamp']"));
				PrimarySource.click();
				Save = driver.findElement(By.xpath("//button[@name='SaveEdit']"));
				Save.click();
				SaveMessage=driver
							.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
				String resulttext = SaveMessage.getText();
				resulttext.trim();
				System.out.println(resulttext);
				GoToDetails = driver.findElement(By.xpath("//a[@id='detailTab__item']"));
				GoToDetails.click();
				FinalOppName = driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Opportunity.Name']//lightning-formatted-text"));
				String finalname = FinalOppName.getText();
				if (finalname.contains(name)) {
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
		clickAppLauncherthenContentandcreateNewOpportunity("SRM Steels","75000");
	}

}
