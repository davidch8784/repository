package com.samsung.biz.board;

import java.util.ArrayList;

import com.samsung.biz.board.impl.BoardDAO;
import com.samsung.biz.board.vo.BoardVO;

public class GetBoardListTest {
	public static void main(String[] args) {
		BoardVO vo = new BoardVO();
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		
		BoardDAO dao = new BoardDAO();
		
		
		ArrayList<BoardVO> boardList = dao.getBoardList(vo);
		
		for (BoardVO board : boardList) {
			System.out.println(board);
		}
	}
}
