import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.AssertJUnit.assertTrue;

public class MultipleUses {

    WebDriver driver = new ChromeDriver();
    String dataToSend = "test data sent";

    public void login(String username, String password) throws InterruptedException {
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

    public void createNewTopic(String topic) throws InterruptedException {

        // click on the link to add a new topic
        WebElement newTopic = driver.findElement(By.linkText("Add a new topic"));
        newTopic.click();

        // seeing the input and the button as well

        WebElement inputField = driver.findElement(By.id("id_text"));

        // the logout button is also named submit so it will see it first
        WebElement sublitButton = driver.findElement(By.cssSelector("button[name='submit']:nth-child(3)"));

        // adding the topic name and then send it over

        inputField.sendKeys(topic);
        sublitButton.click();
    }

    public void addNewEntryForTheTopic(String topic) throws InterruptedException {
        createNewTopic(topic); //this will have to be changed

        //clicking the link with the same name
        WebElement selectedTopic = driver.findElement(By.linkText(topic));
        selectedTopic.click();

        // clicking the link to add a new entry
        WebElement addNewEntry = driver.findElement(By.linkText("Add new entry"));
        addNewEntry.click();

        // adding the text to the field and clicking the add button
        WebElement textinput = driver.findElement(By.id("id_text"));
        WebElement addButton = driver.findElement(By.cssSelector("button[name='submit']:nth-child(3)"));

        // sending the data and clicking the button
        textinput.sendKeys(dataToSend);
        addButton.click();
    }
}
