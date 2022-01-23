package DAO;

import Model.TacGia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author os
 */
public class TacGiaData {
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static ResultSet showTextField(String sql){
        try {
            ps = Connect.getConnect().prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void InsertTacGia(TacGia tg){
        String sql = "Insert into TAC_GIA(Ten_Tac_Gia,Quoc_Tich_Tac_Gia) values(?,?)";
        try {
            ps = Connect.getConnect().prepareStatement(sql);
//            ps.setInt(1, tg.getMaTacGia());
            ps.setString(1, tg.getTenTacGia());
            ps.setString(2, tg.getQuocTichTacGia());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Đã thêm tác giả thành công","Thông báo",1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tác giả không được thêm","Thông báo",1);
        }
    }
    
    public boolean UpdateTacGia(TacGia tg){
        try {
            ps = Connect.getConnect().prepareStatement("UPDATE TAC_GIA SET Ten_Tac_Gia = ?, Quoc_Tich_Tac_Gia = ? where Ma_Tac_Gia = ?");
            ps.setString(3, tg.getMaTacGia());
            ps.setString(1, tg.getTenTacGia());
            ps.setString(2, tg.getQuocTichTacGia());
            return ps.executeUpdate() >0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean DeleteTacGia(String ms){
        try {
            ps = Connect.getConnect().prepareStatement("DELETE FROM TAC_GIA WHERE Ma_Tac_Gia = ?");
            ps.setString(1, ms);
            return  ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
