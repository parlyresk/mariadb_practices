package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
		//1. jdbc driver 로딩
		Connection connection=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url="jdbc:mariadb://192.168.0.212:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url,"webdb","webdb");
			
			System.out.println("success !!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("드라이버 로딩 실패: " + e);
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error: " + e);
			
			
		} finally {
			try {
				if(connection !=null) {
					connection.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

}
