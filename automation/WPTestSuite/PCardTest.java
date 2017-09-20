package WPTestSuite;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.Dimension;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class PCardTest {


	  private ChromeDriver driver;
	  private String baseUrl;
	  private WebDriverWait wait;
		  

	  @BeforeClass
	  public void beforeClass() {
		  
		  System.setProperty("webdriver.chrome.driver","C:\\Users\\axc001\\Desktop\\SELENIUM\\chromedriver.exe"); 

		  driver = new ChromeDriver();
		  baseUrl = "https://internalwpqa.perfect.com/";
		  driver.get(baseUrl + "/Login");
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  wait = new WebDriverWait(driver, 10);
		
		  driver.manage().window().setSize(new Dimension(1280, 1024));
	  }
	  
	  @AfterClass
	  public void afterClass() {
			driver.close();
	  }
	  
		public void Login() {

			Assert.assertNotNull(driver);
			Assert.assertEquals("Log into WebProcure", driver.getTitle());
			
			driver.findElement(By.id("visibleUname")).sendKeys("automation");
			driver.findElement(By.id("visiblePass")).sendKeys("Welcome1!");
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("login-submit")));
			
			driver.findElementById("login-submit").click();
			
			Assert.assertEquals("WebProcure: My WebProcure", driver.getTitle());
		}
		
		@Test
		public void EditPCard()
		{
			// eventually, Login and Logout will be in @BeforeTest/@AfterTest
			Login();
			
			// Go to Payment Options page
		    driver.findElement(By.id("userMenu")).click();
		    driver.findElement(By.linkText("My Account")).click();
		    driver.findElement(By.linkText("Payment Options")).click();
		    
			//*** TEST: Payment Options page appears 
			Assert.assertEquals("WebProcure:User P-Card Info", driver.getTitle());
		    
			// click the first edit icon from the list of User's P-Cards
		    driver.findElement(By.xpath("//table[@id='paymentMethodList']/tbody[2]/tr/td[6]/span/i")).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.id("editNumber")));

		    driver.findElement(By.id("editNumber")).click();
			driver.findElement(By.id("number")).sendKeys("4111111111111111");
			
			//*** TEST: Icon changes to success when number entered correctly
			Assert.assertEquals(
					"glyphicon glyphicon-ok icon-success",
					driver.findElement(By.id("numCheck")).getAttribute("class")
					);
			
			driver.findElement(By.id("name")).sendKeys("Automated User");
			driver.findElement(By.id("expiry")).sendKeys("02/2021");
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//*** TEST: Icon changes to success when expiration date entered correctly			
			Assert.assertEquals(
					"glyphicon glyphicon-ok icon-success",  
					driver.findElement(By.id("expiryCheck")).getAttribute("class"));

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// click the Save button on the P-Card edit overlay
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/div[4]/div[2]/div/div[2]/button[2]")));
		    driver.findElement(By.xpath("html/body/div[4]/div[2]/div/div[2]/button[2]")).click();
			
		    // errorstring: There was an error accessing the Credit Card Vault storage service: Error: Bad Request
		    // /html/body/div[5]/div[2]/div/div[1]/div/text()
		    
			//*** TEST: After save, we're back to the Payment Options page 
			Assert.assertEquals("WebProcure:User P-Card Info", driver.getTitle());

		    Logout();

		}

		public void Logout() {

		    driver.findElement(By.id("userMenu")).click();
		    driver.findElement(By.linkText("Logout")).click();

	        Alert myAlert = wait.until(ExpectedConditions.alertIsPresent());

		    myAlert = driver.switchTo().alert();
		    myAlert.accept();
		    
		    //System.out.format("%s", alert.getText());
		}
	

}
