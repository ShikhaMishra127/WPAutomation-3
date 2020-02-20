package api;

import org.testng.annotations.Test;
import utilities.restbuilder.RestQuery;

public class ERPResponseTest {

    @Test
    public void sampleTest() {
        RestQuery rest = new RestQuery("http://10.5.1.162:9990/wp-request/api/request/integrate");

        String jsontext = "{\"apiKey\":\"QW23WESW-4434ERRD-334233DDFE-4545RFDF354-FFDCVFGFRGT54-DREDDRFRT5X\", " +
                "\"docNum\":\"RN102000022\", \"docType\":\"REQUEST\", \"receiverID\":\"6bf656fe-9ffb-41ba-a125-a6c780691c87\", " +
                "\"message\":\"Manually accepted by POSTMAN\", \"success\":\"0\" }";

        rest.LoadJSON(jsontext);
        rest.Execute();
        System.out.println("INPUT:\n" + jsontext +"\n");
        System.out.println("RESPONSE:\n" + rest.ResponseToString());

    }
}
