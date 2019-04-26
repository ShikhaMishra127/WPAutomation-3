package framework;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Contract {

    private String contractNumber;
    private String contractName;
    private String contractLongDesc;
    private String contractTotalValue;
    private ZonedDateTime contractDateAward;
    private ZonedDateTime contractDateEffective;
    private ZonedDateTime contractDateExpiration;
    private ZonedDateTime contractDateProjected;

    private DateTimeFormatter inputBoxFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    // Setters
    public void setContractNumber(String number) { contractNumber = number; }
    public void setContractName(String number) { contractName = number; }
    public void setContractLongDesc(String number) { contractLongDesc = number; }
    public void setContractTotalValue(String number) { contractTotalValue = number; }
    public void setContractDateAward(ZonedDateTime date) { contractDateAward = date; }
    public void setContractDateEffective(ZonedDateTime date) { contractDateEffective = date; }
    public void setContractDateExpiration(ZonedDateTime date) { contractDateExpiration = date; }
    public void setContractDateProjected(ZonedDateTime date) { contractDateProjected = date; }

    // Getters
    public String getContractNumber() { return contractNumber; }
    public String getContractName() { return contractName; }
    public String getContractLongDesc() { return contractLongDesc; }
    public String getContractTotalValue() { return contractTotalValue; }
    public String getContractDateAwardFormatted() { return this.formatDateWithInputBoxFormatter(this.contractDateAward); }
    public String getContractDateEffectiveFormatted() { return this.formatDateWithInputBoxFormatter(this.contractDateEffective); }
    public String getContractDateExpirationFormatted() { return this.formatDateWithInputBoxFormatter(this.contractDateExpiration); }
    public String getContractDateProjectedFormatted() { return this.formatDateWithInputBoxFormatter(this.contractDateProjected); }

    // Helpers
    private String formatDateWithInputBoxFormatter(ZonedDateTime dateTime){
        return dateTime.format(inputBoxFormatter);
    }
}
