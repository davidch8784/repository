package com.samsung.view.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samsung.biz.board.impl.BoardDAO;
import com.samsung.biz.board.vo.BoardVO;

public class AddBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");
		String nickname = request.getParameter("nickname");
		String content = request.getParameter("content");
		
		String today = "";
		if(request.getParameter("regdate") != null){
			today = request.getParameter("regdate");
		}

		// DAO에서 처리할 로직
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setNickname(nickname);
		vo.setContent(content);
		
		BoardDAO dao = new BoardDAO();
		dao.addBoard(vo, today);
		
		response.sendRedirect("getBoardList");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("EUC-KR");
		doGet(request, response);
	}

}
