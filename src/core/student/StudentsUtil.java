
package core.student;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import static util.db.DBConnection.getConnection;

/**
 *
 * @author moshe
 */
public final class StudentsUtil {

    private StudentsUtil() {
    }

    public static ResultSet getAllStudents() throws SQLException {
        String call = "{CALL student_pkg.get_all_students(?)}";
        CallableStatement statment
                = getConnection().prepareCall(call);
        statment.registerOutParameter(1, OracleTypes.CURSOR);
        statment.execute();
        ResultSet rs = ((OracleCallableStatement) statment).getCursor(1);
        return rs;
    }

    public static void registerStudent(String studentId, String studentName,
            String studentDepartmentName, double studentTotalCredit)
            throws SQLException {
        String call = "{CALL student_pkg.register_student(?,?,?,?)}";
        CallableStatement statment = getConnection().prepareCall(call);

        statment.setString(1, studentId);
        statment.setString(2, studentName);
        statment.setString(3, studentDepartmentName);
        statment.setDouble(4, studentTotalCredit);
        statment.execute();
    }

}
