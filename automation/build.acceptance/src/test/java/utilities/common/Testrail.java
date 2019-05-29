package utilities.common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

public class Testrail {

	public APIClient setup() throws MalformedURLException, IOException, APIException {
		ResourceLoader environment = new ResourceLoader("env");
		String testrailUrl = environment.getValue("testrailurl");
		String railUsername = environment.getValue("testrail_username");
		String railPassword = environment.getValue("testrail_password");
		APIClient client = new APIClient(testrailUrl);
		client.setUser(railUsername);
		client.setPassword(railPassword);
		
		return client;

	}

	public void updateTestCases(String TestCaseNumber, Enum Status, String Results)
			throws MalformedURLException, IOException, APIException {
		/*
		 * JSONObject c = (JSONObject) client.sendGet("get_case/8249");
		 * System.out.println(c.get("title")); 
		 * Map dataStart = new HashMap();
		 * dataStart.put("comment", "This test is 'Running'.");
		 * JSONObject r = (JSONObject) client.sendPost("add_result/10435", dataStart);
		
		 * Map dataEnd = new HashMap(); dataEnd.put("status_id", new Integer(1)); r =
		 * (JSONObject) client.sendPost("add_result/10435", dataEnd);
		 */
		
	}
}
