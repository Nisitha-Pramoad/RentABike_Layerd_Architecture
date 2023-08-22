package lk.ijse.rentabike.dto;

import java.time.LocalDate;

public class PaymentDTO {
    String paymentId;
    Double paymentAmount;
    String paymentDescription;
    String paymentDate;
    String cId;
    String bId;

    public PaymentDTO() {
    }

    public PaymentDTO(String paymentId, Double paymentAmount, String paymentDescription, String paymentDate, String cId, String bId) {
        this.paymentId = paymentId;
        this.paymentAmount = paymentAmount;
        this.paymentDescription = paymentDescription;
        this.paymentDate = paymentDate;
        this.cId = cId;
        this.bId = bId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentId='" + paymentId + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", paymentDescription='" + paymentDescription + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", cId='" + cId + '\'' +
                ", bId='" + bId + '\'' +
                '}';
    }
}
