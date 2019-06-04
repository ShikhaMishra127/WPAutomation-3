package testcases.vendor.sol;

import org.testng.annotations.Test;
import utilities.common.TestRail;
import utilities.common.UniqueID;

public class TestcaseUpdateTest {

    public TestRail trail;

    @Test
    public void UpdateCaseTest() {

        UniqueID TodaysRun = new UniqueID(UniqueID.IDType.DATE);
        trail = new TestRail();

        //System.out.printf("TEST CASE NAME: %s%n", trail.GetTestcase("3516"));

        // trail.UpdateTestcase("3516", TestRail.Status.BLOCKED, "Created automated off-catalog request");

        //  System.out.printf("TEST RUN: %s%n",trail.GetTestRun("302"));

        trail.AddTestRun("New Automation Test " + TodaysRun.getNumber());
        System.out.printf("TEST RUN ID CREATED: %s%n", trail.GetRun());
        System.out.printf("TEST RUN CONTENTS: %n%s%n", trail.GetTestRun(trail.GetRun()));
        
        trail.CloseTestRun();


    }

}
