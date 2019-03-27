/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import irepository.IChuyenNganh;
import irepository.ILopMonHoc;
import irepository.IMonHoc;
import irepository.IMonKhoaHoc;
import irepository.IMonNganh;
import irepository.INienKhoa;
import irepository.ISVLopMonHoc;
import irepository.ISinhVien;
import java.awt.Dialog;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChuyenNganh;
import model.LopMonHoc;
import model.MonChuyenNganh;
import model.MonHoc;
import model.MonKhoaHoc;
import model.NienKhoa;
import model.SVLopMonHoc;
import model.SinhVien;
import repository.ChuyenNganhRepository;
import repository.LopMonHocRepository;
import repository.MonHocRepository;
import repository.MonKhoaHocRepository;
import repository.MonNganhRepository;
import repository.NienKhoaRepository;
import repository.SVLopMonHocRepository;
import repository.SinhVienRepository;

/**
 *
 * @author vanph
 */
public class ViewMain extends javax.swing.JFrame {

    private IMonHoc monHocRepo = new MonHocRepository();
    private INienKhoa nienKhoaRepo = new NienKhoaRepository();
    private IMonKhoaHoc monKhoaHocRepo = new MonKhoaHocRepository();
    private IChuyenNganh chuyenNganhRepo = new ChuyenNganhRepository();
    private ILopMonHoc lopMonHocRepo = new LopMonHocRepository();
    private IMonNganh monNganhRepo = new MonNganhRepository();
    private ISinhVien sinhVienRepo = new SinhVienRepository();
    private ISVLopMonHoc sinhVienLopRepo = new SVLopMonHocRepository();
    private List<LopMonHoc> lopMonHocs = new ArrayList<>();
    private List<MonHoc> monHocs = new ArrayList<MonHoc>();
    private List<ChuyenNganh> chuyenNganhs = new ArrayList();
    public static List<NienKhoa> nienKhoas = new ArrayList<>();
    private List<MonKhoaHoc> monKhoaHocs = new ArrayList<MonKhoaHoc>();
    private List<SinhVien> sinhViens = new ArrayList<>();
    public static List<SVLopMonHoc> sinhVienLopMHs = new ArrayList<>();
    private MonKhoaHoc monKhoaHoc = null;
    public static ChuyenNganh sChuyenNganh;
    public static MonHoc sMonHoc;
    public static LopMonHoc sLopMonHoc;
    public static JDialog dialog;
    private List<SinhVien> sinhVienNienKhoa = new ArrayList<>();
    private List<NienKhoa> nienKhoaSinhVien = new ArrayList<>();

    /**
     * Creates new form ViewMain
     */
    public ViewMain() {
        initComponents();
        chuyenNganhs = chuyenNganhRepo.getAll();
        addCbChuyenNganh();
        addCbNienKhoaSV();
        addCbChuyenNganhSV();
        ChuyenNganh chuyenNganh = chuyenNganhs.get(0);
        if (chuyenNganh != null) {
            NienKhoa nienKhoaSV = nienKhoas.get(0);
            sinhVienNienKhoa = sinhVienRepo.getByIdNienKhoa(nienKhoaSV);
            addTableSV();
        }
    }

    private void addCbNienKhoaSV() {
        cb_nienKhoaSV.removeAllItems();
        for (int i = 0; i < nienKhoas.size(); i++) {
            cb_nienKhoaSV.addItem(nienKhoas.get(i).getNienKhoa());
        }

    }

    private void addCbChuyenNganhSV() {
        cb_chuyenNganhSV.removeAllItems();
        if (!chuyenNganhs.isEmpty()) {
            for (int i = 0; i < chuyenNganhs.size(); i++) {
                cb_chuyenNganhSV.addItem(chuyenNganhs.get(i).getTenChuyenNganh());
            }
        }
    }

