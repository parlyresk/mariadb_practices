package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectEx01 {

	public static void main(String[] args) {
		search("pat");

	}
	
	public static void search(String keyword) {

		
		Connection con = null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			
			// 1. jdbc driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.212:3306/employees?charset=utf8";
			con = DriverManager.getConnection(url, "hr", "hr");

			// 3. Statement 생성하기
			stmt = con.createStatement();

			// 4. SQL 실행
			String sql = "select * from employees where first_name like '%"+keyword+"%'";
			rs= stmt.executeQuery(sql);

			//5. 결과처리
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
				if(stmt!=null) {
					stmt.close();
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
