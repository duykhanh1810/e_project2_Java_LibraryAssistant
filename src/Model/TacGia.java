package Model;

public class TacGia {
    private String maTacGia;
    private String tenTacGia;
    private String quocTichTacGia;

    public TacGia(String maTacGia, String tenTacGia, String quocTichTacGia) {
        this.maTacGia = maTacGia;
        this.tenTacGia = tenTacGia;
        this.quocTichTacGia = quocTichTacGia;
    }

    public TacGia() {
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getQuocTichTacGia() {
        return quocTichTacGia;
    }

    public void setQuocTichTacGia(String quocTichTacGia) {
        this.quocTichTacGia = quocTichTacGia;
    }
    
}
