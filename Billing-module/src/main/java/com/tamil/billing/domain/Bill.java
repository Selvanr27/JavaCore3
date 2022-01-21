package com.tamil.billing.domain;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
//import java.util.Date;

import java.time.LocalDate;

@Data
@Table(name="bill2")
@Entity
public class Bill {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;


@Column(unique = false,nullable = true)
    private String PatientName;
@NotNull
@Column(unique =true,nullable = false)
    private LocalDate billDt;

@Column(unique = true,nullable = true)
    private String billTreatment;
@NotNull
@Column(unique = true,nullable = false)
    private LocalDate billPaidDt;
@NotNull
@Column(unique = false,nullable = false)
    private Boolean billSts;
@NotNull
@Column(unique = true,nullable = false)
    private Long billAmt;

}
