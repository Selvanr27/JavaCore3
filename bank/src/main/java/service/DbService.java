package service;
import java.sql.*;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbService {
    private final Connection connection;

    public DbService(Connection connection) {
        this.connection = connection;
    }

    public int createNewAccount(int acNum, double amt, String acHldNm, Date crDt, boolean status) throws SQLException {
        var sql = "insert into bank_account values (?, ?, ?, ?, ?)";
        var ps1 = connection.prepareStatement(sql); // here you are creating sql
        ps1.setInt(1, acNum);
        ps1.setDouble(2, amt);
        ps1.setString(3, acHldNm);
        ps1.setDate(4, crDt);
        ps1.setBoolean(5, status);

        var affected = ps1.executeUpdate();
        connection.commit();

        return affected;
    }


    // select query - reading the data from database
    public void printAllAccounts() throws SQLException {
        String sql = "select * from bank_account";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("acNum");
            double ammt = rs.getDouble("amt");
            String nm = rs.getString("acHldNm");
            Date dob = rs.getDate("crDt");
            boolean sts = rs.getBoolean("status");

            System.out.println(" acc num : " + id + " amount : " + ammt + "Name " + nm + " dob : " + dob.toString() + " status : " + sts);
        }
        connection.commit();
        rs.close();
    }

    public void Displaybalance(int acNum) throws SQLException {
        String sql = "select amt,acHldNm from bank_account where acNum =?";
        PreparedStatement ps2 = connection.prepareStatement(sql);

        ps2.setInt(1, acNum);
        ResultSet rs = ps2.executeQuery();
        while (rs.next()) {
            double ammt1 = rs.getDouble("amt");
            String nm1 = rs.getString("acHldNm");
            System.out.println("Name :" + nm1 + "  balance  :" + ammt1);

        }
        connection.commit();
        rs.close();
    }

    public void Withdrawamount(int acNum) throws SQLException {
        String sql = "select amt,acHldNm from bank_account where acNum =?";
        PreparedStatement ps2 = connection.prepareStatement(sql);

        ps2.setInt(1, acNum);
        ResultSet rs = ps2.executeQuery();
        while (rs.next()) {
            double ammt1 = rs.getDouble("amt");
            String nm1 = rs.getString("acHldNm");
            System.out.println("Amount :" + ammt1 + "  accHldName  :" + nm1);
            var sc = new Scanner(System.in);
            System.out.println("Enter Amount to withdraw : ");
            int wamt = sc.nextInt();
            if (wamt <= ammt1) {

                String sql1 = "update bank_account set amt=? where acHldNm=?";
                PreparedStatement ps3 = connection.prepareStatement(sql1);
                ps3.setDouble(1, ammt1 - wamt);
                ps3.setString(2, nm1);

                int affected = ps3.executeUpdate();
                connection.commit();
                System.out.println("withdraw Successfull");


            } else {
                System.out.println("Insufficient Balance ");
            }

        }
        connection.commit();
        rs.close();
    }

    public void Depositamount(int acNum) throws SQLException {
        String sql = "select amt,acHldNm from bank_account where acNum =?";
        PreparedStatement ps2 = connection.prepareStatement(sql);

        ps2.setInt(1, acNum);
        ResultSet rs = ps2.executeQuery();
        while (rs.next()) {
            double ammt1 = rs.getDouble("amt");
            String nm1 = rs.getString("acHldNm");
            System.out.println("Amount :" + ammt1 + "  accHldName  :" + nm1);
            var sc = new Scanner(System.in);
            System.out.println("Enter Amount to Deposit : ");
            int damt = sc.nextInt();
            if (damt > 0) {

                String sql1 = "update bank_account set amt=? where acHldNm=?";
                PreparedStatement ps3 = connection.prepareStatement(sql1);
                ps3.setDouble(1, ammt1 + damt);
                ps3.setString(2, nm1);

                int affected = ps3.executeUpdate();
                connection.commit();
                System.out.println("Deposit Successfull");


            } else {
                System.out.println("Please Enter Valid Amount ");
            }

        }
        connection.commit();
        rs.close();
    }

    public void Displaystatus(int acNum) throws SQLException {
        String sql = "select status,acHldNm from bank_account where acNum =?";
        PreparedStatement ps2 = connection.prepareStatement(sql);

        ps2.setInt(1, acNum);
        ResultSet rs = ps2.executeQuery();
        while (rs.next()) {
            boolean sts = rs.getBoolean("status");
            String nm1 = rs.getString("acHldNm");
           if (sts == false) {
                System.out.println("Name :" + nm1 + "  Status  : Inactive");
            } else {
                System.out.println("Name :" + nm1 + "  Status  : Active");

            }
            System.out.println("Name :" + nm1 + "  Status  :"+sts);

            connection.commit();

        }

    }
    /*still its pending
    String sql = " update bank_account set amt=amount- ? where acNum = ?";

    String sql1 = " update bank_account set amount=amount+ ? where account_num = ?";

    PreparedStatement ps = connection.prepareStatement(sql);

    PreparedStatement ps1 = connection.prepareStatement(sql1);

ps.setInt(1, amt);

ps.setInt(2, id);



    Connectivity connectivity = new Connectivity();





ps1.setInt(1, amt);

ps1.setInt(2, id2);



    int affected = ps.executeUpdate(); // actually firing the query

    int affected1 = ps1.executeUpdate();

System.out.println("Amount has bean Transfered ");*/


}



