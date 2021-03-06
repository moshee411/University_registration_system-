
package util.gui;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author moshe
 */
public final class GUI_Util {

    private GUI_Util() {
    }

    public static void duplicateText(JTextField original, JTextField duplicate) {

        original.getDocument().addDocumentListener(
                new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                textChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                textChanged();
            }

            private void textChanged() {
                duplicate.setText(original.getText());
            }
        }
        );
    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        ArrayList<String> columnNames = new ArrayList<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        ArrayList<Object[]> data = new ArrayList<>();
        while (rs.next()) {
            Object[] data1 = new Object[columnCount];
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                data1[columnIndex - 1] = (rs.getObject(columnIndex));
            }
            data.add(data1);
        }
        return new DefaultTableModel(
                data.toArray(new Object[data.size()][columnCount]),
                columnNames.toArray()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // for preventing any cell edit
                return false;
            }
        };

    }

    public static DefaultComboBoxModel buildComboBoxModel(ResultSet rs)
            throws SQLException {

        ArrayList<String> arrayList = new ArrayList<>();
        while (rs.next()) {
            arrayList.add(rs.getString(1));
        }
        return new DefaultComboBoxModel(arrayList.toArray());
    }

    public static void linkFrameToButton(JFrame frame, JButton button) {
        button.setEnabled(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                button.setEnabled(true);
            }
        });
    }

    public static void exitConfirm(Component rootPane) {
        int confirm = JOptionPane.showConfirmDialog(
                rootPane, "Are You Sure to Close Application?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static String promoteString(
            Component pane, String request, String title, String emptyMsg) {

        String promotedString = JOptionPane.
                showInputDialog(pane, request, title, DISPOSE_ON_CLOSE);

        if (promotedString != null && promotedString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(pane, emptyMsg);
            return null;
        }
        return promotedString;
    }

    public static boolean promoteConfirm(
            Component rootPane, String msg, String title) {
        int confirm = JOptionPane.showConfirmDialog(
                rootPane, msg,
                title, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return confirm == JOptionPane.YES_OPTION;
    }

    public static JFrame promoteSpinner(String title, String labelTxt,
            String buttonTxt, SpinnerNumberModel spinnerNumberModel,
            DoSomethingWithSpinner dsws) {
        JFrame promoteFrame
                = new PromoteSpinner(title, labelTxt, spinnerNumberModel, buttonTxt,
                        dsws);
        promoteFrame.setVisible(true);
        return promoteFrame;
    }

    public static JFrame promoteComboBox(String title, String labelTxt,
            String buttonTxt, ComboBoxModel comboBoxModel,
            DoSomethingWithComboBox dswcb) {

        JFrame promoteFrame = new PromoteComboBox(
                title, labelTxt, comboBoxModel, buttonTxt, dswcb);

        promoteFrame.setVisible(true);
        return promoteFrame;
    }

    public static abstract interface DoSomethingWithSpinner {

        /**
         * @param spinnerValue the value of the spinner
         * @return true to close or false to not close
         */
        abstract boolean doSomething(double spinnerValue);
    }

    public static abstract interface DoSomethingWithComboBox {

        /**
         * @param choice
         * @return true to close or false to not close
         */
        abstract boolean doSomething(String choice);
    }
}
