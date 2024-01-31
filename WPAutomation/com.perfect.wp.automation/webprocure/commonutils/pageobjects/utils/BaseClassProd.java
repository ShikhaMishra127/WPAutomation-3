//package commonutils.pageobjects.utils;// BaseClass.java
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;

package commonutils.pageobjects.utils;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class BaseClassProd {
    public WebDriver driver;
    private String username = "your_username";
    private String password = "your_password";
    protected ExtentReports reports;
    protected static ExtentTest test;
   ExtentSparkReporter htmlReporter;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Set the system property for the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win32(2)\\chromedriver.exe");
//        ChromeOptions co = new ChromeOptions();
//        co.setBinary();

//        DesiredCapabilities desc = DesiredCapabilities.chrome();
//        driver = new RemoteWebDriver(new URL("https://rdus.proactis.com"),desc);

        // Initialize the ChromeDriver instance
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    public void create_payment_voucher_prod() throws InterruptedException {

        WebElement Invoice_tab= driver.findElement(By.xpath("//a[@title='Invoice']"));
        Invoice_tab.click();

        WebElement create_PV = driver.findElement(By.xpath("//a[@title='Create Payment Voucher']"));
        create_PV.click();

        // Select Supplier
        driver.findElement(By.xpath("//input[@placeholder='No supplier selected']")).sendKeys("Shikha Prod Vendor");
        driver.findElement(By.xpath("//span[@class='supplier-text']")).click();

        driver.findElement(By.xpath("//textarea[@placeholder='Enter justification note']")).sendKeys("Just for " +
                "testing purpose");


        driver.findElement(By.xpath("/html/body/b-root/div/wp-payment-voucher/div/wp-create/div/div[2]/div[3]/div[2" +
                "]/ngx-select-dropdown/div/button")).click();
        driver.findElement(By.xpath("/html/body/b-root/div/wp-payment-voucher/div/wp-create/div/div[2]/div[3]/div[2" +
                "]/ngx-select-dropdown/div/div/ul[2]/li[1]")).click();

        // Receive Date
        driver.findElement(By.xpath("//input[@placeholder='Enter Receive Date']")).click();
        LocalDate currentDate1 = LocalDate.now();

        // Find the element representing the current date
        String xpath1 = String.format("//span[@class='bg-light ng-star-inserted']", currentDate1.getDayOfMonth());
        WebElement currentDateElement1 = driver.findElement(By.xpath(xpath1));
        currentDateElement1.click();

        //Post Date
        driver.findElement(By.xpath("//input[@placeholder='Enter Post Date']")).click();
        LocalDate currentDate2 = LocalDate.now();

        // Find the element representing the current date
        String xpath2 = String.format("//span[@class='bg-light ng-star-inserted']", currentDate2.getDayOfMonth());
        WebElement currentDateElement2 = driver.findElement(By.xpath(xpath2));
        currentDateElement2.click();

        //Issue Date
        driver.findElement(By.xpath("//input[@placeholder='Enter Issue Date']")).click();
        LocalDate currentDate3 = LocalDate.now();

        // Find the element representing the current date
        String xpath3 = String.format("//span[@class='bg-light ng-star-inserted']", currentDate2.getDayOfMonth());
        WebElement currentDateElement3 = driver.findElement(By.xpath(xpath3));
        currentDateElement3.click();

        //Due Date

        LocalDate currentDate4 = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

//        currentDate = LocalDate.now();
        LocalDate targetDate = currentDate4.plusDays(2);
        formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedTargetDate = targetDate.format(formatter);

        By inputFieldLocator = By.xpath("//input[@placeholder='Enter Due Date']");
        WebElement inputField = driver.findElement(inputFieldLocator);
        inputField.clear();
        // Enter the formatted target date in the input field
        inputField.sendKeys(formattedTargetDate);
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[normalize-space()='Add Items']")).click();

        boolean contract_number_toggle;
        if (contract_number_toggle= true)
        {
            driver.findElement(By.xpath("//label[@for='contractNumberEnable']")).click();
        }

//        driver.findElement(By.xpath("//label[@for='contractNumberEnable']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@placeholder='Enter Commodity Code or Key']")).sendKeys("**");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/modal-container/div/div/div[2]/wp-modal-content/div/div/div[4]/table/tr[5]/td[3]/input")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();

        // supplier Invoice number

        //Invoice creation- Header Information
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Format the date and time as per your requirement
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter2);

        By textBoxLocator = By.xpath("//div[@class='col-3']//div[@class='input-group mb-3']//input[@type='text']");
        WebElement Supplier_Invoice_Number = driver.findElement(textBoxLocator);
        String trimmedDateTime = formattedDateTime.replace(" ", "")
                .replace(":", "")
                .replace("-", "");

        Supplier_Invoice_Number.sendKeys(trimmedDateTime);
        Thread.sleep(4000);

        // Enter Quantity
        driver.findElement(By.xpath("//input[@placeholder='Enter Quantity']")).sendKeys("10");

        driver.findElement(By.xpath("//input[@placeholder='Enter Unit Price']")).sendKeys("10");

        driver.findElement(By.xpath("//ngx-select-dropdown[@class='ng-invalid ng-star-inserted ng-touched " +
                "ng-dirty']//span[@class='display-text'][normalize-space()='Select']")).click();

        driver.findElement(By.xpath("//li[normalize-space()='EA-each']")).click();

        driver.findElement(By.xpath("//button[normalize-space()='Add Account Distribution']")).click();

        driver.findElement(By.xpath("//input[@placeholder='Enter Cost Center']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='1234|5678||']")).click();

        driver.findElement(By.xpath("//button[normalize-space()='Distribute Evenly']")).click();

        driver.findElement(By.xpath("//div[@class='col-12 text-center']//button[@class='btn btn-wp'][normalize-space" +
                "()='Save']")).click();


        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Scroll down till the bottom of the page
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        Thread.sleep(4000);
        driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();


    }

    public void create_contract_prod() throws InterruptedException{

        WebElement contract_tab = driver.findElement(By.xpath("//a[@title='Contracts']"));
        contract_tab.click();

        WebElement create_new_contract = driver.findElement(By.xpath("//a[@title='Create new Contract']"));
        create_new_contract.click();

        // Create Contract
        Select Contract_type = new Select(driver.findElement(By.id("contractType")));
        Contract_type.selectByValue("6");


        // Locate the textbox element
        WebElement textBoxElement = driver.findElement(By.xpath("//*[@id=\"contractNumber\"]"));
        // Create an Actions object
        Actions actions = new Actions(driver);
        Thread.sleep(3000);
        // Double click on the textbox
        actions.doubleClick(textBoxElement).perform();
        Thread.sleep(3000);
        // Use keyboard shortcuts to copy the text
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        Thread.sleep(3000);
        // Verify the copied text if needed
        String copiedText = getClipboardText(); // Implement a method to get the text from the clipboard
        System.out.println("Copied Text: " + copiedText);



        Select Contract_visibility = new Select(driver.findElement(By.id("contractAccessType")));
        Contract_visibility.selectByValue("Public");

        WebElement Contract_title = driver.findElement(By.xpath("//input[@id='title']"));
        Contract_title.sendKeys("TestStage");

        WebElement commodities = driver.findElement(By.xpath("//button[@id='selectCatButton']"));
        commodities.click();

        WebElement commodities_select = driver.findElement(By.xpath("//li[4]//span[1]//span[2]"));
        commodities_select.click();

        driver.findElement(By.xpath("//button[@class='btn btn-wp'][normalize-space()='Close']")).click();

        driver.findElement(By.xpath("//button[@id='contractbtnslt']")).click();


        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
            // Perform actions or check conditions to identify the target window
        }

        // Example declaration within the class
