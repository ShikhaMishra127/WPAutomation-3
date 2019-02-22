package pageobjects.bidboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import testcases.bidboard.SolicitationBidboardTest;
import utilities.common.Browser;

import java.io.IOException;

public class SolicitationBidboardPOM {

    private final Browser browser;

    public SolicitationBidboardPOM(WebDriver browser) throws IOException {
        this.browser = (Browser)browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

}

