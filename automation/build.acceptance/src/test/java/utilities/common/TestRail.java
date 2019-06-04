package utilities.common;

import org.json.simple.JSONObject;
import utilities.testrail.APIClient;
import utilities.testrail.APIException;

import java.io.IOException;


public class TestRail {

	private APIClient API;
	private ResourceLoader env;
	private String ProjectID;
	private String SuiteID;

	public enum Status{ PASS, FAIL, BLOCKED }

	public TestRail() {

		env = new ResourceLoader("env");
		API = new APIClient(env.getValue("testrail_url"));

		API.setUser(env.getValue("testrail_username"));
		API.setPassword(env.getValue("testrail_password"));
		ProjectID = env.getValue("testrail_projectID");
		SuiteID = env.getValue("testrail_suiteID");
	}

	public String GetTestcase(String TCNumber) {

		JSONObject object = this.Get("get_case", TCNumber);

		return (object.get("title")).toString();

	}

	public String GetTestRun(String RunID) {

		return this.Get("get_run", RunID).toJSONString();

	}

	/*
	String runName - Name of the test case run you want to create
	String <return> - test run ID for the test run you just created
	 */
	public String AddTestRun(String runName) {

		JSONObject object = new JSONObject();
		JSONObject returnObj;

		object.put("name", runName);
		object.put("description", "Automated test run created for use by Selenium Build Acceptance");
		object.put("suite_id", Integer.parseInt(SuiteID) );
		object.put("include_all", Boolean.TRUE);

		returnObj = this.Post("add_run", ProjectID, object);
		return returnObj.get("id").toString();
	}


	public JSONObject Get(String command, String RefID) {

		JSONObject object = new JSONObject();
		String tc = command + "/" + RefID;

		try {
			object = (JSONObject) API.sendGet( tc );
		} catch (IOException | APIException e) {
			e.printStackTrace();
		}

		return object;
	}

	public JSONObject Post(String command, String RefID, JSONObject objectIn) {

		JSONObject objectOut = new JSONObject();
		String tc = command + "/" + RefID;

		try {
			objectOut = (JSONObject) API.sendPost(tc, objectIn );
		} catch (IOException | APIException e) {
			e.printStackTrace();
		}

		return objectOut;

	}

	public void UpdateTestcase(String TCNumber, Status TCStatus , String TCComment) {

	}

}
