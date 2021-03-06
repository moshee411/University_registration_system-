
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
public class Student {

    private final String ID;
    private String name;
    private String departmentName;
    private double totalCerdit;

    /**
     *
     * @param id
     * @throws SQLException
     */
    public Student(String id) throws SQLException {
        String call = "{CALL student_pkg.get_student_by_id(?,?)}";
        CallableStatement statment = getConnection().prepareCall(call);
        statment.registerOutParameter(1, OracleTypes.CURSOR);
        statment.setString(2, id);
        statment.execute();
        ResultSet rs = ((OracleCallableStatement) statment).getCursor(1);
        rs.next();

        name = rs.getString("name");
        departmentName = rs.getString("dept_name");
        totalCerdit = rs.getDouble("tot_cred");
        this.ID = id;
    }

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param name the name to set
     * @throws java.sql.SQLException
     */
    public void setName(String name) throws SQLException {
        String call = "{CALL student_pkg.set_name(?,?)}";
        CallableStatement statment
                = getConnection().prepareCall(call);
        statment.setString(1, ID);
        statment.setString(2, name);
        statment.execute();
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param departmentName the departmentName to set
     * @throws java.sql.SQLException
     */
    public void setDepartmentName(String departmentName) throws SQLException {
        String call = "{CALL student_pkg.set_department_name(?,?)}";
        CallableStatement statment = getConnection().prepareCall(call);
        statment.setString(1, ID);
        statment.setString(2, departmentName);
        statment.execute();
        this.departmentName = departmentName;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param totalCerdit the totalCerdit to set
     * @throws java.sql.SQLException
     */
    public void setTotalCerdit(double totalCerdit) throws SQLException {
        String call = "{CALL student_pkg.set_total_cerdit(?,?)}";
        CallableStatement statment
                = getConnection().prepareCall(call);
        statment.setString(1, ID);
        statment.setDouble(2, totalCerdit);
        statment.execute();
    }

    /**
     * @return the totalCerdit
     */
    public double getTotalCerdit() {
        return totalCerdit;
    }

    public ResultSet getCourses() throws SQLException {
        String call = "{CALL student_pkg.get_student_courses(?,?)}";
        CallableStatement statment = getConnection().prepareCall(call);
        statment.registerOutParameter(1, OracleTypes.CURSOR);
        statment.setString(2, ID);
        statment.execute();
        ResultSet rs = ((OracleCallableStatement) statment).getCursor(1);
        return rs;
    }

    /**
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String call = "{CALL student_pkg.Delete_Student(?)}";
        CallableStatement statment = getConnection().prepareCall(call);
        statment.setString(1, ID);
        statment.execute();

        this.name = null;
        this.totalCerdit = -1;
        this.departmentName = null;
    }

}
