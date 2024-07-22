import jdk.jshell.spi.ExecutionControl;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.Keys.PAGE_DOWN;
import static org.testng.AssertJUnit.assertTrue;

public class TestNewTopic {


    String url = "http://127.0.0.1:8000/";
    WebDriver driver;
    String username = "test2";
    String password = "qwerty21!";

    @BeforeTest
    public void openPage(){
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void closePage(){
        if(driver != null){
            driver.close();
        }
    }

    public void login() throws InterruptedException {
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

        Thread.sleep(100);
    }

    @Test
    public void newTopic() throws InterruptedException {
        login();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // click on the link to add a new topic
        WebElement newTopic = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add a new topic")));//driver.findElement(By.linkText("Add a new topic"));
        newTopic.click();

        // seeing the input and the button as well

        WebElement inputField = driver.findElement(By.id("id_text"));

        // the logout button is also named submit so it will see it first
        WebElement sublitButton = driver.findElement(By.cssSelector("button[name='submit']:nth-child(3)"));


        // adding the topic name and then send it over
        inputField.sendKeys("Some test data");
        Thread.sleep(100);

        sublitButton.click();

        String expected = "http://127.0.0.1:8000/topics/";
        String actual = driver.getCurrentUrl();

        assertTrue("There is an error in seeing either the input field or the button is not clicked", expected.equals(actual));
    }
}
