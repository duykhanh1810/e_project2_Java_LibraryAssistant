package View;

import DAO.AdminData;
import DAO.Connect;
import DAO.KhachHangData;
import DAO.PhieuMuonData;
import DAO.SachData;
import DAO.TacGiaData;
import Model.Admin;
import Model.KhachHang;
import Model.PhieuMuon;
import Model.Sach;
import Model.TacGia;
import com.toedter.calendar.JTextFieldDateEditor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.*;


public class JAdminUpdate extends javax.swing.JFrame {
    public Admin admin;
    
    public JAdminUpdate(Admin admin) {
        this.admin = admin;
        initComponents();
        JTextFieldDateEditor dateEditor = (JTextFieldDateEditor) txtNgayMuon.getDateEditor();
//        dateEditor.setEditable(false);
        txtNgayMuon.setEnabled(false);
        UpdateTable.LoadData(sqlSach, tbSach);
        UpdateTable.LoadData(sqlKhach, tbKhach);
        UpdateTable.LoadData(sqlPhieu, tbMuon);
        UpdateTable.LoadData(sqlTacGia, tbTacGia);
        UpdateTable.LoadData(sqlAdmin, tbAdmin);
        String sqlTheLoai = "select * from THE_LOAI";
        combox(sqlTheLoai, this.cbTheLoai,"Ten_The_Loai");
        String sqlTacGia = "select * from TAC_GIA";
        combox(sqlTacGia, this.cbTacGia,"Ten_Tac_Gia");
        String sqlNhaXuatBan = "select * from NHA_XUAT_BAN";
        combox(sqlNhaXuatBan, this.cbNhaXuatBan,"Ten_Nha_Xuat_Ban");
        String sqlKhachHang = "select * from KHACH_HANG";
        combox(sqlKhachHang, this.cbKhachHang,"Ten_Khach_Hang");
        String sqlSach = "select * from SACH where Tinh_Trang = N'Còn'";
        combox(sqlSach, this.cbSach,"Ten_Sach");
        ProcessCrt(false);
        ProcessCrt2(false);
        ProcessCrt3(false);
        ProcessCrt4(false);
        ProcessCrt5(false);
        this.setLocation(250,0);
    }
    /**
     * Creates new form JAdminUpdate
     */
    SachData sachdata = new SachData();
    KhachHangData khachhangdata = new KhachHangData();
    PhieuMuonData phieumuondata = new PhieuMuonData();
    TacGiaData tacgiadata = new TacGiaData();
    AdminData admindata = new AdminData();
    public static PreparedStatement ps = null;
    public static PreparedStatement ps2 = null;
    public static String sqlSach = "select s.Ma_Sach as N'Mã Sách', s.Ten_Sach as N'Tên Sách', l.Ten_The_Loai as N'Tên Thể Loại', "
            + "t.Ten_Tac_Gia as N'Tên Tác Giả', k.Ten_Nha_Xuat_Ban as N'Nhà Xuất Bản', s.Tinh_Trang as N'Tình Trạng' from SACH s \n" +
            "inner join NHA_XUAT_BAN k on s.Ma_Nha_Xuat_Ban = k.Ma_Nha_Xuat_Ban \n" +
            "inner join TAC_GIA t on t.Ma_Tac_Gia = s.Ma_Tac_Gia\n" +
            "inner join THE_LOAI l on l.Ma_The_Loai = s.Ma_The_Loai order by s.Ma_Sach asc";
    
    public static String sqlKhach = "SELECT Ma_Khach_Hang as N'Mã Khách Hàng',Ten_Khach_Hang as N'Tên Khách Hàng',"
            + "Ngay_Sinh as N'Ngày Sinh', Dia_Chi as N'Địa Chỉ',So_Dien_Thoai_KH as N'Số Điện Thoại' FROM KHACH_HANG order by Ma_Khach_Hang asc";
      
    public static String sqlPhieu = "select p.Ma_Phieu_Muon as N'Mã phiếu mượn', k.Ten_Khach_Hang as N'Tên khách hàng', s.Ten_Sach as N'Tên sách', a.Ten_Admin as N'Tên quản trị', p.Ngay_Muon as N'Ngày mượn', p.Han_Tra as N'Hạn trả', p.Ngay_Tra as N'Ngày trả' from PHIEU_MUON p \n" +
                                    "inner join KHACH_HANG k on p.Ma_Khach_Hang = k.Ma_Khach_Hang\n" +
                                    "inner join SACH s on p.Ma_Sach = s.Ma_Sach\n" +
                                    "inner join QUAN_TRI a on p.Ma_Admin = a.Ma_Admin";
    
    public static String sqlTacGia = "SELECT Ma_Tac_Gia as N'Mã Tác Giả', Ten_Tac_Gia as N'Tên Tác Giả', "
            + "Quoc_Tich_Tac_Gia as N'Quốc Tịch' FROM TAC_GIA order by Ma_Tac_Gia asc";
    
    public static String sqlAdmin = "SELECT Ma_Admin as N'Mã Admin', Ten_Admin as N'Tên Admin', So_Dien_Thoai_Admin as N'Số Điện Thoại' FROM QUAN_TRI order by Ma_Admin asc";
    
    public JAdminUpdate() {
    }
    
    public void ProcessCrt(boolean b) {
        this.btAddSach.setEnabled(b);
        this.btEditSach.setEnabled(b);
        this.btDelSach.setEnabled(b);
    }
    
    public void ProcessCrt2(boolean b) {
        this.btAddKhach.setEnabled(b);
        this.btEditKhach.setEnabled(b);
        this.btDelKhach.setEnabled(b);
    }
    
    public void ProcessCrt3(boolean b) {
        this.btAddPhieu.setEnabled(b);
//        this.btEditPhieu.setEnabled(b);
        this.btDelPhieu.setEnabled(b);
    }

    private void ProcessCrt4(boolean b){
        this.btAddTacGia.setEnabled(b);
        this.btEditTacGia.setEnabled(b);
        this.btDelTacGia.setEnabled(b);
    }
    
