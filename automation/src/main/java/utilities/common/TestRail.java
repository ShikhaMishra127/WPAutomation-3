package utilities.common;

import org.json.simple.JSONObject;
import org.junit.runners.Suite;
import utilities.testrail.APIClient;
import utilities.testrail.APIException;

import java.io.IOException;

import static utilities.common.UniqueID.IDType;

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
	private String RunName;
	private String RunDesc;
	private Boolean postToTestRail;

	public enum Status{ COMMENT, PASSED, BLOCKED, UNTESTED, RETEST, FAILED }

	public TestRail() {

		// load resources from env.properties file and connect to Test Rail server
		env = new ResourceLoader("testrail");
		API = new APIClient(env.getValue("testrail_url"));

		API.setUser(env.getValue("testrail_username"));
		API.setPassword(env.getValue("testrail_password"));

		ProjectID = env.getValue("testrail_projectID");
		RunName = env.getValue("testrail_runName");
		RunDesc = env.getValue("testrail_runDesc");

		// load TestRail settings from command-line
		postToTestRail = true;
		String runNum = System.getProperty("RUN", "none").toLowerCase();
		String suiteXml = System.getProperty("suiteXmlFile", "acceptance.xml");

		// get the correct suiteID from the resource file, based on .xml name
		SuiteID = env.getValue("testrail_suite_" + suiteXml);

		switch (runNum) {

			case "none":	// do not run TestRail
				postToTestRail = false;
				break;

			case "auto":	// create a new test run in TestRail (ex. "Automated Build Acceptance (191120.103951)")
				UniqueID id = new UniqueID(IDType.DATE);
				RunID = AddTestRun( (RunName + " (" + id.getNumber() + ")") );
				break;

			default:		// otherwise, set user-specified run number
				RunID = runNum;
				break;
		}
	}

	/**
	 *
	 * @param runName       Title of the test case run you want to create
	 * @return				The newly created run number
	 */
	public String AddTestRun(String runName) {

		JSONObject object = new JSONObject();
		JSONObject returnObj;

		object.put("name", runName);
		object.put("description", RunDesc);
		object.put("suite_id", Integer.parseInt(SuiteID) );
		object.put("include_all", Boolean.TRUE);

		returnObj = this.Post("add_run", ProjectID, object);

		return returnObj.get("id").toString();
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

    // TestRail API specific for posting attachments
    private JSONObject Post(String command, String RefID, String URI) {

        JSONObject objectOut = new JSONObject();

        String tc = command + "/" + RefID;

        try {
            objectOut = (JSONObject) API.sendPost(tc, URI);
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
	 * @param screenShotURI Path to screenshot, showing where item failed
	 */
    public void UpdateTestcase(String TCNumber, Status TCStatus , String TCComment, String screenShotURI) {

        JSONObject object = new JSONObject();
        JSONObject returnObj;

		// fill up our object with test case result data
		if (TCStatus != Status.COMMENT) {
			object.put("status_id", TCStatus.ordinal());
		}
		object.put("comment", TCComment);

		// Post results to either TestRail or StdOut
        if (postToTestRail) {

            returnObj = this.Post("add_result_for_case", RunID + "/" + TCNumber, object);

            // If we were given a screenshot, add it to the test case result
            if (screenShotURI != "") {

                //get resultID from test result we just posted, so we can add an attachment to it
                String resultID = returnObj.get("id").toString();

                returnObj = this.Post("add_attachment_to_result", resultID, screenShotURI);
            }
        }
        else
        {
            // send log information to stdout
            System.out.printf("Test case %s %s\n--------------------------\n%s\n", TCNumber, TCStatus.toString(), TCComment);
        }
    }
}
