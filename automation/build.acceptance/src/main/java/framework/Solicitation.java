package main.java.framework;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Solicitation {

    private String solNumber;
    private String solName;
    private String solLongDesc;
    private ZonedDateTime solStartDate;
    private ZonedDateTime solEndDate;
    private String solStatus;
    private String solAttachment;
    private ZonedDateTime collaborationStartDate;
    private ZonedDateTime collaborationEndDate;

    private DateTimeFormatter inputBoxFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, hh:mm a");

    public Solicitation() {  }

    public void setSolNumber(String number) { solNumber = number; }
    public void setSolName(String name) { solName = name; }
    public void setSolLongDesc(String longdesc) { solLongDesc = longdesc; }
    public void setSolStartDate(ZonedDateTime date) { solStartDate = date; }
    public void setSolEndDate(ZonedDateTime date) { solEndDate = date; }
    public void setSolStatus(String status) { solStatus = status; }
    public void setSolAttachment(String attachment) { solAttachment = attachment; }
    public void setCollaborationStartDate(ZonedDateTime collaborationStartDate) {
        this.collaborationStartDate = collaborationStartDate;
    }
    public void setCollaborationEndDate(ZonedDateTime collaborationEndDate) {
        this.collaborationEndDate = collaborationEndDate;
    }

    public String getSolNumber() { return solNumber; }
    public String getSolName() { return solName; }
    public String getSolLongDesc() { return solLongDesc; }
    public ZonedDateTime getSolStartDate() { return solStartDate; }
    public ZonedDateTime getSolEndDate() { return solEndDate; }
    public String getSolStatus() { return solStatus; }
    public String getSolAttachment() { return solAttachment; }
    public ZonedDateTime getCollaborationStartDate() { return collaborationStartDate; }
    public ZonedDateTime getCollaborationEndDate() { return collaborationEndDate; }


    public String getSolStartDateFormatted() {
        return this.formatDateWithInputBoxFormatter(this.solStartDate);
    }
    public String getSolEndDateFormatted() {
        return this.formatDateWithInputBoxFormatter(this.solEndDate);
    }
    public String getCollaborationStartDateFormatted() {
        return this.formatDateWithInputBoxFormatter(this.collaborationStartDate);
    }
    public String getCollaborationEndDateFormatted() {
        return this.formatDateWithInputBoxFormatter(this.collaborationEndDate);
    }
    private String formatDateWithInputBoxFormatter(ZonedDateTime dateTime){
        return dateTime.format(inputBoxFormatter);
    }

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

