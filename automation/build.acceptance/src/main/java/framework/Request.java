package framework;

public class Request {

    private String reqName;
    private String reqNumber;
    private String reqSupplierName;
    private String reqTotal;
    private String reqStatus;

    // Setters
    public void setReqName(String name) { reqName = name; }
    public void setReqNumber(String number) {reqNumber = number; }
    public void setReqSupplierName(String name) { reqSupplierName = name; }
    public void setReqStatus(String status) { reqStatus = status; }

    // Getters
    public String getReqName() { return reqName; }
    public String getReqNumber() { return reqNumber; }
    public String getReqSupplierName() { return reqSupplierName; }
    public String getReqStatus() { return reqStatus; }

    // contains a List of req items
    // contains private basic info about req (org, name, number, create date, status)
    // contains getters/setters
}
