package Model;

import java.util.Date;

public class KhachHang {
    private String maKhachHang;
    private String password;
    private String name;
    private Date birth;
    private String diaChi;
    private String soDienThoaiKH;

    public KhachHang() {
    }

    public KhachHang(String maKhachHang, String password, String name, Date birth, String diaChi, String soDienThoaiKH) {
        this.maKhachHang = maKhachHang;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.diaChi = diaChi;
        this.soDienThoaiKH = soDienThoaiKH;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoaiKH() {
        return soDienThoaiKH;
    }

    public void setSoDienThoaiKH(String soDienThoaiKH) {
        this.soDienThoaiKH = soDienThoaiKH;
    }

    
    
}
