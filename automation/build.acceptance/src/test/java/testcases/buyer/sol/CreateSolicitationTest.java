package testcases.buyer.sol;

import com.sun.java.swing.ui.CommonMenuBar;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.common.CommodityPickerPOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;

import java.io.IOException;

public class CreateSolicitationTest {

    Browser browser;
    ResourceLoader resource;
    CommodityPickerPOM commodity;

    CreateSolicitationTest() throws IOException {   }

    @BeforeClass
    public void setup() throws IOException {


        browser = new Browser();
        resource = new ResourceLoader("data/solicitation");
        commodity = new CommodityPickerPOM(browser);

        browser.getDriver().get(browser.baseUrl);
    }

    @Test()
    public void ViewSolicitationList() {

    }
}

