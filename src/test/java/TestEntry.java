import com.beust.jcommander.Parameter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class TestEntry {
    String url = "http://127.0.0.1:8000/";
    WebDriver driver;
    String username = "test2";
    String password = "qwerty21!";
    String topic = "Test topic";
    String dataToSend = "In the quiet of night, stars softly gleam,\n" +
            "Whispering secrets in a cosmic stream.\n" +
            "Moonlight dances on a tranquil sea,\n" +
            "Painting dreams for you and me.\n" +
            "\n" +
            "A gentle breeze, a rustling sigh,\n" +
            "Nature's lullaby as time drifts by.\n" +
            "Silence wraps us in its soothing embrace,\n" +
            "A moment of peace, a sacred place.\n" +
            "\n" +
            "In this world of rush and roar,\n" +
            "Find solace on this tranquil shore.\n" +
            "Where worries fade and spirits mend,\n" +
            "In the beauty of this quiet blend.\n" +
            "\n" +
            "So close your eyes, let your soul take flight,\n" +
            "In the gentle embrace of the night.\n" +
            "For in the stillness, we find our way,\n" +
            "To a brighter, more peaceful day.";

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

    public void createNewTopic(String topic) throws InterruptedException {
        Thread.sleep(100);

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

    @Test
    public void addNewEntryForTheTopic() throws InterruptedException {
        login();
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
        Thread.sleep(100);
        addButton.click();

        // checks if the data was added to the entry and then returns to the previous page
        WebElement checkIfLinkIsThere = driver.findElement(By.linkText("Add new entry"));
        String  expected = "Add new entry";
        assertTrue("There is an error in seeing either the input field or the button is not clicked", expected.contains(checkIfLinkIsThere.getText()));
    }


}