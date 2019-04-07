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
import java.awt.event.WindowAdapter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
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
import util.Constants;

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
    public static NienKhoa sNienKhoa;
    public static JDialog dialog;
    private List<SinhVien> sinhVienNienKhoas = new ArrayList<>();
    private List<NienKhoa> nienKhoaSinhViens = new ArrayList<>();
    private SinhVien sinhVienUpdate;
    private boolean edit = false;
    private static final String regex = "^(0[1-9]|[12]\\d|3[01])-(0[1-9]|1[0-2])-[12]\\d{3}$";
    private static final String DATE_FAIL = "Ngày sinh không đúng!";

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
            sinhVienNienKhoas = sinhVienRepo.getByIdNienKhoa(nienKhoaSV);
            addTableSV();
        }
        btnCancel.setEnabled(edit);
    }

    private void addCbNienKhoaSV() {
        cb_nienKhoaSV.removeAllItems();
        for (int i = 0; i < nienKhoaSinhViens.size(); i++) {
            cb_nienKhoaSV.addItem(nienKhoaSinhViens.get(i).getNienKhoa());
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
        if (sinhVienNienKhoas.isEmpty() || cb_chuyenNganhSV.getSelectedIndex() == -1) {
            return;
        }
        for (int i = 0; i < sinhVienNienKhoas.size(); i++) {
            model.addRow(new Object[]{Integer.toString(i + 1), sinhVienNienKhoas.get(i).getId(),
                sinhVienNienKhoas.get(i).getTenSV(), sinhVienNienKhoas.get(i).getNgaySinhString(),
                sinhVienNienKhoas.get(i).getQueQuan()});
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
            model.addRow(new Object[]{sinhVienLopMHs.get(i).getSinhVien().getId(), sinhVienLopMHs.get(i).getSinhVien().getTenSV(), sinhVienLopMHs.get(i).getSinhVien().getNgaySinhString(), sinhVienLopMHs.get(i).getDiemCC(), sinhVienLopMHs.get(i).getDiemGiuaKi(), sinhVienLopMHs.get(i).getDiemThi(), sinhVienLopMHs.get(i).getGhiChu()});
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
                    if (diemCC != sinhVienLopMHs.get(j).getDiemCC() || diemGiuaKi != sinhVienLopMHs.get(j).getDiemGiuaKi()
                            || diemThi != sinhVienLopMHs.get(j).getDiemThi() || !ghiChu.equals(sinhVienLopMHs.get(j).getGhiChu())) {
                        SVLopMonHoc svlmh = new SVLopMonHoc(sinhVienLopMHs.get(j).getSinhVien(), sinhVienLopMHs.get(j).getLop(), diemCC, diemGiuaKi, diemThi, ghiChu);
                        editDiems.add(svlmh);
                    }

                }
            }
        }
        System.out.println("size size " + editDiems.size());
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
        btnSearch2 = new javax.swing.JButton();
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
        txt_quequan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_ngaySinh = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbl_diemsv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SV", "Tên SV", "Ngày Sinh", "Điểm CC", "Điểm giữa kì", "Điểm thi", "Ghi Chú"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
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

        btnSearch2.setText("Xem báo cáo");
        btnSearch2.setActionCommand("btnSearch");
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_monhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSearch2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch1)
                    .addComponent(btnSave)
                    .addComponent(btnSearch2))
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
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_SV.getTableHeader().setReorderingAllowed(false);
        tbl_SV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SVMouseClicked(evt);
            }
        });
        tbl_SV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_SVKeyPressed(evt);
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
        jLabel7.setToolTipText("");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jLabel8.setText("Quê quán:");

        jLabel9.setText("Ngày sinh:(dd-mm-yyyy)");

        txt_ngaySinh.setToolTipText("");

        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(59, 59, 59)
                                .addComponent(cb_chuyenNganhSV, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(txt_tenSV, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_nienKhoaSV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_quequan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_chuyenNganhSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_tenSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cb_nienKhoaSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_quequan)
                            .addComponent(btnCancel))
                        .addGap(16, 16, 16)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
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
        if (cb_monhoc.getSelectedIndex() >= 0) {
            sMonHoc = monHocs.get(cb_monhoc.getSelectedIndex());
        }
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
        if (cb_khoa.getSelectedIndex() >= 0) {
            sChuyenNganh = chuyenNganhs.get(cb_khoa.getSelectedIndex());
        }
        addCbNienKhoa();

    }//GEN-LAST:event_cb_khoaItemStateChanged

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        List<SVLopMonHoc> diems = getDataTable();
        for (SVLopMonHoc diem : diems) {
            sinhVienLopRepo.update(diem);
        }
        addTable();
        JOptionPane.showMessageDialog(this, "Thành công!");
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
                    this, "Bạn có muốn xóa sinh viên khỏi lớp?",
                    "Xác nhận!",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                boolean result = sinhVienLopRepo.delete(sinhVienLopMHs.get(tbl_diemsv.getSelectedRow()));
                if (result) {
                    JOptionPane.showMessageDialog(this, "Thành công!");
                    addTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể xóa sinh viên");
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
        String name = "";
        String ngaySinh = "";
        String queQuan = "";
        name = txt_tenSV.getText();
        queQuan = txt_quequan.getText();
        ngaySinh = txt_ngaySinh.getText();
        if (name.isEmpty() || queQuan.isEmpty() || ngaySinh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên, quên quán, ngày sinh không được để trống!");
            return;
        } else if (!ngaySinh.matches(regex)) {
            JOptionPane.showMessageDialog(this, DATE_FAIL);
            return;
        } else {
            java.sql.Date date = Constants.getConvertDate(ngaySinh);
            if (date != null) {
                SinhVien sinhVien = new SinhVien();
                sinhVien.setTenSV(name);
                sinhVien.setNgaySinh(date);
                if (!nienKhoaSinhViens.isEmpty()) {
                    NienKhoa nienKhoaTmp = nienKhoaSinhViens.get(cb_nienKhoaSV.getSelectedIndex());
                    sinhVien.setNienKhoa(nienKhoaTmp);
                    sinhVien.setQueQuan(queQuan);
                    boolean result = sinhVienRepo.addSinhVien(sinhVien);
                    if (result) {
                        JOptionPane.showMessageDialog(this, "Thêm thành công!");
                        sinhVienNienKhoas = sinhVienRepo.getByIdNienKhoa(nienKhoaTmp);
                        addTableSV();
                        setTextEmpty();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm sinh viên không thành công!");

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể thêm sinh viên vào ngành này, chưa có khóa nào được mở!");
                }
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cb_chuyenNganhSVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_chuyenNganhSVMouseEntered

    }//GEN-LAST:event_cb_chuyenNganhSVMouseEntered

    private void cb_chuyenNganhSVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_chuyenNganhSVItemStateChanged
        sinhVienUpdate = null;
        txt_ngaySinh.setText("");
        txt_tenSV.setText("");
        txt_quequan.setText("");
        DefaultTableModel model = (DefaultTableModel) tbl_SV.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        if (cb_chuyenNganhSV.getSelectedIndex() < chuyenNganhs.size() && cb_chuyenNganhSV.getSelectedIndex() >= 0) {
            ChuyenNganh chuyenNganh = chuyenNganhs.get(cb_chuyenNganhSV.getSelectedIndex());
            nienKhoaSinhViens = nienKhoaRepo.getNienKhoas(chuyenNganh.getId());
            if (!nienKhoaSinhViens.isEmpty()) {
                addTableSV();
            }
            addCbNienKhoaSV();

        }
    }//GEN-LAST:event_cb_chuyenNganhSVItemStateChanged

    private void cb_nienKhoaSVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_nienKhoaSVItemStateChanged
        sinhVienUpdate = null;
        txt_ngaySinh.setText("");
        txt_tenSV.setText("");
        txt_quequan.setText("");
        DefaultTableModel model = (DefaultTableModel) tbl_SV.getModel();
        model.getDataVector().removeAllElements();
        if (cb_nienKhoaSV.getSelectedIndex() < nienKhoaSinhViens.size() && cb_nienKhoaSV.getSelectedIndex() >= 0) {
            sNienKhoa = nienKhoaSinhViens.get(cb_nienKhoaSV.getSelectedIndex());
            NienKhoa nienKhoaSV = nienKhoaSinhViens.get(cb_nienKhoaSV.getSelectedIndex());
            sinhVienNienKhoas = sinhVienRepo.getByIdNienKhoa(nienKhoaSV);
            addTableSV();
        }


    }//GEN-LAST:event_cb_nienKhoaSVItemStateChanged

    private void tbl_SVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_SVKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
            int n = JOptionPane.showConfirmDialog(
                    this, "Xóa sinh viên sẽ mất hết dữ liệu liên quan đến sinh viên này?",
                    "Xác nhận!",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                int row = tbl_SV.getSelectedRow();
                if (row < sinhVienNienKhoas.size()) {
                    int id = Integer.parseInt(tbl_SV.getValueAt(row, 1).toString());
                    boolean result = sinhVienRepo.delete(id);
                    if (result) {
                        JOptionPane.showMessageDialog(this, "Thành công!");
                        sinhVienNienKhoas = sinhVienRepo.getByIdNienKhoa(nienKhoaSinhViens.get(cb_nienKhoaSV.getSelectedIndex()));
                        addTableSV();
                    } else {
                        JOptionPane.showMessageDialog(this, "Không thể xóa sinh viên");
                    }

                }

            } else if (n == JOptionPane.NO_OPTION) {
            } else {

            }
        }
    }//GEN-LAST:event_tbl_SVKeyPressed

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed
        if (!lopMonHocs.isEmpty()) {
            sChuyenNganh = chuyenNganhs.get(cb_khoa.getSelectedIndex());
            sMonHoc = monHocs.get(cb_monhoc.getSelectedIndex());
            sLopMonHoc = lopMonHocs.get(cb_lop.getSelectedIndex());
            sNienKhoa = nienKhoas.get(cb_khoahoc.getSelectedIndex());
            dialog = new JDialog(this);
            dialog.getContentPane().add(new DialogReport());
            dialog.setSize(800, 800);
            dialog.setVisible(true);
        } else if (!cb_monhoc.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có lớp cho môn " + cb_monhoc.getSelectedItem().toString());
        } else {

        }
    }//GEN-LAST:event_btnSearch2ActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (edit && sinhVienUpdate != null) {
            String name = "";
            String ngaySinh = "";
            String queQuan = "";
            name = txt_tenSV.getText();
            queQuan = txt_quequan.getText();
            ngaySinh = txt_ngaySinh.getText();
            if (name.isEmpty() || queQuan.isEmpty() || ngaySinh.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên, quên quán, ngày sinh không được để trống!");
                return;
            } else if (sinhVienUpdate.getTenSV().equals(name) && sinhVienUpdate.getQueQuan().equals(queQuan)
                    && sinhVienUpdate.getNgaySinhString().equals(ngaySinh)) {
                JOptionPane.showMessageDialog(this, "Không có trường nào được sửa, không thể cập nhật!");
                return;
            } else if (!ngaySinh.matches(regex)) {
                JOptionPane.showMessageDialog(this, DATE_FAIL);
                return;
            } else {
                java.sql.Date date = Constants.getConvertDate(ngaySinh);
                sinhVienUpdate.setNgaySinh(date);
                sinhVienUpdate.setQueQuan(queQuan);
                sinhVienUpdate.setTenSV(name);
                if (date != null) {
                    boolean result = sinhVienRepo.updateSinhVien(sinhVienUpdate);
                    if (result) {
                        JOptionPane.showMessageDialog(this, "Cập nhật thông tin sinh viên thành công!");
                        sinhVienNienKhoas = sinhVienRepo.getByIdNienKhoa(nienKhoaSinhViens.get(cb_nienKhoaSV.getSelectedIndex()));
                        addTableSV();
                    } else {
                        JOptionPane.showMessageDialog(this, "Cập nhật thông tin sinh viên không thành công!");
                    }
                }
                edit = false;
                sinhVienUpdate = null;
                btnEdit.setText("Sửa");
                btnCancel.setEnabled(edit);
                jButton1.setEnabled(!edit);
                setTextEmpty();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Chọn vào sinh viên muốn sửa thông tin ");
            edit = true;
            btnCancel.setEnabled(edit);
            jButton1.setEnabled(!edit);
        }

    }//GEN-LAST:event_btnEditActionPerformed

    private void tbl_SVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SVMouseClicked
        int rowSelect = tbl_SV.getSelectedRow();
        if (rowSelect > 0 && rowSelect < sinhVienNienKhoas.size() && edit) {
            sinhVienUpdate = sinhVienNienKhoas.get(rowSelect);
        }
        if (sinhVienUpdate != null && edit) {
            btnEdit.setText("Lưu");
            txt_tenSV.setText(sinhVienUpdate.getTenSV());
            txt_ngaySinh.setText(sinhVienUpdate.getNgaySinhString());
            txt_quequan.setText(sinhVienUpdate.getQueQuan());

        }
    }//GEN-LAST:event_tbl_SVMouseClicked

    private void setTextEmpty() {
        txt_ngaySinh.setText("");
        txt_quequan.setText("");
        txt_tenSV.setText("");
    }
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        edit = false;
        sinhVienUpdate = null;
        btnCancel.setEnabled(edit);
        btnEdit.setText("Sửa");
        jButton1.setEnabled(!edit);
        setTextEmpty();
    }//GEN-LAST:event_btnCancelActionPerformed

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
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSearch2;
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
    private javax.swing.JTextField txt_quequan;
    private javax.swing.JTextField txt_tenSV;
    // End of variables declaration//GEN-END:variables
}
