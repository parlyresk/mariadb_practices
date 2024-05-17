package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectEx02 {

	public static void main(String[] args) {
		search("pat");

	}
	
	public static void search(String keyword) {

		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			
			// 1. jdbc driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.212:3306/employees?charset=utf8";
			con = DriverManager.getConnection(url, "hr", "hr");

			// 3. Statement 준비
			String sql = "select emp_no,first_name,last_name from employees where first_name like ? and last_name like ?";
			pstmt = con.prepareStatement(sql);

			// 4. binding
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			
			//5. SQL 실행
			rs= pstmt.executeQuery();

			//6. 결과처리
			while(rs.next()) {
				Long empNo=rs.getLong(1);
				String firstName=rs.getString(2);
				String lastName=rs.getString(3);
				
				System.out.println(empNo + ":"+ firstName+" "+lastName);
			}
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("드라이버 로딩 실패: " + e);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error: " + e);

		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
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
		
	}

}
