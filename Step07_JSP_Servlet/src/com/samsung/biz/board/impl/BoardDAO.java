package com.samsung.biz.board.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.samsung.biz.board.vo.BoardVO;
import com.samsung.biz.utils.JDBCUtil;

public class BoardDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	public void addBoard(BoardVO vo, String today){
		try{
			conn = JDBCUtil.getConnection();
			
			String sql = "";
			if(today.equals("")){
				sql = "insert into board(seq, title, nickname, content, regdate, userid) " +
						"values( (select nvl(max(seq), 0)+1 from board), ?, ?, ?, sysdate, 'guest')";
			}else{
				sql = "insert into board(seq, title, nickname, content, regdate, userid) " +
						"values( (select nvl(max(seq), 0)+1 from board), ?, ?, ?, ?, 'guest')";
			}
			
			stmt = conn.prepareStatement(sql);
			if(today.equals("")){
				stmt.setString(1, vo.getTitle());
				stmt.setString(2, vo.getNickname());
				stmt.setString(3, vo.getContent());
			}else{
				stmt.setString(1, vo.getTitle());
				stmt.setString(2, vo.getNickname());
				stmt.setString(3, vo.getContent());
				
				Date regdate = Date.valueOf(today);
				stmt.setDate(4, regdate);
			}
			
			stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt);
		}
		
		
	}
	
	public void updateBoard(BoardVO vo){
		try{
			Connection conn = JDBCUtil.getConnection();
			String sql = "update board set title=?, content=? where seq=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getSeq());
			
			stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt);
		}
	}
	
	public void deleteBoard(BoardVO vo){
		try{
			Connection conn = JDBCUtil.getConnection();
			String sql = "delete from board where seq = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt);
		}
	}
	
	// ResultSet 값을 적절히 처리해서 넘겨야 한다
	public BoardVO getBoard(BoardVO vo){
		BoardVO board = null;
		try{
			// 디비연결부터 결과값 받아오기
			Connection conn = JDBCUtil.getConnection();
			
			String sql = "select * from board where seq = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getSeq());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setNickname(rs.getString("nickname"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
				board.setUserid(rs.getString("userid"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt, rs);
		}
		return board;
	}
	
	// 모든 ResultSet 값을 적절히 처리해서 넘겨야 한다
	public ArrayList<BoardVO> getBoardList(BoardVO vo){
		
		// 모든 row(BoardV) 객체를 담을 ArrayList
		ArrayList<BoardVO> boardList = null;
		
		try{
			Connection conn = JDBCUtil.getConnection();
			
			String sql = "";
			if(vo.getSearchCondition().equals("TITLE")){
				sql = "select * from board where title like '%' || ? || '%' order by seq desc";
			}else if(vo.getSearchCondition().equals("CONTENT")){
				sql = "select * from board where content like '%' || ? || '%' order by seq desc";
			}
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			//stmt.setString(1, '%' + vo.getSearchKeyword() + '%');
			stmt.setString(1, vo.getSearchKeyword());
			
			ResultSet rs = stmt.executeQuery();
			
			boardList = new ArrayList<>();
			
			BoardVO board = null;
			while(rs.next()){
				board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setNickname(rs.getString("nickname"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
				board.setUserid(rs.getString("userid"));
				boardList.add(board);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt, rs);
		}
		
		return boardList;
	}
}













