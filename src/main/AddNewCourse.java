
package main.coursesFrames;

import core.course.CoursesUtil;
import static core.department.DepartmentsUtil.displayDepartmentNames;
import java.sql.SQLException;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import util.gui.GUI_Util;

/**
 *
 * @author moshe
 */
public class AddNewCourse extends javax.swing.JFrame {

    /**
     * Creates new form AddNewStudent
     */
    public AddNewCourse() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLbl = new javax.swing.JLabel();
        courseTitleLbl = new javax.swing.JLabel();
        courseTitleTf = new javax.swing.JTextField();
        courseDeptLbl = new javax.swing.JLabel();
        courseIdTf = new javax.swing.JTextField();
        courseCreditsLbl = new javax.swing.JLabel();
        createCourseBtn = new javax.swing.JButton();
        courseCreditsSp = new javax.swing.JSpinner();
        courseIdLbl = new javax.swing.JLabel();
        courseDepartmentCb = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        titleLbl.setText("Create Course");

        courseTitleLbl.setText("Title :");

        courseDeptLbl.setText("Department name :");

        courseCreditsLbl.setText("Credits :");

        createCourseBtn.setText("Create New Course");
        createCourseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCourseBtnActionPerformed(evt);
            }
        });

        courseCreditsSp.setModel(new SpinnerNumberModel(0.0, 0.0, 10000000.0, 10.0));
        courseCreditsSp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        courseIdLbl.setText("Id :");

        courseDepartmentCb.setModel(getDepartmentNameModel());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(titleLbl))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(courseTitleLbl)
                                    .addComponent(courseCreditsLbl)
                                    .addComponent(courseDeptLbl)
                                    .addComponent(courseIdLbl))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(courseTitleTf)
                                    .addComponent(courseIdTf, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(courseCreditsSp)
                                    .addComponent(courseDepartmentCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(createCourseBtn)))))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(titleLbl)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseIdLbl)
                    .addComponent(courseIdTf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseTitleLbl)
                    .addComponent(courseTitleTf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseDeptLbl)
                    .addComponent(courseDepartmentCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseCreditsLbl)
                    .addComponent(courseCreditsSp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(createCourseBtn)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createCourseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCourseBtnActionPerformed
        try {
            CoursesUtil.createCourse(courseIdTf.getText(),
                    this.courseTitleTf.getText(),
                    (String) this.courseDepartmentCb.getSelectedItem(),
                    (double) this.courseCreditsSp.getValue());

            JOptionPane.showMessageDialog(
                    rootPane, "Course Created Successfully");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }
    }//GEN-LAST:event_createCourseBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel courseCreditsLbl;
    private javax.swing.JSpinner courseCreditsSp;
    private javax.swing.JComboBox<String> courseDepartmentCb;
    private javax.swing.JLabel courseDeptLbl;
    private javax.swing.JLabel courseIdLbl;
    private javax.swing.JTextField courseIdTf;
    private javax.swing.JLabel courseTitleLbl;
    private javax.swing.JTextField courseTitleTf;
    private javax.swing.JButton createCourseBtn;
    private javax.swing.JLabel titleLbl;
    // End of variables declaration//GEN-END:variables

    private ComboBoxModel<String> getDepartmentNameModel() {
        try {
            return GUI_Util.buildComboBoxModel(
                    displayDepartmentNames());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }
        return null;
    }
}
