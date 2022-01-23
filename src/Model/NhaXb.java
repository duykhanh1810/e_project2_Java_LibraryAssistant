package Model;

public class NhaXb {
    private String maNhaXuatBan;
    private String tenNhaXuatBan;

    public NhaXb() {
    }

    public NhaXb(String maNhaXuatBan, String tenNhaXuatBan) {
        this.maNhaXuatBan = maNhaXuatBan;
        this.tenNhaXuatBan = tenNhaXuatBan;
    }

    public String getMaNhaXuatBan() {
        return maNhaXuatBan;
    }

    public void setMaNhaXuatBan(String maNhaXuatBan) {
        this.maNhaXuatBan = maNhaXuatBan;
    }

    public String getTenNhaXuatBan() {
        return tenNhaXuatBan;
    }

    public void setTenNhaXuatBan(String tenNhaXuatBan) {
        this.tenNhaXuatBan = tenNhaXuatBan;
    }

    
    
}
