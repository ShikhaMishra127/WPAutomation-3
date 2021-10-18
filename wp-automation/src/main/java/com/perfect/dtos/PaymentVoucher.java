package com.perfect.dtos;

public class PaymentVoucher {

    String supplierName;
    String voucherNumber;
    String invoiceNumber;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String toString() {
        return "PaymentVoucher{" +
                "supplierName='" + supplierName + '\'' +
                ", voucherNumber='" + voucherNumber + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                '}';
    }
}
