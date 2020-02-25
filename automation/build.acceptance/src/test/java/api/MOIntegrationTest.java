package api;

import framework.Request;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.req.ReqCreator;
import utilities.common.Browser;
import utilities.common.ResourceLoader;

public class MOIntegrationTest {

    Request request;
    ResourceLoader resource;

    @BeforeClass
    public void setup() {

        request = new Request();
        resource = new ResourceLoader("data/api");
    }

    @Test
    public void CreateRequestTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        ReqCreator creator = new ReqCreator();

        request = creator.CreateRequest(browser, resource);
    }
}
