package DAO;

import Model.KhachHang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;


public class KhachHangData {
    public static PreparedStatement ps;
    public static ResultSet rs;
    public KhachHang dangNhap(String taiKhoan,String pass){
        KhachHang kh = null;
        try {
            ps = Connect.getConnect().prepareStatement("SELECT * FROM KHACH_HANG where Ma_Khach_Hang = ? and Password=?");
            ps.setString(1, taiKhoan);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while(rs.next()){
                kh = new KhachHang();
                kh.setBirth(rs.getDate("Ngay_Sinh"));
                kh.setName(rs.getString("Ten_Khach_Hang"));
                kh.setDiaChi(rs.getString("Dia_Chi"));
                kh.setSoDienThoaiKH(rs.getString("So_Dien_Thoai_KH"));
            }
        } catch (Exception e) {
            return kh = null;
        }
        return kh;
    }
    public static ResultSet showTextField(String sql){
        try {
            ps = Connect.getConnect().prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void InsertKhachHang(KhachHang kh){
        String sql = "insert into KHACH_HANG values(?,?,?,?,?,?)";
        SimpleDateFormat dsm = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ps = Connect.getConnect().prepareStatement(sql);
            ps.setString(1, kh.getMaKhachHang());
            ps.setString(2, kh.getPassword());
            ps.setString(3, kh.getName());
            ps.setString(4, dsm.format(kh.getBirth()));
            ps.setString(5, kh.getDiaChi());
            ps.setString(6, kh.getSoDienThoaiKH());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Đã thêm khách hàng thành công!" , "Thông báo", 1);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Khách hàng không được thêm" , "Thông báo", 1);
        }
    }
    
    public boolean UpdateKhachHang(KhachHang kh){
        SimpleDateFormat dsm = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ps = Connect.getConnect().prepareStatement("UPDATE KHACH_HANG SET Password = ?, Ten_Khach_Hang = ?,"
                + "Ngay_Sinh = ?, Dia_Chi = ?, So_Dien_Thoai_KH = ? where Ma_Khach_Hang = ?");
            ps.setString(6, kh.getMaKhachHang());
            ps.setString(1, kh.getPassword());
            ps.setString(2, kh.getName());
            ps.setString(3, dsm.format(kh.getBirth()));
            ps.setString(4, kh.getDiaChi());
            ps.setString(5, kh.getSoDienThoaiKH());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean DeleteKhachHang(String maKhachHang){
        try {
            ps = Connect.getConnect().prepareStatement("DELETE FROM KHACH_HANG WHERE Ma_Khach_Hang = ?");
            ps.setString(1, maKhachHang);
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            return false;
        }
    }
}