//        String originalHandle = "someValue";


        driver.findElement(By.id("sname_action")).sendKeys("Shikha Stage vendor");
        driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

        driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[1]/img[1]")).click();
        Thread.sleep(4000);

        Set<String> windowHandles1 = driver.getWindowHandles();

        for (String handle : windowHandles1) {
            driver.switchTo().window(handle);
            // Perform actions or check conditions to identify the target window
        }
//
//        driver.switchTo().defaultContent();

        Select Pricing_type = new Select(driver.findElement(By.xpath("//*[@id=\"contractPricingType\"]")));
        Pricing_type.selectByValue("Fixed Price");

        Select Total_Value_condition = new Select(driver.findElement(By.id("tempcontractTotalValueCondition")));
        Total_Value_condition.selectByValue("Fixed");

        driver.findElement(By.xpath("//input[@id='maxValue']")).sendKeys("500");

        // Award Date
        driver.findElement(By.id("award_date")).click();
        LocalDate currentDate2 = LocalDate.now();

        // Find the element representing the current date
        String xpath2 = String.format("//td[@class='today day']", currentDate2.getDayOfMonth());
        WebElement currentDateElement2 = driver.findElement(By.xpath(xpath2));
        currentDateElement2.click();


        // Effective Date
        driver.findElement(By.xpath("//*[@id=\"effective_date\"]")).click();
        LocalDate currentDate3 = LocalDate.now();
        // Find the element representing the current date
        String xpath3 = String.format("//td[@class='today day']", currentDate3.getDayOfMonth());
        WebElement currentDateElement3 = driver.findElement(By.xpath(xpath3));
        currentDateElement3.click();


        // Expiration Date
        LocalDate currentDate4 = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

