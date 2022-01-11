package service;
import java.sql.*;

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

            System.out.println(" acc num : " + id + " amount : " + ammt+"Name " + nm + " dob : " + dob.toString() + " status : " + sts);
        }
        connection.commit();
        rs.close();
    }

   public void Displaybalance(int acNum) throws SQLException{
        String sql="select amt,acHldNm from where acNum =?";
       PreparedStatement ps2 = connection.prepareStatement(sql);
       System.out.println("sql :"+sql);
       ps2.setInt(1, acNum);
       ResultSet rs = ps2.executeQuery();
       while(rs.next()){
           double ammt1 = rs.getDouble("amt");
           String nm1 = rs.getString("acHldNm");
           System.out.println("Name :"+ammt1+"  balance  :"+nm1);

       }
       connection.commit();
       rs.close();
   }



}



