package emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import emaillist.vo.EmaillistVo;

public class EmaillistDao {

	public boolean insert(EmaillistVo vo) {
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
			String sql = "insert into emaillist values(null,?,?,?)";
			pstmt = con.prepareStatement(sql);

			// 4. SQL 실행
			pstmt.setString(1, vo.getFirstName());
			pstmt.setString(2, vo.getLastName());
			pstmt.setString(3,vo.getEmail());
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

	public boolean deleteByEmail(String email) {
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
			String sql = "delete from emaillist where email=?";
			pstmt = con.prepareStatement(sql);

			// 4. binding
			pstmt.setString(1, email);
			
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

	@SuppressWarnings("finally")
	public List<EmaillistVo> findAll() {
		List<EmaillistVo> result=new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			
			// 1. jdbc driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.212:3306/webdb?charset=utf8";
			con = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. Statement 준비
			String sql = "select no,first_name,last_name,email from emaillist order by no desc";
			pstmt = con.prepareStatement(sql);

			
			//5. SQL 실행
			rs= pstmt.executeQuery();

			//6. 결과처리
			while(rs.next()) {
				Long no=rs.getLong(1);
				String firstName=rs.getString(2);
				String lastName=rs.getString(3);
				String email=rs.getString(4);
				
				EmaillistVo vo=new EmaillistVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setFirstName(email);
				
				result.add(vo);
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
		
		return result;
	}

}
}