//        currentDate = LocalDate.now();
        LocalDate targetDate = currentDate4.plusDays(2);
        formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedTargetDate = targetDate.format(formatter);

        By inputFieldLocator = By.xpath("//*[@id=\"expiry_date\"]");
        WebElement inputField = driver.findElement(inputFieldLocator);
        inputField.clear();
        // Enter the formatted target date in the input field
        inputField.sendKeys(formattedTargetDate);
        Thread.sleep(3000);

        // Click on the Next button
        driver.findElement(By.xpath("//*[text()='Next Step']")).click();

        //Click on the Next button
        driver.findElement(By.xpath("//*[text()='Next']")).click();

        // Click on the Next button
        driver.findElement(By.xpath("//*[@id=\"theform\"]/div[2]/div/div/button[3]")).click();
        // Click on the Next button
        driver.findElement(By.xpath("//*[text()='Next']")).click();

        // Contracts Documents
        driver.findElement(By.xpath("//*[text()='Next']")).click();

        // Authorization page
        driver.findElement(By.xpath("//*[@id=\"authorize_head_org\"]")).click();

        // click on the Finished button
        driver.findElement(By.xpath("//*[text()='Finished']")).click();

        // click on the submit button
        driver.findElement(By.xpath("//*[text()='Submit']")).click();

        // Click on the element to select the current date
