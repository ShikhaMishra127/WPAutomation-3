package utilities.restbuilder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import utilities.common.ResourceLoader;

public class ERPInput {

    private RequestSpecification request;
    private Response response;
    private JSONObject object;

    public ERPInput(String docNum, String docType, String success) {

        ResourceLoader resource = new ResourceLoader("data/api");
        object = new JSONObject();

        object.put("apiKey", resource.getValue("erp_apiKey"));
        object.put("receiverID", resource.getValue("erp_receiverID"));

        object.put("docNum", docNum);
        object.put("docType", docType);
        object.put("message", (success.contains("0") ? "REJECTED" : "ACCEPTED") + " by Automation");
        object.put("success", success);

        if (docType.contains("CHECK")) {
            object.put("check", "0001");
        }
    }

    public void Add(String key, String value) {
        object.put(key, value);
    }

    public void Replace(String key, String newvalue) {
        object.replace(key, newvalue);
    }

    public String Send() {

        request = RestAssured.given();
        String result;

        System.out.println("SENDING:\n" + object.toJSONString());

        request.baseUri("http://10.5.1.162:9990/wp-request/api/request/integrate");
        request.header("Content-Type", "application/json");

        request.body(object);

        try {
            response = request.post();
            result = response.asString();
        } catch (Exception e) {
            throw new RuntimeException("Error posting JSON", e);
        }

        System.out.println("RESPONSE:\n" + result + "\n");

        return result;
    }
}
