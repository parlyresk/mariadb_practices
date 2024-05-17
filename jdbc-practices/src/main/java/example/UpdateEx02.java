package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateEx02 {

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
		PreparedStatement pstmt=null;
		try {
			
			// 1. jdbc driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.212:3306/webdb?charset=utf8";
			con = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. Statement 생성하기
			String sql = "update dept set name=? where no= ?";
			pstmt = con.prepareStatement(sql);

			// 4. SQL 실행
			pstmt.setString(1,vo.getName());
			pstmt.setLong(2,vo.getNo());
			int count = pstmt.executeUpdate();

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
