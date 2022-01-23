package Model;

public class Sach {
    private String maSach;
    private String tenSach;
    private int maTheLoai;
    private int maTacGia;
    private int maNhaXuatBan;
    private String tinhTrang;

    public Sach() {
    }

    public Sach(String maSach, String tenSach, int maTheLoai, int maTacGia, int maNhaXuatBan, String tinhTrang) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maTheLoai = maTheLoai;
        this.maTacGia = maTacGia;
        this.maNhaXuatBan = maNhaXuatBan;
        this.tinhTrang = tinhTrang;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public int getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        this.maTacGia = maTacGia;
    }

    public int getMaNhaXuatBan() {
        return maNhaXuatBan;
    }

    public void setMaNhaXuatBan(int maNhaXuatBan) {
        this.maNhaXuatBan = maNhaXuatBan;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    
    
}
