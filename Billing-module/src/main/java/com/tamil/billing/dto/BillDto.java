package com.tamil.billing.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import java.sql.Date;

import java.time.LocalDate;
//import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BillDto {
    private  long id;
    private String patientName;
    private LocalDate billDt;
    private String billTreatment;
    private LocalDate billPaidDt;
    private Boolean billSts;
    private Long billAmt;
}
