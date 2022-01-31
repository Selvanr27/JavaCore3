package com.tamil.billing.domain;
import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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






}
