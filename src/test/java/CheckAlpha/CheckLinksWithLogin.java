package CheckAlpha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
// chk
public class CheckLinksWithLogin {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
         WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
    }

    @Test(priority = 1)
    public void loginToPortal() {
        // Navigate to login page
        driver.get("https://portal.groundmetrx.com/home");

               // Enter username and password
               driver.findElement(By.xpath("//a[normalize-space()='Log in']")).click();
               driver.findElement(By.xpath("//input[@id='exampleInputUsername']")).sendKeys("Cunex Inc.");
               driver.findElement(By.xpath("//input[@id='exampleInputPassword1']")).sendKeys("Thenewpasswordiscunex@362");
               driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
       
        // Validate successful login (update with appropriate validation logic)
        String expectedUrl = "https://portal.groundmetrx.com/home";
        if (!driver.getCurrentUrl().equals(expectedUrl)) {
            throw new IllegalStateException("Login failed or incorrect redirection.");
        }
    }

    @Test(priority = 2, dependsOnMethods = "loginToPortal")
    public void checkAllLinks() {
        // Fetch all links on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println("Total links found: " + links.size());

        // Iterate over each link and validate
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                validateLink(url);
            } else {
                System.out.println("Empty or null link: " + link.getText());
            }
        }
    }

    public void validateLink(String linkUrl) {
        try {
            @SuppressWarnings("deprecation")
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000); // Timeout of 5 seconds
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                System.out.println(linkUrl + " -> Valid link (Status: " + responseCode + ")");
            } else {
                System.out.println(linkUrl + " -> Broken link (Status: " + responseCode + ")");
            }
        } catch (Exception e) {
            System.out.println(linkUrl + " -> Exception occurred: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        // Quit browser after test execution
        if (driver != null) {
            driver.quit();
        }
    }
}
