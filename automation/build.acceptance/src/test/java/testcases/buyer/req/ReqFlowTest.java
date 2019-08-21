package testcases.buyer.req;

import framework.Request;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ReqFlowTest {

    Request request;

    @BeforeClass
    public void setup() {
        request = new Request();
    }

    @Test
    public void CreateRequest(ITestContext testContext) {
        ReqCreator creator = new ReqCreator();
        request = creator.CreateRequest("data/req", testContext);
    }

}

