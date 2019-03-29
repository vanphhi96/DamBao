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
import static view.ViewMain.sinhVienLopMHs;

/**
 *
 * @author vanph
 */
public class DialogReport extends javax.swing.JPanel {

    /**
     * Creates new form DialogCustom
     */
    public DialogReport() {
        initComponents();
        lb_monhoc.setText("Môn học: "+ViewMain.sMonHoc.getTenMonHoc());
        lb_lop.setText("Mã lớp: "+ViewMain.sLopMonHoc.getTenLop());
        lb_khoa.setText("Khóa: "+ViewMain.sNienKhoa.getNienKhoa()+"-"+ViewMain.sChuyenNganh.getTenChuyenNganh());
        addTable();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_monhoc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_report = new javax.swing.JTable();
        lb_lop = new javax.swing.JLabel();
        lb_khoa = new javax.swing.JLabel();
        lb_tile = new javax.swing.JLabel();
        lb_dat = new javax.swing.JLabel();
        lb_truot = new javax.swing.JLabel();

        lb_monhoc.setText("Môn học:");

        tbl_report.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SV", "Tên SV", "Ngày Sinh", "Điểm cc (10%)", "Điểm giữa kì (20%)", "Điểm thi (70%)", "Trung bình", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_report.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_reportMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_report);

        lb_lop.setText("Lớp :");

        lb_khoa.setText("Khóa:");

        lb_tile.setText("Tỉ lệ:");

        lb_dat.setText("Đạt");

        lb_truot.setText("Trượt");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_truot)
                    .addComponent(lb_dat)
                    .addComponent(lb_tile)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lb_monhoc)
                        .addComponent(lb_lop, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lb_khoa, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_monhoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_lop)
                .addGap(18, 18, 18)
                .addComponent(lb_khoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_tile)
                .addGap(18, 18, 18)
                .addComponent(lb_dat)
                .addGap(11, 11, 11)
                .addComponent(lb_truot)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
 private void addTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_report.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        if (sinhVienLopMHs.isEmpty()) {
            return;
        }
        int dau = 0;
        for (int i = 0; i < sinhVienLopMHs.size(); i++) {
            SVLopMonHoc hoc = sinhVienLopMHs.get(i);
            if(hoc.getStatus().equals("Đạt")){
                dau++;
            }
            model.addRow(new Object[]{i+1,hoc.getSinhVien().getId(), 
                hoc.getSinhVien().getTenSV(), hoc.getSinhVien().getNgaySinhString(), 
                hoc.getDiemCC(), hoc.getDiemGiuaKi(),hoc.getDiemThi(),hoc.getDiemTB(), hoc.getStatus()});
        }
        int dat = Math.round(100*dau/sinhVienLopMHs.size());
        int truot = 100-dat;
        String tong = "Tổng số: "+sinhVienLopMHs.size();
        String soDat = "Đạt: "+dau+" ("+dat+"%)";
        String soTruot = "Trượt: "+(sinhVienLopMHs.size()-dau)+" ("+truot+"%)";
        lb_tile.setText(tong);
        lb_dat.setText(soDat);
        lb_truot.setText(soTruot);
    }

    private void tbl_reportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_reportMouseClicked

    }//GEN-LAST:event_tbl_reportMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_dat;
    private javax.swing.JLabel lb_khoa;
    private javax.swing.JLabel lb_lop;
    private javax.swing.JLabel lb_monhoc;
    private javax.swing.JLabel lb_tile;
    private javax.swing.JLabel lb_truot;
    private javax.swing.JTable tbl_report;
    // End of variables declaration//GEN-END:variables
}
