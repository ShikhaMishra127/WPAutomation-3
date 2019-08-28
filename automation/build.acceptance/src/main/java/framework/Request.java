package framework;

public class Request {

    private String reqName;
    private String reqNumber;
    private String reqSupplierName;
    private String reqTotal;
    private String reqStatus;
    private String reqPONumber;

    // Setters
    public void setReqName(String name) { this.reqName = name; }
    public void setReqNumber(String number) { this.reqNumber = number; }
    public void setReqSupplierName(String name) { this.reqSupplierName = name; }
    public void setReqStatus(String status) { this.reqStatus = status; }
    public void setReqPONumber(String reqPONumber) { this.reqPONumber = reqPONumber; }
    public void setReqTotal(String reqTotal) { this.reqTotal = reqTotal; }

    // Getters
    public String getReqName() { return reqName; }
    public String getReqNumber() { return reqNumber; }
    public String getReqSupplierName() { return reqSupplierName; }
    public String getReqStatus() { return reqStatus; }
    public String getReqPONumber() { return reqPONumber; }
    public String getReqTotal() { return reqTotal; }

}
