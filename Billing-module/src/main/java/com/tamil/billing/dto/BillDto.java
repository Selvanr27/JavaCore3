package com.tamil.billing.dto;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import java.sql.Date;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
//import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BillDto {
    private  long id;

    @NotNull
    @NotBlank(message = "Patient Name Required")
    @Column(unique = true,nullable = false)
    private String patientName;

    private LocalDate billDt;

    @NotNull
    @NotEmpty(message = "Treatment Name Required")
    @Column(unique = false,nullable = false)
    private String billTreatment;

    private LocalDate billPaidDt;

    private Boolean billSts;


    @Min(100)
    @Max(10000)
    @Column(unique = false,nullable = true)
    private Long billAmt;
}