//        Thread.sleep(3000);

        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
        }

    }




    public void Edit_Payment_Voucher_prod() throws InterruptedException
    {
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        WebElement Invoice_tab= driver.findElement(By.xpath("//a[@title='Invoice']"));
        Invoice_tab.click();

        WebElement create_PV = driver.findElement(By.xpath("//a[@title='Create Payment Voucher']"));
        create_PV.click();

        // Select Supplier
        driver.findElement(By.xpath("//input[@placeholder='No supplier selected']")).sendKeys("Shikha Prod Vendor");
        driver.findElement(By.xpath("//span[@class='supplier-text']")).click();

        driver.findElement(By.xpath("//textarea[@placeholder='Enter justification note']")).sendKeys("Just for " +
                "testing purpose");


        // Copy the Payment Voucher Number
        WebElement textBoxElement = driver.findElement(By.xpath("/html/body/b-root/div/wp-payment-voucher/div/wp-create/div/div[2]/div[1]/div/div/div[2]/div/input"));

        // Create an Actions object
        Actions actions = new Actions(driver);
        Thread.sleep(3000);
        // Double click on the textbox
        actions.doubleClick(textBoxElement).perform();
        Thread.sleep(3000);
        // Use keyboard shortcuts to copy the text
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        Thread.sleep(3000);
        // Verify the copied text if needed
        String copiedText = getClipboardText(); // Implement a method to get the text from the clipboard
        System.out.println("Copied Text: " + copiedText);



        driver.findElement(By.xpath("/html/body/b-root/div/wp-payment-voucher/div/wp-create/div/div[2]/div[3]/div[2" +
                "]/ngx-select-dropdown/div/button")).click();
        driver.findElement(By.xpath("/html/body/b-root/div/wp-payment-voucher/div/wp-create/div/div[2]/div[3]/div[2" +
                "]/ngx-select-dropdown/div/div/ul[2]/li[1]")).click();

        // Receive Date
        driver.findElement(By.xpath("//input[@placeholder='Enter Receive Date']")).click();
        LocalDate currentDate1 = LocalDate.now();

        // Find the element representing the current date
        String xpath1 = String.format("//span[@class='bg-light ng-star-inserted']", currentDate1.getDayOfMonth());
        WebElement currentDateElement1 = driver.findElement(By.xpath(xpath1));
        currentDateElement1.click();

        //Post Date
        driver.findElement(By.xpath("//input[@placeholder='Enter Post Date']")).click();
        LocalDate currentDate2 = LocalDate.now();

        // Find the element representing the current date
        String xpath2 = String.format("//span[@class='bg-light ng-star-inserted']", currentDate2.getDayOfMonth());
        WebElement currentDateElement2 = driver.findElement(By.xpath(xpath2));
        currentDateElement2.click();

        //Issue Date
        driver.findElement(By.xpath("//input[@placeholder='Enter Issue Date']")).click();
        LocalDate currentDate3 = LocalDate.now();

        // Find the element representing the current date
        String xpath3 = String.format("//span[@class='bg-light ng-star-inserted']", currentDate2.getDayOfMonth());
        WebElement currentDateElement3 = driver.findElement(By.xpath(xpath3));
        currentDateElement3.click();

        //Due Date

        LocalDate currentDate4 = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

//        currentDate = LocalDate.now();
        LocalDate targetDate = currentDate4.plusDays(2);
        formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedTargetDate = targetDate.format(formatter);

        By inputFieldLocator = By.xpath("//input[@placeholder='Enter Due Date']");
        WebElement inputField = driver.findElement(inputFieldLocator);
        inputField.clear();
        // Enter the formatted target date in the input field
        inputField.sendKeys(formattedTargetDate);
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[normalize-space()='Add Items']")).click();

        boolean contract_number_toggle;
        if (contract_number_toggle= true)
        {
            driver.findElement(By.xpath("//label[@for='contractNumberEnable']")).click();
        }

//        driver.findElement(By.xpath("//label[@for='contractNumberEnable']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@placeholder='Enter Commodity Code or Key']")).sendKeys("**");

        driver.findElement(By.xpath("/html/body/modal-container/div/div/div[2]/wp-modal-content/div/div/div[4]/table/tr[5]/td[3]/input")).click();

        driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();

        // supplier Invoice number

        //Invoice creation- Header Information
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Format the date and time as per your requirement
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter2);

        By textBoxLocator = By.xpath("//div[@class='col-3']//div[@class='input-group mb-3']//input[@type='text']");
        WebElement Supplier_Invoice_Number = driver.findElement(textBoxLocator);
        String trimmedDateTime = formattedDateTime.replace(" ", "")
                .replace(":", "")
                .replace("-", "");

        Supplier_Invoice_Number.sendKeys(trimmedDateTime);
        Thread.sleep(4000);

        // Enter Quantity
        driver.findElement(By.xpath("//input[@placeholder='Enter Quantity']")).sendKeys("10");

        driver.findElement(By.xpath("//input[@placeholder='Enter Unit Price']")).sendKeys("10");

        driver.findElement(By.xpath("//ngx-select-dropdown[@class='ng-invalid ng-star-inserted ng-touched " +
                "ng-dirty']//span[@class='display-text'][normalize-space()='Select']")).click();

        driver.findElement(By.xpath("//li[normalize-space()='EA-each']")).click();

        driver.findElement(By.xpath("//button[normalize-space()='Add Account Distribution']")).click();

        driver.findElement(By.xpath("//input[@placeholder='Enter Cost Center']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='1234|5678||']")).click();

        driver.findElement(By.xpath("//button[normalize-space()='Distribute Evenly']")).click();

        driver.findElement(By.xpath("//div[@class='col-12 text-center']//button[@class='btn btn-wp'][normalize-space" +
                "()='Save']")).click();


        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Scroll down till the bottom of the page
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        Thread.sleep(4000);

        // Save the Payment voucher
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        Thread.sleep(3000);
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(2000);
        // Click on the close button
        driver.findElement(By.xpath("//span[contains(text(),'Close')]")).click();

        Thread.sleep(4000);


        // Paste the copied payment voucher in the request number filter

        WebElement buyer_no = driver.findElement(By.xpath("//*[@id=\"binvoicenum\"]"));
        // Create an Actions object
        Actions actions2 = new Actions(driver);
        // Click inside the textbox to focus on it
        buyer_no.click();
        // Use keyboard shortcuts to paste the text
        actions2.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


        Select select_doc_type = new Select(driver.findElement(By.xpath("//select[@name='doctype_filter']")));
        select_doc_type.selectByVisibleText("Payment Vouchers");

        Select select_status = new Select(driver.findElement(By.xpath("//*[@id=\"filter_invstat\"]")));
        select_status.selectByVisibleText("Payment Voucher Created");

        driver.findElement(By.xpath("//button[normalize-space()='Apply Filter']")).click();




    }






    public void create_contract_Prod() throws InterruptedException{

        WebElement contract_tab = driver.findElement(By.xpath("//a[@title='Contracts']"));
        contract_tab.click();

        WebElement create_new_contract = driver.findElement(By.xpath("//a[@title='Create new Contract']"));
        create_new_contract.click();

        // Create Contract
        Select Contract_type = new Select(driver.findElement(By.id("contractType")));
        Contract_type.selectByValue("6");


        // Locate the textbox element
        WebElement textBoxElement = driver.findElement(By.xpath("//*[@id=\"contractNumber\"]"));
        // Create an Actions object
        Actions actions = new Actions(driver);
        Thread.sleep(3000);
        // Double click on the textbox
        actions.doubleClick(textBoxElement).perform();
        Thread.sleep(3000);
        // Use keyboard shortcuts to copy the text
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        Thread.sleep(3000);
        // Verify the copied text if needed
        String copiedText = getClipboardText(); // Implement a method to get the text from the clipboard
        System.out.println("Copied Text: " + copiedText);



        Select Contract_visibility = new Select(driver.findElement(By.id("contractAccessType")));
        Contract_visibility.selectByValue("Public");

        WebElement Contract_title = driver.findElement(By.xpath("//input[@id='title']"));
        Contract_title.sendKeys("TestStage");

        WebElement commodities = driver.findElement(By.xpath("//button[@id='selectCatButton']"));
        commodities.click();

        WebElement commodities_select = driver.findElement(By.xpath("//li[4]//span[1]//span[2]"));
        commodities_select.click();

        driver.findElement(By.xpath("//button[@class='btn btn-wp'][normalize-space()='Close']")).click();

        driver.findElement(By.xpath("//button[@id='contractbtnslt']")).click();


        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
            // Perform actions or check conditions to identify the target window
        }

        // Example declaration within the class
