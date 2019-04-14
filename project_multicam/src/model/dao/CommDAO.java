package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.CommVO;

public class CommDAO {
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
         // 댓글 insert함수!
         public boolean insert(CommVO vo) {

            boolean result = true;

            try (Connection conn = connectDB();
                  PreparedStatement pstmt = conn.prepareStatement("INSERT into comm values(?,?,?,sysdate)");) {
               pstmt.setInt(1, vo.getAgora_num());
               pstmt.setString(2, vo.getId());
               pstmt.setString(3, vo.getComm());
               pstmt.executeUpdate();
               close(conn, pstmt, null);
            } catch (SQLException sqle) {
               result = false;
               sqle.printStackTrace();
               System.out.println("SQL insert오류");
            }
            return result;
         }
            
         // comment 다보여주기
         public ArrayList<CommVO> listAll_comm(int agora_num) {

            ArrayList<CommVO> comm_list = new ArrayList<>();

            try (Connection conn = connectDB();
                  Statement stmt = conn.createStatement();
                  ResultSet rs = stmt.executeQuery("SELECT id, comm, to_char(writedate,'rr.mm.dd hh:mi') as writedate FROM comm where agora_num = '" + agora_num + "'");) {
               if (rs.next()) {
                  do {
                     CommVO vo2 = new CommVO();
                     vo2.setId(rs.getString("id"));
                     vo2.setComm(rs.getString("comm"));
                     vo2.setWritedate(rs.getString("writedate"));   
                     comm_list.add(vo2);
                  } while (rs.next());
               }
               close(conn, stmt, rs);

            } catch (SQLException sqle) {
               sqle.printStackTrace();
               System.out.println("SQL listOne 오류");
            }
            return comm_list;
         }
}