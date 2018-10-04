package utilities.common;

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

    public void LoadJSON(String inputTxt) {
    	request.body(inputTxt);
    }

    public void  Execute() {
        try {
			response = request.post();		
		} catch (Exception e) {
			throw new RuntimeException("Error posting JSON", e);
		}
    }

    public String ResponseToString() {
        return response.asString();
    }
}