//        String originalHandle = "someValue";


        driver.findElement(By.id("sname_action")).sendKeys("Shikha Stage vendor");
        driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

        driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[1]/img[1]")).click();
        Thread.sleep(4000);

        Set<String> windowHandles1 = driver.getWindowHandles();

        for (String handle : windowHandles1) {
            driver.switchTo().window(handle);
            // Perform actions or check conditions to identify the target window
        }
//
//        driver.switchTo().defaultContent();

        Select Pricing_type = new Select(driver.findElement(By.xpath("//*[@id=\"contractPricingType\"]")));
        Pricing_type.selectByValue("Fixed Price");

        Select Total_Value_condition = new Select(driver.findElement(By.id("tempcontractTotalValueCondition")));
        Total_Value_condition.selectByValue("Fixed");

        driver.findElement(By.xpath("//input[@id='maxValue']")).sendKeys("500");

        // Award Date
        driver.findElement(By.id("award_date")).click();
        LocalDate currentDate2 = LocalDate.now();

        // Find the element representing the current date
        String xpath2 = String.format("//td[@class='today day']", currentDate2.getDayOfMonth());
        WebElement currentDateElement2 = driver.findElement(By.xpath(xpath2));
        currentDateElement2.click();


        // Effective Date
        driver.findElement(By.xpath("//*[@id=\"effective_date\"]")).click();
        LocalDate currentDate3 = LocalDate.now();
        // Find the element representing the current date
        String xpath3 = String.format("//td[@class='today day']", currentDate3.getDayOfMonth());
        WebElement currentDateElement3 = driver.findElement(By.xpath(xpath3));
        currentDateElement3.click();


        // Expiration Date
        LocalDate currentDate4 = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

