package content;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewChart {
	
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
			
			public static void clickAppLauncherthenContentNewChart(String chartname,String charttype,String aggregatefield,
					String groupingfield) throws Exception
			{
				WebElement AppLauncher,ViewAll,Content,ViewAllTasks,SelectListDisplay,Table,TaskDropDown,
				OpenTasks,ShowCharts,NewChart,ChartName,ChartTypeOptions,ChartType,AggregateFieldOptions,
				AggregateField,GroupingFieldOptions,GroupingField,Save,VerifyName,VerifyCount,
				Status,StatusDropdown,ChangeStatus,SaveStatus,SaveMessage,Refresh,CloseChart,
				VerifyFinalMessage;
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
				
				TaskDropDown=driver.findElement(By.xpath("//button[@title='Select a List View: Tasks']"));
				TaskDropDown.click();
				OpenTasks=driver.findElement(By.xpath("//a/span[text()='Open Tasks']"));
				OpenTasks.click();
				ShowCharts=driver.findElement(By.xpath("//button[@title='Show charts']"));
				ShowCharts.click();
				NewChart=driver.findElement(By.xpath("//span[text()='New Chart']"));
				NewChart.click();
				Thread.sleep(5000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				ChartName=driver.findElement(By.xpath("//div[@class='chartInput forceRecordLayout']//input"));
				ChartName.sendKeys(chartname);
				ChartTypeOptions=driver.findElement(By.xpath("(//a[@class='select'])[1]"));
				js.executeScript("arguments[0].click();", ChartTypeOptions);
				ChartType=driver.findElement(By.xpath("//a[@title='"+charttype+"']"));
				ChartType.click();
				AggregateFieldOptions=driver.findElement(By.xpath("(//a[@class='select'])[3]"));
				js.executeScript("arguments[0].scrollIntoView()",AggregateFieldOptions);
				js.executeScript("arguments[0].click();", AggregateFieldOptions);
				AggregateField=driver.findElement(By.xpath("//a[@title='"+aggregatefield+"']"));
				js.executeScript("arguments[0].scrollIntoView()",AggregateField);
				AggregateField.click();
				GroupingFieldOptions=driver.findElement(By.xpath("(//a[@class='select'])[4]"));
				js.executeScript("arguments[0].click();", GroupingFieldOptions);
				GroupingField=driver.findElement(By.xpath("//a[@title='"+groupingfield+"']"));
				js.executeScript("arguments[0].click();", GroupingField);
				
				Save=driver.findElement(By.xpath("//button[@title='Save']"));
				wait.until(ExpectedConditions.visibilityOf(Save));
				Save.click();
				Thread.sleep(5000);
				VerifyName=driver.findElement(By.xpath("//span[@class='left uiOutputText']"));
				String name = VerifyName.getText();
				System.out.println(name);
				VerifyCount=driver.findElement(By.xpath("//span[@class='right uiOutputNumber']"));
				String count = VerifyCount.getText();
				System.out.println(count);
				CloseChart=driver.findElement(By.xpath("//button[@class='slds-button slds-button_icon slds-panel__close slds-button_icon-bare']"));
				CloseChart.click();
				Status=driver.findElement(By.xpath("//div[text()='In Progress']"));
				Actions actions=new Actions(driver);
				actions.moveToElement(Status).doubleClick().perform();
				StatusDropdown=driver.findElement(By.xpath("//a[text()='In Progress']"));
				StatusDropdown.click();
				ChangeStatus=driver.findElement(By.xpath("//a[@title='Completed']"));
				ChangeStatus.click();
				SaveStatus=driver.findElement(By.xpath("//span[text()='Save']/.."));
				SaveStatus.click();
				SaveMessage=driver
						.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
		    wait.until(ExpectedConditions.visibilityOf(SaveMessage));
		    String resulttext = SaveMessage.getText();
		    resulttext.trim();
			System.out.println(resulttext);
				Thread.sleep(3000);
				Refresh=driver.findElement(By.xpath("//button[@title='Refresh']"));
				Refresh.click();
				ShowCharts.click();
				VerifyFinalMessage=driver.findElement(By.xpath("//span[@class='emptychart uiOutputText']"));
				String text = VerifyFinalMessage.getText();
				System.out.println(text);
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
		clickAppLauncherthenContentNewChart("Opened Tasks","Donut Chart","Priority","Status");
	}

}
