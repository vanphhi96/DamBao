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
import java.util.ArrayList;
import java.util.List;
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
    private List<NienKhoa> nienKhoas = new ArrayList<>();
    private List<MonKhoaHoc> monKhoaHocs = new ArrayList<MonKhoaHoc>();
    private List<SinhVien> sinhViens = new ArrayList<>();
    private List<SVLopMonHoc> sinhVienLopMHs = new ArrayList<>();
    private MonKhoaHoc monKhoaHoc = null;

    /**
     * Creates new form ViewMain
     */
    public ViewMain() {
        initComponents();
        chuyenNganhs = chuyenNganhRepo.getAll();
        addCbChuyenNganh();
    }

    private void addCbMonHoc() {
        cb_monhoc.removeAllItems();
        cb_lop.removeAllItems();
        if (!nienKhoas.isEmpty()) {
            NienKhoa khoa = nienKhoas.get(cb_khoahoc.getSelectedIndex());
            monHocs = monKhoaHocRepo.getMonKhoa(khoa.getId());
            if (!monHocs.isEmpty()) {
                for (int i = 0; i < monHocs.size(); i++) {
                    cb_monhoc.addItem(monHocs.get(i).getTenMonHoc());
                }

            }
            addCbLop();
        }

    }

    private void addCbNienKhoa() {
        cb_khoahoc.removeAllItems();
        cb_monhoc.removeAllItems();
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
        addCbMonHoc();

    }

    private void addCbLop() {
        cb_lop.removeAllItems();
        MonHoc mh = null;
        NienKhoa nk = null;
        if (monHocs.size() != 0) {
            mh = monHocs.get(cb_monhoc.getSelectedIndex());
        } else {
            addTable();
            return;
        }
        if (nienKhoas.size() != 0) {
            nk = nienKhoas.get(cb_khoahoc.getSelectedIndex());

        } else {
            addTable();
            return;

        }
        if (chuyenNganhs.size() == 0) {
            addTable();
            return;
        }
        ChuyenNganh chuyenNganh = chuyenNganhs.get(cb_khoa.getSelectedIndex());
        if (nk == null || chuyenNganh == null) {
            addTable();
            return;
        }
        MonChuyenNganh mNganh = monNganhRepo.getMonChuyenNganh(mh.getId(), chuyenNganh.getId());
        if (mNganh == null) {
            addTable();
            return;
        }
        monKhoaHoc = monKhoaHocRepo.getMonKhoaHoc(mNganh.getId(), nk.getId());
        if (monKhoaHoc == null) {
            addTable();
            return;
        }
        System.out.println(" id mon khoa hoc " + monKhoaHoc.getIdMonKhoaHoc());
        lopMonHocs = lopMonHocRepo.getLopMonHocs(monKhoaHoc.getIdMonKhoaHoc());
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
        if (lopMonHocs.isEmpty()) {
            return;
        }
        sinhVienLopMHs = sinhVienLopRepo.getSVLopMonHoc(lopMonHocs.get(cb_lop.getSelectedIndex()).getIdLop());
        for (int i = 0; i < sinhVienLopMHs.size(); i++) {
            model.addRow(new Object[]{sinhVienLopMHs.get(i).getSinhVien().getId(), sinhVienLopMHs.get(i).getSinhVien().getTenSV(), sinhVienLopMHs.get(i).getDiemCC(), sinhVienLopMHs.get(i).getDiemGiuaKi(), sinhVienLopMHs.get(i).getDiemCuoiKi()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
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
        btnSearch2 = new javax.swing.JButton();
        cb_khoa = new javax.swing.JComboBox();
        cb_khoahoc = new javax.swing.JComboBox();
        cb_monhoc = new javax.swing.JComboBox();
        cb_lop = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbl_diemsv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã SV", "Tên SV", "Điểm CC", "Điểm giữa kì", "Điểm thi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_diemsv);
        if (tbl_diemsv.getColumnModel().getColumnCount() > 0) {
            tbl_diemsv.getColumnModel().getColumn(0).setResizable(false);
            tbl_diemsv.getColumnModel().getColumn(1).setResizable(false);
            tbl_diemsv.getColumnModel().getColumn(2).setResizable(false);
            tbl_diemsv.getColumnModel().getColumn(2).setHeaderValue("Điểm CC");
            tbl_diemsv.getColumnModel().getColumn(3).setHeaderValue("Điểm giữa kì");
            tbl_diemsv.getColumnModel().getColumn(4).setResizable(false);
            tbl_diemsv.getColumnModel().getColumn(4).setHeaderValue("Điểm thi");
        }

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

        btnSearch2.setText("Lưu thay đổi");
        btnSearch2.setActionCommand("btnSearch");
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
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

        cb_monhoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cb_monhocMouseEntered(evt);
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
                                .addComponent(btnSearch2, javax.swing.GroupLayout.Alignment.LEADING)))
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
                    .addComponent(btnSearch2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        jTabbedPane1.addTab("Quản lý điểm", jPanel1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 769, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Môn học", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearch2ActionPerformed

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

    private void cb_khoahocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_khoahocItemStateChanged

    }//GEN-LAST:event_cb_khoahocItemStateChanged

    private void cb_khoahocMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_khoahocMouseEntered
        addCbMonHoc();
    }//GEN-LAST:event_cb_khoahocMouseEntered

    private void cb_monhocMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_monhocMouseEntered
        addCbLop();
    }//GEN-LAST:event_cb_monhocMouseEntered

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
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JComboBox cb_khoa;
    private javax.swing.JComboBox cb_khoahoc;
    private javax.swing.JComboBox cb_lop;
    private javax.swing.JComboBox cb_monhoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbl_diemsv;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
