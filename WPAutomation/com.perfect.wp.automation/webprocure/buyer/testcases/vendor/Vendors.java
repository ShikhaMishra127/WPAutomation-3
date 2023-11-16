package buyer.testcases.vendor;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vendors {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up the WebDriver instance
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @BeforeMethod
    public void testLoginPage() throws InterruptedException {
        // Open the login page
        driver.get("https://internalwpqa.perfect.com/login.do");

        // Perform login
        WebElement usernameField = driver.findElement(By.id("visibleUname"));
        WebElement passwordField = driver.findElement(By.id("visiblePass"));
        WebElement loginButton = driver.findElement(By.id("login-submit"));

        usernameField.sendKeys("shikham4");
        passwordField.sendKeys("Welcome@1");
        loginButton.click();

        Thread.sleep(5000);


    }

    @Test
    public void vendor_invoice() throws InterruptedException {

        driver.findElement(By.xpath("//*[@title='Invoice']")).click();
        driver.findElement(By.xpath("//*[@title='Create New'][1]")).click();
        Thread.sleep(3000);

        //Invoice creation- Header Information

        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Format the date and time as per your requirement
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        // Find the text box element using its locator
        // Replace "yourLocator" with the appropriate locator strategy and value for your text box
        // For example, By.id("yourTextBoxId") or By.xpath("yourXpathExpression")
        By textBoxLocator = By.xpath("//*[@name='invoicenum']");
        WebElement Invoice_Number = driver.findElement(textBoxLocator);
        String trimmedDateTime = formattedDateTime.replace(" ", "")
                .replace(":", "")
                .replace("-", "");

        Invoice_Number.sendKeys(trimmedDateTime);
        Thread.sleep(4000);

        // Click on issue date
        driver.findElement(By.xpath("//*[@name='issue_date']")).click();
        LocalDate currentDate = LocalDate.now();

        // Find the element representing the current date
        String xpath = String.format("//td[@class='today day']", currentDate.getDayOfMonth());
        WebElement currentDateElement = driver.findElement(By.xpath(xpath));

        // Click on the element to select the current date
        currentDateElement.click();
        Thread.sleep(5000);

        // Click on Due date

        // Calculate the target date (e.g., one day ahead)
        // Get the current date
        currentDate = LocalDate.now();

        // Calculate the target date (e.g., one day ahead)
        LocalDate targetDate = currentDate.plusDays(2);

        // Format the target date as per the calendar's expected format
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedTargetDate = targetDate.format(formatter);

        // Find the calendar input field using its locator
        // Replace "yourLocator" with the appropriate locator strategy and value for the input field
        // For example, By.id("yourInputFieldId") or By.xpath("yourXpathExpression")
        By inputFieldLocator = By.xpath("//*[@name='due_date']");
        WebElement inputField = driver.findElement(inputFieldLocator);

        // Clear the input field
        inputField.clear();

        // Enter the formatted target date in the input field
        inputField.sendKeys(formattedTargetDate);
        Thread.sleep(3000);
        // Find PO Items button
        driver.findElement(By.xpath("/html/body/div[1]/section[3]/form/div[1]/div[1]/button[1]")).click();
        Thread.sleep(5000);

        // Find the iframe element using its XPath
        By iframeLocator = By.xpath("/html/body/div[6]/div[2]/div/div[2]/div/iframe");
        WebElement iframeElement = driver.findElement(iframeLocator);

        // Switch to the iframe
        driver.switchTo().frame(iframeElement);

        driver.findElement(By.xpath("//*[@id=\"cont-search\"]/div[2]/div/table/tbody/tr[1]/td[1]/i")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"itemtable_2036\"]/tbody/tr[1]/th[1]/input")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"cont-search\"]/div[3]/div/button")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@name='invoiceQty_2036_1']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        driver.findElement(By.xpath("//*[@name='invoiceQty_2036_1']")).sendKeys("1");

        driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"save\"]")).click();
      Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/button[2]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div[2]/button[2]")).click();

        WebElement invoice_alert = driver.findElement(By.xpath("//*[@id='myModal']/div[2]/div/div[1]/p"));
        invoice_alert.getText();
//        driver.findElement(By.xpath("//*[@id=\"myModal\"]/div[2]/div/div[1]/p")).getText();
        Thread.sleep(2000);
        Assert.assertEquals(invoice_alert.getText(), "Invoice was submitted successfully");

    }



@AfterMethod(enabled = false)
public void teardownaftertest(){
        //quit the vendor
}


    @AfterClass(enabled = false)
    public void tearDown() {
        // Quit the WebDriver instance
        driver.quit();
    }
}

