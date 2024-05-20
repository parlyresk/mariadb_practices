package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectEx02 {

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        ResultSetProcessor processor = new ResultSetProcessor();

        dbManager.searchWithKeywordProcessor("pat", processor);
    }
}

class DatabaseManager {
    
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public void searchWithKeywordProcessor(String keyword, ResultSetProcessor processor) {
        try {
            connect();
            prepareStatementWithKeyword(keyword);
            executeQuery();
            processor.process(rs);
        } catch (ClassNotFoundException | SQLException e) {
            handleException(e);
        } finally {
            closeResources();
        }
    }

    private void connect() throws ClassNotFoundException, SQLException {
        // 1. JDBC 드라이버 로딩
        Class.forName("org.mariadb.jdbc.Driver");

        // 2. 연결하기
        String url = "jdbc:mariadb://192.168.0.212:3306/employees?charset=utf8";
        con = DriverManager.getConnection(url, "hr", "hr");
    }

    private void prepareStatementWithKeyword(String keyword) throws SQLException {
        // 3. Statement 준비
        String sql = "select emp_no, first_name, last_name from employees where first_name like ? and last_name like ?";
        pstmt = con.prepareStatement(sql);

        // 4. 바인딩
        pstmt.setString(1, "%" + keyword + "%");
        pstmt.setString(2, "%" + keyword + "%");
    }

    private void executeQuery() throws SQLException {
        // 5. SQL 실행
        rs = pstmt.executeQuery();
    }

    private void handleException(Exception e) {
        if (e instanceof ClassNotFoundException) {
            System.out.println("드라이버 로딩 실패: " + e);
        } else if (e instanceof SQLException) {
            System.out.println("오류: " + e);
        }
    }

    private void closeResources() {
        // 리소스 닫기
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class ResultSetProcessor {

    public void process(ResultSet rs) throws SQLException {
        // 6. 결과처리
        while (rs.next()) {
            Long empNo = rs.getLong(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);

            System.out.println(empNo + ": " + firstName + " " + lastName);
        }
    }
}