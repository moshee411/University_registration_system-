
package main;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.db.DBConnection;
import util.gui.GUI_Util;
import util.db.DbUtil;

public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        GUI_Util.duplicateText(this.userNameTf, this.userSchemaTf);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoginBtn = new javax.swing.JButton();
        userNameTf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userSchemaTf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        userPasswordPf = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        LoginBtn.setText("Login");
        LoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
            }
        });

        userNameTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameTfActionPerformed(evt);
            }
        });

        jLabel1.setText("User");

        jLabel2.setText("Password");

        userSchemaTf.setEditable(false);
        userSchemaTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userSchemaTfActionPerformed(evt);
            }
        });

        jLabel3.setText("Schema");

        userPasswordPf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userPasswordPfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userNameTf)
                    .addComponent(userSchemaTf, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(userPasswordPf))
                .addGap(59, 59, 59))
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(LoginBtn)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(userNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(userPasswordPf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(userSchemaTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(LoginBtn)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userPasswordPfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userPasswordPfActionPerformed
        login();
    }//GEN-LAST:event_userPasswordPfActionPerformed

    private void userSchemaTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userSchemaTfActionPerformed
        login();
    }//GEN-LAST:event_userSchemaTfActionPerformed

    private void userNameTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameTfActionPerformed
        login();
    }//GEN-LAST:event_userNameTfActionPerformed

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBtnActionPerformed
        login();
    }//GEN-LAST:event_LoginBtnActionPerformed

    private void login() {
        try {
            DBConnection.establishConnection(
                    userNameTf.getText(),
                    new String(userPasswordPf.getPassword())
            );

            if (DBConnection.getConnection() == null) {
                return;
            }
            if (!DbUtil.checkUniversitySchema()) {
                if (JOptionPane.showConfirmDialog(
                        rootPane,
                        "The Database you are connecting to doesn't contain "
                        + "university schema !\n"
                        + "Do you want to replace your database with university "
                        + "database ?\n"
                        + "PS : this means you will drop all your tables and data!",
                        "schema not found",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) {
                    DbUtil.applyUniversity();
                }
            }
            new MainMenu().setVisible(true);
            this.dispose();
        } catch (SQLException | IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField userNameTf;
    private javax.swing.JPasswordField userPasswordPf;
    private javax.swing.JTextField userSchemaTf;
    // End of variables declaration//GEN-END:variables
}