    private void addTableSV() {
        DefaultTableModel model = (DefaultTableModel) tbl_SV.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        if (sinhVienNienKhoa.isEmpty() || cb_chuyenNganhSV.getSelectedIndex() == -1) {
            return;
        }
        for (int i = 0; i < sinhVienNienKhoa.size(); i++) {
            model.addRow(new Object[]{Integer.toString(i + 1), sinhVienNienKhoa.get(i).getId(),
                sinhVienNienKhoa.get(i).getTenSV(), sinhVienNienKhoa.get(i).getNgaySinhString(), sinhVienNienKhoa.get(i).getQueQuan()});
        }
    }

    private void addCbMonHoc() {
        cb_monhoc.removeAllItems();
        cb_lop.removeAllItems();
        if (!nienKhoas.isEmpty() && cb_khoahoc.getSelectedIndex() != -1) {
            NienKhoa khoa = nienKhoas.get(cb_khoahoc.getSelectedIndex());
            System.out.println("nien khoa: " + khoa.getNienKhoa());
            monHocs = monKhoaHocRepo.getMonKhoa(khoa.getId());
            System.out.println("tes test");
            if (!monHocs.isEmpty()) {
                for (int i = 0; i < monHocs.size(); i++) {
                    cb_monhoc.addItem(monHocs.get(i).getTenMonHoc());
                }
            }

        }
        addCbLop();

    }

    private void addCbNienKhoa() {
        cb_khoahoc.removeAllItems();
        cb_monhoc.removeAllItems();
        if (!chuyenNganhs.isEmpty() || cb_khoa.getSelectedIndex() != -1) {
            ChuyenNganh chuyenNganh = chuyenNganhs.get(cb_khoa.getSelectedIndex());
            if (chuyenNganh != null) {
                nienKhoas = nienKhoaRepo.getNienKhoas(chuyenNganh.getId());
                if (!nienKhoas.isEmpty()) {
                    for (int i = 0; i < nienKhoas.size(); i++) {
                        cb_khoahoc.addItem(nienKhoas.get(i).getNienKhoa());
                    }
                    monHocs = monKhoaHocRepo.getMonKhoa(nienKhoas.get(0).getId());

                }
            }
        }
        txtSearch.setText("");
        addCbMonHoc();

    }

    private void addCbLop() {
        cb_lop.removeAllItems();
        MonHoc mh = null;
        NienKhoa nk = null;
        if (monHocs.size() != 0 && cb_monhoc.getSelectedIndex() != -1) {
            mh = monHocs.get(cb_monhoc.getSelectedIndex());
        } else {
            addTable();
            return;
        }
        if (nienKhoas.size() != 0 && cb_khoahoc.getSelectedIndex() != -1) {
            nk = nienKhoas.get(cb_khoahoc.getSelectedIndex());

        } else {
            addTable();
            return;

        }
        if (chuyenNganhs.size() == 0 || cb_khoa.getSelectedIndex() == -1) {
            addTable();
            return;
        }
        ChuyenNganh chuyenNganh = chuyenNganhs.get(cb_khoa.getSelectedIndex());
        if (nk == null || chuyenNganh == null) {
            addTable();
            return;
        }
        MonChuyenNganh mNganh = monNganhRepo.getMonChuyenNganh(mh, chuyenNganh);
        if (mNganh == null) {
            addTable();
            return;
        }
        monKhoaHoc = monKhoaHocRepo.getMonKhoaHoc(mNganh, nk);
        if (monKhoaHoc == null) {
            addTable();
            return;
        }
        lopMonHocs = lopMonHocRepo.getLopMonHocs(monKhoaHoc);
        if (!lopMonHocs.isEmpty()) {
            for (int i = 0; i < lopMonHocs.size(); i++) {
                cb_lop.addItem(lopMonHocs.get(i).getTenLop());
            }
        }
        addTable();

    }

