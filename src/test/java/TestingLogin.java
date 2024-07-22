import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration; // Import Duration from java.time

import static org.testng.AssertJUnit.assertTrue;

public class TestingLogin {

    String url = "http://127.0.0.1:8000/";
    WebDriver driver;

    @BeforeTest
    public void openingThePage(){
        // open page
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void closeThePage(){
        if(driver != null){
            driver.close();
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData(){
        return new Object[][]{
                {"David", "qwerty21!"},//fail
                {"Alice", "alicepwd"},
                {"Mihail","muie22"}, // fail
                {"Bob", "bobspassword"},//fail
                {"david", "qwerty21!"},//pass
                //{"ll_admin", "test"},//pass
                //{"test1", "qwerty21!"},//pass
                //{"test2", "qwerty21!"},//pass
                //{"user", "qwerty21!"}//pass
                        };
    }

    //@Test
    @Test(dataProvider = "loginData")
    @Parameters({"username", "password"}) // for the username-password.xml file (to run right-click the file and choose run)
    public void accessThePage(String username, String password) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Specify timeout using Duration


        // Click the login button
        WebElement loginButton = driver.findElement(By.className("nav-link"));
        loginButton.click();

        // Enter the username and password
        WebElement usernameField = driver.findElement(By.id("id_username"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("id_password"));
        passwordField.sendKeys(password);

        Timeoff(100);

        // Click the login button
        WebElement loginButton2 = driver.findElement(By.cssSelector(".btn.btn-primary[type='submit']"));
        loginButton2.click();

        // Sleep for 2 seconds to wait for the next page to load
        Timeoff(1000);

        // check if the url has changed
        String expected = "http://127.0.0.1:8000/topics/";
        String actual = driver.getCurrentUrl();;

        // logging out
        //WebElement logoutButton = driver.findElement(By.cssSelector(".btn.btn-outline-secondary.btn-sm[]"));

        assertTrue("Login for username: " + username + " and password " + password, expected.contains(actual));

        // Logout after successful login
        // this will wait untill the logout button is visible
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='submit'].btn.btn-outline-secondary.btn-sm")));
        logoutButton.click();
    }

    public void Timeoff(int miliseconds) throws InterruptedException {
        Thread.sleep(miliseconds);
    }

}
