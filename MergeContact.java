package week4.day2.assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebElement userName = driver.findElement(By.id("username"));
		WebElement passWord = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.className("decorativeSubmit"));
		userName.sendKeys("Demosalesmanager");
		passWord.sendKeys("crmsfa");
		loginButton.click();
		WebElement crmsfaLink = driver.findElement(By.linkText("CRM/SFA"));
		crmsfaLink.click();
		// click on contact button
		driver.findElement(By.linkText("Contacts")).click();
		// click on merge contacts using xpath
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		// click on widget of from contact
		driver.findElement(By.xpath("//span[text()='From Contact']/ancestor::tr//img[@alt='Lookup']")).click();
		Set<String> allWindows = driver.getWindowHandles();
		List<String> listAllWindows = new ArrayList<String>(allWindows);
		driver.switchTo().window(listAllWindows.get(1));
		// click on first contact
		driver.findElement(By.xpath("(//table/tbody/tr/td[1]//a[@class='linktext'])[1]")).click();
		driver.switchTo().window(listAllWindows.get(0));
		// click on widget of from contact
		driver.findElement(By.xpath("//span[text()='To Contact']/ancestor::tr//img[@alt='Lookup']")).click();
		Set<String> allWindows2 = driver.getWindowHandles();
		List<String> listAllWindows2 = new ArrayList<String>(allWindows2);
		driver.switchTo().window(listAllWindows2.get(1));
		// click on second contact
		driver.findElement(By.xpath("(//table/tbody/tr/td[1]//a[@class='linktext'])[2]")).click();
		driver.switchTo().window(listAllWindows2.get(0));
		// click on merge button
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		Alert objAlert = driver.switchTo().alert();
		objAlert.accept();
		//Thread.sleep(2000);
		String windowTitle = driver.getTitle();
		System.out.println(windowTitle);
		driver.close();
	}

}
