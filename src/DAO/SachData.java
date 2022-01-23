package DAO;

import Model.Sach;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class SachData {
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
    
    public static void InsertSach(Sach s){
        String sql = "INSERT into SACH values(?,?,?,?,?,?)";
        try {
            ps = Connect.getConnect().prepareStatement(sql);
            ps.setString(1, s.getMaSach());
            ps.setString(2, s.getTenSach());
            ps.setInt(3, s.getMaTheLoai());
            ps.setInt(4, s.getMaTacGia());
            ps.setInt(5, s.getMaNhaXuatBan());
            ps.setString(6, s.getTinhTrang());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Đã thêm sách thành công!","Thông báo",1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sách không được thêm!","Thông báo",1);            
        }
    }
    
    public boolean UpdateSach(Sach s){
        try {
            ps = Connect.getConnect().prepareStatement("UPDATE SACH SET Ten_Sach = ?, Ma_The_Loai = ?,"
                    + "Ma_Tac_Gia = ?, Ma_Nha_Xuat_Ban = ?, Tinh_Trang = ? where Ma_Sach = ?");
            ps.setString(6, s.getMaSach());
            ps.setString(1, s.getTenSach());
            ps.setInt(2, s.getMaTheLoai());
            ps.setInt(3, s.getMaTacGia());
            ps.setInt(4, s.getMaNhaXuatBan());
            ps.setString(5, s.getTinhTrang());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean DeleteSach(String ms){
        try {
            ps = Connect.getConnect().prepareStatement("DELETE FROM SACH WHERE Ma_Sach = ?");
            ps.setString(1, ms);
            return  ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
