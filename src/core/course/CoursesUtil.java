
package core.course;

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
public final class CoursesUtil {

    private CoursesUtil() {
    }

    public static ResultSet displayCourseInformation() throws SQLException {
        String call = "{CALL Course_pkg.get_all_courses(?)}";
        CallableStatement statment = getConnection().prepareCall(call);
        statment.registerOutParameter(1, OracleTypes.CURSOR);
        statment.execute();
        ResultSet rs = ((OracleCallableStatement) statment).getCursor(1);
        return rs;
    }

    public static ResultSet getAllCoursesStudentsNumber() throws SQLException {
        String call = "{CALL Course_pkg.get_courses_students_number(?)}";
        CallableStatement statment = getConnection().prepareCall(call);
        statment.registerOutParameter(1, OracleTypes.CURSOR);
        statment.execute();
        ResultSet rs = ((OracleCallableStatement) statment).getCursor(1);
        return rs;
    }

    public static void createCourse(String courseId, String courseTitle,
            String courseDepartmentName, double courseCredits)
            throws SQLException {
        String call = "{CALL course_pkg.create_course(?,?,?,?)}";
        CallableStatement statment = getConnection().prepareCall(call);

        statment.setString(1, courseId);
        statment.setString(2, courseTitle);
        statment.setString(3, courseDepartmentName);
        statment.setDouble(4, courseCredits);
        statment.execute();
    }
}
