package DataFolder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base 
{

    public WebDriver driver;
    public void setup() 
    {
        WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://dev-test.groundmetrx.com/company/login");
		driver.findElement(By.xpath("//input[@placeholder='Enter your username']")).sendKeys("qademo");
		driver.findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys("12345678");
		driver.findElement(By.xpath("//button[@class='btn full-btn']")).sendKeys(Keys.RETURN);
    }

    
 


}
