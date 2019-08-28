package testcases.buyer.req;

import framework.Request;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.common.TestRailListener;
import utilities.common.TestRailReference;

@Listeners({TestRailListener.class})

public class ReqFlowTest {

    Request request;

    @BeforeClass
    public void setup() {
        request = new Request();
    }

    @Test
    @TestRailReference(id=3597)
    public void CreateRequest(ITestContext testContext) {

        ReqCreator creator = new ReqCreator();
        request = creator.CreateRequest("data/req", testContext);
    }

}