//        currentDate = LocalDate.now();
        LocalDate targetDate = currentDate4.plusDays(2);
        formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedTargetDate = targetDate.format(formatter);

        By inputFieldLocator = By.xpath("//*[@id=\"expiry_date\"]");
        WebElement inputField = driver.findElement(inputFieldLocator);
        inputField.clear();
        // Enter the formatted target date in the input field
        inputField.sendKeys(formattedTargetDate);
        Thread.sleep(3000);

        // Click on the Next button
        driver.findElement(By.xpath("//*[text()='Next Step']")).click();

        //Click on the Next button
        driver.findElement(By.xpath("//*[text()='Next']")).click();

        // Click on the Next button
        driver.findElement(By.xpath("//*[@id=\"theform\"]/div[2]/div/div/button[3]")).click();
        // Click on the Next button
        driver.findElement(By.xpath("//*[text()='Next']")).click();

        // Contracts Documents
        driver.findElement(By.xpath("//*[text()='Next']")).click();

        // Authorization page
        driver.findElement(By.xpath("//*[@id=\"authorize_head_org\"]")).click();

        // click on the Finished button
        driver.findElement(By.xpath("//*[text()='Finished']")).click();

        // click on the submit button
        driver.findElement(By.xpath("//*[text()='Submit']")).click();




        // Click on the element to select the current date
//        Thread.sleep(3000);



        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
        }


    }





    public WebDriver Buyer_login() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

        // Navigate to the login page of your application
//        driver.get("https://webprocure-stage.proactiscloud.com/Login");// Stage

        driver.get("https://webprocure.proactiscloud.com/Login"); // Prod

        driver.findElement(By.id("visibleUname")).sendKeys("smperfect");
//        driver.findElement(By.xpath("//input[@id='visiblePass']")).sendKeys("Welcome@8"); // Stage
//        driver.findElement(By.xpath("//input[@id='visiblePass']")).sendKeys("Welcome@9"); // Prod


//        driver.findElement(By.id("visibleUname")).sendKeys("smperfect");
        driver.findElement(By.xpath("//input[@id='visiblePass']")).sendKeys("Pass@456");

        Thread.sleep(4000);
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(3000);

        // Switch to the pop-up window
        String mainWindowHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Find and click the "Ignore" button
        WebElement ignoreButton = driver.findElement(By.xpath("//*[@id=\"session_info_display_modal\"]/div[2]/div/div[3]/button[1]"));
        ignoreButton.click();

        // Switch back to the main window
        driver.switchTo().window(mainWindowHandle);

        Thread.sleep(5000);

//        test.info("Logging in with credentials: " + username + "/" + password);

        return driver;
    }


    public void logout() {
        // Perform logout actions
//        driver.findElement(By.id("logout-button")).click();

        driver.findElement(By.xpath("//*[@id=\"userMenu\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"userMenuList\"]/li[4]/a")).click();

        driver.switchTo().alert().accept();

//        test.info("Logging out");
    }

    public void vendors_login() throws InterruptedException {

//        driver.get("https://webprocure-stage.proactiscloud.com/Login"); //Stage

        driver.get("https://webprocure.proactiscloud.com/Login"); //Prod

        // Perform login
        WebElement usernameField = driver.findElement(By.id("visibleUname"));
        WebElement passwordField = driver.findElement(By.id("visiblePass"));
        WebElement loginButton = driver.findElement(By.id("login-submit"));

//        usernameField.sendKeys("shikhav2"); // Stage
//        passwordField.sendKeys("Welcome@2"); // Stage

        usernameField.sendKeys("Vendor01"); // Prod
        passwordField.sendKeys("Welcome@2");  // Prod


        loginButton.click();
        Thread.sleep(4000);

    }

    public void vendor_logout() {
        driver.findElement(By.xpath("//*[@id=\"userMenu\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"userMenuList\"]/li[5]/a")).click();

        driver.switchTo().alert().accept();

    }


    public void create_request() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Create a request
        WebElement Request_tab = driver.findElement(By.xpath("//a[@title=\"Request\"]"));
        Request_tab.click();
        WebElement Create_new = driver.findElement(By.xpath("//a[@title=\"Create new\"]"));
        Create_new.click();

        driver.switchTo().frame("C1ReqMain");


        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[1]/ul/li[4]/a")).click();


        driver.switchTo().frame("C1ReqMain");
        driver.findElement(By.xpath("//*[@id=\"OrderQty\"]")).sendKeys("100");
        driver.findElement(By.xpath("//*[@id='UnitPrice']")).sendKeys("50");
        driver.findElement(By.xpath("//input[@id='SupplierPartNum']")).sendKeys("64836493");

        Select dropdown = new Select(driver.findElement(By.id("cboUsageCode")));
        dropdown.selectByVisibleText("1 - 10%");

        driver.findElement(By.xpath("//input[@id='input_SupplierName']")).sendKeys("Shikha Prod Vendor ");
        driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/li[1]")).click();

        driver.findElement(By.xpath("//input[@id='input_catcode']")).sendKeys("**");
        driver.findElement(By.xpath("//li[@class='ui-menu-item'][3]/a/div/div")).click();
        driver.findElement(By.xpath("//button[text()='No']")).click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"btn-add-bottom\"]")).click();

        Thread.sleep(6000);

        driver.findElement(By.xpath("//*[@id=\"idView Request\"]")).click();
        Thread.sleep(4000);
        driver.switchTo().frame("C1ReqMain");
        Thread.sleep(5000);
        // Assign Account Code
        driver.findElement(By.xpath("//*[@title=\"Assign Account Distribution\"]")).click();

        driver.findElement(By.xpath("//*[@id=\"frmReqCostDist\"]/div[3]/button")).click();

        driver.findElement(By.xpath("//*[@id=\"frmReqCostDist\"]/div[1]/button[1]")).click();

