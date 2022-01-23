package Model;
import java.util.Date;

public class PhieuMuon {
    private String maPhieuMuon;
    private String maKhachHang;
    private String maSach;
    private String maAdmin;
    private Date ngayMuon;
    private Date hanTra;
//    private Date ngayTra;

    public PhieuMuon() {
    }

    public PhieuMuon(String maPhieuMuon, String maKhachHang, String maSach, String maAdmin, Date ngayMuon, Date hanTra) {
        this.maPhieuMuon = maPhieuMuon;
        this.maKhachHang = maKhachHang;
        this.maSach = maSach;
        this.maAdmin = maAdmin;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
//        this.ngayTra = ngayTra;
    }

//    public Date getNgayTra() {
//        return ngayTra;
//    }
//
//    public void setNgayTra(Date ngayTra) {
//        this.ngayTra = ngayTra;
//    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaAdmin() {
        return maAdmin;
    }

    public void setMaAdmin(String maAdmin) {
        this.maAdmin = maAdmin;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getHanTra() {
        return hanTra;
    }

    public void setHanTra(Date hanTra) {
        this.hanTra = hanTra;
    }

    
    

    
}
