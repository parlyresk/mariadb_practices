package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertEx02 {
	public static void main(String[] args) {
		System.out.println(insert("기획1팀"));
		System.out.println(insert("기획2팀"));
		
	}

	public static boolean insert(String deptName) {
		boolean result = false;
		
		Connection con = null;
		PreparedStatement pstmt=null;
		try {
			
			// 1. jdbc driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.212:3306/webdb?charset=utf8";
			con = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. Statement 준비
			String sql = "insert into dept values(null,?)";
			pstmt = con.prepareStatement(sql);

			// 4. SQL 실행
			
			int count = pstmt.executeUpdate(sql);

			// 5. 결과 처리
			result = count == 1;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("드라이버 로딩 실패: " + e);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error: " + e);

		} finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
