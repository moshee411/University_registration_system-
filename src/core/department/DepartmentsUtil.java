
package core.department;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import static util.db.DBConnection.getConnection;

/**
 *
 * @author moshee
 */
public final class DepartmentsUtil {

    private DepartmentsUtil() {
    }

    public static ResultSet displayDepartmentNames() throws SQLException {
        String call = "{CALL department_pkg.Get_Dept_Name(?)}";
        CallableStatement statment
                = getConnection().prepareCall(call);
        statment.registerOutParameter(1, OracleTypes.CURSOR);
        statment.execute();
        ResultSet rs = ((OracleCallableStatement) statment).getCursor(1);
        return rs;
    }
}