    private void addCbChuyenNganh() {
        cb_khoa.removeAllItems();
        cb_khoahoc.removeAllItems();
        if (!chuyenNganhs.isEmpty()) {
            for (int i = 0; i < chuyenNganhs.size(); i++) {
                cb_khoa.addItem(chuyenNganhs.get(i).getTenChuyenNganh());
            }
            nienKhoas = nienKhoaRepo.getNienKhoas(chuyenNganhs.get(0).getId());
            addCbNienKhoa();
        }
    }

    private void addTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_diemsv.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        if (lopMonHocs.isEmpty() || cb_lop.getSelectedIndex() == -1) {
            return;
        }
        sinhVienLopMHs = sinhVienLopRepo.getSVLopMonHoc(lopMonHocs.get(cb_lop.getSelectedIndex()).getIdLop());
        for (int i = 0; i < sinhVienLopMHs.size(); i++) {
            model.addRow(new Object[]{sinhVienLopMHs.get(i).getSinhVien().getId(), sinhVienLopMHs.get(i).getSinhVien().getTenSV(), sinhVienLopMHs.get(i).getSinhVien().getNgaySinhString(), sinhVienLopMHs.get(i).getDiemCC(), sinhVienLopMHs.get(i).getDiemGiuaKi(), sinhVienLopMHs.get(i).getDiemCuoiKi(), sinhVienLopMHs.get(i).getGhiChu()});
        }
    }

    private List<SVLopMonHoc> getDataTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_diemsv.getModel();
        List<SVLopMonHoc> editDiems = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < sinhVienLopMHs.size(); j++) {
                if ((model.getValueAt(i, 0).toString()).equals(Integer.toString(sinhVienLopMHs.get(j).getSinhVien().getId()))) {
                    double diemCC = (double) model.getValueAt(i, 3);
                    double diemGiuaKi = (double) model.getValueAt(i, 4);
                    double diemThi = (double) model.getValueAt(i, 5);
                    String ghiChu = (String) model.getValueAt(i, 6);
                    if (ghiChu == null) {
                        ghiChu = " ";
                    }
                    System.out.println("ghi chu test: " + ghiChu);

                    if (diemCC != sinhVienLopMHs.get(j).getDiemCC() || diemGiuaKi != sinhVienLopMHs.get(j).getDiemGiuaKi()
                            || diemThi != sinhVienLopMHs.get(j).getDiemThi() || !ghiChu.equals(sinhVienLopMHs.get(j).getGhiChu())) {
                        SVLopMonHoc svlmh = new SVLopMonHoc(sinhVienLopMHs.get(j).getSinhVien(), sinhVienLopMHs.get(j).getLop(), diemCC, diemGiuaKi, diemThi, ghiChu);
                        editDiems.add(svlmh);
                    }

                }
            }
        }
        return editDiems;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_sinhVien = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_diemsv = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        cb_khoa = new javax.swing.JComboBox();
        cb_khoahoc = new javax.swing.JComboBox();
        cb_monhoc = new javax.swing.JComboBox();
        cb_lop = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        cb_chuyenNganhSV = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cb_nienKhoaSV = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_SV = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_tenSV = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_tenSV1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_ngaySinh = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbl_diemsv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SV", "Tên SV", "Ngày Sinh", "Điểm CC", "Điểm giữa kì", "Điểm thi", "Ghi Chú"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_diemsv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_diemsvKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_diemsv);

        jLabel1.setText("Chuyên ngành: ");

        jLabel2.setText("Khóa học: ");

        jLabel3.setText("Môn Học: ");

        jLabel4.setText("Lớp Môn Học: ");

        btnSearch.setText("Tìm kiếm");
        btnSearch.setActionCommand("btnSearch");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtSearch.setToolTipText("");

        btnSearch1.setText("Thêm SV");
        btnSearch1.setActionCommand("btnSearch");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        btnSave.setText("Lưu thay đổi");
        btnSave.setActionCommand("btnSearch");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        cb_khoa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_khoaItemStateChanged(evt);
            }
        });
        cb_khoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_khoaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cb_khoaMouseEntered(evt);
            }
        });
        cb_khoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_khoaActionPerformed(evt);
            }
        });

        cb_khoahoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_khoahocItemStateChanged(evt);
            }
        });
        cb_khoahoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cb_khoahocMouseEntered(evt);
            }
        });

        cb_monhoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_monhocItemStateChanged(evt);
            }
        });
        cb_monhoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cb_monhocMouseEntered(evt);
            }
        });

        cb_lop.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_lopItemStateChanged(evt);
            }
        });
        cb_lop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cb_lopMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSearch1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch)
                            .addComponent(cb_khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_khoahoc, 0, 85, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSave, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_monhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(cb_khoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_khoahoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_monhoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch1)
                    .addComponent(btnSave))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        panel_sinhVien.addTab("Quản lý điểm", jPanel1);

        cb_chuyenNganhSV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_chuyenNganhSVItemStateChanged(evt);
            }
        });
        cb_chuyenNganhSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cb_chuyenNganhSVMouseEntered(evt);
            }
        });

        jLabel5.setText("Chuyên ngành: ");

        jLabel6.setText("Niên khóa: ");

        cb_nienKhoaSV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_nienKhoaSVItemStateChanged(evt);
            }
        });
        cb_nienKhoaSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_nienKhoaSVActionPerformed(evt);
            }
        });

        tbl_SV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã VS", "Tên SV", "Ngày Sinh", "Quê Quán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_SV);

        jButton1.setText("Thêm SV");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Tên SV:");

        jLabel8.setText("Quê quán:");

        jLabel9.setText("Ngày sinh:");

        txt_ngaySinh.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tenSV)
                            .addComponent(cb_chuyenNganhSV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_ngaySinh))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cb_nienKhoaSV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_tenSV1)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addGap(338, 338, 338))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_chuyenNganhSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(cb_nienKhoaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txt_tenSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel8)))
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_tenSV1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txt_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_sinhVien.addTab("Quản lý sinh viên", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_sinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_sinhVien)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_lopMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_lopMouseEntered
        // addTable();
    }//GEN-LAST:event_cb_lopMouseEntered

    private void cb_lopItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_lopItemStateChanged
        addTable();
    }//GEN-LAST:event_cb_lopItemStateChanged

    private void cb_monhocMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_monhocMouseEntered

    }//GEN-LAST:event_cb_monhocMouseEntered

    private void cb_monhocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_monhocItemStateChanged
        addCbLop();
    }//GEN-LAST:event_cb_monhocItemStateChanged

    private void cb_khoahocMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_khoahocMouseEntered

    }//GEN-LAST:event_cb_khoahocMouseEntered

    private void cb_khoahocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_khoahocItemStateChanged
        addCbMonHoc();
    }//GEN-LAST:event_cb_khoahocItemStateChanged

    private void cb_khoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_khoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_khoaActionPerformed

    private void cb_khoaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_khoaMouseEntered

    }//GEN-LAST:event_cb_khoaMouseEntered

    private void cb_khoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_khoaMouseClicked

    }//GEN-LAST:event_cb_khoaMouseClicked

    private void cb_khoaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_khoaItemStateChanged
        addCbNienKhoa();

    }//GEN-LAST:event_cb_khoaItemStateChanged

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        List<SVLopMonHoc> diems = getDataTable();
        for (SVLopMonHoc diem : diems) {
            sinhVienLopRepo.update(diem);
        }
        addTable();
        JOptionPane.showMessageDialog(this, "Success!");
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        if (!lopMonHocs.isEmpty()) {
            sChuyenNganh = chuyenNganhs.get(cb_khoa.getSelectedIndex());
            sMonHoc = monHocs.get(cb_monhoc.getSelectedIndex());
            sLopMonHoc = lopMonHocs.get(cb_lop.getSelectedIndex());
            dialog = new JDialog(this);
            dialog.getContentPane().add(new DialogCustom());
            dialog.setSize(800, 400);
            dialog.setVisible(true);
        } else if (!cb_monhoc.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có lớp cho môn " + cb_monhoc.getSelectedItem().toString());
        } else {

        }
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl_diemsv.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        String name = txtSearch.getText();
        for (int i = 0; i < sinhVienLopMHs.size(); i++) {
            if (sinhVienLopMHs.get(i).getSinhVien().getTenSV().contains(name)) {
                model.addRow(new Object[]{sinhVienLopMHs.get(i).getSinhVien().getId(), sinhVienLopMHs.get(i).getSinhVien().getTenSV(), sinhVienLopMHs.get(i).getDiemCC(),
                    sinhVienLopMHs.get(i).getDiemGiuaKi(), sinhVienLopMHs.get(i).getDiemCuoiKi(), sinhVienLopMHs.get(i).getGhiChu()});
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tbl_diemsvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_diemsvKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
            int n = JOptionPane.showConfirmDialog(
                    this, "Do you want to remove students from the class?",
                    "Confirm!",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                boolean result = sinhVienLopRepo.delete(sinhVienLopMHs.get(tbl_diemsv.getSelectedRow()));
                if (result) {
                    JOptionPane.showMessageDialog(this, "Success!");
                    addTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Can not delete student");
                }
            } else if (n == JOptionPane.NO_OPTION) {
            } else {

            }
        }
    }//GEN-LAST:event_tbl_diemsvKeyPressed

    private void cb_nienKhoaSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_nienKhoaSVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_nienKhoaSVActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cb_chuyenNganhSVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_chuyenNganhSVMouseEntered

    }//GEN-LAST:event_cb_chuyenNganhSVMouseEntered

    private void cb_chuyenNganhSVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_chuyenNganhSVItemStateChanged
        if (cb_chuyenNganhSV.getSelectedIndex() < chuyenNganhs.size() && cb_chuyenNganhSV.getSelectedIndex() >= 0) {
            ChuyenNganh chuyenNganh = chuyenNganhs.get(cb_chuyenNganhSV.getSelectedIndex());
            nienKhoaSinhVien = nienKhoaRepo.getNienKhoas(chuyenNganh.getId());
            addCbNienKhoaSV();
            addTableSV();
        }
    }//GEN-LAST:event_cb_chuyenNganhSVItemStateChanged

    private void cb_nienKhoaSVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_nienKhoaSVItemStateChanged
        if (cb_chuyenNganhSV.getSelectedIndex() < chuyenNganhs.size() && cb_chuyenNganhSV.getSelectedIndex() >= 0) {
            ChuyenNganh chuyenNganh = chuyenNganhs.get(cb_chuyenNganhSV.getSelectedIndex());
            if (chuyenNganh != null) {
                if (cb_nienKhoaSV.getSelectedIndex() < nienKhoas.size() && cb_nienKhoaSV.getSelectedIndex() >= 0) {
                    NienKhoa nienKhoaSV = nienKhoas.get(cb_nienKhoaSV.getSelectedIndex());
                    sinhVienNienKhoa = sinhVienRepo.getByIdNienKhoa(nienKhoaSV);
                    addTableSV();
                }
            }
        }

    }//GEN-LAST:event_cb_nienKhoaSVItemStateChanged

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
            java.util.logging.Logger.getLogger(ViewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JComboBox cb_chuyenNganhSV;
    private javax.swing.JComboBox cb_khoa;
    private javax.swing.JComboBox cb_khoahoc;
    private javax.swing.JComboBox cb_lop;
    private javax.swing.JComboBox cb_monhoc;
    private javax.swing.JComboBox cb_nienKhoaSV;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane panel_sinhVien;
    private javax.swing.JTable tbl_SV;
    private javax.swing.JTable tbl_diemsv;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txt_ngaySinh;
    private javax.swing.JTextField txt_tenSV;
    private javax.swing.JTextField txt_tenSV1;
    // End of variables declaration//GEN-END:variables
}
