package week4.day2.assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkWithMultipleWindows {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// click on home page button
		driver.findElement(By.id("home")).click();
		System.out.println("New window opened by clicking Home page button");
		// click on Multiple Windows button
		driver.findElement(By.xpath("//button[contains(text(),'Multiple Windows')]")).click();
		Set<String> setWindowsObj = driver.getWindowHandles();
		System.out.println("number of window Opened" + setWindowsObj.size());
		// close all window except original
		driver.findElement(By.xpath("//button[contains(text(),'Do not close me')]")).click();
		Set<String> setWindowsObj2 = driver.getWindowHandles();
		List<String> listWindowsObj2 = new ArrayList<String>(setWindowsObj2);
		for (int i = 1; i < listWindowsObj2.size(); i++) {
			driver.switchTo().window(listWindowsObj2.get(i));
			driver.close();
		}
		driver.switchTo().window(listWindowsObj2.get(0));
		// wait for 2 seconds
		driver.findElement(By.xpath("//button[contains(text(),'Wait for 5 seconds')]")).click();
		System.out.println("New window opened");
		driver.quit();
	}

}
