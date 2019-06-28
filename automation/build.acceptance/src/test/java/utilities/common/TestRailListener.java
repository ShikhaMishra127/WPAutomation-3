package utilities.common;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TestRailListener extends TestListenerAdapter {

    private TestRail tRail = new TestRail();

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

    private void postTestResult(ITestResult tr, TestRail.Status status, String comment) {

        int tc = getTestcase(tr);

        if (tc != 0) {
            tRail.UpdateTestcase(String.valueOf(tc), status, comment);
        }
    }

    @Override
    public void onTestStart(ITestResult tr) {
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        postTestResult(tr, TestRail.Status.PASSED, "Test '" + tr.getName() + "' PASSED");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        postTestResult(tr, TestRail.Status.FAILED, "Test '" + tr.getName() + "' FAILED");
    }

}
