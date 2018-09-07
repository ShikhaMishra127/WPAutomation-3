package testcases.buyer.login;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.common.Environment;

/**
 * 
 * THIS IS A STUB - Please delete me once we start putting real test cases into the system!
 * @author AXC001
 *
 */

public class Login {

	private WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeTest
	public void setupTest() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=" + Environment.getValue("Language"));
		driver = new ChromeDriver(options);
	}

	@AfterTest
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void test() {

		String bu = Environment.getValue("baseURL");

		System.out.printf("baseURL is: %s\n", bu);
		driver.get(bu);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testREST() {

		RestAssured.baseURI = "http://10.5.1.162:9990/wp-integration-service/api/vendor";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject custom = new JSONObject();
		custom.put("value", "N");
		custom.put("name", "Sheltered Workshop");
		array.add(custom);

		requestParams.put("apiKey", "QW23WESW-4434ERRD-334233DDFE-4545RFDF354-FFDCVFGFRGT54-DREDDRFRT5");
		requestParams.put("supplierID", "88682");
		requestParams.put("receiverID", "23a0b0ca-78f2-1000-a0b2-0afaa07e0001");
		requestParams.put("message", "Bogus Response from ERP system");
		requestParams.put("docType", "VENDOR");
		requestParams.put("docNumber", "1000003");
		requestParams.put("success", "1");
		requestParams.put("custom", array);

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());

		Response response = request.post();

		System.out.println(response.asString());

	}
}
