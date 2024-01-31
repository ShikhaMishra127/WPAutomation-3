package buyer.testcases.Admin.testcases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Admin {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up the WebDriver instance
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (4)\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginPage() {
        // Open the login page
        driver.get("https://internalwpqa.perfect.com/login.do");

        // Perform login
        WebElement usernameField = driver.findElement(By.id("visibleUname"));
        WebElement passwordField = driver.findElement(By.id("visiblePass"));
        WebElement loginButton = driver.findElement(By.id("login-submit"));

        usernameField.sendKeys("ShikhaM ");
        passwordField.sendKeys("Welcome@2");
        loginButton.click();


        // Verify successful login
//        WebElement welcomeMessage = driver.findElement(By.id("welcome-message"));
//        Assert.assertEquals(welcomeMessage.getText(), "Welcome, ShikhaM");
    }

    @Test
    public void testHomePage() {
        // Open the home page
        driver.get("https://example.com/home");

        // Perform actions on the home page
        // ...

        // Verify something on the home page
        // ...

        // Assert.assertTrue(condition);
    }

    @AfterClass
    public void tearDown() {
        // Quit the WebDriver instance
        driver.quit();
    }
}

