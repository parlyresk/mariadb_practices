package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class OrderDao {
	
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

	public void insert(OrderVo vo) {
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt1 = conn.prepareStatement("insert into orders(number,payment,shipping,status,user_no) values(?,?,?,?,?)");
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
			) {
				pstmt1.setString(1, vo.getNumber());
				pstmt1.setInt(2, vo.getPayment());
				pstmt1.setString(3, vo.getShipping());
				pstmt1.setString(4, vo.getStatus());
				pstmt1.setLong(5, vo.getUser_no());
				
				pstmt1.executeUpdate();
				
				ResultSet rs = pstmt2.executeQuery();
				vo.setNo(rs.next() ?  rs.getLong(1) : null);
				rs.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		
	}

	public void insertBook(OrderBookVo vo) {
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt1 = conn.prepareStatement("insert into orders_book(price,quantity,orders_no,book_no) values(?,?,?,?)");
				
			) {
				pstmt1.setInt(1, vo.getPrice());
				pstmt1.setInt(2, vo.getQuantity());
				pstmt1.setLong(3, vo.getOrders_no());
				pstmt1.setLong(4, vo.getBook_no());
				
				pstmt1.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		
	}

	public OrderVo findByNoAndUserNo(long order_no, Long user_no) {
		OrderVo vo = null;

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select no,number,payment,shipping,status,user_no from orders where no=? and user_no=?");
				) {
			
			pstmt.setLong(1, order_no);
			pstmt.setLong(2, user_no);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				vo=new OrderVo();
				Long no=rs.getLong(1);
				String number=rs.getString(2);
				int payment=rs.getInt(3);
				String shipping=rs.getString(4);
				String status=rs.getString(5);
				Long user_no1=rs.getLong(6);
				
				
				
				
				vo.setNo(no);
				vo.setNumber(number);
				vo.setPayment(payment);
				vo.setShipping(shipping);
				vo.setStatus(status);
				vo.setUser_no(user_no1);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return vo;
	}

	public List<OrderBookVo> findBooksByNoAndUserNo(Long order_no, Long user_no) {
		List<OrderBookVo> result = new ArrayList<>();

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select ob.orders_no,ob.quantity,ob.price,ob.book_no,b.title "
						+ "from orders_book ob,book b,orders o "
						+ "where ob.book_no=b.no and ob.orders_no=o.no and ob.orders_no=? and o.user_no=?");
				) {
			
			pstmt.setLong(1, order_no);
			pstmt.setLong(2, user_no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Long orders_no = rs.getLong(1);
				int quantity = rs.getInt(2);
				int price=rs.getInt(3);
				Long book_no = rs.getLong(4);
				String title=rs.getString(5);
				
				OrderBookVo vo=new OrderBookVo();
				vo.setOrders_no(orders_no);
				vo.setQuantity(quantity);
				vo.setPrice(price);
				vo.setBook_no(book_no);
				vo.setBookTitle(title);
				
				

				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return result;
	}

	public void deleteBooksByNo(Long no) {
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from orders_book where orders_no = ?");
			) {
				pstmt.setLong(1, no);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		
	}

	public void deleteByNo(Long no) {
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from orders where no = ?");
			) {
				pstmt.setLong(1, no);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		
	}
}
