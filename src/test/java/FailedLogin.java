import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration; // Import Duration from java.time

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.time.Duration;

public class FailedLogin {String url = "http://127.0.0.1:8000/";
    WebDriver driver;

    @BeforeTest
    public void openingThePage(){
        // open page
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--search-engine-choice-country");

        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void closeThePage(){
        if(driver != null){
            driver.close();
        }
    }

    @Test
    @Parameters({"username","password"})
    public void failedLogin(@Optional ("alice") String username,@Optional ("alicepassword")  String password){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Specify timeout using Duration


        // Click the login button
        WebElement loginButton = driver.findElement(By.className("nav-link"));
        loginButton.click();

        // Enter the username and password
        WebElement usernameField = driver.findElement(By.id("id_username"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("id_password"));
        passwordField.sendKeys(password);


        // Click the login button
        WebElement loginButton2 = driver.findElement(By.cssSelector(".btn.btn-primary[type='submit']"));
        loginButton2.click();

        // check if the url has changed
        String expected = "http://127.0.0.1:8000/topics/";
        String actual = driver.getCurrentUrl();;


        assertFalse("Login for username: " + username + " and password " + password, expected.contains(actual));
    }

}
