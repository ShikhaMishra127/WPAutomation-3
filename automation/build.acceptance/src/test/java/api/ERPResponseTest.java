package api;

import org.testng.annotations.Test;
import utilities.restbuilder.ERPInput;

public class ERPResponseTest {

    @Test
    public void sampleTest() {

        ERPInput reqObj = new ERPInput("RN102000022", "REQUEST", "1");
        reqObj.Send();

        reqObj.Replace("success", "0");
        reqObj.Send();
    }
}
