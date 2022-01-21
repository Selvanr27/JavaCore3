package com.tamil.billing.domain;
import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Table(name="bill4")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;

    @Column(unique = true,nullable = false)
    private String patientName;

    @Column(unique = false,nullable = true)
    private LocalDate billDt;

    @Column(unique = false,nullable = false)
    private String billTreatment;

    @Column(unique = false,nullable = true)
    private LocalDate billPaidDt;

    @Column(unique = false,nullable = true)
    private Boolean billSts;

    @Column(unique = false,nullable = true)
    private Long billAmt;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getBillDt() {
        return billDt;
    }

    public void setBillDt(LocalDate billDt) {
        this.billDt = billDt;
    }

    public String getBillTreatment() {
        return billTreatment;
    }

    public void setBillTreatment(String billTreatment) {
        this.billTreatment = billTreatment;
    }

    public LocalDate getBillPaidDt() {
        return billPaidDt;
    }

    public void setBillPaidDt(LocalDate billPaidDt) {
        this.billPaidDt = billPaidDt;
    }

    public Boolean getBillSts() {
        return billSts;
    }

    public void setBillSts(Boolean billSts) {
        this.billSts = billSts;
    }

    public Long getBillAmt() {
        return billAmt;
    }

    public void setBillAmt(Long billAmt) {
        this.billAmt = billAmt;
    }



}
