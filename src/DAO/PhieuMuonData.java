package DAO;

import Model.PhieuMuon;
import Model.Sach;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class PhieuMuonData {
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
    
    public static void InsertPhieu(PhieuMuon p){
        String sql = "INSERT into PHIEU_MUON(Ma_Khach_Hang,Ma_Sach,Ma_Admin,Ngay_Muon,Han_Tra) values(?,?,?,?,?)";
        SimpleDateFormat dsm = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ps = Connect.getConnect().prepareStatement(sql);
//            ps.setString(1, p.getMaPhieuMuon());
            ps.setString(1, p.getMaKhachHang());
            ps.setString(2, p.getMaSach());
            ps.setString(3, p.getMaAdmin());
            ps.setString(4, dsm.format(p.getNgayMuon()));
            ps.setString(5, dsm.format(p.getHanTra()));
//            ps.setDate(6, p.getNgayTra());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Đã thêm phiếu thành công!", "Thông báo",1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Thông báo",1);
        }
    }
    
    public boolean UpdatePhieu(PhieuMuon p){
        SimpleDateFormat dsm = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ps = Connect.getConnect().prepareStatement("UPDATE PHIEU_MUON SET Ma_Khach_Hang = ?, Ma_Sach = ?, Ma_Admin = ?"
                    + "Ngay_Muon = ?, Han_Tra = ? where Ma_Phieu_Muon = ?");
            ps.setString(6, p.getMaPhieuMuon());
            ps.setString(1, p.getMaKhachHang());
            ps.setString(2, p.getMaSach());
            ps.setString(3, p.getMaAdmin());
            ps.setString(4, dsm.format(p.getNgayMuon()));
            ps.setString(5, dsm.format(p.getHanTra()));
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean DeletePhieu(String ms){
        try {
            ps = Connect.getConnect().prepareStatement("DELETE FROM PHIEU_MUON WHERE Ma_Phieu_Muon = ?");
            ps.setString(1, ms);
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            return false;
        }
    }
}
