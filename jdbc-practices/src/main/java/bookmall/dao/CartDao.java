package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;

public class CartDao {
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.212:3306/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		
		return conn;
	}

	public void insert(CartVo vo) {
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt1 = conn.prepareStatement("insert into cart(quantity,book_no,user_no) values(?,?,?)");
				
			) {
				pstmt1.setInt(1, vo.getQuantity());
				pstmt1.setLong(2, vo.getBookNo());
				pstmt1.setLong(3, vo.getUserNo());
				
				pstmt1.executeUpdate();
				
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		
	}

	public List<CartVo> findByUserNo(Long user_no) {
		
		List<CartVo> result = new ArrayList<>();

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select c.quantity,c.book_no,c.user_no,b.title from cart c,book b where user_no=? and b.no=c.book_no");
				) {
			
			pstmt.setLong(1, user_no);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int quantity=rs.getInt(1);
				Long book_no=rs.getLong(2);
				Long user_no1=rs.getLong(3);
				String title=rs.getString(4);
				
				
				
				CartVo vo = new CartVo();
				vo.setQuantity(quantity);
				vo.setBookNo(book_no);
				vo.setUserNo(user_no1);
				vo.setBookTitle(title);
				

				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;
	}

	public void deleteByUserNoAndBookNo(Long userNo, Long bookNo) {
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from cart where user_no = ? and book_no=?");
			) {
				pstmt.setLong(1, userNo);
				pstmt.setLong(2, bookNo);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		
	}
}
