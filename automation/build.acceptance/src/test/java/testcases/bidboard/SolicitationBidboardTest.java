package testcases.bidboard;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.bidboard.SolicitationBidboardPOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;

import java.io.IOException;

public class SolicitationBidboardTest {

    Browser browser;
    ResourceLoader resource;
    SolicitationBidboardPOM bidboard;

    SolicitationBidboardTest() throws IOException {  }

    @BeforeClass
    public void setup() throws IOException {
        resource = new ResourceLoader("data/bidboard");
        browser = new Browser();
        bidboard = new SolicitationBidboardPOM(browser);

        browser.get(browser.solicitationUrl);
    }

    @AfterClass
    public void teardown() {
        browser.close();
    }

    @Test()
    public void ViewSolicitationList() {

    }
}
