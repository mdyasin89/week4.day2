package week4.day2.assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NykaaDotCom {

	public static void main(String[] args) {
		//1) Go to https://www.nykaa.com/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Actions objaction = new Actions(driver);
		//2) Click Brands and Search L'Oreal Paris
		objaction.moveToElement(driver.findElement(By.xpath("//li/a[text()='brands']"))).build().perform();
		//3) Click L'Oreal Paris
        driver.findElement(By.linkText("L'Oreal Paris")).click();
        //4) Check the title contains L'Oreal Paris
        String windowTitle = driver.getTitle();
        if(windowTitle.contains("L'Oreal Paris")) {
        	System.out.println("Title contains \"L'Oreal Paris\" is verified");
        }
        //5) Click sort By and select customer top rated
        driver.findElement(By.className("sort-name")).click();
        //6) Click Category and click Hair->Click haircare->Shampoo
        //7) Click->Concern->Color Protection
        driver.findElement(By.xpath("//span[contains(text(),'customer top rated')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Category')]")).click();
        driver.findElement(By.xpath("//span[text()='Hair']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Hair Care')]")).click();
        driver.findElement(By.xpath("//span[@class='title' and text()='Shampoo']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Concern')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Color Protection')]")).click();
       //8)check whether the Filter is applied with Shampoo
        List<WebElement> filteredValues = driver.findElements(By.className("filter-value"));
        List<String> checkValues = new ArrayList<String>();
        for(WebElement eachValue: filteredValues)
        {
        	String strValue= eachValue.getText();
        	checkValues.add(strValue);
        }
        if(filteredValues.size()==checkValues.size()) {
        	System.out.println("Filterted Values applied "+checkValues);
        }
        else {
        	System.out.println("Filterted Values not applied");
        }
        //9) Click on L'Oreal Paris Colour Protect Shampoo
        //String test="//div[contains(text(),'L\"Oreal Paris Colour Protect Shampoo')]";
        String test="//div[contains(text(),'Oreal Paris Colour Protect Shampoo')]";
        driver.findElement(By.xpath(test)).click();
        //10) GO to the new window and select size as 175ml  --before need to switch to new window
        Set<String> allWindowsObj = driver.getWindowHandles();
        List<String> allWindowListsObj= new ArrayList<String>(allWindowsObj);
        driver.switchTo().window(allWindowListsObj.get(1));
        WebElement dropSize=driver.findElement(By.xpath("//select[@title='SIZE']"));
        Select objDropSize= new Select(dropSize);
        objDropSize.selectByVisibleText("175ml");
        //11) Print the MRP of the product
        String textMrp = driver.findElement(By.xpath("//div[contains(text(),'inclusive of all taxes')]/preceding-sibling::div//span[contains(text(),'MRP')]/following-sibling::span")).getText();
        System.out.println("MRP is "+textMrp);
        //12) Click on ADD to BAG
        driver.findElement(By.xpath("(//span[contains(text(),'Add to Bag')])[1]")).click();
        //13) Go to Shopping Bag
        driver.findElement(By.xpath("//span[@class='cart-count']/preceding-sibling::*[name()='svg']")).click();
        //14) Print the Grand Total amount
        driver.switchTo().frame(0);
        String textTotal = driver.findElement(By.xpath("(//span[text()='Grand Total']/following::div[1])[1]")).getText();
        System.out.println("Grand Total is is "+textTotal);
        //15) Click Proceed
        driver.findElement(By.xpath("//span[text()='Proceed']")).click();
        //16) Click on Continue as Guest
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
        //17) Check if this grand total is the same in step 14
        String textTotalNew = driver.findElement(By.xpath("//div[text()='Grand Total']/following::div[1]")).getText();
      if(textTotal.equals(textTotalNew)) {
    	  System.out.println("Both Grand Total Matching");
      }
      else {
    	  System.out.println("Both Grand Total Not Matching");
      }
      //18) Close all windows
      driver.quit();
      
	}

}
