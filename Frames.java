package week4.day2.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Frames {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		driver.switchTo().frame("frame1");
		driver.findElement(By.xpath("//b[@id=\"topic\"]/following::input")).sendKeys("Not a Friendly Topic");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame1");
		driver.switchTo().frame("frame3");
		driver.findElement(By.xpath("//input[@id='a']")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame2");
		WebElement dropdownAnimal = driver.findElement(By.id("animals"));
		Select selObj = new Select(dropdownAnimal);
		selObj.selectByVisibleText("Avatar");
		driver.switchTo().defaultContent();
		String strText = driver.findElement(By.xpath("//span[contains(text(),'Not a Friendly Topic')]")).getText();
		System.out.println("Topic " + strText);
		driver.quit();
	}

}
