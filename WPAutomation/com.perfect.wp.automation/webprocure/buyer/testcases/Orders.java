package buyer.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.LocalDate;

public class Orders {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up the WebDriver instance
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
// D:\chromedriver-win64\chromedriver-win64

    // D:\chromedriver_win32 (4)\chromedriver.exe

    @BeforeMethod
    public void testLoginPage() throws InterruptedException
    {
        // Open the login page
        driver.get("https://webprocure-stage.proactiscloud.com/Login");

        // Perform login
        WebElement usernameField = driver.findElement(By.id("visibleUname"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='visiblePass']"));
        WebElement loginButton = driver.findElement(By.id("login-submit"));

        usernameField.sendKeys("smperfect");
        passwordField.sendKeys("Welcome@2");
        loginButton.click();
        Thread.sleep(7000);

        // Switch to the pop-up window
        String mainWindowHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles())
        {
            if (!handle.equals(mainWindowHandle))
            {
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


    }

    @Test
    public void OrderViewAll() throws InterruptedException {

        WebElement order = driver.findElement(By.xpath("//a[@title='Order']"));
        Thread.sleep(3000);
        WebElement order_viewAll = driver.findElement(By.xpath("/html/body/nav/div/div/div[2]/ul/li[2]/ul/li[1]/a"));
        Thread.sleep(4000);

        order.click();
        order_viewAll.click();
        driver.findElement(By.xpath("//*[@id=\"FSts\"]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"FSts\"]/option[3]")).click();

        WebElement submit = driver.findElement(By.xpath("//*[@id=\"filter\"]/div/div/div/button[1]"));
        Thread.sleep(4000);
        submit.click();

        // Change the sort of column
        driver.findElement(By.xpath("//*[@id=\"poTable\"]/thead/tr/th[3]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"poTable\"]/thead/tr/th[9]")).click();

    }


    @Test
    public void Receive() throws InterruptedException {
        // Open the home page
//        testLoginPage();
        WebElement order = driver.findElement(By.xpath("//a[@title='Order']"));
        Thread.sleep(3000);


        order.click();

        WebElement Receive_list = driver.findElement(By.xpath("/html/body/nav/div/div/div[2]/ul/li[2]/ul/li[2]/a"));
        Receive_list.click();
        Thread.sleep(3000);


        driver.findElement(By.xpath("//*[@id=\"rcvTable\"]/tbody/tr[1]/td[9]/span/button/img")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"rcvTable\"]/tbody/tr[1]/td[9]/span/ul/li[1]/a")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("rcvDate")).click();


        LocalDate currentDate = LocalDate.now();

        // Find the element representing the current date
        String xpath = String.format("//td[@class='today day']", currentDate.getDayOfMonth());
        WebElement currentDateElement = driver.findElement(By.xpath(xpath));

        // Click on the element to select the current date
        currentDateElement.click();
        Thread.sleep(5000);

        WebElement receivedQty = driver.findElement(By.xpath("//*[@id=\"orderDetails\"]/div[4]/div/table/tbody/tr[1]/td[4]/input"));

        // Clear the value from the textbox
        //receivedQty.clear();
        // Select the existing value and clear it using the combination of Ctrl+A and Backspace
        receivedQty.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);


        driver.findElement(By.name("txtReceivedQty")).sendKeys("1.000");
        Thread.sleep(6000);
        driver.findElement(By.id("cmdSubmit")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"rcvTable\"]/tbody/tr[1]/td[9]/span/button/img")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"rcvTable\"]/tbody/tr[1]/td[9]/span/ul/li[2]/a")).click();
        Thread.sleep(6000);

        driver.findElement(By.xpath("//*[@id=\"userMenu\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"userMenuList\"]/li[4]/a")).click();

        driver.switchTo().alert().accept();


    }

    @Test
    public void vendors_profile() throws InterruptedException {

        // Create a request
          WebElement Request_tab = driver.findElement(By.xpath("//a[@title=\"Request\"]"));
          Request_tab.click();
          WebElement Create_new = driver.findElement(By.xpath("//a[@title=\"Create new\"]"));
          Create_new.click();

        Thread.sleep(4000);
        driver.switchTo().frame("C1ReqMain");


          driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[1]/ul/li[4]/a")).click();
          Thread.sleep(5000);
//        driver.switchTo().parentFrame();
          driver.switchTo().frame("C1ReqMain");
          driver.findElement(By.xpath("//*[@id=\"OrderQty\"]")).sendKeys("100");
          Thread.sleep(2000);
          driver.findElement(By.xpath("//*[@id='UnitPrice']")).sendKeys("50");
          Thread.sleep(2000);
          driver.findElement(By.xpath("//input[@id='SupplierPartNum']")).sendKeys("64836493");
          Thread.sleep(4000);

          driver.findElement(By.xpath("//input[@id='input_SupplierName']")).sendKeys("Shikha Perfect City");
          Thread.sleep(4000);
          driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/li[1]")).click();


        driver.findElement(By.xpath("//input[@id='input_catcode']")).sendKeys("**");
          Thread.sleep(3000);
          driver.findElement(By.xpath("/html/body/ul[2]/li[2]/a/div/div")).click();
        Thread.sleep(2000);
          driver.findElement(By.xpath("//*[@id=\"btn-add-bottom\"]")).click();
          Thread.sleep(2000);


        driver.findElement(By.xpath("//*[@id=\"idView Request\"]")).click();
        Thread.sleep(4);

        driver.switchTo().frame("C1ReqMain");
        // Assign Account Code
        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[4]/form/div[5]/table/tbody/tr[1]/td[5]/p/a[2]/img")).click();
        Thread.sleep(3);

        driver.findElement(By.xpath("//*[@id=\"frmReqCostDist\"]/div[3]/button")).click();
        Thread.sleep(3);

        driver.findElement(By.xpath("//*[@id=\"frmReqCostDist\"]/div[1]/button[1]")).click();
        Thread.sleep(3);

        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[4]/form/div[1]/button[6]")).click();
        Thread.sleep(2);




        // Logout from buyer side
        driver.findElement(By.xpath("//*[@id=\"userMenu\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"userMenuList\"]/li[4]/a")).click();

        driver.switchTo().alert().accept();
        Thread.sleep(5000);


        // Login to vendor side

        driver.findElement(By.xpath("//*[@id=\"visibleUname\"]")).sendKeys("shikham4" );
        driver.findElement(By.xpath("//*[@id=\"visiblePass\"]")).sendKeys("Welcome@1");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();
        Thread.sleep(7000);

        //Click on Order tab and select "view orders"

        WebElement Order_tab= driver.findElement(By.xpath("//*[@id=\"wrapper\"]/nav/div/div/div[2]/ul[1]/li[3]/a"));
        Order_tab.click();
        Thread.sleep(2000);


        WebElement view_orders = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/nav/div/div/div[2]/ul[1]/li[3]/ul/li[2]/a"));
        view_orders.click();
        Thread.sleep(2000);


        // Click on the "From date"

        driver.findElement(By.xpath("//*[@id=\"filter_fromDate\"]")).click();
        Thread.sleep(2000);

        LocalDate currentDate = LocalDate.now();

        // Find the element representing the current date
        String xpath = String.format("//td[@class='today day']", currentDate.getDayOfMonth());
        WebElement currentDateElement = driver.findElement(By.xpath(xpath));

        // Click on the element to select the current date
        currentDateElement.click();
        Thread.sleep(5000);

        // Click on To date

        driver.findElement(By.xpath("//*[@id=\"filter_toDate\"]")).click();
        Thread.sleep(2000);

        LocalDate currentDate2 = LocalDate.now();

        // Find the element representing the current date
        String xpath2 = String.format("//td[@class='today day']", currentDate2.getDayOfMonth());
        WebElement currentDateElement2 = driver.findElement(By.xpath(xpath));

        // Click on the element to select the current date
        currentDateElement2.click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id=\"find\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"poTable\"]/tbody/tr/td[1]/a")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"acknowledgeAction\"]")).click();

        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"vendorAckComments\"]")).sendKeys("Just for testing purpose");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"confirmAcknowledgeMessage\"]/form/div/input[2]")).click();

    }


    @AfterMethod(enabled = false)
    public void tearDownAfterTest()
    {
     //Quit the Order

    }

    @AfterClass(enabled = false)
    public void tearDown()
    {
        // Quit the WebDriver instance
        driver.quit();
    }
}
