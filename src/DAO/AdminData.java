package DAO;

import static DAO.KhachHangData.ps;
import Model.Admin;
import Model.KhachHang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class AdminData {
    public static PreparedStatement ps ;
    public static ResultSet rs;
    public Admin dangNhap(String taiKhoan, String pass){
        Admin ad = null;
        try {
            ps = Connect.getConnect().prepareStatement("SELECT * FROM QUAN_TRI where Ma_Admin = ? and Password = ?");
            ps.setString(1,taiKhoan);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while(rs.next()){
                ad = new Admin();
                ad.setMaAdmin(rs.getString("Ma_Admin"));
                ad.setTenAdmin(rs.getString("Ten_Admin"));
            }
        } catch (Exception e) {
            return ad = null;
        }
        return ad;
    }
    
    public static ResultSet showTextField(String sql){
        try {
            ps = Connect.getConnect().prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void InsertAdmin(Admin ad){
        String sql = "Insert into QUAN_TRI values(?,?,?,?)";
        try {
            ps = Connect.getConnect().prepareStatement(sql);
            ps.setString(1, ad.getMaAdmin());
            ps.setString(2, ad.getPassword());
            ps.setString(3, ad.getTenAdmin());
            ps.setString(4, ad.getSoDienThoaiAdmin());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Đã thêm Admin thành công!" , "Thông báo", 1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Admin không được thêm thành công!","Thông báo",1);
        }
    }
    
    public boolean UpdateAdmin(Admin ad){
        try {
            ps = Connect.getConnect().prepareStatement("UPDATE QUAN_TRI SET Password = ?, Ten_Admin = ?, "
                    + "So_Dien_Thoai_Admin = ? where Ma_Admin = ?");
            ps.setString(4, ad.getMaAdmin());
            ps.setString(1, ad.getPassword());
            ps.setString(2, ad.getTenAdmin());
            ps.setString(3, ad.getSoDienThoaiAdmin());
            return ps.executeUpdate() >0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean DeleteAdmin(String maAd){
        try {
            ps = Connect.getConnect().prepareStatement("DELETE FROM QUAN_TRI WHERE Ma_Admin = ?");
            ps.setString(1, maAd);
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            return false;
        }
    }
}
