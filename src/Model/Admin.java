package Model;

public class Admin {
    private String maAdmin;
    private String password;
    private String tenAdmin;
    private String soDienThoaiAdmin;

    public Admin() {
    }

    public Admin(String maAdmin, String password, String tenAdmin, String soDienThoaiAdmin) {
        this.maAdmin = maAdmin;
        this.password = password;
        this.tenAdmin = tenAdmin;
        this.soDienThoaiAdmin = soDienThoaiAdmin;
    }

    public String getMaAdmin() {
        return maAdmin;
    }

    public void setMaAdmin(String maAdmin) {
        this.maAdmin = maAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTenAdmin() {
        return tenAdmin;
    }

    public void setTenAdmin(String tenAdmin) {
        this.tenAdmin = tenAdmin;
    }

    public String getSoDienThoaiAdmin() {
        return soDienThoaiAdmin;
    }

    public void setSoDienThoaiAdmin(String soDienThoaiAdmin) {
        this.soDienThoaiAdmin = soDienThoaiAdmin;
    }
    
}
