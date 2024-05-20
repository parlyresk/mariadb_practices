package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.AuthorVo;

public class AuthorDao {

	private Connection getConnection() throws SQLException {
		Connection con = null;

		try {
			// 1. jdbc driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.212:3306/webdb?charset=utf8";
			con = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {

			System.out.println("드라이버 로딩 실패: " + e);
		}

		return con;
	}

	public List<AuthorVo> findAll() {
		List<AuthorVo> result = new ArrayList<>();

		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement("select no,name from author");
				ResultSet rs = pstmt.executeQuery();) {

			// 5. SQL 실행

			// 6. 결과처리
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				AuthorVo vo=new AuthorVo();
				vo.setNo(no);
				vo.setName(name);

				result.add(vo);
			}

			

		} catch (SQLException e) {

			System.out.println("error: " + e);

		}
		return result;
	}

}
