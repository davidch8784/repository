package com.samsung.view.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.samsung.biz.board.impl.BoardDAO;
import com.samsung.biz.board.vo.BoardVO;

public class GetBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 안했으면 로그인 하고 오시와여...
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id == null){
			response.sendRedirect("login.jsp");
			return;
		}
		
		// 매개변수값이 안 넘어왔을때를 대비해서 처리
		// 검색을 하지 않으면 넘어오는 값이 없다.
		String searchCondition = "";
		if(request.getParameter("searchCondition") == null){
			searchCondition = "TITLE";
		}else{
			searchCondition = request.getParameter("searchCondition");
		}
		
		String searchKeyword = "";
		if(request.getParameter("searchKeyword") != null){
			searchKeyword = request.getParameter("searchKeyword");
		}else{
			searchKeyword = "";
		}
		
		// 검색을 하지 않았다면 
		// searchCondition => TITLE
		// searchKeyword => ""
		BoardVO vo = new BoardVO();
		vo.setSearchCondition(searchCondition);
		vo.setSearchKeyword(searchKeyword);
		
		// getBoardList method에 검색 값을 넘긴다.
		// 이때 searchKeyword가 빈 문자열이면 전체를 출력
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> boardList = dao.getBoardList(vo);
		
		request.setAttribute("boardList", boardList);
		RequestDispatcher view = 
				request.getRequestDispatcher("getBoardList.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		doGet(request, response);
	}

}
