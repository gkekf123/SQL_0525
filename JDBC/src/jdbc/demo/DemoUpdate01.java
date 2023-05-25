package jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DemoUpdate01 {

	public static void main(String[] args) {

		// 부서아이디, 부서이름을 입력받아서 해당부서의 이름을 변경하는 update 구문
		
		Scanner sc = new Scanner(System.in);
		System.out.print("부서 아이디");
		String id = sc.nextLine();
		
		System.out.print("부서 이름");
		String name = sc.nextLine();
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "HR";
		String upw = "hr";
		String sql = "UPDATE depts SET department_name = ? WHERE department_id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, uid, upw);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			
			
			int result = pstmt.executeUpdate();
			System.out.println(result);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				conn.close();
				pstmt.close();
				
			} catch (Exception e2) {
				
			}
		}
		
	}

}
