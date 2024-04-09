package content;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
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

public class EditTask {
	
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
		
		public static void clickAppLauncherthenContentEditTask(String name,String accname) throws Exception {
			WebElement AppLauncher,ViewAll,Content,ViewAllTasks,SelectListDisplay,Table,TasksDropDown,
			RecentlyViewed,Search,Actions,Edit,DueDate,RemoveRelatedTo,RelatedtoDropdown,Accounts,
			SearchAccount,SelectAccount,Save,SaveMessage,VerifyDueDate;
			
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
			RecentlyViewed=driver.findElement(By.xpath("//ul[@role='listbox']//span[text()='Recently Viewed']/.."));
			RecentlyViewed.click();
			Search=driver.findElement(By.xpath("//input[@name='Task-search-input']"));
			wait.until(ExpectedConditions.visibilityOf(Search));
			Search.sendKeys(name);
			Search.sendKeys(Keys.ENTER);
			Thread.sleep(5000);
			Actions=driver.findElement(By.xpath("//a[contains(@class,'rowActionsPlaceHolder')]"));
			Actions.click();
			Edit=driver.findElement(By.xpath("//a[@data-target-selection-name='sfdc:StandardButton.Task.Edit']"));
			Edit.click();
			Thread.sleep(3000);
			DueDate=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.ActivityDate']//input"));
			 // Get the current date
	        LocalDate currentDate = LocalDate.now();
	        // Get the year and month information from the current date
	        YearMonth currentYearMonth = YearMonth.from(currentDate);
	        // Add one month to the current year and month
	        YearMonth nextMonth = currentYearMonth.plusMonths(1);
	        // Get the 5th day of the next month
	        LocalDate fifteenthDayOfNextMonth = nextMonth.atDay(15);
	        // Define the formatter with the desired pattern
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        // Format the date
	        String duedate = fifteenthDayOfNextMonth.format(formatter);
	        DueDate.sendKeys(duedate);
	        RemoveRelatedTo=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.WhatId']//a[@class='deleteAction']"));
	        RemoveRelatedTo.click();
	        RelatedtoDropdown=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Task.WhatId']//a[contains(@aria-label,'Related To')]"));
	        wait.until(ExpectedConditions.visibilityOf(RelatedtoDropdown));
	        RelatedtoDropdown.click();
	        Accounts=driver.findElement(By.xpath("//a[@title='Accounts']"));
	        wait.until(ExpectedConditions.visibilityOf(Accounts));
	        Accounts.click();
	        SearchAccount=driver.findElement(By.xpath("//input[@title='Search Accounts']"));
	        SearchAccount.click();
	        SelectAccount=driver.findElement(By.xpath("//div[@title='"+accname+"']"));
	        SelectAccount.click();
	        Save=driver.findElement(By.xpath("//div[@class='actionsContainer']//button[@title='Save']"));
	        Save.click();
	        SaveMessage=driver
					.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
	        wait.until(ExpectedConditions.visibilityOf(SaveMessage));
	        String resulttext = SaveMessage.getText();
			resulttext.trim();
			System.out.println(resulttext);
	        VerifyDueDate=driver.findElement(By.xpath("(//span[text()='"+duedate+"'])[2]"));
	        String verifyduedate = VerifyDueDate.getText();
	        System.out.println("Due date is : "+verifyduedate);
			
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
		clickAppLauncherthenContentEditTask("Email", "Credits");
	}

}
