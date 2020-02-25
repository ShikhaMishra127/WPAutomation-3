package utilities.restbuilder;

import utilities.common.ResourceLoader;

public class ERPInput {

    public String apiKey;
    public String docNum;
    public String docType;
    public String receiverID;
    public String message;
    public String success;

    public ERPInput() {
        ResourceLoader resource;
        resource = new ResourceLoader("data/api");

        apiKey = resource.getValue("erp_apiKey");
        receiverID = resource.getValue("erp_receiverID");
    }

    public void buildObj(String docNum, String docType, String success) {
        this.docNum = docNum;
        this.docType = docType;
        this.message = (success.contains("0")?"REJECTED":"ACCEPTED") + " by Automation";
        this.success = success;
    }
}
