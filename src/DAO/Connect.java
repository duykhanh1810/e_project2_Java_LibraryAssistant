package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection con;
    
    public static Connection getConnect(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QUANLYTV2;Username=sa;Password = sa");
        } catch (Exception e) {
            System.out.println("Kết nối không thành công");
        }
            return con;
    }
    public static String testConnect(){
        try {
            con = Connect.getConnect();
            return "Kết nối thành công";
        } catch (Exception e) {
            return "Kết nối thất bại";
        }
    }
}
