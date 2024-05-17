package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEx02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean result=delete(4L);
		System.out.println(result?"성공":"실패");

	}
	
	private static boolean delete(long no) {
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
			String sql = "delete from dept where no = ?";
			pstmt = con.prepareStatement(sql);

			// 4. binding
			pstmt.setLong(1, no);
			
			int count=pstmt.executeUpdate();
			result=count==1;

			

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
