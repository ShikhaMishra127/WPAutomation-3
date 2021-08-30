package com.perfect.pages.paymentvouchers.enums;

public class PaymentCreatorEnums {

    public enum PaymentIndicator{
        HELD("Held Check"),
        COMBINED("Combined Check"),
        SINGLE("Single Check");

        private final String value;

        PaymentIndicator(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum EFTIndicator{
        YES("Yes"),
        NO("No");

        private final String value;

        EFTIndicator(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum CheckCategory{
        HARDWARE("HARDWARE (H@W)"),
        LEGAL("LEGAL (LGL)"),
        RENT("RENT (RT1)"),
        SERVICES("SERVICES (SVC)"),
        PETTYCASH("PETTY CASH (PC)"),
        MISC("MISCELLANEOUS (MS)");

        private final String value;

        CheckCategory(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
