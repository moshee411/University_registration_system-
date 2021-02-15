
package main;

import com.db.sql.dependencies.Initialize;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import static java.awt.EventQueue.invokeLater;

/**
 *
 * @author moshe
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Initialize.init();
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

}
