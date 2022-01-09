create table bank_acc(
        acc_num int NOT NULL,
        amount varchar(255) NOT NULL,
        acc_hld_nm varchar(45) NOT NULL,
        acc_cre_dt date,
        status BOOLEAN,
        PRIMARY KEY (`acc_num`)
        );


