package api;

import org.testng.annotations.Test;
import utilities.restbuilder.ERPInput;
import utilities.restbuilder.RestQuery;

public class ERPResponseTest {

    @Test
    public void sampleTest() {

        RestQuery rest = new RestQuery("http://10.5.1.162:9990/wp-request/api/request/integrate");
        ERPInput reqObj = new ERPInput();

        reqObj.buildObj( "RN102000022", "REQUEST", "1");
        rest.sendRequest(reqObj);

        reqObj.buildObj( "RN102000023", "REQUEST", "1");
        rest.sendRequest(reqObj);

    }
}
