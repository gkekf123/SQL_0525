package jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DemoInsert01 {

	public static void main(String[] args) {

		// 입력 될 값은 콘솔로 입력
		Scanner sc = new Scanner(System.in);
		System.out.println("부서 이름 > ");
		String name = sc.nextLine();
		
		System.out.println("매니저 아이디 >");
		String manager_id = sc.nextLine();
		
		System.out.println("로케이션 아이디 >");
		String location_id = sc.nextLine();
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "HR";
		String upw = "hr";
		String sql = "INSERT INTO depts (department_id, department_name, manager_id, location_id)"
				+ 				 "VALUES(depts_seq.nextval, ?, ?, ?)";
		
		//INSERT, UPDATE, DELETE는 ResultSet객체가 필요없음
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, uid, upw);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  name); // 첫번째 물음표, name
			pstmt.setInt(2, Integer.parseInt(manager_id));	// 정수로 변형
			pstmt.setString(3, location_id); // 자동 형변환
			
			int result = pstmt.executeUpdate();
			System.out.println("성공 실패? : " + result);
			
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
