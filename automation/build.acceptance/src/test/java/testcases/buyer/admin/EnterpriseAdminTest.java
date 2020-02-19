package testcases.buyer.admin;

import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.admin.EnterpriseAdminPOM;
import pageobjects.buyer.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailReference;

import java.util.Map;

import static pageobjects.buyer.admin.EnterpriseAdminPOM.SupplierListColumn;

public class EnterpriseAdminTest {

    Browser browser;
    ResourceLoader resource;

    LoginPagePOM login;
    BuyerNavBarPOM navbar;
    EnterpriseAdminPOM admin;

    EnterpriseAdminTest() {
    }

    @BeforeClass
    public void setup(ITestContext testContext) {

        browser = new Browser(testContext);
        resource = new ResourceLoader("data/admin");
        login = new LoginPagePOM(browser);
        navbar = new BuyerNavBarPOM(browser);
        admin = new EnterpriseAdminPOM(browser);

        browser.getDriver().get(browser.baseUrl);
        login.loginAsBuyer();

    }

    @AfterClass
    public void tearDown() {
        browser.clickWhenAvailable(navbar.homePageButton);
        navbar.logout();
        browser.close();
    }

    @Test
    public void GoToEnterpriseAdmin() {

        navbar.selectDropDownItem(resource.getValue("navbar_admin"), resource.getValue("navbar_entadmin"));

    }

    @Test(enabled = true, dependsOnMethods = {"GoToEnterpriseAdmin"})
    @TestRailReference(id = 3527)
    public void GeneralOrgInfoTest() {

        admin.SelectFromMenu(resource.getValue("navbar_orginfo"), resource.getValue("navbar_editorginfo"));
        browser.switchToFrame(admin.dataFrame);

        // go to General Org Info and set Solicitation Bid Board values
        browser.clickSetCheckbox(admin.oiBidBoardAllowDownloadCheckbox, true);
        browser.clickSetCheckbox(admin.oiBidBoardRemoveTypeFilterCheckbox, false);
        browser.clickSetCheckbox(admin.oiBidBoardRemoveCommodityFilterCheckbox, false);
        browser.clickSetCheckbox(admin.oiBidBoardRemovePrintButtonCheckbox, false);

        browser.clickWhenAvailable(admin.oiSaveButton);

        browser.switchBackToTopFrame();

        browser.Log("Solicitation Bid Board settings updated");
    }

    @Test(enabled = true, dependsOnMethods = {"GoToEnterpriseAdmin"})
    @TestRailReference(id = 3527)
    public void WorkflowTest() {

        // go to Request Workflow and shut off all workflows *except* Off-Catalog Requests
        admin.SelectFromMenu(resource.getValue("navbar_wf"), resource.getValue("navbar_reqwf"));

        browser.HideElement(admin.footer); // sometimes the footer pops-up in front of save button - hide it
        browser.switchToFrame(admin.dataFrame);
        browser.switchToFrame(admin.wfFrame);

        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_01"), true);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_02"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_03"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_04"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_05"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_06"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_07"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_08"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_09"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_10"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_11"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_12"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_13"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_14"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_15"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_16"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_17"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_18"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_19"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_20"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_21"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_22"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_23"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_24"), false);
        admin.wfSetWorkflowCheckbox(resource.getValue("wfrule_25"), false);

        browser.clickWhenAvailable(admin.wfSaveButton);
        browser.switchBackToTopFrame();

        browser.Log("'Off-Catalog' workflow enabled; all others disabled");

        // now go to Request Approver and set Off-Catalog approver to our user
        admin.SelectFromMenu(resource.getValue("navbar_wf"), resource.getValue("navbar_reqapp"));
        browser.switchToFrame(admin.dataFrame);

        browser.clickWhenAvailable(admin.raOffCatalogChangeApproverButton);
        browser.clickTypeAheadDropdownItem(admin.raApproverNameEdit, admin.raApproverList, browser.approverName);
        browser.clickWhenAvailable(admin.raSelectButton);

        browser.clickWhenAvailable(admin.raSaveAllButton);

        browser.switchBackToTopFrame();

        browser.Log("Off-Catalog Approver updated");
    }

    @Test(enabled = true, dependsOnMethods = {"GoToEnterpriseAdmin"})
    @TestRailReference(id = 3524)
    public void SuppliersTest() {

        // go to Edit Suppliers and look up our target supplier
        admin.SelectFromMenu(resource.getValue("navbar_supplier"), resource.getValue("navbar_editsupplier"));
        browser.switchToFrame(admin.dataFrame);

        browser.sendKeysWhenAvailable(admin.esSearchName, browser.supplierName);
        browser.clickWhenAvailable(admin.esSearchButton);

        browser.waitForElementToAppear(admin.esSupplierResultTable);

        Map<Browser.HTMLTableColumn, WebElement> supplier = admin.getElementsForSupplierLine(browser.supplierName);

        // if the status of the supplier is NOT "Approved", go and make it active
        if (!supplier.get(SupplierListColumn.STATUS).getText().contains(resource.getValue("supplier_status"))) {

            // edit the supplier
            browser.clickSubElement(supplier.get(SupplierListColumn.ACTION), admin.esActionEdit);

            // change status to "Active" and save
            browser.clickWhenAvailable(admin.esApprovedRadio);
            browser.clickWhenAvailable(admin.esSaveButton);

            browser.Log("Supplier set to APPROVED");
        }

        browser.switchBackToTopFrame();

        browser.Log("Searched for Supplier and verified status");
    }

    @Test(enabled = true, dependsOnMethods = {"GoToEnterpriseAdmin"})
    @TestRailReference(id = 3531)
    public void EditUserTest() {

        // go to Edit Users and look up our target user
        admin.SelectFromMenu(resource.getValue("navbar_user"), resource.getValue("navbar_edituser"));
        browser.switchToFrame(admin.dataFrame);

        browser.sendKeysWhenAvailable(admin.euUsernameEdit, browser.buyerUsername);
        browser.clickWhenAvailable(admin.euFindButton);

        admin.euClickEditIconForUser(browser.buyerUsername);

        // wait for user summary page to appear
        browser.waitForElementToAppear(admin.euUserInfoTable);

        // click on Select All to give our user all rights, then save
        browser.clickWhenAvailable(admin.euSelectAllButton);
        browser.clickWhenAvailable(admin.euSaveUserButton);

        // verify user is active
        browser.Assert("Verify user is Active", admin.euUserStatus.getText(), resource.getValue("buyer_user_status"));

        browser.clickWhenAvailable(admin.euCloseButton);

        browser.Log("User (" + browser.buyerUsername + ") is Active and has been given all permissions");

        browser.switchBackToTopFrame();
    }

}