    private void ProcessCrt5(boolean b){
        this.btAddAdmin.setEnabled(b);
        this.btEditAdmin.setEnabled(b);
        this.btDelAdmin.setEnabled(b);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtLookSach = new javax.swing.JTextField();
        btLookSach = new javax.swing.JButton();
        btNewSach = new javax.swing.JButton();
        btAddSach = new javax.swing.JButton();
        btEditSach = new javax.swing.JButton();
        btDelSach = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenTacGia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNhaXb = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTL = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTinhTrang = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSach = new javax.swing.JTable();
        cbTheLoai = new javax.swing.JComboBox<>();
        cbTacGia = new javax.swing.JComboBox<>();
        cbNhaXuatBan = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        btReset2 = new javax.swing.JButton();
        btRet = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtLookPhieu = new javax.swing.JTextField();
        btLookMuon = new javax.swing.JButton();
        btNewPhieu = new javax.swing.JButton();
        btAddPhieu = new javax.swing.JButton();
        btDelPhieu = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtMaPhieuMuon = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtNguoiMuon = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtSachMuon = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtNgayTra = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbMuon = new javax.swing.JTable();
        btTra = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txtAdmin = new javax.swing.JTextField();
        txtNgayMuon = new com.toedter.calendar.JDateChooser();
        txtHanTra = new com.toedter.calendar.JDateChooser();
        cbKhachHang = new javax.swing.JComboBox<>();
        cbSach = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        btReset3 = new javax.swing.JButton();
        btRet2 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtLookKhach = new javax.swing.JTextField();
        btLookKhach = new javax.swing.JButton();
        btNewKhach = new javax.swing.JButton();
        btAddKhach = new javax.swing.JButton();
        btEditKhach = new javax.swing.JButton();
        btDelKhach = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtMaKhach = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTenKhach = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbKhach = new javax.swing.JTable();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jPanel8 = new javax.swing.JPanel();
        btReset4 = new javax.swing.JButton();
        btRet3 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtLookTacGia = new javax.swing.JTextField();
        btLookTacGia = new javax.swing.JButton();
        btNewTacGia = new javax.swing.JButton();
        btAddTacGia = new javax.swing.JButton();
        btEditTacGia = new javax.swing.JButton();
        btDelTacGia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtMaTacGia = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTenTG = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtQuocTIch = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbTacGia = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        btReset = new javax.swing.JButton();
        btRet4 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtLookAdmin = new javax.swing.JTextField();
        btLookAdmin = new javax.swing.JButton();
        btNewAdmin = new javax.swing.JButton();
        btAddAdmin = new javax.swing.JButton();
        btEditAdmin = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbAdmin = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txtTenAdmin = new javax.swing.JTextField();
        txtSoDienThoaiAdmin = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtMaAdmin = new javax.swing.JTextField();
        txtMatKhauAdmin = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btDelAdmin = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        btReset5 = new javax.swing.JButton();
        btRet5 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản Trị");

        jTabbedPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtLookSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btLookSach.setBackground(new java.awt.Color(0, 102, 102));
        btLookSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btLookSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        btLookSach.setText("Tìm Kiếm");
        btLookSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLookSachActionPerformed(evt);
            }
        });

        btNewSach.setBackground(new java.awt.Color(0, 204, 204));
        btNewSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btNewSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/input.png"))); // NOI18N
        btNewSach.setText("Nhập ");
        btNewSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewSachActionPerformed(evt);
            }
        });

        btAddSach.setBackground(new java.awt.Color(255, 255, 0));
        btAddSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btAddSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btAddSach.setText("Thêm");
        btAddSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddSachActionPerformed(evt);
            }
        });

        btEditSach.setBackground(new java.awt.Color(255, 204, 255));
        btEditSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btEditSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/fix.png"))); // NOI18N
        btEditSach.setText("Sửa");
        btEditSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditSachActionPerformed(evt);
            }
        });

        btDelSach.setBackground(new java.awt.Color(255, 51, 51));
        btDelSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btDelSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png"))); // NOI18N
        btDelSach.setText("Xóa");
        btDelSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDelSachActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Mã Sách");

        txtMaSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Tên Sách");

        txtTenSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Mã Tác Giả");

        txtTenTacGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Mã Nhà Xuất Bản");

        txtNhaXb.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Mã Thể Loại");

        txtTL.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Tình Trạng");

        txtTinhTrang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        tbSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSachMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbSach);

        cbTheLoai.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbTheLoaiPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTheLoaiActionPerformed(evt);
            }
        });

        cbTacGia.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbTacGiaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cbNhaXuatBan.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbNhaXuatBanPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        btReset2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btReset2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/reset.png"))); // NOI18N
        btReset2.setText("Reset");
        btReset2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReset2ActionPerformed(evt);
            }
        });

        btRet.setBackground(new java.awt.Color(204, 204, 204));
        btRet.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btRet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/out.png"))); // NOI18N
        btRet.setText("Quay Lại");
        btRet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRetActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("Thông Tin Sách");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btReset2)
                .addGap(315, 315, 315)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btRet))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btReset2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btRet)
                .addComponent(jLabel27))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaSach, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(txtTenSach))
                                .addGap(145, 145, 145)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtTL, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtTenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTacGia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNhaXb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 135, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btNewSach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAddSach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btEditSach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btDelSach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtLookSach, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btLookSach)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtNhaXb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNewSach)
                    .addComponent(btAddSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btEditSach)
                    .addComponent(btDelSach)
                    .addComponent(txtLookSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLookSach))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addGap(120, 120, 120))
        );

        jTabbedPane1.addTab("Sách", jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(913, 989));

        txtLookPhieu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btLookMuon.setBackground(new java.awt.Color(0, 102, 102));
        btLookMuon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btLookMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        btLookMuon.setText("Tìm Kiếm");
        btLookMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLookMuonActionPerformed(evt);
            }
        });

        btNewPhieu.setBackground(new java.awt.Color(0, 204, 204));
        btNewPhieu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btNewPhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/input.png"))); // NOI18N
        btNewPhieu.setText("Nhập");
        btNewPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewPhieuActionPerformed(evt);
            }
        });

        btAddPhieu.setBackground(new java.awt.Color(255, 255, 0));
        btAddPhieu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btAddPhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btAddPhieu.setText("Thêm");
        btAddPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddPhieuActionPerformed(evt);
            }
        });

        btDelPhieu.setBackground(new java.awt.Color(255, 51, 51));
        btDelPhieu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btDelPhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png"))); // NOI18N
        btDelPhieu.setText("Xóa");
        btDelPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDelPhieuActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("Mã Phiếu Mượn");

        txtMaPhieuMuon.setEditable(false);
        txtMaPhieuMuon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel20.setText("Mã Khách Hàng");

        txtNguoiMuon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setText("Mã Sách");

        txtSachMuon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel22.setText("Ngày Mượn");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel23.setText("Hạn Trả");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel24.setText("Ngày Trả");

        txtNgayTra.setEditable(false);
        txtNgayTra.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        tbMuon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMuonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbMuon);

        btTra.setBackground(new java.awt.Color(0, 255, 0));
        btTra.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/return.png"))); // NOI18N
        btTra.setText("Trả");
        btTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTraActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel25.setText("Mã Admin");

        txtAdmin.setEditable(false);
        txtAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtNgayMuon.setDate(new Date());
        txtNgayMuon.setDateFormatString("dd-MM-yyyy");

        txtHanTra.setDateFormatString("dd-MM-yyyy");

        cbKhachHang.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbKhachHangPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cbSach.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbSachPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        btReset3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btReset3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/reset.png"))); // NOI18N
        btReset3.setText("Reset");
        btReset3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReset3ActionPerformed(evt);
            }
        });

        btRet2.setBackground(new java.awt.Color(204, 204, 204));
        btRet2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btRet2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/out.png"))); // NOI18N
        btRet2.setText("Quay Lại");
        btRet2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRet2ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setText("Thông tin Phiếu Mượn");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(btReset3)
                .addGap(299, 299, 299)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btRet2))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btReset3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btRet2)
                    .addComponent(jLabel28)))
        );

        btRet2.getAccessibleContext().setAccessibleParent(jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btNewPhieu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btAddPhieu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btDelPhieu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btTra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                        .addComponent(txtLookPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btLookMuon))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtAdmin)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtSachMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbSach, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtMaPhieuMuon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtNguoiMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(40, 40, 40)
                                .addComponent(txtNgayMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtHanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(txtMaPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22))
                    .addComponent(txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(txtNguoiMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23))
                    .addComponent(txtHanTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtSachMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNewPhieu)
                    .addComponent(btAddPhieu)
                    .addComponent(btDelPhieu)
                    .addComponent(btTra)
                    .addComponent(txtLookPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLookMuon))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(231, 231, 231))
        );

        txtLookPhieu.getAccessibleContext().setAccessibleParent(jPanel1);
        btLookMuon.getAccessibleContext().setAccessibleParent(jPanel1);
        btNewPhieu.getAccessibleContext().setAccessibleParent(jPanel1);
        btAddPhieu.getAccessibleContext().setAccessibleParent(jPanel1);
        btDelPhieu.getAccessibleContext().setAccessibleDescription("");
        btDelPhieu.getAccessibleContext().setAccessibleParent(jPanel1);
        jLabel19.getAccessibleContext().setAccessibleParent(jPanel1);
        txtMaPhieuMuon.getAccessibleContext().setAccessibleParent(jPanel1);
        jLabel20.getAccessibleContext().setAccessibleParent(jPanel1);
        txtNguoiMuon.getAccessibleContext().setAccessibleParent(jPanel1);
        jLabel21.getAccessibleContext().setAccessibleParent(jPanel1);
        txtSachMuon.getAccessibleContext().setAccessibleParent(jPanel1);
        jLabel22.getAccessibleContext().setAccessibleParent(jPanel1);
        jLabel23.getAccessibleContext().setAccessibleParent(jPanel1);
        jLabel24.getAccessibleContext().setAccessibleParent(jPanel1);
        txtNgayTra.getAccessibleContext().setAccessibleParent(jPanel1);
        btTra.getAccessibleContext().setAccessibleParent(jPanel1);
        jLabel25.getAccessibleContext().setAccessibleParent(jPanel1);
        txtAdmin.getAccessibleContext().setAccessibleParent(jPanel1);

        jTabbedPane1.addTab("Phiếu Mượn", jPanel2);

        txtLookKhach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btLookKhach.setBackground(new java.awt.Color(0, 102, 102));
        btLookKhach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btLookKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        btLookKhach.setText("Tìm Kiếm");
        btLookKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLookKhachActionPerformed(evt);
            }
        });

        btNewKhach.setBackground(new java.awt.Color(0, 204, 204));
        btNewKhach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btNewKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/input.png"))); // NOI18N
        btNewKhach.setText("Nhập");
        btNewKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewKhachActionPerformed(evt);
            }
        });

        btAddKhach.setBackground(new java.awt.Color(255, 255, 0));
        btAddKhach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btAddKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btAddKhach.setText("Thêm ");
        btAddKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddKhachActionPerformed(evt);
            }
        });

        btEditKhach.setBackground(new java.awt.Color(255, 204, 255));
        btEditKhach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btEditKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/fix.png"))); // NOI18N
        btEditKhach.setText("Sửa");
        btEditKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditKhachActionPerformed(evt);
            }
        });

        btDelKhach.setBackground(new java.awt.Color(255, 51, 51));
        btDelKhach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btDelKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png"))); // NOI18N
        btDelKhach.setText("Xóa");
        btDelKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDelKhachActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Mã Khách Hàng");

        txtMaKhach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Password");

        txtPassword.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Tên Khách Hàng");

        txtTenKhach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Ngày Sinh");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Địa Chỉ");

        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Số Điện Thoại");

        txtPhone.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneActionPerformed(evt);
            }
        });

        tbKhach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbKhach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbKhach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhachMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbKhach);
        tbKhach.getAccessibleContext().setAccessibleParent(jScrollPane2);

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        btReset4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btReset4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/reset.png"))); // NOI18N
        btReset4.setText("Reset");
        btReset4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReset4ActionPerformed(evt);
            }
        });

        btRet3.setBackground(new java.awt.Color(204, 204, 204));
        btRet3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btRet3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/out.png"))); // NOI18N
        btRet3.setText("Quay Lại");
        btRet3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRet3ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel29.setText("Thông tin Khách Hàng");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(btReset4)
                .addGap(294, 294, 294)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btRet3))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btReset4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btRet3)
                    .addComponent(jLabel29)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btNewKhach)
                        .addGap(18, 18, 18)
                        .addComponent(btAddKhach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btEditKhach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btDelKhach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtLookKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btLookKhach))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(52, 52, 52)
                                            .addComponent(txtMaKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(115, 115, 115)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 142, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtMaKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNewKhach)
                    .addComponent(btAddKhach)
                    .addComponent(btEditKhach)
                    .addComponent(btDelKhach)
                    .addComponent(txtLookKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLookKhach))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
        );

        jLabel8.getAccessibleContext().setAccessibleDescription("");

        jTabbedPane1.addTab("Khách Hàng", jPanel3);

        txtLookTacGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btLookTacGia.setBackground(new java.awt.Color(0, 102, 102));
        btLookTacGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btLookTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        btLookTacGia.setText("Tìm Kiếm");
        btLookTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLookTacGiaActionPerformed(evt);
            }
        });

        btNewTacGia.setBackground(new java.awt.Color(0, 204, 204));
        btNewTacGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btNewTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/input.png"))); // NOI18N
        btNewTacGia.setText("Nhập");
        btNewTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewTacGiaActionPerformed(evt);
            }
        });

        btAddTacGia.setBackground(new java.awt.Color(255, 255, 0));
        btAddTacGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btAddTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btAddTacGia.setText("Thêm");
        btAddTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddTacGiaActionPerformed(evt);
            }
        });

        btEditTacGia.setBackground(new java.awt.Color(255, 204, 255));
        btEditTacGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btEditTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/fix.png"))); // NOI18N
        btEditTacGia.setText("Sửa");
        btEditTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditTacGiaActionPerformed(evt);
            }
        });

        btDelTacGia.setBackground(new java.awt.Color(255, 51, 51));
        btDelTacGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btDelTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png"))); // NOI18N
        btDelTacGia.setText("Xóa");
        btDelTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDelTacGiaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Mã Tác Giả");

        txtMaTacGia.setEditable(false);
        txtMaTacGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Tên Tác Giả");

        txtTenTG.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Quốc Tịch");

        txtQuocTIch.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        tbTacGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbTacGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTacGiaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbTacGia);

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        btReset.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/reset.png"))); // NOI18N
        btReset.setText("Reset");
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });

        btRet4.setBackground(new java.awt.Color(204, 204, 204));
        btRet4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btRet4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/out.png"))); // NOI18N
        btRet4.setText("Quay Lại");
        btRet4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRet4ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel30.setText("Thông tin Tác Giả");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(btReset)
                .addGap(312, 312, 312)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btRet4))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btReset, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btRet4)
                    .addComponent(jLabel30)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel14))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenTG, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtMaTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(163, 163, 163)
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(txtQuocTIch, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 192, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btNewTacGia)
                        .addGap(18, 18, 18)
                        .addComponent(btAddTacGia)
                        .addGap(18, 18, 18)
                        .addComponent(btEditTacGia)
                        .addGap(18, 18, 18)
                        .addComponent(btDelTacGia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtLookTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btLookTacGia)))
                .addContainerGap())
            .addComponent(jScrollPane5)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtQuocTIch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTenTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNewTacGia)
                    .addComponent(btAddTacGia)
                    .addComponent(btEditTacGia)
                    .addComponent(btDelTacGia)
                    .addComponent(txtLookTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLookTacGia))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(339, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tác Giả", jPanel4);

        txtLookAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btLookAdmin.setBackground(new java.awt.Color(0, 102, 102));
        btLookAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btLookAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        btLookAdmin.setText("Tìm Kiếm");
        btLookAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLookAdminActionPerformed(evt);
            }
        });

        btNewAdmin.setBackground(new java.awt.Color(0, 204, 204));
        btNewAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btNewAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/input.png"))); // NOI18N
        btNewAdmin.setText("Nhập");
        btNewAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewAdminActionPerformed(evt);
            }
        });

        btAddAdmin.setBackground(new java.awt.Color(255, 255, 0));
        btAddAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btAddAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btAddAdmin.setText("Thêm");
        btAddAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddAdminActionPerformed(evt);
            }
        });

        btEditAdmin.setBackground(new java.awt.Color(255, 204, 255));
        btEditAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btEditAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/fix.png"))); // NOI18N
        btEditAdmin.setText("Sửa");
        btEditAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditAdminActionPerformed(evt);
            }
        });

        tbAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAdminMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbAdmin);

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Tên Admin");

        txtTenAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtSoDienThoaiAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Số điện thoại");

        txtMaAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtMatKhauAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Mã Admin");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel26.setText("Mật khẩu");

        btDelAdmin.setBackground(new java.awt.Color(255, 51, 51));
        btDelAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btDelAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png"))); // NOI18N
        btDelAdmin.setText("Xóa");
        btDelAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDelAdminActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));

        btReset5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btReset5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/reset.png"))); // NOI18N
        btReset5.setText("Reset");
        btReset5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReset5ActionPerformed(evt);
            }
        });

        btRet5.setBackground(new java.awt.Color(204, 204, 204));
        btRet5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btRet5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/out.png"))); // NOI18N
        btRet5.setText("Quay Lại");
        btRet5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRet5ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setText("Thông tin Thủ Thư");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(btReset5)
                .addGap(304, 304, 304)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btRet5))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btReset5)
                    .addComponent(btRet5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatKhauAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(149, 149, 149)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoDienThoaiAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 137, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btNewAdmin)
                        .addGap(18, 18, 18)
                        .addComponent(btAddAdmin)
                        .addGap(18, 18, 18)
                        .addComponent(btEditAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btDelAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtLookAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btLookAdmin)))
                .addContainerGap())
            .addComponent(jScrollPane6)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtMaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtTenAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtMatKhauAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtSoDienThoaiAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNewAdmin)
                    .addComponent(btAddAdmin)
                    .addComponent(btEditAdmin)
                    .addComponent(txtLookAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLookAdmin)
                    .addComponent(btDelAdmin))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(335, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Admin", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Cập Nhật Sách");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAdminMouseClicked
        // TODO add your handling code here:
        ProcessCrt5(true);
        this.btAddAdmin.setEnabled(false);
        try {
            int row = this.tbAdmin.getSelectedRow();
            String IDrow = (this.tbAdmin.getModel().getValueAt(row, 0)).toString();
            String sql1 = "SELECT * FROM QUAN_TRI where Ma_Admin ='"+IDrow+"'";
            ResultSet rs = UpdateTable.ShowTextField(sql1);
            if(rs.next()){
                this.txtMaAdmin.setText(rs.getString("Ma_Admin"));
                this.txtMatKhauAdmin.setText(rs.getString("Password"));
                this.txtTenAdmin.setText(rs.getString("Ten_Admin"));
                this.txtSoDienThoaiAdmin.setText((rs.getString("So_Dien_Thoai_Admin")));
            }
            if(this.txtMaAdmin.getText().equals("admin")){
                this.btDelAdmin.setEnabled(false);
                this.btEditAdmin.setEnabled(false);
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_tbAdminMouseClicked

    private void btEditAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditAdminActionPerformed
        // TODO add your handling code here:
        if(this.txtMaAdmin.getText().length()==0) JOptionPane.showMessageDialog(null, "Mã Admin không thể bỏ trống","Thông báo",2);
            else if(this.txtMatKhauAdmin.getText().length()==0) JOptionPane.showMessageDialog(null, "Mật khẩu Admin không thể bỏ trống","Thông báo",2);
            else if(this.txtTenAdmin.getText().length()==0) JOptionPane.showMessageDialog(null, "Tên Admin không thể bỏ trống","Thông báo",2);
            else if(this.txtSoDienThoaiAdmin.getText().length()==0 || this.txtSoDienThoaiAdmin.getText().length()<10 || this.txtSoDienThoaiAdmin.getText().length()>11) JOptionPane.showMessageDialog(null, "Số điện thoại Admin không hợp lệ","Thông báo",2);
            else if(this.txtMaAdmin.getText().length()>10) JOptionPane.showMessageDialog(null, "Mã Admin không thể lớn hơn 10 ký tự","Thông báo",2);
            else{
            Admin ad = new Admin(this.txtMaAdmin.getText(),this.txtMatKhauAdmin.getText(),this.txtTenAdmin.getText(),this.txtSoDienThoaiAdmin.getText());
            if(admindata.UpdateAdmin(ad)){
                JOptionPane.showMessageDialog(null, "Bạn đã sửa thành công","Thông báo",1);
            }
            else JOptionPane.showMessageDialog(null, "Tác giả không được sửa","Thông báo",2);
            this.btLookTacGia.doClick();
            UpdateTable.LoadData(sqlAdmin, tbAdmin);
        }
    }//GEN-LAST:event_btEditAdminActionPerformed

    private void btAddAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddAdminActionPerformed
        try {
            if(this.txtMaAdmin.getText().length()==0) JOptionPane.showMessageDialog(null, "Mã Admin không thể bỏ trống","Thông báo",2);
            else if(this.txtMatKhauAdmin.getText().length()==0) JOptionPane.showMessageDialog(null, "Mật khẩu Admin không thể bỏ trống","Thông báo",2);
            else if(this.txtTenAdmin.getText().length()==0) JOptionPane.showMessageDialog(null, "Tên Admin không thể bỏ trống","Thông báo",2);
            else if(this.txtSoDienThoaiAdmin.getText().length()==0 || this.txtSoDienThoaiAdmin.getText().length()<10 || this.txtSoDienThoaiAdmin.getText().length()>11) JOptionPane.showMessageDialog(null, "Số điện thoại Admin không Hợp lệ","Thông báo",2);
            else if(this.txtMaAdmin.getText().length()>10) JOptionPane.showMessageDialog(null, "Mã Admin không thể lớn hơn 10 ký tự","Thông báo",2);
            else{
                Admin ad = new Admin(this.txtMaAdmin.getText(),this.txtMatKhauAdmin.getText(), this.txtTenAdmin.getText(), this.txtSoDienThoaiAdmin.getText() );
                AdminData .InsertAdmin(ad);
                this.btLookAdmin.doClick();
                UpdateTable.LoadData(sqlAdmin, tbAdmin);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Thông báo",2);
        }
    }//GEN-LAST:event_btAddAdminActionPerformed

    private void btNewAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewAdminActionPerformed
        // TODO add your handling code here:
        ProcessCrt5(false);
        this.btAddAdmin.setEnabled(true);
        this.txtMaAdmin.setText(null);
        this.txtTenAdmin.setText(null);
        this.txtMatKhauAdmin.setText(null);
        this.txtSoDienThoaiAdmin.setText(null);
    }//GEN-LAST:event_btNewAdminActionPerformed

    private void btLookAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLookAdminActionPerformed
        if(this.txtLookAdmin.getText().length() == 0) {
            String sql1 = "SELECT Ma_Admin as N'Mã Admin', Ten_Admin as N'Tên Admin', So_Dien_Thoai_Admin as N'Số Điện Thoại' FROM QUAN_TRI";
            UpdateTable.LoadData(sql1, tbAdmin);
        }
        else {
            String sql1 = "SELECT Ma_Admin as N'Mã Admin', Ten_Admin as N'Tên Admin', So_Dien_Thoai_Admin as N'Số Điện Thoại' FROM QUAN_TRI WHERE Ma_Admin like N'%"+this.txtLookAdmin.getText()+"%' "
            + "or Ten_Admin like N'%"+this.txtLookAdmin.getText()+"%'";
            UpdateTable.LoadData(sql1, tbAdmin);
        }
    }//GEN-LAST:event_btLookAdminActionPerformed

    private void btRet5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRet5ActionPerformed
        // TODO add your handling code here:
        choice c = new choice(admin);
        c.setVisible(true);
        dispose();
    }//GEN-LAST:event_btRet5ActionPerformed

    private void tbTacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTacGiaMouseClicked
        // TODO add your handling code here:
        ProcessCrt4(true);
        this.btAddTacGia.setEnabled(false);
        try{
            int row = this.tbTacGia.getSelectedRow();
            String IDRow = (this.tbTacGia.getModel().getValueAt(row, 0)).toString();
            String sql1 = "SELECT * FROM TAC_GIA where Ma_Tac_Gia = '"+IDRow+"'";
            ResultSet rs = UpdateTable.ShowTextField(sql1);
            if(rs.next()){
                this.txtMaTacGia.setText(rs.getString("Ma_Tac_Gia"));
                this.txtTenTG.setText(rs.getString("Ten_Tac_Gia"));
                this.txtQuocTIch.setText(rs.getString("Quoc_Tich_Tac_Gia"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
        }

    }//GEN-LAST:event_tbTacGiaMouseClicked

    private void btDelTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDelTacGiaActionPerformed
        // TODO add your handling code here:
        int click = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa tác giả "+this.txtTenTG.getText()+"?","Thông báo",JOptionPane.YES_NO_OPTION);
            if(click==JOptionPane.YES_OPTION){
        if(tacgiadata.DeleteTacGia(this.txtMaTacGia.getText())){
            JOptionPane.showMessageDialog(null, "Bạn đã xóa thành công","Thông báo",1);
        }
        else JOptionPane.showMessageDialog(null, "Tác giả không được xóa","Thông báo",2);
            }else{}
        this.btLookTacGia.doClick();
        UpdateTable.LoadData(sqlSach, tbSach);
        UpdateTable.LoadData(sqlTacGia, tbTacGia);
    }//GEN-LAST:event_btDelTacGiaActionPerformed

    private void btEditTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditTacGiaActionPerformed
        // TODO add your handling code here:
        if(this.txtMaTacGia.getText().length()==0) JOptionPane.showMessageDialog(null, "Mã tác giả không thể bỏ trống","Thông báo",2);
        else if(this.txtTenTG.getText().length()==0) JOptionPane.showMessageDialog(null, "Tên tác giả không thể bỏ trống","Thông báo",2);
        else if(this.txtQuocTIch.getText().length()==0) JOptionPane.showMessageDialog(null, "Quốc tịch tác giả không thể bỏ trống","Thông báo",2);
        else if(this.txtMaTacGia.getText().length()>10) JOptionPane.showMessageDialog(null, "Mã tác giả không thể lớn hơn 10 ký tự","Thông báo",2);
        else{
            TacGia tg = new TacGia(this.txtMaTacGia.getText(),this.txtTenTG.getText(),this.txtQuocTIch.getText());
            if(tacgiadata.UpdateTacGia(tg)){
                JOptionPane.showMessageDialog(null, "Bạn đã sửa thành công","Thông báo",1);
            }
            else JOptionPane.showMessageDialog(null, "Tác giả không được sửa","Thông báo",2);
            this.btLookTacGia.doClick();
            UpdateTable.LoadData(sqlSach, tbSach);
            UpdateTable.LoadData(sqlTacGia, tbTacGia);
        }
    }//GEN-LAST:event_btEditTacGiaActionPerformed

    private void btAddTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddTacGiaActionPerformed
        // TODO add your handling code here:
        try {
//            if(this.txtMaTacGia.getText().length()==0) JOptionPane.showMessageDialog(null, "Mã tác giả không thể bỏ trống","Thông báo",2);
            if(this.txtTenTG.getText().length()==0) JOptionPane.showMessageDialog(null, "Tên tác giả không thể bỏ trống","Thông báo",2);
            else if(this.txtQuocTIch.getText().length()==0) JOptionPane.showMessageDialog(null, "Quốc tịch tác giả không thể bỏ trống","Thông báo",2);
            else if(this.txtMaTacGia.getText().length()>10) JOptionPane.showMessageDialog(null, "Mã tác giả không thể lớn hơn 10 ký tự","Thông báo",2);
            else{
                TacGia tg = new TacGia(this.txtMaTacGia.getText(),this.txtTenTG.getText(),this.txtQuocTIch.getText());
                TacGiaData.InsertTacGia(tg);
                this.btLookTacGia.doClick();
                UpdateTable.LoadData(sqlSach, tbSach);
                UpdateTable.LoadData(sqlTacGia, tbTacGia);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Thông báo",2);
        }
    }//GEN-LAST:event_btAddTacGiaActionPerformed

    private void btNewTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewTacGiaActionPerformed
        // TODO add your handling code here:
        ProcessCrt4(false);
        this.btAddTacGia.setEnabled(true);
        this.txtMaTacGia.setText(null);
        this.txtTenTG.setText(null);
        this.txtQuocTIch.setText(null);
    }//GEN-LAST:event_btNewTacGiaActionPerformed

    private void btLookTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLookTacGiaActionPerformed
        if(this.txtLookTacGia.getText().length() == 0) {
            String sql1 = "SELECT Ma_Tac_Gia as N'Mã Tác Giả', Ten_Tac_Gia as N'Tên Tác Giả', "
            + "Quoc_Tich_Tac_Gia as N'Quốc Tịch' FROM TAC_GIA";
            UpdateTable.LoadData(sql1, tbTacGia);
        }
        else {
            String sql1 = "SELECT Ma_Tac_Gia as N'Mã Tác Giả', Ten_Tac_Gia as N'Tên Tác Giả', "
            + "Quoc_Tich_Tac_Gia as N'Quốc Tịch' FROM TAC_GIA WHERE Ma_Tac_Gia like N'%"+this.txtLookTacGia.getText()+"%' "
            + "or Ten_Tac_Gia like N'%"+this.txtLookTacGia.getText()+"%' or Quoc_Tich_Tac_Gia like N'%"+this.txtLookTacGia.getText()+"%'";
            UpdateTable.LoadData(sql1, tbTacGia);
        }
    }//GEN-LAST:event_btLookTacGiaActionPerformed

    private void btRet4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRet4ActionPerformed
        // TODO add your handling code here:
        choice c = new choice(admin);
        c.setVisible(true);
        dispose();
    }//GEN-LAST:event_btRet4ActionPerformed

    private void tbKhachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachMouseClicked
        // TODO add your handling code here:
        ProcessCrt2(true);
        this.btAddKhach.setEnabled(false);
        try{
            int row = this.tbKhach.getSelectedRow();
            String IDrow = (this.tbKhach.getModel().getValueAt(row, 0)).toString();
            String sql1 = "SELECT * FROM KHACH_HANG where Ma_Khach_Hang='"+IDrow+"'";
            ResultSet rs = UpdateTable.ShowTextField(sql1);
            if(rs.next()) {
                this.txtMaKhach.setText(rs.getString("Ma_Khach_Hang"));
                this.txtPassword.setText(rs.getString("Password"));
                this.txtTenKhach.setText(rs.getString("Ten_Khach_Hang"));
                this.txtNgaySinh.setDate(rs.getDate("Ngay_Sinh"));
                this.txtDiaChi.setText(rs.getString("Dia_Chi"));
                this.txtPhone.setText(rs.getString("So_Dien_Thoai_KH"));
            }
        }catch(Exception e) {

        }
    }//GEN-LAST:event_tbKhachMouseClicked

    private void txtPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneActionPerformed

    private void btDelKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDelKhachActionPerformed
        // TODO add your handling code here:
        int click = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa khách hàng "+this.txtTenKhach.getText()+"?","Thông báo",JOptionPane.YES_NO_OPTION);
            if(click==JOptionPane.YES_OPTION){
        if(khachhangdata.DeleteKhachHang(this.txtMaKhach.getText())) {
            JOptionPane.showMessageDialog(null, "Bạn đã xóa thành công", "Thông báo", 1);
        }
        else JOptionPane.showMessageDialog(null, "Có lỗi xảy ra", "Thông báo", 2);
            }else{}
        this.btLookKhach.doClick();
            UpdateTable.LoadData(sqlKhach, tbKhach);        
    }//GEN-LAST:event_btDelKhachActionPerformed

    private void btEditKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditKhachActionPerformed
        // TODO add your handling code here:
        if (this.txtMaKhach.getText().length()==0) JOptionPane.showMessageDialog(null, "Mã khách hàng không thể bỏ trống", "thông báo", 2);
        else if(this.txtMaKhach.getText().length()>10) JOptionPane.showMessageDialog(null, "Mã khách hàng không được lớn hơn 10 ký tự", "thông báo", 2);
        else if(this.txtPassword.getText().length()==0) JOptionPane.showMessageDialog(null, "Mật khẩu khách hàng không được bỏ trống","Thông báo",2);
        else if(this.txtTenKhach.getText().length()==0 || this.txtTenKhach.getText().length()>255) JOptionPane.showMessageDialog(null, "Tên khách hàng không được bỏ trống","Thông báo",2);
        else if(this.txtNgaySinh.getDate()==null) JOptionPane.showMessageDialog(null, "Ngày sinh khách hàng không được bỏ trống","Thông báo",2);
        else if(this.txtDiaChi.getText().length()==0) JOptionPane.showMessageDialog(null, "Địa chỉ khách hàng không được bỏ trống","Thông báo",2);
        else if(this.txtPhone.getText().length()==0 || this.txtPhone.getText().length()<10 || this.txtPhone.getText().length()>12) JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng không hợp lệ","Thông báo",2);
        else {
            KhachHang s = new KhachHang(this.txtMaKhach.getText(), this.txtPassword.getText(), this.txtTenKhach.getText(),this.txtNgaySinh.getDate(),
                this.txtDiaChi.getText(),this.txtPhone.getText());
            if(khachhangdata.UpdateKhachHang(s)) {
                JOptionPane.showMessageDialog(null, "Bạn đã sửa thành công", "Thông báo", 1);
            }
            else JOptionPane.showMessageDialog(null, "Có lỗi xảy ra", "Thông báo", 2);
            this.btLookKhach.doClick();
            UpdateTable.LoadData(sqlKhach, tbKhach);
        }
    }//GEN-LAST:event_btEditKhachActionPerformed

    private void btAddKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddKhachActionPerformed
        // TODO add your handling code here:
        try{
            if (this.txtMaKhach.getText().length()==0) JOptionPane.showMessageDialog(null, "Mã khách hàng không thể bỏ trống", "thông báo", 2);
            else if(this.txtMaKhach.getText().length()>10) JOptionPane.showMessageDialog(null, "Mã khách hàng không được lớn hơn 10 ký tự", "thông báo", 2);
            else if(this.txtPassword.getText().length()==0) JOptionPane.showMessageDialog(null, "Mật khẩu khách hàng không được bỏ trống","Thông báo",2);
            else if(this.txtTenKhach.getText().length()==0 || this.txtTenKhach.getText().length()>255) JOptionPane.showMessageDialog(null, "Tên khách hàng không được bỏ trống","Thông báo",2);
            else if(this.txtNgaySinh.getDate()==null) JOptionPane.showMessageDialog(null, "Ngày sinh khách hàng không được bỏ trống","Thông báo",2);
            else if(this.txtDiaChi.getText().length()==0) JOptionPane.showMessageDialog(null, "Địa chỉ khách hàng không được bỏ trống","Thông báo",2);
            else if(this.txtPhone.getText().length()==0 || this.txtPhone.getText().length()<10 || this.txtPhone.getText().length()>12) JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng không hợp lệ","Thông báo",2);
            else {
                KhachHang s = new KhachHang(this.txtMaKhach.getText(), this.txtPassword.getText(), this.txtTenKhach.getText(),this.txtNgaySinh.getDate(),
                    this.txtDiaChi.getText(),this.txtPhone.getText());
                KhachHangData.InsertKhachHang(s);
                this.btLookKhach.doClick();
                UpdateTable.LoadData(sqlKhach, tbKhach);
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra", "Thông báo", 2);
        }
    }//GEN-LAST:event_btAddKhachActionPerformed

    private void btNewKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewKhachActionPerformed
        // TODO add your handling code here:
        ProcessCrt2(false);
        this.btAddKhach.setEnabled(true);
        this.txtMaKhach.setText(null);
        this.txtPassword.setText(null);
        this.txtTenKhach.setText(null);
        this.txtDiaChi.setText(null);
        this.txtNgaySinh.setDate(null);
        this.txtPhone.setText(null);
    }//GEN-LAST:event_btNewKhachActionPerformed

    private void btLookKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLookKhachActionPerformed
        // TODO add your handling code here:
        if(this.txtLookKhach.getText().length() == 0) {
            String sql1 = "SELECT Ma_Khach_Hang as N'Mã Khách Hàng', Password as N'Mật Khẩu',Ten_Khach_Hang as N'Tên Khách Hàng',"
            + "Ngay_Sinh as N'Ngày Sinh', Dia_Chi as N'Địa Chỉ',So_Dien_Thoai_KH as N'Số Điện Thoại' FROM KHACH_HANG";
            UpdateTable.LoadData(sql1, tbKhach);
        }
        else {
            String sql1 = "SELECT Ma_Khach_Hang as N'Mã Khách Hàng', Password as N'Mật Khẩu',Ten_Khach_Hang as N'Tên Khách Hàng'," +
"            + Ngay_Sinh as N'Ngày Sinh', Dia_Chi as N'Địa Chỉ',So_Dien_Thoai_KH as N'Số Điện Thoại' FROM KHACH_HANG  WHERE Ma_Khach_Hang like N'%"+this.txtLookKhach.getText()+"%' "
            + "or Ten_Khach_Hang like N'%"+this.txtLookKhach.getText()+"%'";
            UpdateTable.LoadData(sql1, tbKhach);
        }
    }//GEN-LAST:event_btLookKhachActionPerformed

    private void btRet3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRet3ActionPerformed
        // TODO add your handling code here:
        choice c = new choice(admin);
        c.setVisible(true);
        dispose();
    }//GEN-LAST:event_btRet3ActionPerformed

    private void btTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTraActionPerformed
        // TODO add your handling code here:
        String sql = "UPDATE SACH SET Tinh_Trang = ? where Ma_Sach = ?";
        String sql1 = "SELECT Tinh_Trang from SACH where Ma_Sach = '"+this.txtSachMuon.getText()+"'";
        String sql2 = "UPDATE PHIEU_MUON SET Ngay_Tra = (select GETDATE()) where Ma_Phieu_Muon = ?";

        try {
            ps = Connect.getConnect().prepareStatement(sql);
            ResultSet rs = UpdateTable.ShowTextField(sql1);
            ps.setString(2,this.txtSachMuon.getText());
            String tt = "";
            if(rs.next()) tt = rs.getString("Tinh_Trang");
            ps.setString(1, "Còn");
            ps.execute();
            ps2 = Connect.getConnect().prepareStatement(sql2);
            if(this.txtNgayTra.getText().length()==0){
            ps2.setString(1, this.txtMaPhieuMuon.getText());
            }else{
                JOptionPane.showMessageDialog(null, "Phiếu mượn đã được trả rồi","Thông báo",1);
            }
            ps2.executeQuery();
            this.btLookMuon.doClick();
        } catch (Exception ex) {
        }
        UpdateTable.LoadData(sqlPhieu, tbMuon);
        UpdateTable.LoadData(sqlSach, tbSach);
    }//GEN-LAST:event_btTraActionPerformed

    private void tbMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMuonMouseClicked
        // TODO add your handling code here:
        ProcessCrt3(true);
        this.btAddPhieu.setEnabled(false);
        try {
            int row = this.tbMuon.getSelectedRow();
            String IDrow = (this.tbMuon.getModel().getValueAt(row, 0)).toString();
            String sql1 = "SELECT * from PHIEU_MUON where Ma_Phieu_Muon='"+IDrow+"'";
            ResultSet rs = UpdateTable.ShowTextField(sql1);
            if(rs.next()){
                this.txtMaPhieuMuon.setText(rs.getString("Ma_Phieu_Muon"));
                this.txtNguoiMuon.setText(rs.getString("Ma_Khach_Hang"));
                this.txtSachMuon.setText(rs.getString("Ma_Sach"));
                this.txtAdmin.setText(rs.getString("Ma_Admin"));
                this.txtNgayMuon.setDate(rs.getDate("Ngay_Muon"));
                this.txtHanTra.setDate(rs.getDate("Han_Tra"));
                this.txtNgayTra.setText(rs.getString("Ngay_Tra"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tbMuonMouseClicked

    private void btDelPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDelPhieuActionPerformed
        // TODO add your handling code here:
        int click = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa phiếu mượn "+this.txtMaPhieuMuon.getText()+"?","Thông báo",JOptionPane.YES_NO_OPTION);
            if(click==JOptionPane.YES_OPTION){
        if(phieumuondata.DeletePhieu(this.txtMaPhieuMuon.getText())){
            JOptionPane.showMessageDialog(null, "Xóa thành công","Thông báo",1);
        }else JOptionPane.showMessageDialog(null, "Có lõi xảy ra","Thông báo",2);
        }else{}
        this.btLookMuon.doClick();
        UpdateTable.LoadData(sqlPhieu, tbMuon);
    }//GEN-LAST:event_btDelPhieuActionPerformed

    private void btAddPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddPhieuActionPerformed
        // TODO add your handling code here:
        String sql2 ="select Ma_Sach from SACH where Tinh_Trang = N'Đã được mượn'";
        try{
//            if (this.txtMaPhieuMuon.getText().length()==0 ){
//                JOptionPane.showMessageDialog(null, "Mã phiếu mượn không thể bỏ trống", "thông báo", 2);
            if(this.txtNguoiMuon.getText().length()==0){
                JOptionPane.showMessageDialog(null, "Mã người mượn không thể bỏ trống", "thông báo", 2);
            }else if(this.txtSachMuon.getText().length()==0){
                JOptionPane.showMessageDialog(null, "Mã sách mượn không thể bỏ trống", "thông báo", 2);
            }else if(this.txtNguoiMuon.getText().length()==0){
                JOptionPane.showMessageDialog(null, "Mã người mượn không thể bỏ trống", "thông báo", 2);
            }else if(this.txtAdmin.getText().length()==0){
                JOptionPane.showMessageDialog(null, "Người cho mượn không thể bỏ trống", "thông báo", 2);
            }else if(this.txtNgayMuon.getDate()==null){
                JOptionPane.showMessageDialog(null, "Ngày mượn không thể bỏ trống", "thông báo", 2);
            }else if(this.txtHanTra.getDate()==null){
                JOptionPane.showMessageDialog(null, "Hạn trả không thể bỏ trống", "thông báo", 2);
            }else if(this.txtMaPhieuMuon.getText().length()>10) {
                JOptionPane.showMessageDialog(null, "Mã phiếu mượn không được lớn hơn 10 ký tự", "thông báo", 2);
            }else if(this.txtHanTra.getDate().compareTo(txtNgayMuon.getDate())<0) {
                JOptionPane.showMessageDialog(null, "Hạn trả không hợp lệ", "thông báo", 2);
            }
            else {
                PhieuMuon p = new PhieuMuon(this.txtMaPhieuMuon.getText(),this.txtNguoiMuon.getText(),this.txtSachMuon.getText(),
                    this.txtAdmin.getText(),this.txtNgayMuon.getDate(),this.txtHanTra.getDate());
                PhieuMuonData.InsertPhieu(p);
                this.btLookMuon.doClick();
                UpdateTable.LoadData(sqlSach, tbSach);
                UpdateTable.LoadData(sqlPhieu, tbMuon);
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Thông báo", 2);
        }

        String sql = "UPDATE SACH SET Tinh_Trang = ? where Ma_Sach = ?";
        String sql1 = "SELECT Tinh_Trang from SACH where Ma_Sach = '"+this.txtSachMuon.getText()+"'";
        try {
            ps = Connect.getConnect().prepareStatement(sql);
            ResultSet rs = UpdateTable.ShowTextField(sql1);
            ps.setString(2,this.txtSachMuon.getText());
            String tt = "";
            if(rs.next()) tt = rs.getString("Tinh_Trang");
            ps.setString(1, "Đã được mượn");
            ps.execute();
            this.btLookSach.doClick();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_btAddPhieuActionPerformed

    private void btNewPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewPhieuActionPerformed
        // TODO add your handling code here:
        ProcessCrt3(false);
        this.btAddPhieu.setEnabled(true);
        this.txtMaPhieuMuon.setText(null);
        this.txtNguoiMuon.setText(null);
        this.txtSachMuon.setText(null);
        this.txtAdmin.setText(admin.getMaAdmin());
//        this.txtNgayMuon.setDate(null);
        this.txtHanTra.setDate(null);
        this.txtNgayTra.setText(null);
    }//GEN-LAST:event_btNewPhieuActionPerformed

    private void btLookMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLookMuonActionPerformed
        // TODO add your handling code here:
        if(this.txtLookPhieu.getText().length() ==0){
            String sql1 = "select p.Ma_Phieu_Muon as N'Mã phiếu mượn', k.Ten_Khach_Hang as N'Tên khách hàng', s.Ten_Sach as N'Tên sách', a.Ten_Admin as N'Tên quản trị', p.Ngay_Muon as N'Ngày mượn', p.Han_Tra as N'Hạn trả', p.Ngay_Tra as N'Ngày trả' from PHIEU_MUON p \n" +
                                    "inner join KHACH_HANG k on p.Ma_Khach_Hang = k.Ma_Khach_Hang\n" +
                                    "inner join SACH s on p.Ma_Sach = s.Ma_Sach\n" +
                                    "inner join QUAN_TRI a on p.Ma_Admin = a.Ma_Admin";
            UpdateTable.LoadData(sql1, tbMuon);
//              JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin để tìm kiếm","Thông báo",1);
        }else{
            String sql1 = "select p.Ma_Phieu_Muon as N'Mã phiếu mượn', k.Ten_Khach_Hang as N'Tên khách hàng', s.Ten_Sach as N'Tên sách', a.Ten_Admin as N'Tên quản trị', p.Ngay_Muon as N'Ngày mượn', p.Han_Tra as N'Hạn trả', p.Ngay_Tra as N'Ngày trả' from PHIEU_MUON p \n" +
                                    "inner join KHACH_HANG k on p.Ma_Khach_Hang = k.Ma_Khach_Hang\n" +
                                    "inner join SACH s on p.Ma_Sach = s.Ma_Sach\n" +
                                    "inner join QUAN_TRI a on p.Ma_Admin = a.Ma_Admin WHERE Ma_Phieu_Muon like N'%"+this.txtLookPhieu.getText()+"%'"
            + " or Ten_Khach_Hang like N'%"+this.txtLookPhieu.getText()+"%'"
            + " or Ten_Sach like N'%"+this.txtLookPhieu.getText()+"%'";
            UpdateTable.LoadData(sql1, tbMuon);
        }
    }//GEN-LAST:event_btLookMuonActionPerformed

    private void btRet2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRet2ActionPerformed
        // TODO add your handling code here:
        choice c = new choice(admin);
        c.setVisible(true);
        dispose();
    }//GEN-LAST:event_btRet2ActionPerformed

    private void cbNhaXuatBanPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbNhaXuatBanPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String nhaXuatBan = "select * from NHA_XUAT_BAN where Ten_Nha_Xuat_Ban = ?";
        getID(nhaXuatBan, this.cbNhaXuatBan, this.txtNhaXb, "Ma_Nha_Xuat_Ban");
    }//GEN-LAST:event_cbNhaXuatBanPopupMenuWillBecomeInvisible

    private void cbTacGiaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbTacGiaPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String tacGia = "select * from TAC_GIA where Ten_Tac_Gia = ?";
        getID(tacGia, this.cbTacGia, this.txtTenTacGia, "Ma_Tac_Gia");
    }//GEN-LAST:event_cbTacGiaPopupMenuWillBecomeInvisible

    private void cbTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTheLoaiActionPerformed
        //        // TODO add your handling code here:
        //        String sql = "select * from THE_LOAI";
        //        try {
            //            PreparedStatement theLoai = conn.prepareStatement(sql);
            //            ResultSet rs = theLoai.executeQuery();
            //            while(rs.next()){
                //                cbTheLoai.addItem(rs.getString("Ten_The_Loai"));
                //            }
            //        } catch (Exception e) {
            //        }
    }//GEN-LAST:event_cbTheLoaiActionPerformed

    private void cbTheLoaiPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbTheLoaiPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String theLoai = "select * from THE_LOAI where Ten_The_Loai = ?";
        getID(theLoai, this.cbTheLoai, this.txtTL, "Ma_The_Loai");
    }//GEN-LAST:event_cbTheLoaiPopupMenuWillBecomeInvisible

    private void tbSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSachMouseClicked
        // TODO add your handling code here:
        ProcessCrt(true);
        this.btAddSach.setEnabled(false);
        try{
            int row = this.tbSach.getSelectedRow();
            String IDrow = (this.tbSach.getModel().getValueAt(row, 0)).toString();
            String sql1 = "SELECT * FROM SACH where Ma_Sach='"+IDrow+"'";
            ResultSet rs = UpdateTable.ShowTextField(sql1);
            if(rs.next()) {
                this.txtMaSach.setText(rs.getString("Ma_Sach"));
                this.txtTenSach.setText(rs.getString("Ten_Sach"));
                this.txtTL.setText(rs.getString("Ma_The_Loai"));
                this.txtTenTacGia.setText(rs.getString("Ma_Tac_Gia"));
                this.txtNhaXb.setText((rs.getString("Ma_Nha_Xuat_Ban")));
                this.txtTinhTrang.setText(rs.getString("Tinh_Trang"));
            }
        }catch(Exception e) {

        }
    }//GEN-LAST:event_tbSachMouseClicked

    private void btDelSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDelSachActionPerformed
        // TODO add your handling code here:
        int click = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa sách "+this.txtTenSach.getText()+"?","Thông báo",JOptionPane.YES_NO_OPTION);
            if(click==JOptionPane.YES_OPTION){
        if(sachdata.DeleteSach(this.txtMaSach.getText())){
            JOptionPane.showMessageDialog(null, "Bạn đã xóa thành công", "Thông báo", 1);
        }else{
            JOptionPane.showMessageDialog(null, "Sách không được xóa","Thông báo",2);
        }
        }else{
                
            }
        this.btLookSach.doClick();
        UpdateTable.LoadData(sqlSach, tbSach);
    }//GEN-LAST:event_btDelSachActionPerformed

    private void btEditSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditSachActionPerformed
        // TODO add your handling code here:
        if(this.txtMaSach.getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Mã sách không thể bỏ trống","Thông báo",2);
        }else if(this.txtTenSach.getText().length() == 0 ){
            JOptionPane.showMessageDialog(null, "Tên sách không thể bỏ trống","Thông báo",2);
        }else if(this.txtTL.getText().length() == 0 ){
            JOptionPane.showMessageDialog(null, "Thể loại sách không thể bỏ trống","Thông báo",2);
        }else if(this.txtTenTacGia.getText().length() == 0 ){
            JOptionPane.showMessageDialog(null, "Tên tác giả không thể bỏ trống","Thông báo",2);
        }else if(this.txtNhaXb.getText().length() == 0 ){
            JOptionPane.showMessageDialog(null, "Nhà xuất bản không thể bỏ trống","Thông báo",2);
        }else if(this.txtTinhTrang.getText().length() == 0 ){
            JOptionPane.showMessageDialog(null, "Tình trạng sách không thể bỏ trống","Thông báo",2);
        }else if(this.txtMaSach.getText().length()>255){
            JOptionPane.showMessageDialog(null, "Mã sách không được lớn hơn 255 ký tự","Thông báo",2);
        }else{
            Sach s = new Sach(this.txtMaSach.getText(), this.txtTenSach.getText(), Integer.parseInt(this.txtTL.getText()),
                Integer.parseInt(this.txtTenTacGia.getText()), Integer.parseInt(this.txtNhaXb.getText()),this.txtTinhTrang.getText());
            if(sachdata.UpdateSach(s)){
                JOptionPane.showMessageDialog(null, "Bạn đã sửa sách thành công","Thông báo",1);
            }else{
                JOptionPane.showMessageDialog(null, "Sách không được sửa thành công","Thông báo",2);
            }
            this.btLookSach.doClick();
        }
            UpdateTable.LoadData(sqlSach, tbSach);
    }//GEN-LAST:event_btEditSachActionPerformed

    private void btAddSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddSachActionPerformed
        // TODO add your handling code here:
        if(this.txtMaSach.getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Mã sách không thể bỏ trống","Thông báo",2);
        }else if(this.txtTenSach.getText().length() == 0 ){
            JOptionPane.showMessageDialog(null, "Tên sách không thể bỏ trống","Thông báo",2);
        }else if(this.txtTL.getText().length() == 0 ){
            JOptionPane.showMessageDialog(null, "Thể loại sách không thể bỏ trống","Thông báo",2);
        }else if(this.txtTenTacGia.getText().length() == 0 ){
            JOptionPane.showMessageDialog(null, "Tên tác giả không thể bỏ trống","Thông báo",2);
        }else if(this.txtNhaXb.getText().length() == 0 ){
            JOptionPane.showMessageDialog(null, "Nhà xuất bản không thể bỏ trống","Thông báo",2);
        }else if(this.txtTinhTrang.getText().length() == 0 ){
            JOptionPane.showMessageDialog(null, "Tình trạng sách không thể bỏ trống","Thông báo",2);
        }else if(this.txtMaSach.getText().length()>255){
            JOptionPane.showMessageDialog(null, "Mã sách không được lớn hơn 255 ký tự","Thông báo",2);
        }else{
            Sach s = new Sach(this.txtMaSach.getText(), this.txtTenSach.getText(), Integer.parseInt(this.txtTL.getText()),
                Integer.parseInt(this.txtTenTacGia.getText()), Integer.parseInt(this.txtNhaXb.getText()),this.txtTinhTrang.getText());
            SachData.InsertSach(s);
            this.btLookSach.doClick();
        }
        UpdateTable.LoadData(sqlSach, tbSach);
    }//GEN-LAST:event_btAddSachActionPerformed

    private void btNewSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewSachActionPerformed
        // TODO add your handling code here:
        ProcessCrt(false);
        this.btAddSach.setEnabled(true);
        this.txtTinhTrang.setText(null);
        this.txtMaSach.setText(null);
        this.txtNhaXb.setText(null);
        this.txtTenSach.setText(null);
        this.txtTenTacGia.setText(null);
        this.txtTL.setText(null);
    }//GEN-LAST:event_btNewSachActionPerformed

    private void btLookSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLookSachActionPerformed
        // TODO add your handling code here:
        if(this.txtLookSach.getText().length() == 0){
            String sql1 = "select s.Ma_Sach as N'Mã Sách', s.Ten_Sach as N'Tên Sách', l.Ten_The_Loai as N'Tên Thể Loại', "
            + "t.Ten_Tac_Gia as N'Tên Tác Giả', k.Ten_Nha_Xuat_Ban as N'Nhà Xuất Bản', s.Tinh_Trang as N'Tình Trạng' from SACH s \n" +
            "inner join NHA_XUAT_BAN k on s.Ma_Nha_Xuat_Ban = k.Ma_Nha_Xuat_Ban \n" +
            "inner join TAC_GIA t on t.Ma_Tac_Gia = s.Ma_Tac_Gia\n" +
            "inner join THE_LOAI l on l.Ma_The_Loai = s.Ma_The_Loai";
            UpdateTable.LoadData(sql1, tbSach);
//              JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin để tìm kiếm","Thông báo",1);
        }else{
            String sql1 = "select s.Ma_Sach as N'Mã sách', s.Ten_Sach as N'Tên sách', l.Ten_The_Loai as N'Tên thể loại', t.Ten_Tac_Gia as N'Tên tác giả', k.Ten_Nha_Xuat_Ban as N'Nhà xuất bản', s.Tinh_Trang as N'Tình trạng' from SACH s \n" +
"inner join NHA_XUAT_BAN k on s.Ma_Nha_Xuat_Ban = k.Ma_Nha_Xuat_Ban \n" +
"inner join TAC_GIA t on t.Ma_Tac_Gia = s.Ma_Tac_Gia\n" +
"inner join THE_LOAI l on l.Ma_The_Loai = s.Ma_The_Loai WHERE Ma_Sach like N'%"+this.txtLookSach.getText()+"%'" 
                        +"or s.Ten_Sach like N'%"+this.txtLookSach.getText()+"%'"
                        +"or l.Ten_The_Loai like N'%"+this.txtLookSach.getText()+"%'"
                        +"or t.Ten_Tac_Gia like N'%"+this.txtLookSach.getText()+"%'";
            UpdateTable.LoadData(sql1, tbSach);
        }
    }//GEN-LAST:event_btLookSachActionPerformed

    private void btRetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRetActionPerformed
        // TODO add your handling code here:
        choice c = new choice(admin);
        c.setVisible(true);
        dispose();
    }//GEN-LAST:event_btRetActionPerformed

    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        // TODO add your handling code here:
        JAdminUpdate jad = new JAdminUpdate(admin);
        jad.setVisible(true);
        dispose();
    }//GEN-LAST:event_btResetActionPerformed

    private void cbKhachHangPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbKhachHangPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String khachHang = "select * from KHACH_HANG where Ten_Khach_Hang = ?";
        getID(khachHang, this.cbKhachHang, this.txtNguoiMuon, "Ma_Khach_Hang");
    }//GEN-LAST:event_cbKhachHangPopupMenuWillBecomeInvisible

    private void cbSachPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbSachPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String Sach = "select * from SACH where Ten_Sach = ?";
        getID(Sach, this.cbSach, this.txtSachMuon, "Ma_Sach");
    }//GEN-LAST:event_cbSachPopupMenuWillBecomeInvisible

    private void btReset2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReset2ActionPerformed
        // TODO add your handling code here:
        JAdminUpdate jad = new JAdminUpdate(admin);
        jad.setVisible(true);
        dispose();
    }//GEN-LAST:event_btReset2ActionPerformed

    private void btReset3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReset3ActionPerformed
        // TODO add your handling code here:
        JAdminUpdate jad = new JAdminUpdate(admin);
        jad.setVisible(true);
        dispose();
    }//GEN-LAST:event_btReset3ActionPerformed

    private void btReset4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReset4ActionPerformed
        // TODO add your handling code here:
        JAdminUpdate jad = new JAdminUpdate(admin);
        jad.setVisible(true);
        dispose();
    }//GEN-LAST:event_btReset4ActionPerformed

    private void btReset5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReset5ActionPerformed
        // TODO add your handling code here:
        JAdminUpdate jad = new JAdminUpdate(admin);
        jad.setVisible(true);
        dispose();
    }//GEN-LAST:event_btReset5ActionPerformed

    private void btDelAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDelAdminActionPerformed
        // TODO add your handling code here:
        int click = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa admin " +this.txtTenAdmin.getText()+"?","Thông báo",JOptionPane.YES_NO_OPTION);
            if(click==JOptionPane.YES_OPTION){
        if(admindata.DeleteAdmin(this.txtMaAdmin.getText())){
            JOptionPane.showMessageDialog(null, "Bạn đã xóa thành công", "Thông báo", 1);
        }else{
            JOptionPane.showMessageDialog(null, "Admin không được xóa","Thông báo",2);
        }
            }else{}
        this.btLookAdmin.doClick();
        UpdateTable.LoadData(sqlAdmin, tbAdmin);
        
    }//GEN-LAST:event_btDelAdminActionPerformed
    
    public static Connection conn = Connect.getConnect();
    public void combox( String sql, JComboBox cb, String bien){

        try {
            PreparedStatement combo = conn.prepareStatement(sql);
            ResultSet rs = combo.executeQuery();
            while(rs.next()){
                cb.addItem(rs.getString(bien));
            }
        } catch (Exception e) {
        }
    }
    
    public void getID(String sql, JComboBox cb, JTextField tf, String bien){
        try {
            PreparedStatement combo = conn.prepareStatement(sql);
            combo.setString(1, (String)cb.getSelectedItem());
            ResultSet rs = combo.executeQuery();
            while(rs.next()){
                tf.setText(rs.getString(bien));
            }
        } catch (Exception e) {
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JAdminUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JAdminUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JAdminUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JAdminUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JAdminUpdate().setVisible(true);
            }
        });
    }
    
    public JTable getTbSach(){
        return tbSach;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddAdmin;
    private javax.swing.JButton btAddKhach;
    private javax.swing.JButton btAddPhieu;
    private javax.swing.JButton btAddSach;
    private javax.swing.JButton btAddTacGia;
    private javax.swing.JButton btDelAdmin;
    private javax.swing.JButton btDelKhach;
    private javax.swing.JButton btDelPhieu;
    private javax.swing.JButton btDelSach;
    private javax.swing.JButton btDelTacGia;
    private javax.swing.JButton btEditAdmin;
    private javax.swing.JButton btEditKhach;
    private javax.swing.JButton btEditSach;
    private javax.swing.JButton btEditTacGia;
    private javax.swing.JButton btLookAdmin;
    private javax.swing.JButton btLookKhach;
    private javax.swing.JButton btLookMuon;
    private javax.swing.JButton btLookSach;
    private javax.swing.JButton btLookTacGia;
    private javax.swing.JButton btNewAdmin;
    private javax.swing.JButton btNewKhach;
    private javax.swing.JButton btNewPhieu;
    private javax.swing.JButton btNewSach;
    private javax.swing.JButton btNewTacGia;
    private javax.swing.JButton btReset;
    private javax.swing.JButton btReset2;
    private javax.swing.JButton btReset3;
    private javax.swing.JButton btReset4;
    private javax.swing.JButton btReset5;
    private javax.swing.JButton btRet;
    private javax.swing.JButton btRet2;
    private javax.swing.JButton btRet3;
    private javax.swing.JButton btRet4;
    private javax.swing.JButton btRet5;
    private javax.swing.JButton btTra;
    private javax.swing.JComboBox<String> cbKhachHang;
    private javax.swing.JComboBox<String> cbNhaXuatBan;
    private javax.swing.JComboBox<String> cbSach;
    private javax.swing.JComboBox<String> cbTacGia;
    private javax.swing.JComboBox<String> cbTheLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbAdmin;
    private javax.swing.JTable tbKhach;
    private javax.swing.JTable tbMuon;
    private javax.swing.JTable tbSach;
    private javax.swing.JTable tbTacGia;
    private javax.swing.JTextField txtAdmin;
    private javax.swing.JTextField txtDiaChi;
    private com.toedter.calendar.JDateChooser txtHanTra;
    private javax.swing.JTextField txtLookAdmin;
    private javax.swing.JTextField txtLookKhach;
    private javax.swing.JTextField txtLookPhieu;
    private javax.swing.JTextField txtLookSach;
    private javax.swing.JTextField txtLookTacGia;
    private javax.swing.JTextField txtMaAdmin;
    private javax.swing.JTextField txtMaKhach;
    private javax.swing.JTextField txtMaPhieuMuon;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtMaTacGia;
    private javax.swing.JTextField txtMatKhauAdmin;
    private com.toedter.calendar.JDateChooser txtNgayMuon;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtNgayTra;
    private javax.swing.JTextField txtNguoiMuon;
    private javax.swing.JTextField txtNhaXb;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtQuocTIch;
    private javax.swing.JTextField txtSachMuon;
    private javax.swing.JTextField txtSoDienThoaiAdmin;
    private javax.swing.JTextField txtTL;
    private javax.swing.JTextField txtTenAdmin;
    private javax.swing.JTextField txtTenKhach;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTenTG;
    private javax.swing.JTextField txtTenTacGia;
    private javax.swing.JTextField txtTinhTrang;
    // End of variables declaration//GEN-END:variables
}
