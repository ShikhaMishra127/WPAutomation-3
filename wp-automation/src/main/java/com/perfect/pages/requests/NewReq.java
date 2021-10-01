package com.perfect.pages.requests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class NewReq {

    // handle to entire req container
    SelenideElement reqIFrame = $(byName("C1ReqMain"));

    // tab elements above new req container
    SelenideElement catalogTab = $x("//li[contains(@class,'paginate_button')]/a[contains(@href,'javascript:void')]");

    SelenideElement  roundTripTab = $x("//a[@id='idRoundTrip']");

    SelenideElement favoritesTab = $x("//a[@id='idFavorites']");

    SelenideElement offCatalogTab = $x("//a[@id='idOff-Catalog Request']");

    SelenideElement templatesTab = $x("//a[@id='idTemplates']");

    SelenideElement viewReqTab = $x("//a[@id='idView Request']");

    // any modal dialogs that pop-up when creating a req
    SelenideElement modalDialog = $x("//body[contains(@onload,'DisplayMsg()')]");

    SelenideElement modalNoButton = $x("//button[@data-bb-handler='cancel']");

    //////////////////////////////////////////////////////////////////////// NEW REQ FOOTER

    SelenideElement footerIFrame = $(byName("reqcart"));

    SelenideElement reqNameEdit =$x("//input[@id='txtReqName']");

    SelenideElement footerItemCount = $x("//span[@class='CartInfo'])[2]");


    //////////////////////////////////////////////////////////////////////// OFF CATALOG PAGE

    SelenideElement ocOrderQtyEdit = $x("//input[@id='OrderQty']");

    SelenideElement ocUnitPriceEdit = $x("//input[@id='UnitPrice']");

    SelenideElement ocSupplierPartNoEdit  = $x("//input[@id='SupplierPartNum']");

    SelenideElement ocMfrNameEdit  = $x("//input[@id='input_MfrName']");

    SelenideElement ocNeedByDateEdit = $x("//div[@id='grp_needbydate']//input[@id='dateNeedBy']");

    SelenideElement ocCommodityEdit = $x("//input[@id='input_catcode']");

    SelenideElement ocNoContractConfirmButton = $x("//button[@data-bb-handler='cancel']");

    ElementsCollection ocCommodityList = $$x("//ul[contains(@class,'ui-autocomplete')]/li/a/div/div");

    SelenideElement ocCommodityDescEdit = $x("//div[@id='input_CommodityDescc']");

    SelenideElement ocUOMDrop = $x("//span[@class='select2-selection select2-selection--single']");

    SelenideElement ocCurrencyDrop = $x("//select[@id='selCurrencyCode']");

    SelenideElement ocBuyerPartNoEdit = $x("//input[@id='BuyerPartNum']");

    SelenideElement ocVendorNameEdit = $x("//input[@id='input_SupplierName']");

    SelenideElement ocVendorList  = $x("//ul[contains(@class,'ui-autocomplete')]/li/div");

    SelenideElement ocVendorSearchButton = $x("//span[@id='btnSuppSrch']");

    SelenideElement ocUsageCodeDrop = $x("//select[@id='cboUsageCode']");

    SelenideElement ocRetainInfoCheck = $x("//input[@id='chk-retain-top']");

    SelenideElement ocAddItemButton = $x("//button[@id='btn-add-top']");

    SelenideElement ocAssociateContractCheck = $x("//input[@id='contractNum_Toggle']/parent::*");

    SelenideElement ocResetCommoditySearchButton = $x("//span[contains(@href,'resetCommoditySrch')]");

    //////////////////////////////////////////////////////////////////////// VIEW REQUEST PAGE

    SelenideElement vrSubmitReqButton = $x("//button[contains(@onclick,'ReqSubmit')]");

    SelenideElement vrConfirmSubmitReqButton = $x("//button[contains(@onclick,'OrderSubmit')]");

    SelenideElement vrCloseReqButton = $x("//button[@name='btnClose']");
}
