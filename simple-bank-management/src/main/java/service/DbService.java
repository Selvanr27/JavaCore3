package service;

import java.sql.*;

public class DbService {

    private final Connection connection;

    public DbService(Connection connection) {
        this.connection = connection;
    }

    // insert query - saving data to database
    public int create(int acc_num, int amount,String acc_hld_name,Date acc_cre_dt,Boolean status ) throws SQLException {
        String sql = "insert into bank_acc values(?, ? ,? ,? ,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, acc_num);
        ps.setInt(2, amount);
        ps.setString(3, acc_hld_name);
        ps.setDate(4, acc_cre_dt);
        ps.setBoolean(5, status );
        // above 5 lines create SQL statement

        int affected = ps.executeUpdate(); // actually firing the query
        connection.commit();
        return affected;
    }

    // update query
    public int update() throws  SQLException {
        String sql="update emp_info set emp_name='Tam',dob='1999-09-27' where emp_id=6";
        PreparedStatement ps=connection.prepareStatement(sql);
        int affected=ps.executeUpdate();
        connection.commit();
        return affected;
    }

    // delete query
    public int delete() throws SQLException {
        String sql="delete from emp_info where emp_id=60 ";
        PreparedStatement ps=connection.prepareStatement(sql);
        int affected=ps.executeUpdate();
        connection.commit();
        return affected;
    }

    // select query - reading the data from database
    public void find() throws SQLException {
        String sql = "select * from emp_info";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); // logical representation of physical table
        while(rs.next()) {
            int id = rs.getInt("emp_id");
            String name = rs.getString("emp_name");
            Date dob = rs.getDate("dob");
            boolean isManager = rs.getBoolean("is_manager");

            System.out.println(" id : "+id + " name : " + name +" dob : "+dob.toString() +" Manager : "+isManager);
        }
        connection.commit();
        rs.close(); // important to close the result set
    }

    public void findEmployeeByName(String name) throws SQLException {

        String sql = "select * from emp_info where emp_name = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            int id = rs.getInt("emp_id");
            String nm = rs.getString("emp_name");
            Date dob = rs.getDate("dob");
            boolean isManager = rs.getBoolean("is_manager");
            System.out.println(" id : "+id + " name : " + nm +" dob : "+dob.toString() +" Manager : "+isManager);
        }

        rs.close();
    }

    public void txnDemo(int empId, String name, Date dob, boolean isManager, int upId) throws SQLException {
        var sql1 = "insert into emp_info values(?, ? , ?, ?)";
        var ps1 = connection.prepareStatement(sql1);
        ps1.setInt(1, empId);
        ps1.setString(2, name);
        ps1.setDate(3, dob);
        ps1.setBoolean(4, isManager);
        var aff1 = ps1.executeUpdate();
        connection.commit();

        var sql2 = "update emp_info set emp_name = 'none' where emp_id = ?";
        var ps2 = connection.prepareStatement(sql2);
        ps2.setInt(1, upId);
        var aff2 = ps2.executeUpdate();
        if(aff2 == 0) connection.rollback();

        connection.commit();
    }
}