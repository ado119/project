package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.AgoraVO;

public class AgoraDAO {
	private Connection connectDB() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("드라이버로딩오류");
		}
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java2", "java2");

		return conn;
	}

	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
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
			System.out.println("SQL close 오류");
		}
	}
	public int paging(){
		int cnt;
		int page_cnt = 1;		
		try(Connection conn = connectDB(); 
				Statement stmt=conn.createStatement();
				ResultSet rs = stmt.executeQuery("select count(*) as page_num from agora");){	
			if (rs.next()) {
				//do {
					AgoraVO vo = new AgoraVO();
					vo.setPage_cnt(rs.getInt("page_num"));
					cnt = vo.getPage_cnt();
					System.out.println(cnt + "위치1");
					if(cnt%10==0) {
						page_cnt = cnt/10;
					}else {
						page_cnt = cnt/10+1;
					}
					System.out.println(page_cnt + "진짜 제발");
			}close(conn, stmt, rs);
		}catch(SQLException sqle) { 
			sqle.printStackTrace();
			System.out.println("SQL page_cnt오류"); 
		}
		
		return page_cnt;
	}
	// 쓴 글 다 보여주는 함수
	public ArrayList<AgoraVO> listAll(int page) {

		int cnt;
		int page_cnt;		
		int page_start = 1+((page-1) * 10);
		int page_end = page * 10;
		ArrayList<AgoraVO> list = new ArrayList<>();

		try(Connection conn = connectDB(); 
				Statement stmt=conn.createStatement();
				ResultSet rs = stmt.executeQuery("select count(*) as page_num from agora");){	
			if (rs.next()) {
					AgoraVO vo = new AgoraVO();
					vo.setPage_cnt(rs.getInt("page_num"));
					cnt = vo.getPage_cnt();
					if(cnt%10==0) {
						page_cnt = cnt/10;
					}else {
						page_cnt = cnt/10+1;
					}
					System.out.println(page_cnt + "위치2");
					vo.setPage_cnt(page_cnt);
			}
	
			close(conn, stmt, rs);
		}catch(SQLException sqle) { 
			sqle.printStackTrace();
			System.out.println("SQL page_cnt오류"); 
		}

		try (Connection conn = connectDB();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select agora_num, title, id, to_char(writedate,'rr.mm.dd hh:mi') as writedate, cnt from( select rownum r , imsi.* from(select * from agora order by agora_num desc) imsi) where r >='"+ page_start +"' and r<= '"+page_end +"'");) {
			if (rs.next()) {
				do {
					AgoraVO vo = new AgoraVO();
					vo.setAgora_num(rs.getInt("agora_num"));
					vo.setTitle(rs.getString("title"));
					vo.setId(rs.getString("id"));
					vo.setWritedate(rs.getString("writedate"));
					vo.setCnt(rs.getInt("cnt"));
					list.add(vo);
				} while (rs.next());
			} else {
				System.out.println("추출된 행이 없습니다!!!");
			}
			close(conn, stmt, rs);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("SQL listAll() 오류");
		}
		return list;
	}

	// insert함수!
	public boolean insert(AgoraVO vo) {

		boolean result = true;

		try (Connection conn = connectDB();
				PreparedStatement pstmt = conn.prepareStatement("INSERT into agora values(?,agora_seq.nextval,?,?,sysdate,0)");) {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
			close(conn, pstmt, null);
		} catch (SQLException sqle) {
			result = false;
			sqle.printStackTrace();
			System.out.println("SQL insert오류");
		}
		return result;
	}

	// 글 삭제하는 함수
	public boolean delete(int agora_num) {
		boolean result = true;
		try (Connection conn = connectDB();
				PreparedStatement pstmt = conn.prepareStatement("Delete from agora where agora_num=?");) {
			pstmt.setInt(1, agora_num);
			pstmt.executeUpdate();
			close(conn, pstmt, null);
		} catch (SQLException sqle) {
			result = false;
			sqle.printStackTrace();
			System.out.println("SQL delete() 오류");
		}
		return result;
	}

	// 리스트 선택하여 하나 아래 보여주기
	public AgoraVO listOne(int agora_num) {

		AgoraVO vo = new AgoraVO();

		try (Connection conn = connectDB();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT title, id, writedate, cnt, content FROM agora where agora_num = '" + agora_num + "'");) {
			if (rs.next()) {
				do {
					vo.setAgora_num(agora_num);
					vo.setTitle(rs.getString("title"));
					vo.setId(rs.getString("id"));
					vo.setWritedate(rs.getString("writedate"));
					vo.setCnt(rs.getInt("cnt"));
					vo.setContent(rs.getString("content"));
				} while (rs.next());
			}

			close(conn, stmt, rs);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("SQL listOne 오류");
		}


		try(Connection conn = connectDB(); 
				Statement stmt=conn.createStatement();
				ResultSet rs = stmt.executeQuery("Update agora set cnt = cnt+1 where agora_num = '" + agora_num + "'");){
			vo.setCnt(rs.getInt("cnt")); 
			close(conn, stmt, rs);
		}catch(SQLException sqle) { 
			sqle.printStackTrace();
			System.out.println("SQL listOne 조회수 오류"); 
		}
		return vo;
	}
}
