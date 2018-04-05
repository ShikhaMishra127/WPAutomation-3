package buyer.testcases.login;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonutils.pageobjects.generic.LoginPage;
import commonutils.pageobjects.utils.ExtentReport;
import commonutils.pageobjects.utils.ReadExcelData;

@Listeners(ExtentReport.class)
public class loginTestCases 
{
  LoginPage page= new LoginPage();
  
  
  @Test
  public void wrongPassword()
  {
	  try {
     String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	  page.setUsername(ReadExcelData.getInstance("WrongPass").getStringValue("Username")+ System.currentTimeMillis());
      page.setPassword(ReadExcelData.getInstance("WrongPass").getStringValue("Password"));
	  page.clickOnLogin();
	  try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  Assert.assertTrue(page.passwordIncorrect().contains("Your login attempt was not successful"));
	  } catch (IOException e) {
			
		}
  }
}
