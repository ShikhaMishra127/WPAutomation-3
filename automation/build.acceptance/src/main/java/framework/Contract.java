package framework;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Contract {

    private String contractNumber;
    private String contractName;
    private String contractLongDesc;
    private String contractSupplier;
    private String contractTotalValue;
    private ZonedDateTime contractDateAward;
    private ZonedDateTime contractDateEffective;
    private ZonedDateTime contractDateExpiration;
    private ZonedDateTime contractDateProjected;
    private String contractVisibility;
    private String contractType;
    private String contractPricingType;
    private String contractValueFormatted;

    public DateTimeFormatter inputBoxFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public DateTimeFormatter bidboardFormatter = DateTimeFormatter.ofPattern("LLL d, yyyy");

    // Setters
    public void setContractNumber(String number) { contractNumber = number; }
    public void setContractName(String number) { contractName = number; }
    public void setContractLongDesc(String number) { contractLongDesc = number; }
    public void setContractSupplier(String supplier) {contractSupplier = supplier; }
    public void setContractTotalValue(String number) { contractTotalValue = number; }
    public void setContractDateAward(ZonedDateTime date) { contractDateAward = date; }
    public void setContractDateEffective(ZonedDateTime date) { contractDateEffective = date; }
    public void setContractDateExpiration(ZonedDateTime date) { contractDateExpiration = date; }
    public void setContractDateProjected(ZonedDateTime date) { contractDateProjected = date; }
    public void setVisibility(String visibility) { contractVisibility = visibility; }
    public void setContractType(String type) { contractType = type; }
    public void setContractPricingType(String type) { contractPricingType = type; }
    public void setContractValueFormatted(String value) { contractValueFormatted = value; }

    // Getters
    public String getContractNumber() { return contractNumber; }
    public String getContractName() { return contractName; }
    public String getContractLongDesc() { return contractLongDesc; }
    public String getContractSupplier() { return contractSupplier; }
    public String getContractTotalValue() { return contractTotalValue; }
    public String getContractDateAwardFormatted() { return this.formatDateWithInputBoxFormatter(this.contractDateAward); }
    public String getContractDateEffectiveFormatted() { return this.formatDateWithInputBoxFormatter(this.contractDateEffective); }
    public String getContractDateExpirationFormatted() { return this.formatDateWithInputBoxFormatter(this.contractDateExpiration); }
    public String getContractDateProjectedFormatted() { return this.formatDateWithInputBoxFormatter(this.contractDateProjected); }

    public String getContractDateAwardBidboardFormatted() { return this.formatDateWithBidboardFormatter(this.contractDateAward); }
    public String getContractDateExpirationBidboardFormatted() { return this.formatDateWithBidboardFormatter(this.contractDateExpiration); }

    public String getContractVisibility() { return this.contractVisibility; }
    public String getContractType() { return this.contractType; }
    public String getContractPricingType() { return this.contractPricingType; }
    public String getContractValueFormatted() { return this.contractValueFormatted; }

    // Helpers
    private String formatDateWithInputBoxFormatter(ZonedDateTime dateTime){
        return dateTime.format(inputBoxFormatter);
    }
    private String formatDateWithBidboardFormatter(ZonedDateTime dateTime){
        return dateTime.format(bidboardFormatter);
    }

    // eventually, optimize formatter for getter so that it's taken as a parameter
    private String formatDate(ZonedDateTime dateTime, DateTimeFormatter textFormat ){
        return dateTime.format(textFormat);
    }

    public void DumpContractInfo() {
        System.out.printf(" contractNumber: %s%n", contractNumber );
        System.out.printf(" contractName: %s%n", contractName );
        System.out.printf(" contractLongDesc: %s%n", contractLongDesc);
        System.out.printf(" contractTotalValue: %s%n", contractTotalValue);
        System.out.printf(" DateAward: %s%n", getContractDateAwardFormatted());
        System.out.printf(" DateEffective: %s%n", getContractDateEffectiveFormatted());
        System.out.printf(" DateExpiration: %s%n", getContractDateExpirationFormatted());
        System.out.printf(" DateProjected: %s%n", getContractDateProjectedFormatted());
    }

}
