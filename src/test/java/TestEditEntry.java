import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class TestEditEntry{
    WebDriver driver;
    String url = "http://127.0.0.1:8000/";
    String username = "test2";
    String password = "qwerty21!";
    String topic = "Test topic";
    String oldData = "test data";

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
    public void editAnEntry() throws InterruptedException {

        /*
        * log in
        * choose the topic
        * choose the top entry
        * click Edit
        * clear the text
        * add the new data
        * click save
        * */
        login();
        Thread.sleep(200);

        // choose the top topic
        WebElement clickTopic = driver.findElement(By.linkText(topic));
        clickTopic.click();

        // the old text
        WebElement oldEntry = driver.findElement(By.className("card-body"));
        String old = oldEntry.getText();

        // click on the edit link
        WebElement editLink = driver.findElement(By.linkText("edit entry"));
        editLink.click();

        // select the area to write
        WebElement canvas = driver.findElement(By.id("id_text"));
        canvas.clear();
        canvas.sendKeys(oldData);
        Thread.sleep(200);

        //click on the save button
        WebElement save = driver.findElement(By.cssSelector("button[name='submit']:nth-child(3)"));
        save.click();
        Thread.sleep(100);

        // get the new data
        WebElement newEntry = driver.findElement(By.className("card-body"));

        assertFalse("The new entry was not changed",newEntry.getText().contains(old));
    }
    
}
