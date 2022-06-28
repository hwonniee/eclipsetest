package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.board.vo.BoardVO;

import util.DBManager;

public class BoardDAO {

	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	// 게시글 전체 리스트 조회
	public List<BoardVO> selectAllBoards() {
		String sql = "SELECT * FROM BOARD ORDER BY NUM DESC";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				
				list.add(bVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}
	
	// 게시판 글 상세 내용 보기 : 글번호로 찾아온다. : 실패 null
	public BoardVO selectOneBoardByNum(String num) {
		String sql = "SELECT * FROM BOARD WHERE NUM = ?";
		
		BoardVO bVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bVo = new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setPass(rs.getString("pass"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				bVo.setReadcount(rs.getInt("readcount"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return bVo;
	}
	
	// 게시글 보면 조회수 1증가
	public void updateReadCount(String num) {
		String sql = "UPDATE BOARD SET READCOUNT = READCOUNT + 1 WHERE NUM = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 게시글 등록
	public void insertBoard(BoardVO bVo) {
		String sql = "INSERT INTO BOARD(NAME, EMAIL, PASS, TITLE, CONTENT) " +
				"VALUES(?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 게시글 수정
	public void updateBoard(BoardVO bVo) {
		String sql = "UPDATE BOARD SET NAME = ?, EMAIL = ?, PASS = ?, "
						+ "TITLE = ?, CONTENT = ? WHERE NUM = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.setInt(6, bVo.getNum());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 게시글 비밀번호 check 
	public BoardVO checkPassWord(String pass, String num) {
		String sql = "SELECT * FROM BOARD WHERE PASS = ? AND NUM = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO bVo = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pass);
			pstmt.setString(2, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bVo = new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return bVo;
	}
	
	// 게시물 삭제
	public void deleteBoard(String num) {
		String sql = "DELETE FROM BOARD WHERE NUM = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 전체 게시글 수
	public int selectAllBoardCount(String searchType, String searchText) {
		//String sql = "SELECT COUNT(*) FROM BOARD";
		String sql = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int boardCnt = 0;
		
		try {
			conn = DBManager.getConnection();
			
			if(searchText != null && searchText.length() > 0) {
				if(searchType.equals("all")) {		// 제목+내용
					sql = "SELECT COUNT(*) FROM BOARD WHERE TITLE LIKE ? OR CONTENT LIKE ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + searchText + "%");
					pstmt.setString(2, "%" + searchText + "%");
				} else {
					switch(searchType) {
						case "title":		// 제목
							sql = "SELECT COUNT(*) FROM BOARD WHERE TITLE LIKE ?";
							break;						
						case "content":		// 내용
							sql = "SELECT COUNT(*) FROM BOARD WHERE CONTENT LIKE ?";
							break;
						case "author":		// 작성자
							sql = "SELECT COUNT(*) FROM BOARD WHERE NAME LIKE ?";
							break;
					}
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + searchText + "%");
				}
			} else {
				sql = "SELECT COUNT(*) FROM BOARD";
				pstmt = conn.prepareStatement(sql);
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardCnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return boardCnt;
	}
	
	// 게시글 리스트 조회(페이징)
	public List<BoardVO> selectBoardsPaging(int offset, int pageSize, String searchType, String searchText) {
		//String sql = "SELECT * FROM BOARD ORDER BY NUM DESC LIMIT ?, ?";
		String sql = "";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			if(searchText != null && searchText.length() > 0) {
				if(searchType.equals("all")) {
					sql = "SELECT * FROM BOARD WHERE TITLE LIKE ? OR CONTENT LIKE ? "
							+ "ORDER BY NUM DESC LIMIT ?, ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + searchText + "%");
					pstmt.setString(2, "%" + searchText + "%");
					pstmt.setInt(3, offset);
					pstmt.setInt(4, pageSize);
				} else {
					switch(searchType) {
						case "title":
							sql = "SELECT * FROM BOARD WHERE TITLE LIKE ?  "
									+ "ORDER BY NUM DESC LIMIT ?, ?";
							break;
						case "content":
							sql = "SELECT * FROM BOARD WHERE CONTENT LIKE ?  "
									+ "ORDER BY NUM DESC LIMIT ?, ?";
							break;
						case "author":
							sql = "SELECT * FROM BOARD WHERE NAME LIKE ?  "
									+ "ORDER BY NUM DESC LIMIT ?, ?";
							break;
					}
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + searchText + "%");
					pstmt.setInt(2, offset);
					pstmt.setInt(3, pageSize);
				}
			} else {
				sql = "SELECT * FROM BOARD ORDER BY NUM DESC LIMIT ?, ?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, offset);
				pstmt.setInt(2, pageSize);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				
				list.add(bVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}