//        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[4]/form/div[1]/button[6]")).click();
//        Thread.sleep(2);

        driver.findElement(By.xpath("//*[@title='Submit Request']")).click();
    }


    public void create_request_for_approval() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

        // Create a request
        WebElement Request_tab = driver.findElement(By.xpath("//a[@title=\"Request\"]"));
        Request_tab.click();
        WebElement Create_new = driver.findElement(By.xpath("//a[@title=\"Create new\"]"));
        Create_new.click();


        driver.switchTo().frame("C1ReqMain");


        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[1]/ul/li[4]/a")).click();

//        driver.switchTo().parentFrame();
        driver.switchTo().frame("C1ReqMain");
        driver.findElement(By.xpath("//*[@id=\"OrderQty\"]")).sendKeys("100");
        driver.findElement(By.xpath("//*[@id='UnitPrice']")).sendKeys("50");
        driver.findElement(By.xpath("//input[@id='SupplierPartNum']")).sendKeys("64836493");

        Select dropdown = new Select(driver.findElement(By.id("cboUsageCode")));
        dropdown.selectByVisibleText("1 - 10%");

        driver.findElement(By.xpath("//input[@id='input_SupplierName']")).sendKeys("Shikha Prod Vendor "); // Prod
        driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/li[1]")).click();


//        driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/li[1]")).click();

        driver.findElement(By.xpath("//input[@id='input_catcode']")).sendKeys("**");
        sleep(2000);


        driver.findElement(By.xpath("//li[@class='ui-menu-item'][3]/a/div/div")).click();
        driver.findElement(By.xpath("//button[text()='No']")).click();
        sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"btn-add-bottom\"]")).click();
        sleep(2000);

        driver.switchTo().parentFrame();
        Thread.sleep(3000);
        driver.switchTo().frame("reqcart");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@name='txtReqName']"));

        // Locate the textbox element
