package week4.day2.assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForce {
	public void createDashboard() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// 1. Login to https://login.salesforce.com
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password@123");
		driver.findElement(By.id("Login")).click();
		// 2. Click on the toggle menu button from the left corner
		driver.findElement(By.className("slds-icon-waffle")).click();
		// 3. Click View All
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(3000);
		// click Dash boards from App Launcher
		WebElement eleDashboard = driver.findElement(By.xpath("//p[text()='Dashboards']"));
		JavascriptExecutor jsObj = (JavascriptExecutor) driver;
		// able to click element become visible
		driver.executeScript("arguments[0].scrollIntoView(true);", eleDashboard);
		driver.findElement(By.xpath("//p[text()='Dashboards']")).click();
		// 4. Click on the New Dash board option
		driver.findElement(By.xpath("//div[@title='New Dashboard']")).click();
		// 5.Handle the frame
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));
		// 6. Enter Name as 'Sales force Automation by Your Name '
		driver.findElement(By.id("dashboardNameInput")).sendKeys("Salesforce Automation by Yasin");
		// Click on Create
		driver.findElement(By.id("submitBtn")).click();
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		// Click on Save
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(4000);
		// Click on Done
		driver.findElement(By.xpath("//button[text()='Done']")).click();
		// Verify Dash board name
		String strDashboardName = driver.findElement(By.xpath("//span[text()='Dashboard']/following-sibling::span"))
				.getText();
		if (strDashboardName.equalsIgnoreCase("Salesforce Automation by Yasin")) {
			System.out.println("Dashboared created sucessfully");
		}
		driver.quit();
	}

	public void editDashboard() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// 1. Login to https://login.salesforce.com
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password@123");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(3000);
		// 2. Click on the toggle menu button from the left corner
		driver.findElement(By.className("slds-icon-waffle")).click();
		// 3. Click View All
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(3000);
		// click Dash boards from App Launcher
		WebElement eleDashboard = driver.findElement(By.xpath("//p[text()='Dashboards']"));
		JavascriptExecutor jsObj = (JavascriptExecutor) driver;
		// able to click element become visible
		jsObj.executeScript("arguments[0].scrollIntoView(true);", eleDashboard);
		driver.findElement(By.xpath("//p[text()='Dashboards']")).click();
		// 4. Click on the Dashboards tab
		WebElement eleDashboard2 = driver.findElement(By.xpath("//a[@title='Dashboards']"));
		jsObj.executeScript("arguments[0].click();", eleDashboard2);
		// 5. Search the Dashboard 'Salesforce Automation by Your Name'
		driver.findElement(By.xpath("//div[@data-aura-class='folderActionBar']//input")).sendKeys("by Yasin");
		// 6. Click on the Dropdown icon and Select Edit
		driver.findElement(By.xpath("//tr[1]/td//button[@type='button']//*[name()='svg']")).click();
		driver.findElement(By.xpath("//span[@class='slds-truncate' and text()='Edit']")).click();
		// 7.Click on the Edit Dashboard Properties icon
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span[contains(text(),'Edit Dashboard Properties')]/preceding-sibling::*[name()='svg']")).click();
		// 8. Enter Description as 'SalesForce' and click on save.
		driver.findElement(By.id("dashboardDescriptionInput")).clear();
		driver.findElement(By.id("dashboardDescriptionInput")).sendKeys("SalesForce");
		driver.findElement(By.id("submitBtn")).click();
		// 9. Click on Done and Click on save in the popup window displayed.
		Thread.sleep(5000);
		// driver.switchTo().defaultContent();
		// driver.switchTo().frame(0);
		// Click on Done
		driver.findElement(By.xpath("//button[text()='Done']")).click();
		Thread.sleep(2000);
		// Click on Save
		driver.findElement(By.xpath("//footer/button[text()='Save']")).click();
		// Verify Dash board name
		String strDashboardName = driver
				.findElement(By.xpath("//div[@class='info']//p[@class='slds-page-header__info']")).getText();
		if (strDashboardName.equalsIgnoreCase("SalesForce")) {
			System.out.println("Dashboared edited sucessfully");
		}
		driver.quit();

	}

	public static void main(String[] args) throws InterruptedException {
		SalesForce objSalesForce = new SalesForce();
		objSalesForce.createDashboard();
		objSalesForce.editDashboard();
	}

}
