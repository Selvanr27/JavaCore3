package com.tamil.billing.repository;

/*
import com.tamil.billing.domain.Bill;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

@Modifying
    @Query(value = "update bill_info set billName,billDt,billTreatment,billPaidDt,billSts,billAmt where id=:id",nativeQuery = true)
    void updateBillRepository(@Param("id") Long id);

@Modifying
    @Query(value = "delete from bill_info where id=:id",nativeQuery = false)
    void deleteBill(@Param("id") Long id);


    Object save();
}
*/



import com.tamil.billing.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}