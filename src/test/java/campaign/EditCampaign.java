package campaign;

import java.time.Duration;
import java.time.LocalDate;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditCampaign {
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

	public static void clickAppLauncherthenSalesCampaignsNewCampaign(String name,String revenue,String cost) throws Exception {
		WebElement AppLauncher, ViewAll, Sales, Campaigns, CampaignsLink,EndDate, EndDateEdit, SaveEdit,
				GoToDetails,ExpectedRevenue,EditExpectedRevenue,BudgetCost,EditBudgetCost, 
				VerifyEndDate,VerifyExpectedRevenue,VerifyBudgetCost,SaveRevenue,SaveBudgetCost;
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
		Thread.sleep(3000);
		GoToDetails = driver.findElement(By.xpath("//a[@title='Details']"));
		GoToDetails.click();
		Thread.sleep(5000);
		//js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//button[@title='Edit Status']")));
		Actions action=new Actions(driver);
//		EndDate=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Campaign.EndDate']//span[@data-aura-class='uiOutputDate']"));
//		action.moveToElement(EndDate).doubleClick().perform();
		//EndDate.click();
		js.executeScript("window.scrollBy(0,300)");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		EndDateEdit=driver.findElement(By.xpath("//button[@title='Edit End Date']"));
		action.moveToElement(EndDateEdit).click().perform();
		EndDate=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Campaign.EndDate']//input"));
		EndDate.clear();
		LocalDate todayplus4 = LocalDate.now();
		todayplus4 = todayplus4.plusDays(4);
		String enddate = todayplus4.format(formatter);
		EndDate.sendKeys(enddate);
		SaveEdit=driver.findElement(By.xpath("//button[@title='Save']/span"));
		SaveEdit.click();
		ExpectedRevenue=driver.findElement(By.xpath("//button[contains(@title,'Expected Revenue')]"));
		ExpectedRevenue.click();
		EditExpectedRevenue=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Campaign.ExpectedRevenue']//input"));
		EditExpectedRevenue.sendKeys(revenue);
		SaveRevenue=driver.findElement(By.xpath("//button[@title='Save']/span"));
		//js.executeScript("arguments[0].click();", SaveRevenue);
		SaveRevenue.click();
		Thread.sleep(6000);
		BudgetCost=driver.findElement(By.xpath("//button[@title='Edit Budgeted Cost in Campaign']"));
		BudgetCost.click();
		EditBudgetCost=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Campaign.BudgetedCost']//input"));
		EditBudgetCost.sendKeys(cost);
		SaveBudgetCost=driver.findElement(By.xpath("//button[@title='Save']/span"));
		//js.executeScript("arguments[0].click();", SaveBudgetCost);
		SaveBudgetCost.click();
		Thread.sleep(6000);
		js.executeScript("window.scrollBy(0,-300)");
		VerifyEndDate=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Campaign.EndDate']//span[@class='uiOutputDate']"));
		String verifyenddate = VerifyEndDate.getText();
		System.out.println("Both Date are Same : "+verifyenddate);
		VerifyExpectedRevenue=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Campaign.ExpectedRevenue']//span[@class='forceOutputCurrency']"));
		String verifyrevenue = VerifyExpectedRevenue.getText();
		System.out.println("Both Revenue are Same : "+verifyrevenue);
		VerifyBudgetCost=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Campaign.BudgetedCost']//span[@class='forceOutputCurrency']"));
		String verifycost = VerifyBudgetCost.getText();
		System.out.println("Both Budget Cost are Same : "+verifycost);
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
		clickAppLauncherthenSalesCampaignsNewCampaign("Bootcamp","1000000","100000");
		// closeBrowser();
	}

}
