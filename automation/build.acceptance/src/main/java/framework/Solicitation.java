package main.java.framework;

public class Solicitation {

    private String solNumber;
    private String solName;
    private String solStartDate;
    private String solStatus;

    public Solicitation() {  }

    public void setSolNumber(String number) { solNumber = number; }
    public void setSolName(String name) { solName = name; }
    public void setSolStartDate(String date) { solStartDate = date; }
    public void setSolStatus(String status) { solStatus = status; }

    public String getSolNumber() { return solNumber; }
    public String getSolName() { return solName; }
    public String getSolStartDate() { return solStartDate; }
    public String getSolStatus() { return solStatus; }

}

