package buyer.testcases.solicitation;

import com.beust.jcommander.Strings;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public  class apitest {
    public static void main(Strings[] args){

        RestAssured.baseURI = "https://webprocure-stage.proactiscloud.com/wp-request/api/request/integrate";
        given().log().all().header("Content-Type","application/json").body("{  \n" +
                        "   \"apiKey\": \"uQMYAbHtBjmrG3RW-HATwQmUguXDL9z3r-VxPrtfeMRzACL2Zn-TMn3pu4qG6gJVF7L\",\n" +
                        "   \"docNum\":\" C9990000075\",\n" +
                        "   \"docType\":\"RECEIPT\",\n" +
                        "   \"receiverID\": \"a78fee9b-5004-408d-ae46-3b639489f59f\",\n" +
                        "   \"message\":\"Manually accepted by POSTMAN\",\n" +
                        "   \"success\": 1\n" +
                        "}").when().post("wp-order/api/receipt/integrate")
                .then().log().all().assertThat().statusCode(200);
    }

}
