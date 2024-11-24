package MavenTest.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

    protected WebDriver driver;
    protected WebDriverWait wait;


    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login() {
        driver.get("https://dev.groundmetrx.com/company/login");
        driver.findElement(By.xpath("//input[@placeholder='Enter your username']")).sendKeys("qademo");
        driver.findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@class='btn full-btn']")).click();
    }

}
