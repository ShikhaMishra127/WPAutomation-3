package utilities.common;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * TestRailListener extends TestNGs listener in order to update Test Rail whenever
 * a test passes or fails. To implement, the user can either:
 *
 * 1) add the listener to the testng.xml file
 *
 *      <listeners>
 *          <listener class-name="utilities.common.TestRailListener" />
 *      </listeners>
 *
 * 2) add a reference to the listener in the test class itself
 *
 *      @Listeners({TestRailListener.class})
 *
 */
public class TestRailListener extends TestListenerAdapter {

    private TestRail tRail = new TestRail();

    /**
     * Searches the code, looking for the annotation "@TestRailReference(id=nnnn)".
     *
     * @param tr    Test result information from the method being tested
     * @return      Test Rail test case number associated with method being tested
     */
    private int getTestcase(ITestResult tr) {

        int testCaseID = 0;
        Method currentTestMethod = tr.getMethod().getConstructorOrMethod().getMethod();

        if (currentTestMethod.isAnnotationPresent(TestRailReference.class)) {
            Annotation annotation = currentTestMethod.getAnnotation(TestRailReference.class);
            TestRailReference reference = (TestRailReference) annotation;
            testCaseID = reference.id();
        }

        return testCaseID;
    }

    /**
     * Update Test Rail with the appropriate information on test completion
     *
     * @param tr        Test result information from the method being tested
     * @param status    Test Rail status; typically "PASSED/FAILED"
     * @param comment   String containing additional information included in result
     */
    private void postTestResult(ITestResult tr, TestRail.Status status, String comment) {

        int tc = getTestcase(tr);

        String logData;

        Browser browser = (Browser)tr.getTestContext().getAttribute("browser");

        logData = comment + "\n" + browser.GetLog();

        // if we are given a valid test case number, add results to Test Rail
        if (tc != 0) {
            tRail.UpdateTestcase(String.valueOf(tc), status, logData);
        }
    }

    /**
     * Perform an action when the automated test PASSES
     *
     * @param tr    Test result information from the method being tested
     */
    @Override
    public void onTestSuccess(ITestResult tr) {
        postTestResult(tr, TestRail.Status.PASSED, "Test '" + tr.getName() + "' PASSED");
    }

    /**
     * Perform an action when the automated test FAILS
     *
     * @param tr    Test result information from the method being tested
     */
    @Override
    public void onTestFailure(ITestResult tr) {
        postTestResult(tr, TestRail.Status.FAILED, "Test '" + tr.getName() + "' FAILED");
    }
}
