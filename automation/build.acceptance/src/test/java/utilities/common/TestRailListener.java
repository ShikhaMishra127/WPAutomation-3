package utilities.common;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.lang.annotation.Annotation;

public class TestRailListener extends TestListenerAdapter {


    public @interface TestRailID {
        public int id() default 0;
    }

    public void getTestcase(ITestResult tr) {

        Class<ITestResult> obj = ITestResult.class;

        if (obj.isAnnotationPresent(TestRailID.class)) {

            Annotation annotation = obj.getAnnotation(TestRailID.class);
            TestRailID ourIDInfo = (TestRailID)annotation;

            System.out.printf("Found testrail ID: %d%n", ourIDInfo.id());

        }
    }

    @Override
    public void onTestStart(ITestResult tr) {
        getTestcase(tr);
        System.out.println("test started...");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        System.out.println("Test '" + tr.getName() + "' PASSED");

        //        tRail.UpdateTestcase("5781", TestRail.Status.PASSED, "Verified report "+reportName+ "runs.");

    }

    @Override
    public void onTestFailure(ITestResult tr) {
        System.out.println("Test '" + tr.getName() + "' FAILED");
    }

}
