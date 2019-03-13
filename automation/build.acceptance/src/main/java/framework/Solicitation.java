package main.java.framework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Solicitation {

    private String solNumber;
    private String solName;
    private String solLongDesc;
    private String solStartDate;
    private String solEndDate;
    private String solStatus;
    private String solAttachment;

    public Solicitation() {  }

    public void setSolNumber(String number) { solNumber = number; }
    public void setSolName(String name) { solName = name; }
    public void setSolLongDesc(String longdesc) { solLongDesc = longdesc; }
    public void setSolStartDate(String date) { solStartDate = date; }
    public void setSolStartDatePlusMin(int min) { solStartDate = getDatePlusMin(min); }
    public void setSolEndDate(String date) { solEndDate = date; }
    public void setSolEndDatePlusMin(int min) { solEndDate = getDatePlusMin(min); }
    public void setSolStatus(String status) { solStatus = status; }
    public void setSolAttachment(String attachment) { solAttachment = attachment; }

    private String getDatePlusMin(int min) {
        return LocalDateTime.now().plusMinutes(min).format(DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm:ss a"));
    }

    public String getSolNumber() { return solNumber; }
    public String getSolName() { return solName; }
    public String getSolLongDesc() { return solLongDesc; }
    public String getSolStartDate() { return solStartDate; }
    public String getSolEndDate() { return solEndDate; }
    public String getSolStatus() { return solStatus; }
    public String getSolAttachment() { return solAttachment; }

    public void dumpSolInfo() {

        System.out.format("NAME: %s%n", solName);
        System.out.format("NUMBER: %s%n", solNumber);
        System.out.format("START: %s%n", solStartDate);
        System.out.format("END: %s%n", solEndDate);
        System.out.format("LONGDESC: %s%n", solLongDesc);
        System.out.format("STATUS: %s%n", solStatus);
        System.out.format("ATTACH: %s%n%n", solAttachment);
    }
}

