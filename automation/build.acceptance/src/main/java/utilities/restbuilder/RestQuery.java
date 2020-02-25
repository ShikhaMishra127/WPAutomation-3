package utilities.restbuilder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestQuery {

    private RequestSpecification request;
    private Response response;

    public RestQuery(String uri) {
        request = RestAssured.given();
        request.baseUri(uri);
        request.header("Content-Type", "application/json");
    }

    public void sendRequest(Object inputObj) {

        // load up the object (will be converted to JSON)
        request.body(inputObj);

        // post object to REST interface
        try {
            response = request.post();
            System.out.println("RESPONSE:\n" + ResponseToString());

        } catch (Exception e) {
            throw new RuntimeException("Error posting JSON", e);
        }

    }

    public String ResponseToString() {
        return response.asString();
    }
}
