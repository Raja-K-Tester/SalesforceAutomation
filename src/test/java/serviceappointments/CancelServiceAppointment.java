package serviceappointments;

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

public class CancelServiceAppointment {
	
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
			
			public static void clickAppLauncherthenViewAllServiceAppointmentsCancelService(String serviceappointment) throws Exception
			{
				WebElement AppLauncher,ViewAll,ServiceAppointments,ShowActions,
				CancelServiceAppointment,ConfirmCancel,CancelMessage,SearchandVerify,VerifyStatus;
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
				// click on App Launcher menu
				AppLauncher = driver.findElement(By.xpath("//button[contains(@class,'AppLauncherHeader')]"));
				wait.until(ExpectedConditions.visibilityOf(AppLauncher));
				Thread.sleep(3000);
				AppLauncher.click();
				Thread.sleep(3000);
				// click view all and then click Service Appointments from app launcher
				ViewAll = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
				wait.until(ExpectedConditions.visibilityOf(ViewAll));
				ViewAll.click();
				Thread.sleep(5000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				ServiceAppointments=driver.findElement(By.xpath("//a[@data-label='Service Appointments']"));
				js.executeScript("arguments[0].scrollIntoView();",ServiceAppointments);
				js.executeScript("arguments[0].click();",ServiceAppointments);
				Thread.sleep(3000);
				ShowActions=driver.findElement(By.xpath("//a[contains(@class,'rowActionsPlaceHolder')]"));
				ShowActions.click();
				CancelServiceAppointment=driver.findElement(By.xpath("//a[@title='Cancel Service Appointment']"));
				wait.until(ExpectedConditions.visibilityOf(CancelServiceAppointment));
				CancelServiceAppointment.click();
				Thread.sleep(3000);
				ConfirmCancel=driver.findElement(By.xpath("//button[text()='Confirm Cancellation']"));
				ConfirmCancel.click();
				CancelMessage=driver
							.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
			    wait.until(ExpectedConditions.visibilityOf(CancelMessage));
			    String resulttext = CancelMessage.getText();
			    resulttext.trim();
				System.out.println(resulttext);
				Thread.sleep(4000);
				SearchandVerify=driver.findElement(By.xpath("//input[@aria-label='Search Recently Viewed list view.']"));
				SearchandVerify.click();
				SearchandVerify.sendKeys(serviceappointment);
				SearchandVerify.sendKeys(Keys.ENTER);
				VerifyStatus=driver.findElement(By.xpath("//span[text()='Canceled']"));
				String status = VerifyStatus.getText();
				System.out.println("Status Verified : "+status);
				
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
		clickAppLauncherthenViewAllServiceAppointmentsCancelService("SA-0002");
	}

}
