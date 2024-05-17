package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateEx01 {

	public static void main(String[] args) {
		DeptVo vo=new DeptVo();
		vo.setNo(1L);
		vo.setName("경영지원");
		
		boolean result=update(vo);
		System.out.println(result?"성공":"실패");

	}
	
	public static boolean update(DeptVo vo) {
		boolean result = false;
		
		Connection con = null;
		Statement stmt=null;
		try {
			
			// 1. jdbc driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.212:3306/webdb?charset=utf8";
			con = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. Statement 생성하기
			stmt = con.createStatement();

			// 4. SQL 실행
			String sql = "update dept set name='" + vo.getName() + "' where no="+vo.getNo();
			int count = stmt.executeUpdate(sql);

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
		return result;
	}

}
