package View;

import DAO.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class UpdateTable {
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
    public static Connection con = Connect.getConnect();
    public static void LoadData (String sql, JTable tb){
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            tb.setModel((DbUtils.resultSetToTableModel(rs)));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"Thông báo lỗi",1);
        }
    }
    public static ResultSet ShowTextField(String sql){
        try {
            ps = con.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }
}
