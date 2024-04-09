package contacts;

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

public class CreateNewContact {

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
			
			public static void clickAppLauncherthenContactsCreateNewContact(String firstname,
					String lastname,String email,String accountname) throws Exception {
				WebElement AppLauncher, ViewAll,Contacts,New,SalutationButton,SelectSalutation,
				FirstName,LastName,Email,SearchAccountName,NewAccount,AccountName,
				SaveAccountName,Save,SaveMessage,GoToDetails,VerifyContactName;
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
				// click on App Launcher menu
				AppLauncher = driver.findElement(By.xpath("//button[contains(@class,'AppLauncherHeader')]"));
				wait.until(ExpectedConditions.visibilityOf(AppLauncher));
				AppLauncher.click();
				// click view all and then click Service Territories from app launcher
				ViewAll = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
				ViewAll.click();
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView()",
						driver.findElement(By.xpath("//a[@data-label='Contacts']")));
				Contacts = driver.findElement(By.xpath("//a[@data-label='Contacts']"));
				js.executeScript("arguments[0].click();", Contacts);
				Thread.sleep(5000);
				New=driver.findElement(By.xpath("//button[contains(@class,'middleButton action')]"));
				New.click();
				SalutationButton=driver.findElement(By.xpath("//button[@name='salutation']"));
				SalutationButton.click();
				SelectSalutation=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Mr.']"));
				SelectSalutation.click();
				FirstName=driver.findElement(By.xpath("//input[@name='firstName']"));
				FirstName.sendKeys(firstname);
				LastName=driver.findElement(By.xpath("//input[@name='lastName']"));
				LastName.sendKeys(lastname);
				//js.executeScript("arguments[0].scrollIntoView()",
						//driver.findElement(By.xpath("//input[@name='Email']")));
				Email=driver.findElement(By.xpath("//input[@name='Email']"));
				Email.sendKeys(email);
				SearchAccountName=driver.findElement(By.xpath("//input[@placeholder='Search Accounts...']"));
				SearchAccountName.click();
				Thread.sleep(3000);
				NewAccount=driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='actionCreateNew']"));
				NewAccount.click();
				Thread.sleep(3000);
				AccountName=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.Name']//input"));
				AccountName.sendKeys(accountname);
				SaveAccountName=driver.findElement(By.xpath("//button[@title='Save']//span"));
				SaveAccountName.click();
				Thread.sleep(6000);
				Save=driver.findElement(By.xpath("//button[@name='SaveEdit']"));
				Save.click();
				Thread.sleep(5000);
				SaveMessage=driver
						.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
				String resulttext = SaveMessage.getText();
				resulttext.trim();
				System.out.println(resulttext);
				GoToDetails = driver.findElement(By.xpath("//a[@id='detailTab__item']"));
				GoToDetails.click();
				VerifyContactName=driver.findElement(By.xpath("//records-record-layout-item[@field-label='Name']//lightning-formatted-name"));
				String contactname = VerifyContactName.getText();
				System.out.println("Contact Name is "+contactname);
				
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
		clickAppLauncherthenContactsCreateNewContact("Naveen", "Elumalai", "naveen@test.com", "Credits");
		//closeBrowser();
		
	}

}
