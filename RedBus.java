package week4.day2.assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBus {

	public static void main(String[] args) throws InterruptedException {
		//Launch the url https://www.redbus.in/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.redbus.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Enter From -Madiwala Bangalore
		driver.findElement(By.id("src")).sendKeys("Madiwala, Bangalore");
		driver.findElement(By.xpath("//input[@id='src']/parent::div/ul[@class='autoFill homeSearch']//li[@select-id='results[0]']")).click();
		//Enter To Koyambedu Chennai
		driver.findElement(By.id("dest")).sendKeys("Koyambedu, Chennai");
		driver.findElement(By.xpath("//input[@id='dest']/parent::div/ul[@class='autoFill homeSearch']//li[@select-id='results[0]']")).click();
		//Select the Date 10-Jun-2022(first available date)
		driver.findElement(By.xpath("//label[@for='onward_cal']")).click();
		driver.findElement(By.xpath("//td[@class='wd day']")).click();
		//Click Search buses
		driver.findElement(By.id("search_btn")).click();
		Thread.sleep(4000);
		//Click After 6pm under Departure time
		driver.findElement(By.xpath("//input[@id='dtAfter 6 pm']/parent::li/label[@title='After 6 pm']")).click();
		//Click Sleeper under Bus types
		driver.findElement(By.xpath("//label[@title='SLEEPER']")).click();
		//Select the Primo
		driver.findElement(By.xpath("//span[contains(text(),'Primo Bus')]")).click();	
		Actions objaction = new Actions(driver);
		//scroll until the page bottom
		objaction.sendKeys(Keys.PAGE_DOWN).build().perform();
		boolean displayedFooter= false;
		do {
			objaction.sendKeys(Keys.PAGE_DOWN).build().perform();
			if(driver.findElements(By.xpath("//button[text()='CLEAR ALL FILTERS']")).size()!=0)
			{
			displayedFooter= true;
			}
		}while(!displayedFooter);
		//Get the number of buses found
		List<WebElement> noOfBuses = driver.findElements(By.xpath("//div[@class='seat-fare ']/div[contains(@class,'fare')]/span"));
		System.out.println("Number of Buses " + noOfBuses.size());
		List<Integer> busFare = new ArrayList<Integer>();
		for (WebElement eachBus : noOfBuses) {
			String strBusFare = eachBus.getText();
			busFare.add(Integer.valueOf(strBusFare));
		}
		Collections.sort(busFare);
		//Get the Bus fare and sort them in ascending order
		System.out.println("Bus fares in ascending order " + busFare);
		//Close the application
		driver.close();

	}

}
