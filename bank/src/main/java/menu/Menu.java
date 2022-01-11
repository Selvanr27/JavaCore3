package menu;

import db.Connectivity;
import service.DbService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {

    private final DbService service;
    public Menu() throws SQLException {

        var connectivity = new Connectivity();
        service = new DbService(connectivity.getConnection());
    }

    private String prepareMenu() {
        String title = "\n --- Admin Menu ---";
        String m1 = "\n 1. Create New Account";
        String m2 = "\n 2. Show All Accounts";
        String m3 = "\n 3. Display Balance";
        String m4 = "\n 4. Transfer";
        String m5 = "\n 5. withdraw";
        String m6 = "\n 6. deposit";
        String m7 = "\n 7. status";
        String m8 = "\n 8. Exit";

        return title + m1 + m2 + m3 + m4 +m5 +m6 + m7 +m8 ;
    }
    private String Usermenu() {
        String title = "\n --- User Menu ---";

        String m1 = "\n 1. Display Balance";
        String m2 = "\n 2. Transfer";
        String m3 = "\n 3. withdraw";
        String m4 = "\n 4. deposit";

        String m5 = "\n 5. Exit";

        return title + m1 + m2 + m3 + m4 +m5  ;
    }
    private String Mainmenu(){
        String title="\n ----Main menu------";
        String m1="\n 1.Admin";
        String m2="\n 2.User ";
        String m3="\n 3.Exit";
        return title +m1+m2+m3;
    }

    public void showMenu() {
        var scanner = new Scanner(System.in);
        System.out.println(Mainmenu());
        int ch1 = scanner.nextInt();
        if (ch1 == 3) System.exit(1);
        if (ch1 == 1) {

           // var scanner = new Scanner(System.in);
            while (true) {
                System.out.println(prepareMenu());
                int ch = scanner.nextInt();

                if (ch == 8) System.exit(1);
                if (ch == 1) {
                    System.out.println("Enter Account Number : ");
                    int acNum = scanner.nextInt();

                    System.out.println("Enter Balance : ");
                    double amt = scanner.nextDouble();

                    System.out.println("Enter Name : ");
                    String acHldNm = scanner.next();

                    try {
                        service.createNewAccount(
                                acNum,
                                amt,
                                acHldNm,
                                Date.valueOf(LocalDate.now()),
                                false
                        );
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (ch == 2) {


                    try {
                        service.printAllAccounts();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (ch == 3) {
                    System.out.println("Enter Account Number : ");
                    int acNum = scanner.nextInt();
                    try {
                        service.Displaybalance(acNum);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(ch1==2) {
            while (true) {
                System.out.println(Usermenu());
                int ch3 = scanner.nextInt();
                if (ch3 == 5) System.exit(1);
            }
        }
    }

}