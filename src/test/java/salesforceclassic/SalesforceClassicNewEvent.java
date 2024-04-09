package salesforceclassic;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;

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

public class SalesforceClassicNewEvent {
	
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
			
			public static void ClickProfileSwitchtoSalesforceClassicCreateNewEvent(String eventname) throws Exception
			{
				WebElement UserImage,SwitchToSalesforceClassic,CreateNewButton,Event,
				SubjectCombo,SelectSubject,StartDate,EndDate,AddInvitees,Go,SelectContactName,
				Done,AttachFile,File,Attach,DoneUpload,Save,VerifyEventName,SwitchToLightningExperience;
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
				JavascriptExecutor js=(JavascriptExecutor) driver;
				Thread.sleep(10000);
				UserImage=driver.findElement(By.xpath("//button[contains(@class,'userProfile')]"));
				wait.until(ExpectedConditions.visibilityOf(UserImage));
				Actions actions=new Actions(driver);
				actions.moveToElement(UserImage).doubleClick().perform();
				SwitchToSalesforceClassic=driver.findElement(By.linkText("Switch to Salesforce Classic"));
				wait.until(ExpectedConditions.visibilityOf(SwitchToSalesforceClassic));
				actions.moveToElement(SwitchToSalesforceClassic).click().perform();
				Thread.sleep(5000);
				CreateNewButton=driver.findElement(By.id("createNewButton"));
				CreateNewButton.click();
				Event=driver.findElement(By.linkText("Event"));
				wait.until(ExpectedConditions.visibilityOf(Event));
				Event.click();
				Thread.sleep(5000);
				SubjectCombo=driver.findElement(By.cssSelector("img.comboboxIcon"));
				actions.moveToElement(SubjectCombo).click().perform();
				Thread.sleep(5000);
				//js.executeScript("arguments[0].click();", SubjectCombo);
				String parentWindowHandle = driver.getWindowHandle();
				Set<String> SubjectWindowHandles = driver.getWindowHandles();
				Iterator<String> it = SubjectWindowHandles.iterator();
				while(it.hasNext())
				{
					String childWindowHandle=it.next();
					if(!parentWindowHandle.equalsIgnoreCase(childWindowHandle))
					{
						driver.switchTo().window(childWindowHandle);
						SelectSubject=driver.findElement(By.linkText(eventname));
						wait.until(ExpectedConditions.visibilityOf(SelectSubject));
						js.executeScript("arguments[0].click();", SelectSubject);
					}
				}
				Thread.sleep(5000);
				driver.switchTo().window(parentWindowHandle);
				StartDate=driver.findElement(By.id("StartDateTime"));
				 // Get the current date
		        LocalDate currentDatePlusOne = LocalDate.now().plusDays(1);
		        // Define the formatter with the desired pattern
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        String tomorrow = currentDatePlusOne.format(formatter);
		        StartDate.clear();
		        StartDate.sendKeys(tomorrow);
		        LocalDate currentDatePlusTwo = LocalDate.now().plusDays(2);
		        String tomorrowplusone = currentDatePlusTwo.format(formatter);
		        EndDate=driver.findElement(By.id("EndDateTime"));
		        EndDate.clear();
		        EndDate.sendKeys(tomorrowplusone);
		        AddInvitees=driver.findElement(By.id("addInviteesButton"));
		        js.executeScript("arguments[0].scrollIntoView();", AddInvitees);
		        //actions.moveToElement(AddInvitees).click().perform();
		        js.executeScript("arguments[0].click();", AddInvitees);
		        Thread.sleep(6000);
		        //String parentWindowHandle1 = driver.getWindowHandle();
		        Set<String> ContactwindowHandles = driver.getWindowHandles();
		       for (String handle : ContactwindowHandles) {
				if(!parentWindowHandle.equalsIgnoreCase(handle))
				{
					driver.switchTo().window(handle);
	        		 Go=driver.findElement(By.xpath("//input[@title='Go!']"));
	 		        Go.click();
	 		        SelectContactName=driver.findElement(By.xpath("//input[@title='Select map']"));
	 		       wait.until(ExpectedConditions.visibilityOf(SelectContactName));
	 		        SelectContactName.click();
	 		        Done=driver.findElement(By.xpath("//input[@title='Done']"));
	 		       wait.until(ExpectedConditions.visibilityOf(Done));
	 		        Done.click();
				}
		       }
		       
		        Thread.sleep(5000);
				driver.switchTo().window(parentWindowHandle);
		        AttachFile=driver.findElement(By.xpath("//input[@title='Attach File']"));
		        AttachFile.click();
		        Thread.sleep(5000);
		        Set<String> AttachfilewindowHandles = driver.getWindowHandles();
		        for (String handle : AttachfilewindowHandles) {
		        	if(!parentWindowHandle.equalsIgnoreCase(handle))
		        	{
		        		driver.switchTo().window(handle);
		        		File=driver.findElement(By.cssSelector("input#file"));
		        		actions.moveToElement(File).click().perform();
		        		File.sendKeys("D://Amazon.xlsx");
				        Attach=driver.findElement(By.cssSelector("input#Attach"));
				        wait.until(ExpectedConditions.visibilityOf(Attach));
				        Attach.click();
				        //Thread.sleep(6000);
				        DoneUpload=driver.findElement(By.xpath("//input[@title='Done']"));
				        wait.until(ExpectedConditions.visibilityOf(DoneUpload));
				        DoneUpload.click();
		        	}
		        }
		        Thread.sleep(6000);
		        driver.switchTo().window(parentWindowHandle);
		        Save=driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@title='Save']"));
		        Save.click();
		        Thread.sleep(6000);
		        VerifyEventName=driver.findElement(By.xpath("//span[text()='"+eventname+"']"));
		        String verifyevent = VerifyEventName.getText();
		        System.out.println(verifyevent);
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
		ClickProfileSwitchtoSalesforceClassicCreateNewEvent("Email");
		logout();
		closeBrowser();
	}

}
