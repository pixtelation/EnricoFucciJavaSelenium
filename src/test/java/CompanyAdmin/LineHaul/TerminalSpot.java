package CompanyAdmin.LineHaul;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

// Importing manual created class for Code-Reusability

import DataFolder.Data;


public class TerminalSpot extends Data
{
    
    WebDriver driver;
	String msg = "AVR has been successfully inserted";

	static int RanTermi = Data.getRandomTerminalNumber();
	static long RanPhone = Data.getRandomPhoneNumber();

// Initializing driver & Closing after task executed
@BeforeSuite	
	public void OpenBrowser() 
	{
        //	ChromeOptions options = new ChromeOptions();
        //	options.addArguments("--headless");
	
		// WebDriverManager.safaridriver().setup();
		// driver = new SafariDriver();	


		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();


		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
		driver.get("https://dev-test.groundmetrx.com/company/login");
		driver.findElement(By.xpath("//input[@placeholder='Enter your username']")).sendKeys("DHL");
		driver.findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys("DHL@123456");
		driver.findElement(By.xpath("//button[@class='btn full-btn']")).sendKeys(Keys.RETURN);
		

    }



    @Test 
    public void AddTerminal() throws InterruptedException
    {   
		driver.findElement(By.xpath("//a[normalize-space()='Line Haul']")).click();
		driver.findElement(By.xpath("//a[text()='Terminals/Spots']")).click();
		driver.findElement(By.linkText("Add New Terminal")).click();

		WebElement location = driver.findElement(By.id("terminal_address")); //
		location.sendKeys(RandTerminalStr);
		Thread.sleep(4000);
		location.sendKeys(Keys.ARROW_DOWN);
		location.sendKeys(Keys.ENTER);

		driver.findElement(By.id("terminal_number")).sendKeys(Integer.toString(RanTermi));
		driver.findElement(By.id("terminal_name")).sendKeys(TerminalName + RandTerminalStr); 
        driver.findElement(By.id("abbreviation")).sendKeys(RandTerminalStr);
		driver.findElement(By.id("email")).sendKeys(RandTerminalEmail + TerminalEmail);
		driver.findElement(By.id("phone")).sendKeys(Long.toString(RanPhone));

		driver.findElement(By.xpath("//button[@class='w-100 btn btn-primary']")).click();
		driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']")).click();
		
		//dhs
    }
    
	
    

	@Test
	public void ShowTerminalData()
	{
		driver.findElement(By.xpath("//a[normalize-space()='Line Haul']")).click();
		driver.findElement(By.xpath("//a[text()='Terminals/Spots']")).click();

		WebElement table = driver.findElement(By.xpath("/html/body/main/div/div/div/div[2]/div[5]/div[1]/div[1]/div[2]/div/table/tbody"));
		List<WebElement> rows = new ArrayList<WebElement>();
		rows = table.findElements(By.tagName("tr"));
		 for (WebElement row : rows) 
		 	{
	            // Get all columns for each row
	            List<WebElement> columns = row.findElements(By.tagName("td"));
	            // Iterate through columns
	            for (WebElement column : columns) {
	            	
	            	
	                // Print text content of each cell
	                System.out.print(column.getText() + "\t");
	            
	            }
	            // Move to the next line after printing all columns of a row
	            System.out.println();
	        }

	}

	
	


	
	


}
