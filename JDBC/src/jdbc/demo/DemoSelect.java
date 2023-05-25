package jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

public class DemoSelect {

	public static void main(String[] args) {

		// 데이터베이스 연결 정보
		// "jdbc:oracle:thin:호스트이름:포트번호:DB이름“
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "HR";
		String upw = "hr";
		
		String sql = "SELECT * FROM employees WHERE salary > ?"; // 전달 될 값을 ?로 나타냄
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 모든 데이터베이스 연결은 try~catch 작성이 되야 한다
		try {
			// 1. 드라이버클래스 준비
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. 커넥션 객체 생성
			conn = DriverManager.getConnection(url, uid, upw);
			
			// 3. 커넥션 객체에서 state객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 3-1. SQL문에 전달 할 값이 있다면 ?로 처리하고, 순서대로 1번 부터 시작한다.
			// ?에 전달 될 값은 set메서드를 이용해서 채운다
			// setString, setInt, setDouble, setDate, setTimestramp
			pstmt.setInt(1, 20000);	// (첫번째 물음표, 5000)
			
			// 4. sql문의 실행
			// SELECT는 executeQuery()
			//  UPDATE, INSERT, DELETE 문장은 executeUpdate()
			rs = pstmt.executeQuery();
			
			// 5. rs.next() 다음행이 있으면 다음행으로 접근
			while(rs.next()) { // 한 행에서 처리 할 작업을 while문 안에 넣으면 된다
				
				String first_name = rs.getString("first_name"); // getString, getInt, get Double, getDate, Gettimes
				int salary = rs.getInt("salary");	//정수
				double cpt = rs.getDouble("commission_pct");
				Date date = rs.getDate("hire_date");	//java.sql 패키지
				Timestamp date2 = rs.getTimestamp("hire_date");
				
				System.out.println(first_name);
				System.out.println(salary);
				System.out.println(cpt);
				System.out.println(date);
				System.out.println(date2);

				
				System.out.println("-------------");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				conn.close();
				pstmt.close();
				rs.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}

}
