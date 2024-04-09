package dashboard;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNewDashboard {

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

		public static void clickAppLauncherthenCreateNewDashboard(String name) throws Exception
		{
			WebElement AppLauncher, ViewAll,Dashboards,NewDashboard,Iframe,Name,Create,
			DashboardName,Done,DashBoardLink;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			// click on App Launcher menu
			AppLauncher = driver.findElement(By.xpath("//button[contains(@class,'AppLauncherHeader')]"));
			wait.until(ExpectedConditions.visibilityOf(AppLauncher));
			AppLauncher.click();
			// click view all and then click sales from app launcher
			ViewAll = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
			ViewAll.click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath("//a[@data-label='Dashboards']")));
			Dashboards=driver.findElement(By.xpath("//a[@data-label='Dashboards']"));
			js.executeScript("arguments[0].click();", Dashboards);
			NewDashboard=driver.findElement(By.xpath("//a[@title='New Dashboard']"));
			wait.until(ExpectedConditions.visibilityOf(NewDashboard));
			NewDashboard.click();
			Thread.sleep(3000);
			Iframe=driver.findElement(By.xpath("//iframe[@title='dashboard']"));
			driver.switchTo().frame(Iframe);
			Name=driver.findElement(By.xpath("//input[@id='dashboardNameInput']"));
			Name.sendKeys("Salesforce Automation by "+name);
			Create=driver.findElement(By.xpath("//button[@id='submitBtn']"));
			Create.click();
			Done=driver.findElement(By.xpath("//button[@class='slds-button doneEditing']"));
			Done.click();
			Thread.sleep(4000);
			DashBoardLink=driver.findElement(By.xpath("//a[@title='Dashboards']"));
			js.executeScript("arguments[0].click();", DashBoardLink);
			Thread.sleep(4000);
			DashboardName=driver.findElement(By.xpath("//lightning-primitive-cell-factory[@data-label='Dashboard Name']//a"));
			String dashboardname = DashboardName.getText();
			if(dashboardname.contains("Salesforce Automation by "+name))
			{
				System.out.println("Both name are matching : "+dashboardname);
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
		//login("krish.wobble@multiplier.sandbox", "Wobble1960$");
		clickAppLauncherthenCreateNewDashboard("Mom");
		// closeBrowser();
	}

}
