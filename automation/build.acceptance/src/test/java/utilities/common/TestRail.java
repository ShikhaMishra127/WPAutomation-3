package utilities.common;

import org.json.simple.JSONObject;

import utilities.common.TestRail.Status;
import utilities.testrail.APIClient;
import utilities.testrail.APIException;

import java.io.IOException;


public class TestRail {

	private APIClient API;
	private ResourceLoader env;
	private String ProjectID;
	private String SuiteID;
	private String RunID;

	public enum Status{ PASSED, BLOCKED, UNTESTED, RETEST, FAILED }

	public TestRail() {

		env = new ResourceLoader("env");
		API = new APIClient(env.getValue("testrail_url"));

		API.setUser(env.getValue("testrail_username"));
		API.setPassword(env.getValue("testrail_password"));
		ProjectID = env.getValue("testrail_projectID");
		SuiteID = env.getValue("testrail_suiteID");
	}

	public void SetProject(String id) { ProjectID = id; }
	public void SetSuite(String id) { SuiteID = id; }
	public void SetRun(String id) { RunID = id; }

	public String GetProject() { return ProjectID; }
	public String GetSuite() { return SuiteID; }
	public String GetRun() { return RunID; }

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
	public void AddTestRun(String runName) {

		JSONObject object = new JSONObject();
		JSONObject returnObj;

		object.put("name", runName);
		object.put("description", "Automated test run created for use by Selenium Build Acceptance");
		object.put("suite_id", Integer.parseInt(SuiteID) );
		object.put("include_all", Boolean.TRUE);

		returnObj = this.Post("add_run", ProjectID, object);

		SetRun(returnObj.get("id").toString());
	}

	public void CloseTestRun() {

		this.Post("close_run", this.RunID, new JSONObject() );
	}


	public JSONObject Get(String command, String RefID) {

		JSONObject objectOut = new JSONObject();
		String tc = command + "/" + RefID;

		try {
			objectOut = (JSONObject) API.sendGet( tc );
		} catch (IOException | APIException e) {
			e.printStackTrace();
		}

		return objectOut;
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
		JSONObject object = new JSONObject();
		JSONObject returnObj;

		object.put("name"," New Automation Test ");
		object.put("description", "Automated test run created for use by Selenium Build Acceptance");
		object.put("suite_id", Integer.parseInt(SuiteID) );
		object.put("status_id", TCStatus.ordinal());
		object.put("include_all", Boolean.TRUE);

		returnObj = this.Post("add_result_for_case", ProjectID+"/"+TCNumber, object);

		SetRun(returnObj.get("id").toString());
		

	}
	

}
