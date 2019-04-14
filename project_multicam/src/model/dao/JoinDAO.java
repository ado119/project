package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.vo.JoinVO;

public class JoinDAO {
	private Connection connectDB() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");// 드라이버로딩
		} catch (ClassNotFoundException cnfe) {
			System.out.println("드라이버 로딩 오류");
		}
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java2", "java2");
		System.out.println("3번 경우");
		return conn;
	}
	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			System.out.println("4번 경우");
			if (conn != null) {
				conn.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("SQL 오류");
		}
	}
	public boolean insert(JoinVO vo) { //회원가입

		boolean result = true;

		try (Connection conn = connectDB();
				PreparedStatement pstmt = conn
						.prepareStatement("INSERT into JOIN values(?,?,?,?)");) {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getName());
			pstmt.executeUpdate();
			close(conn, pstmt, null);
			System.out.println("테스트 성공");
		} catch (SQLException sqle) {
			result = false;
			sqle.printStackTrace();
			System.out.println("SQL 오류");
		}
		return result;
	}

	public int login(String id, String pass) { //로그인 기능
		int result = -1;
		try (Connection conn = connectDB();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT password from JOIN where id='" + id +"'");){
			System.out.println("2번 경우");
			if(rs.next()){
				if(rs.getString("password") != null && rs.getString("password").equals(pass)) {
					result = 1; // 정상 로그인s
				}else {
					result = 0; //비밀번호 틀린 경우
				}
			}
			close(conn,stmt,rs);
		}catch(Exception e) {
			System.out.println("에러: " + e);
			e.printStackTrace();
		}
		return result;
	}
	
	// 수정하는 함수!
	public boolean update(JoinVO vo) {
		boolean result = true;
		try (Connection conn = connectDB();
				PreparedStatement pstmt = conn
	                  .prepareStatement("Update JOIN set PASSWORD = ?, EMAIL = ?, NAME = ? where id = ?");) {
	         pstmt.setString(1, vo.getPassword());
	         pstmt.setString(2, vo.getEmail());
	         pstmt.setString(3, vo.getName());
	         pstmt.setString(4, vo.getId());
	         pstmt.executeUpdate();
	         close(conn, pstmt, null);
	      } catch (SQLException sqle) {
	         result = false;
	         sqle.printStackTrace();
	         System.out.println("SQL 오류");
	      }

	      return result;
	   }

}
