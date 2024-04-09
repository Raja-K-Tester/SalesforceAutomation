package serviceappointments;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

public class CreateNewServiceAppointments {
	
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
			
			public static void clickAppLauncherthenViewAllServiceAppointmentsCreateNew(String description,String accountname) throws Exception
			{
				WebElement AppLauncher,ViewAll,ServiceAppointments,New,Description,SearchAccounts,
				NewAccount,AccountName,Save,EarliestStartDate,EarliestStartTime,
				SelectEarliestStartTime,DueDate,SaveServiceAppointment,SaveMessage,
				AppointmentNumber,ServiceAppointmentLink,VerifyAppointmentNumber;
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
				New=driver.findElement(By.xpath("//a[@title='New']"));
				New.click();
				Thread.sleep(3000);
				Description=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.ServiceAppointment.Description']//textarea"));
				Description.sendKeys(description);
				SearchAccounts=driver.findElement(By.xpath("//input[@title='Search Accounts']"));
				SearchAccounts.click();
				NewAccount=driver.findElement(By.xpath("//span[@title='New Account']/.."));
				wait.until(ExpectedConditions.visibilityOf(NewAccount));
				NewAccount.click();
				Thread.sleep(3000);
				AccountName=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.Name']//input"));
				AccountName.sendKeys(accountname);
				Save=driver.findElement(By.xpath("//div[@class='modal-footer slds-modal__footer']//button[@title='Save']"));
				Save.click();
				Thread.sleep(6000);
				EarliestStartDate=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.ServiceAppointment.EarliestStartTime']//input"));
				String pattern = "dd/MM/yyyy"; // Define your desired date format
			    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			    String date = dateFormat.format(new Date());
			    EarliestStartDate.sendKeys(date);
				EarliestStartTime=driver.findElement(By.xpath("(//div[@data-target-selection-name='sfdc:RecordField.ServiceAppointment.EarliestStartTime']//input)[2]"));
				LocalTime currentTime = LocalTime.now();
				// Round down the minutes to the nearest multiple of 30
		        int roundedMinutes = (currentTime.getMinute() / 30) * 30;
		        LocalTime nearestTime = currentTime.withMinute(roundedMinutes);
		        // Format the nearest time
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
		        String formattedNearestTime = formatter.format(nearestTime);
		        EarliestStartTime.click();
		        SelectEarliestStartTime=driver.findElement(By.xpath("//li[text()='"+formattedNearestTime+"']"));
		        SelectEarliestStartTime.click();
		        DueDate=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.ServiceAppointment.DueDate']//input"));
		        LocalDate currentDate = LocalDate.now().plusDays(5);
		        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        String plus5days=currentDate.format(dateformatter);
		        DueDate.sendKeys(plus5days);
		        SaveServiceAppointment=driver.findElement(By.xpath("//button[@title='Save']"));
		        SaveServiceAppointment.click();
		        SaveMessage=driver
						.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
		        wait.until(ExpectedConditions.visibilityOf(SaveMessage));
		        String resulttext = SaveMessage.getText();
				resulttext.trim();
				System.out.println(resulttext);
				Thread.sleep(4000);
				AppointmentNumber=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.ServiceAppointment.AppointmentNumber']//span[@class='uiOutputText']"));
				String appointmentno = AppointmentNumber.getText();
				System.out.println("Appointment No is : "+appointmentno);
				ServiceAppointmentLink=driver.findElement(By.xpath("//a[@title='"+appointmentno+"']"));
				js.executeScript("arguments[0].click();",ServiceAppointmentLink);
				Thread.sleep(4000);
				VerifyAppointmentNumber=driver.findElement(By.xpath("//span/a[@data-refid='recordId']"));
				String verifyappointmentno = VerifyAppointmentNumber.getText();
				if(appointmentno.equalsIgnoreCase(verifyappointmentno))
				{
					System.out.println("Both are Same : "+verifyappointmentno);
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
		clickAppLauncherthenViewAllServiceAppointmentsCreateNew("Creating Service Appointments","Raja");
	}

}
