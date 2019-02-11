package testcases.buyer.sol;

//import com.sun.java.swing.ui.CommonMenuBar;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.sol.NewSolicitationPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.CommodityPickerPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.UniqueID;

import java.io.IOException;

public class CreateSolicitationTest {

    Browser browser;
    ResourceLoader resource;
    CommodityPickerPOM commodity;
    BuyerNavBarPOM navbar;
    LoginPagePOM login;
    NewSolicitationPOM sol;

    CreateSolicitationTest() throws IOException {   }

    @BeforeClass
    public void setup() throws IOException {

        browser = new Browser();
        resource = new ResourceLoader("data/solicitation");
        commodity = new CommodityPickerPOM(browser);
        navbar = new BuyerNavBarPOM(browser);
        login = new LoginPagePOM(browser);
        sol = new NewSolicitationPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        login.loginAsBuyer();
    }

    @Test()
    public void ViewSolicitationList() {

        navbar.selectDropDownItem("Solicitations", "Create Informal Solicitation" );

        UniqueID solNum = new UniqueID(UniqueID.IDType.DATE);
        String solName = "Automated Sol " + solNum.getNumber();

        sol.headBidTitleEdit.sendKeys(solName);
        sol.headBidNumberEdit.clear();
        sol.headBidNumberEdit.sendKeys(solNum.getNumber());
        sol.headDescriptionEdit.sendKeys("This is a long description for " + solName );

        new Select(sol.headSolPublicTypeDrop).selectByValue("Y");
        new Select(sol.headInvitationTypeDrop).selectByIndex(1);

        sol.headEstTotalEdit.sendKeys("1500.00");

        sol.headSelectCatButton.click();

        browser.waitForElementToAppear(commodity.commoditySearchButton);

        commodity.selectCommodityByCode("05240");
        commodity.selectCommodityByCode("44505");

        commodity.commodityCloseButton.click();


    }
}

