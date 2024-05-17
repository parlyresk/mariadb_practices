package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriverTest {

	public static void main(String[] args) {
		
		Connection connection=null;
		try {
			//1. jdbc driver 로딩
			Class.forName("driver.MyDriver");
			
			//2. 연결하기
			String url="jdbc:mariadb://127.0.0.1:2202/webdb";
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