//        WebDriver copyreqnumber = null;
//        WebDriver driver = copyreqnumber;

        // Locate the textbox element
        WebElement textBoxElement = driver.findElement(By.xpath("//*[@name='txtReqName']"));
        // Create an Actions object
        Actions actions = new Actions(driver);
        Thread.sleep(3000);
        // Double click on the textbox
        actions.doubleClick(textBoxElement).perform();
        Thread.sleep(3000);
        // Use keyboard shortcuts to copy the text
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        Thread.sleep(3000);
        // Verify the copied text if needed
        String copiedText = getClipboardText(); // Implement a method to get the text from the clipboard
        System.out.println("Copied Text: " + copiedText);


        Thread.sleep(3000);
        driver.switchTo().parentFrame();
        driver.switchTo().frame("C1ReqMain");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\'idView Request\']")).click();

        Thread.sleep(5000);
        driver.switchTo().frame("C1ReqMain");
        // Assign Account Code
        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[4]/form/div[5]/table/tbody/tr[1]/td[5]/p/a[2]/img")).click();

        driver.findElement(By.xpath("//*[@id=\"frmReqCostDist\"]/div[3]/button")).click();

        driver.findElement(By.xpath("//*[@id=\"frmReqCostDist\"]/div[1]/button[1]")).click();

        // Approval preview button

        driver.findElement(By.xpath("//*[@id=\"btn-approvalPreview\"]")).click();


        driver.switchTo().frame("topframe");


        driver.findElement(By.xpath("//*[@id=\"addApprover\"]")).click();

        driver.switchTo().parentFrame();
        driver.switchTo().frame("bodyframe");
        driver.findElement(By.xpath("//*[@id=\"workflowbody\"]/table/tbody/tr/td[2]/table/tbody/tr/td[1]/table/tbody/tr/td/div/div/a/i")).click();

        // Switch to popup window to select approver
        String parentWindowHandle = driver.getWindowHandle();

        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        driver.switchTo().window(parentWindowHandle);


        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchText\"]")));


//        driver.findElement(By.xpath("//*[@id=\"searchText\"]")).sendKeys("Shikha Mishra"); // Stage

        driver.findElement(By.xpath("//*[@id=\"searchText\"]")).sendKeys("Shikha M");



        Thread.sleep(3000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-1']/li")));

        driver.findElement(By.xpath("//*[@id='ui-id-1']/li")).click();


        driver.findElement(By.xpath("//*[@class=\"btn btn-wp selectApp\"]")).click();


        driver.switchTo().frame("C1ReqMain");
        driver.switchTo().frame("topframe");


        driver.findElement(By.xpath("//*[@title='Close']")).click();

        //Switch to frame
        driver.switchTo().frame("C1ReqMain");

        driver.findElement(By.xpath("//*[@title='Submit Request']")).click();
    }

    public void approval_login() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"visibleUname\"]")).sendKeys("smperfect2");
//        driver.findElement(By.xpath("//*[@id=\"visiblePass\"]")).sendKeys("Welcome@3");// Stage

        driver.findElement(By.xpath("//*[@id=\"visiblePass\"]")).sendKeys("Welcome@2");
        sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();

    }

    protected String getClipboardText() {
        return null;
    }

    public void from_date() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"filter_fromDate\"]")).click();
        Thread.sleep(2000);

        LocalDate currentDate = LocalDate.now();

        // Find the element representing the current date
        String xpath = String.format("//td[@class='today day']", currentDate.getDayOfMonth());
        WebElement currentDateElement = driver.findElement(By.xpath(xpath));

        // Click on the element to select the current date
        currentDateElement.click();
        Thread.sleep(5000);

    }

    public void to_date() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"filter_toDate\"]")).click();
        Thread.sleep(2000);

        LocalDate currentDate2 = LocalDate.now();

        // Find the element representing the current date
        String xpath2 = String.format("//td[@class='today day']", currentDate2.getDayOfMonth());
        WebElement currentDateElement2 = driver.findElement(By.xpath(xpath2));

        // Click on the element to select the current date
        currentDateElement2.click();
        Thread.sleep(3000);

    }

    public void request_from_date() throws InterruptedException {
        driver.findElement(By.xpath("//*[@name='FromDate']")).click();
        sleep(2000);

        LocalDate currentDate = LocalDate.now();

        // Find the element representing the current date
        String xpath = String.format("//td[@class='today day']", currentDate.getDayOfMonth());
        WebElement currentDateElement = driver.findElement(By.xpath(xpath));

        // Click on the element to select the current date
        currentDateElement.click();
        sleep(5000);
    }

    public void request_to_date() throws InterruptedException {
        driver.findElement(By.xpath("//*[@name='ToDate']")).click();
        sleep(2000);

        LocalDate currentDate2 = LocalDate.now();

        // Find the element representing the current date
        String xpath2 = String.format("//td[@class='today day']", currentDate2.getDayOfMonth());
        WebElement currentDateElement2 = driver.findElement(By.xpath(xpath2));
        currentDateElement2.click();
        sleep(5000);

    }

    @AfterClass(enabled = false)
    public void tearDown() {
        // Close the browser after the test execution is complete
        driver.quit();

//        extent.flush();


    }


}

