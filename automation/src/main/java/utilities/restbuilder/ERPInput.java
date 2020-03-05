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
    ResourceLoader resource = new ResourceLoader("data/api");

    public ERPInput(String docNum, String docType, String success) {

        object = new JSONObject();

        object.put("receiverID", resource.getValue("erp_receiverID"));

        object.put("docNum", docNum);
        object.put("docType", docType);
        object.put("message", (success.equals("0") ? "REJECTED" : "ACCEPTED") + " by Automation");
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
        if (key.equals("success")) {
            object.replace("message", (newvalue.equals("0") ? "REJECTED" : "ACCEPTED") + " by Automation");
        }
    }

    public String Send() {

        request = RestAssured.given();
        String result;

        String sendType = object.get("docType").toString();

        if (sendType == "REQUEST") {
            object.put("apiKey", resource.getValue("erp_req_apiKey"));
        }
        else {
            object.put("apiKey", resource.getValue("erp_apiKey"));
        }

        request.baseUri(resource.getValue("erp_uri_" + sendType ));
        request.header("Content-Type", "application/json");
        request.body(object);

        try {
            System.out.println("SENDING:\n" + object.toJSONString());
            response = request.post();
            result = response.asString();
        } catch (Exception e) {
            throw new RuntimeException("Error posting JSON", e);
        }

        System.out.println("RESPONSE:\n" + result + "\n");

        return result;
    }

}
