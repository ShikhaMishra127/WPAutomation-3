package buyer.testcases.registration;


import commonutils.pageobjects.utils.ReadExcelData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static commonutils.pageobjects.utils.PCDriver.driver;
import static java.lang.System.out;
import static java.lang.System.setProperty;


public class RegistrationTestcasesQA {

    public static void waitForElementToBeClickable(WebElement ele, Long... i) {

        if (i.length >= 1) {
            WebDriverWait wait = new WebDriverWait(driver, i[0]);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
        } else {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
        }
    }


    @Test(enabled = true ,priority = 1)
    public void registerFEIN() throws InterruptedException, IOException {
        setProperty("webdriver.chrome.driver","D:\\chromedriver_win32 (2)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://internalwpqa.perfect.com/html/whitelabel/WHITELABEL.html?&LOC=internalwpqa&FUNC=whitelabelreg&TOK=d9272c0f-3e5f-4fde-8fbc-cd3dadbfc81d&EBO=38&OID=86637&CSS=N");
//        driver.manage().timeouts().implicitlyWait(7000,TimeUnit.MICROSECONDS);

        driver.manage().window().maximize();
        Thread.sleep(15000);
        driver.switchTo().frame("whitelabelregframe");

        driver.findElement(By.xpath("/html/body/div[1]/form/div/div[1]/fieldset/div/input")).click();
        driver.findElement(By.xpath("//*[@id='next']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//*[@name='country']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Country"));
//        driver.findElement(By.xpath("//*[@id='fein1']")).sendKeys(ReadExcelData.getInstance("Registration").getStringValue("FEIN"));
//        driver.findElement(By.xpath("//*[@id='retypefein1']")).sendKeys(ReadExcelData.getInstance("Registration").getStringValue("FEIN"));

        String time = String.valueOf(System.currentTimeMillis());
        driver.findElement(By.id("fein1")).sendKeys(time.substring(0,2));
        driver.findElement(By.id("fein2")).sendKeys(time.substring(2,9));

        driver.findElement(By.id("retypefein1")).sendKeys(time.substring(0,2));
        driver.findElement(By.id("retypefein2")).sendKeys(time.substring(2,9));

        driver.findElement(By.xpath("//*[@id='supname']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Name"));
        driver.findElement(By.xpath("//*[@id='Address1']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Address1"));
        driver.findElement(By.xpath("//*[@id='Address2']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Address2"));
        driver.findElement(By.xpath("//*[@id='city']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("City"));
        driver.findElement(By.xpath("//*[@id='state']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("State"));
        Thread.sleep(3000);
//        WebDriverWait w2 = new WebDriverWait(driver,30);
//        w2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='state']")));

        driver.findElement(By.xpath("//*[@id='otherstate']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("OtherState"));
        driver.findElement(By.xpath("//*[@id='zipcode']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Zip Code"));
        driver.findElement(By.xpath("//*[@id='suptype']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("TypeofBuss"));
        Thread.sleep(4000);

        driver.findElement(By.xpath("//*[@id='next']")).click();
        Thread.sleep(3000);

//        driver.switchTo().alert().accept();
//        JavascriptExecutor js1 = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,350)", "");

        driver.findElement(By.xpath("//*[text()='Yes']")).click();
        Thread.sleep(2000);

        // Supplier Registration

        driver.findElement(By.xpath("//*[@name='fname']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("FirstName"));
        driver.findElement(By.xpath("//*[@name='lname']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("LastName"));
        driver.findElement(By.xpath("//*[@id='myphone']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("PhnNo1"));
        driver.findElement(By.xpath("//*[@id='phoneNumberext']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("PhnNoExt"));
        driver.findElement(By.xpath("//*[@name='verifymyphone']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("ConfirmPhnNo"));
        driver.findElement(By.xpath("//*[@name='verifyphoneNumberext']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("VerifyphoneNumberExt"));
        driver.findElement(By.xpath("//*[@name='myfax']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("FaxNumber"));
        driver.findElement(By.xpath("//*[@name='verifymyfax']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("VerifyFaxNum"));
        driver.findElement(By.xpath("//*[@name='username']")).sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("Email"));

        driver.findElement(By.xpath("//*[@name='verifyusername']")).sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("VerifyEmail"));
        driver.findElement(By.xpath("//*[@name='username1']")).sendKeys("User"+time.substring(4,8));
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("Password"));
        driver.findElement(By.xpath("//*[@name='retypePassword']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("RetypePassword"));

        driver.findElement(By.xpath("//*[text()='Next']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[text()='Next']")).click();
        Thread.sleep(4000);
//        driver.switchTo().frame("whitelabelregframe");
        driver.findElement(By.xpath("//div[@id='catTreeDiv']//span[@class='fancytree-checkbox'][1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='Process My Registration']")).click();
        Thread.sleep(4000);
        driver.close();

    }

    @Test(description = "This test case will check if user is able to register using Duplicate Fein", enabled = true,priority = 2)
    public void DuplicateFeinCheck1() throws IOException, InterruptedException {

        setProperty("webdriver.chrome.driver","D:\\chromedriver_win32 (2)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://internalwpqa.perfect.com/html/whitelabel/WHITELABEL.html?&LOC=internalwpqa&FUNC=whitelabelreg&TOK=d9272c0f-3e5f-4fde-8fbc-cd3dadbfc81d&EBO=38&OID=86637&CSS=N");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(7000,TimeUnit.MICROSECONDS);
        Thread.sleep(15000);
        driver.switchTo().frame("whitelabelregframe");
        driver.findElement(By.xpath("//*[@id='termsncondition']")).click();
        driver.findElement(By.xpath("//*[@id='next']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@name='country']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Country"));

//        driver.findElement(By.xpath("//*[@id='fein1']")).sendKeys(ReadExcelData.getInstance("Registration").getStringValue("FEIN")+ System.currentTimeMillis());
//        driver.findElement(By.xpath("//*[@id='retypefein1']")).sendKeys(ReadExcelData.getInstance("Registration").getStringValue("FEIN")+ System.currentTimeMillis());
//        WebElement fein1= driver.findElement(By.xpath("//*[@id='fein1']"));

//        fein1.sendKeys();
//        String time = String.valueOf(System.currentTimeMillis());
//        PCDriver.driver.findElement(By.id("fein1")).sendKeys(time.substring(0,2));
//        PCDriver.driver.findElement(By.id("fein2")).sendKeys(time.substring(2,9));

        driver.findElement(By.xpath("//*[@id='fein1']")).sendKeys("1234567890");
        driver.findElement(By.xpath("//*[@id='retypefein1']")).sendKeys("1234567890");

        driver.findElement(By.xpath("//*[@id='supname']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Name"));
        driver.findElement(By.xpath("//*[@id='Address1']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Address1"));
        driver.findElement(By.xpath("//*[@id='Address2']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Address2"));
        driver.findElement(By.xpath("//*[@id='city']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("City"));
        driver.findElement(By.xpath("//*[@id='state']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("State"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='otherstate']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("OtherState"));
        driver.findElement(By.xpath("//*[@id='zipcode']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Zip Code"));
        driver.findElement(By.xpath("//*[@id='suptype']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("TypeofBuss"));
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id='next']")).click();

        out.println("Error Message : "+"Our records indicate that Federal Tax Id already exists in the system for Parent Holding Company.");
        driver.close();

    }

    @Test(enabled = true,priority = 3)
    public void registerSSN() throws InterruptedException, IOException {
        setProperty("webdriver.chrome.driver","D:\\chromedriver_win32 (2)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://internalwpqa.perfect.com/html/whitelabel/WHITELABEL.html?&LOC=internalwpqa&FUNC=whitelabelreg&TOK=d9272c0f-3e5f-4fde-8fbc-cd3dadbfc81d&EBO=38&OID=86637&CSS=N");
        driver.manage().window().maximize();

//        driver.manage().timeouts().implicitlyWait(7000,TimeUnit.MICROSECONDS);
        Thread.sleep(15000);
        driver.switchTo().frame("whitelabelregframe");
        driver.findElement(By.xpath("//*[@id='termsncondition']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='next']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//*[@name='country']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Country"));

        String time = String.valueOf(System.currentTimeMillis());
        driver.findElement(By.id("ssn1")).sendKeys(time.substring(0,3));
        driver.findElement(By.id("ssn2")).sendKeys(time.substring(3,5));
        driver.findElement(By.id("ssn3")).sendKeys(time.substring(5,9));


        driver.findElement(By.id("retypessn1")).sendKeys(time.substring(0,3));
        driver.findElement(By.id("retypessn2")).sendKeys(time.substring(3,5));
        driver.findElement(By.id("retypessn3")).sendKeys(time.substring(5,9));
        driver.findElement(By.xpath("//*[@id='ssn1']")).sendKeys(ReadExcelData.getInstance("Registration").getStringValue("SSN"));
        driver.findElement(By.xpath("//*[@id='retypessn1']")).sendKeys(ReadExcelData.getInstance("Registration").getStringValue("SSN"));
        driver.findElement(By.xpath("//*[@id='supname']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Name"));
        driver.findElement(By.xpath("//*[@id='Address1']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Address1"));
        driver.findElement(By.xpath("//*[@id='Address2']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Address2"));
        driver.findElement(By.xpath("//*[@id='city']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("City"));
        driver.findElement(By.xpath("//*[@id='state']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("State"));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='otherstate']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("OtherState"));
        driver.findElement(By.xpath("//*[@id='zipcode']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Zip Code"));
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id='suptype']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("TypeofBuss2"));

        driver.findElement(By.xpath("//*[@id='next']")).click();
        Thread.sleep(4000);

        driver.findElement(By.xpath("//*[text()='Yes']")).click();

        // Supplier Registration

        driver.findElement(By.xpath("//*[@name='fname']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("FirstName"));
        driver.findElement(By.xpath("//*[@name='lname']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("LastName"));
        driver.findElement(By.xpath("//*[@id='myphone']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("PhnNo1"));
        driver.findElement(By.xpath("//*[@id='phoneNumberext']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("PhnNoExt"));
        driver.findElement(By.xpath("//*[@name='verifymyphone']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("ConfirmPhnNo"));
        driver.findElement(By.xpath("//*[@name='verifyphoneNumberext']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("VerifyphoneNumberExt"));
        driver.findElement(By.xpath("//*[@name='myfax']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("FaxNumber"));
        driver.findElement(By.xpath("//*[@name='verifymyfax']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("VerifyFaxNum"));
        driver.findElement(By.xpath("//*[@name='username']")).sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("Email"));

        driver.findElement(By.xpath("//*[@name='verifyusername']")).sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("VerifyEmail"));
        driver.findElement(By.xpath("//*[@name='username1']")).sendKeys("User"+time.substring(4,8));
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("Password"));
        driver.findElement(By.xpath("//*[@name='retypePassword']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("RetypePassword"));

        driver.findElement(By.xpath("//*[text()='Next']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='Next']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@class='fancytree-checkbox']")).click();
        Thread.sleep(3000);
//        driver.findElement(By.xpath("//*[text()='Next']")).click();
        driver.findElement(By.xpath("//*[text()='Process My Registration']")).click();
        Thread.sleep(4000);
        driver.close();

    }



    @Test(description = "This test case will register supplier using Ssn", enabled = true,priority = 4)
    public void DuplicateSsn1() throws InterruptedException, IOException {

        setProperty("webdriver.chrome.driver","D:\\chromedriver_win32 (2)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://internalwpqa.perfect.com/html/whitelabel/WHITELABEL.html?&LOC=internalwpqa&FUNC=whitelabelreg&TOK=d9272c0f-3e5f-4fde-8fbc-cd3dadbfc81d&EBO=38&OID=86637&CSS=N");
        driver.manage().window().maximize();

//        driver.manage().timeouts().implicitlyWait(7000,TimeUnit.MICROSECONDS);
        Thread.sleep(15000);
        driver.switchTo().frame("whitelabelregframe");
        driver.findElement(By.xpath("//*[@id='termsncondition']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='next']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//*[@name='country']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Country"));
        driver.findElement(By.xpath("//*[@id='ssn1']")).sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id='retypessn1']")).sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id='supname']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Name"));
        driver.findElement(By.xpath("//*[@id='Address1']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Address1"));
        driver.findElement(By.xpath("//*[@id='Address2']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Address2"));
        driver.findElement(By.xpath("//*[@id='city']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("City"));
        driver.findElement(By.xpath("//*[@id='state']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("State"));
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id='otherstate']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("OtherState"));
        driver.findElement(By.xpath("//*[@id='zipcode']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Zip Code"));
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id='suptype']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("TypeofBuss2"));

        driver.findElement(By.xpath("//*[@id='next']")).click();
        Thread.sleep(4000);
        out.println("Our records indicate that Social Security Number already exists in the system.");
        driver.close();


    }

    @Test(description = "This test case will check Duplicate Ssn", enabled = true,priority = 5)
    public void DuplicateUsernameCheck1() throws IOException, InterruptedException {

        setProperty("webdriver.chrome.driver","D:\\chromedriver_win32 (2)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://internalwpqa.perfect.com/html/whitelabel/WHITELABEL.html?&LOC=internalwpqa&FUNC=whitelabelreg&TOK=d9272c0f-3e5f-4fde-8fbc-cd3dadbfc81d&EBO=38&OID=86637&CSS=N");
        driver.manage().window().maximize();

//        driver.manage().timeouts().implicitlyWait(7000,TimeUnit.MICROSECONDS);
        Thread.sleep(15000);
        driver.switchTo().frame("whitelabelregframe");
        driver.findElement(By.xpath("//*[@id='termsncondition']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='next']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//*[@name='country']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Country"));
        String time = String.valueOf(System.currentTimeMillis());
        driver.findElement(By.id("ssn1")).sendKeys(time.substring(0,3));
        driver.findElement(By.id("ssn2")).sendKeys(time.substring(3,5));
        driver.findElement(By.id("ssn3")).sendKeys(time.substring(5,9));


        driver.findElement(By.id("retypessn1")).sendKeys(time.substring(0,3));
        driver.findElement(By.id("retypessn2")).sendKeys(time.substring(3,5));
        driver.findElement(By.id("retypessn3")).sendKeys(time.substring(5,9));

        driver.findElement(By.xpath("//*[@id='supname']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Name"));
        driver.findElement(By.xpath("//*[@id='Address1']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Address1"));
        driver.findElement(By.xpath("//*[@id='Address2']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Address2"));
        driver.findElement(By.xpath("//*[@id='city']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("City"));
        driver.findElement(By.xpath("//*[@id='state']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("State"));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='otherstate']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("OtherState"));
        driver.findElement(By.xpath("//*[@id='zipcode']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Zip Code"));
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id='suptype']")).sendKeys(ReadExcelData.getInstance("Test1").getStringValue("TypeofBuss2"));

        driver.findElement(By.xpath("//*[@id='next']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[text()='Yes']")).click();

        // Supplier Registration

        driver.findElement(By.xpath("//*[@name='fname']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("FirstName"));
        driver.findElement(By.xpath("//*[@name='lname']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("LastName"));
        driver.findElement(By.xpath("//*[@id='myphone']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("PhnNo1"));
        driver.findElement(By.xpath("//*[@id='phoneNumberext']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("PhnNoExt"));
        driver.findElement(By.xpath("//*[@name='verifymyphone']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("ConfirmPhnNo"));
        driver.findElement(By.xpath("//*[@name='verifyphoneNumberext']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("VerifyphoneNumberExt"));
        driver.findElement(By.xpath("//*[@name='myfax']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("FaxNumber"));
        driver.findElement(By.xpath("//*[@name='verifymyfax']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("VerifyFaxNum"));
        driver.findElement(By.xpath("//*[@name='username']")).sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("Email"));

        driver.findElement(By.xpath("//*[@name='verifyusername']")).sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("VerifyEmail"));
        driver.findElement(By.xpath("//*[@name='username1']")).sendKeys("NehaR");
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("Password"));
        driver.findElement(By.xpath("//*[@name='retypePassword']")).sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("RetypePassword"));

        driver.findElement(By.xpath("//*[text()='Next']")).click();
        Thread.sleep(3000);
        driver.close();


    }




    }












