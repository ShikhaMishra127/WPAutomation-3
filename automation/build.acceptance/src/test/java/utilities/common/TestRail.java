package utilities.common;

import org.json.simple.JSONObject;
import utilities.testrail.APIClient;
import utilities.testrail.APIException;

import java.io.IOException;

/**
 * The TestRail class allows users to interface with the Gurok TestRail API.
 *
 * The companyspecification is located at: http://docs.gurock.com/testrail-api2/start
 *
 */
public class TestRail {

	private APIClient API;
	private ResourceLoader env;
	private String ProjectID;
	private String SuiteID;
	private String RunID;

	public enum Status{ COMMENT, PASSED, BLOCKED, UNTESTED, RETEST, FAILED }

	public TestRail() {

		// load resources from env.properties file and connect to Test Rail server
		env = new ResourceLoader("env");
		API = new APIClient(env.getValue("testrail_url"));

		API.setUser(env.getValue("testrail_username"));
		API.setPassword(env.getValue("testrail_password"));
		ProjectID = env.getValue("testrail_projectID");
		SuiteID = env.getValue("testrail_suiteID");
		RunID = env.getValue("testrail_runID");
	}

	// public Getters/Setters
	public void SetProject(String id) { ProjectID = id; }
	public void SetSuite(String id) { SuiteID = id; }
	public void SetRun(String id) { RunID = id; }

	public String GetProject() { return ProjectID; }
	public String GetSuite() { return SuiteID; }
	public String GetRun() { return RunID; }

	/**
	 * use System property -DTESTRAIL to allow posting to our testcase server, defaults to 'false'
	 *
	 * @return  whether to post results to TestRail repository
	 */
	private boolean postToTestRail() {
		String val = System.getProperty("TESTRAIL", "false");
		return Boolean.valueOf(val).booleanValue();
	}

	/**
	 *
	 * @param runName		Name of the test case run you want to create
	 *                      Project, Suite
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


	private JSONObject Get(String command, String RefID) {

		JSONObject objectOut = new JSONObject();

		String tc = command + "/" + RefID;

		try {
			objectOut = (JSONObject) API.sendGet(tc);
		} catch (IOException | APIException e) {
			e.printStackTrace();
		}

		return objectOut;
	}

	private JSONObject Post(String command, String RefID, JSONObject objectIn) {

		JSONObject objectOut = new JSONObject();

		String tc = command + "/" + RefID;

		try {
			objectOut = (JSONObject) API.sendPost(tc, objectIn);
		} catch (IOException | APIException e) {
			e.printStackTrace();
		}

		return objectOut;
	}

	/**
	 * Update a test case with both a status and comments
	 *
	 * @param TCNumber	Test Case number - from "Test Suites and Cases"
	 * @param TCStatus	Test Case status. Most common is PASSED/FAILED
	 * @param TCComment String containing additional information included in result
	 */
	 public void UpdateTestcase(String TCNumber, Status TCStatus , String TCComment) {

		JSONObject object = new JSONObject();
		JSONObject returnObj;

		 // fill up our object with test case result data
		 if (TCStatus != Status.COMMENT) {
			 object.put("status_id", TCStatus.ordinal());
		 }
		 object.put("comment", TCComment);

		 if (postToTestRail()) {
			 returnObj = this.Post("add_result_for_case", RunID + "/" + TCNumber, object);
		 }
		 else
		 {
		 	System.out.printf("Test case %s %s (%s)\n", TCNumber, TCStatus.toString(), TCComment);
		 }
	}
}
