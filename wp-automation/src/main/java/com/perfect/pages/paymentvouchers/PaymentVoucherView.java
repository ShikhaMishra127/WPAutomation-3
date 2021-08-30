package com.perfect.pages.paymentvouchers;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class PaymentVoucherView {

    //String paymentViewTitle = "//wp-payment-voucher-card//h3";
    //String commodityCodeSelector = "//div[@class='card-shade'][3]/div/div[3]";
    //String justificationSelector = "//div[@class='card-shade'][1]/div[@class='row'][4]/div[2]";
    //String EFTindicatorSelector = "//div[@class='card-shade'][1]/div[@class='row'][6]/div[2]";
    //Striung checkCategory = "//div[@class='card-shade'][1]/div[@class='row'][7]/div[2]";

    public PaymentVoucherView checkPageTitle(String pageTitle) {
        SelenideElement paymentViewTitle = $x("//wp-payment-voucher-card//h3");
        paymentViewTitle.shouldBe(visible, Duration.ofSeconds(2))
                .shouldHave(text(pageTitle));
        return this;
    }

    public PaymentVoucherView checkCommodityCode(String commodityCode) {
        SelenideElement commodityCodeSelector = $x("//div[@class='card-shade'][3]/div/div[3]");
        commodityCodeSelector.shouldBe(visible)
                .shouldHave(text(commodityCode));
        return this;
    }

    public PaymentVoucherView checkJustification(String justification) {
        SelenideElement justificationSelector = $x("//div[@class='card-shade'][1]/div[@class='row'][4]/div[2]");
        justificationSelector.shouldBe(visible)
                .shouldHave(text(justification));
        return this;
    }
}
