/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import irepository.ISVLopMonHoc;
import irepository.ISinhVien;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.SVLopMonHoc;
import model.SinhVien;
import repository.SVLopMonHocRepository;
import repository.SinhVienRepository;

/**
 *
 * @author vanph
 */
public class DialogCustom extends javax.swing.JPanel {

    private List<SinhVien> sinhViens = new ArrayList<>();
    private ISinhVien iSinhVienRepo = new SinhVienRepository();
    private ISVLopMonHoc iSVLopMonHocRepo = new SVLopMonHocRepository();

    /**
     * Creates new form DialogCustom
     */
    public DialogCustom() {
        initComponents();
        addNienKhoa();
        tv_title.setText("Chuyên ngành: " + ViewMain.sChuyenNganh.getTenChuyenNganh() + "   Môn: " + ViewMain.sMonHoc.getTenMonHoc());
        addTable();
    }

    private void addNienKhoa() {
        cb_nienkhoa_add.removeAllItems();;
        for (int i = 0; i < ViewMain.nienKhoas.size(); i++) {
            cb_nienkhoa_add.addItem(ViewMain.nienKhoas.get(i).getNienKhoa());
        }
        addTable();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tv_title = new javax.swing.JLabel();
        cb_nienkhoa_add = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtSearchAdd = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSv = new javax.swing.JTable();

        tv_title.setText("Title");

        cb_nienkhoa_add.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_nienkhoa_addItemStateChanged(evt);
            }
        });
        cb_nienkhoa_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_nienkhoa_addActionPerformed(evt);
            }
        });

        jLabel2.setText("Khóa:");

        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        tblSv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SV", "Tên SV", "Ngày Sinh", "Quê Quán", "Check"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSvMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSv);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(tv_title)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cb_nienkhoa_add, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(txtSearchAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(79, 79, 79)
                .addComponent(btnAdd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tv_title)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_nienkhoa_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(txtSearchAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
 private void addTable() {
        DefaultTableModel model = (DefaultTableModel) tblSv.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        sinhViens = iSinhVienRepo.getByIdNienKhoa(ViewMain.nienKhoas.get(cb_nienkhoa_add.getSelectedIndex()));
        System.out.println("add sv " + sinhViens.size());
        List<SinhVien> svRemove = new ArrayList<>();
        for (SinhVien sv : sinhViens) {
            boolean xetDk = true;
            for (SVLopMonHoc svLop : ViewMain.sinhVienLopMHs) {
                if (svLop.getSinhVien().getId() == sv.getId()) {
                    xetDk = false;
                    svRemove.add(sv);
                }
            }
            if (xetDk) {
                model.addRow(new Object[]{sv.getId(), sv.getTenSV(),
                    sv.getNgaySinhString(), sv.getQueQuan(), false});
            }

        }
        sinhViens.removeAll(svRemove);
    }

    private void cb_nienkhoa_addItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_nienkhoa_addItemStateChanged
        addTable();
        txtSearchAdd.setText("");
    }//GEN-LAST:event_cb_nienkhoa_addItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String keyWord = txtSearchAdd.getText();
        DefaultTableModel model = (DefaultTableModel) tblSv.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        for (int i = 0; i < sinhViens.size(); i++) {
            if (sinhViens.get(i).getTenSV().contains(keyWord)) {
                model.addRow(new Object[]{sinhViens.get(i).getId(), sinhViens.get(i).getTenSV(),
                    sinhViens.get(i).getNgaySinhString(), sinhViens.get(i).getQueQuan(), sinhViens.get(i).isChecked()});
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblSv.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            boolean checked = (boolean) model.getValueAt(i, 4);
            int id = (int) model.getValueAt(i, 0);
            for (int j = 0; j < sinhViens.size(); j++) {
                if (sinhViens.get(j).getId() == id) {
                    sinhViens.get(j).setChecked(checked);
                }
            }
        }
        for (int j = 0; j < sinhViens.size(); j++) {
            if (sinhViens.get(j).isChecked()) {
                iSVLopMonHocRepo.addSvLopMonHoc(new SVLopMonHoc(sinhViens.get(j), ViewMain.sLopMonHoc, 0, 0, 0, ""));
            }
        }
        JOptionPane.showMessageDialog(this, "Success!");
        if( ViewMain.dialog!=null){
            ViewMain.dialog.setVisible(false);
        }
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblSvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSvMouseClicked

    }//GEN-LAST:event_tblSvMouseClicked

    private void cb_nienkhoa_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_nienkhoa_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_nienkhoa_addActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JComboBox cb_nienkhoa_add;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSv;
    private javax.swing.JLabel tv_title;
    private javax.swing.JTextField txtSearchAdd;
    // End of variables declaration//GEN-END:variables
}
