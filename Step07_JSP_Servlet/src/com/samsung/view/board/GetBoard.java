package com.samsung.view.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.samsung.biz.board.impl.BoardDAO;
import com.samsung.biz.board.vo.BoardVO;

public class GetBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id == null){
			response.sendRedirect("login.jsp");
			return;
		}
		
		
		
		int seq = Integer.parseInt(request.getParameter("seq"));

		// DAO에서 처리
		BoardVO vo = new BoardVO();
		vo.setSeq(seq);
		
		BoardDAO dao = new BoardDAO();
		BoardVO board = dao.getBoard(vo);
		
		// request, session, context 중에
		// view 페이지로의 전달은 request에 담는다.
		// 이때 모든 객체를 Object 타입으로 캐스팅한다.
		// Object board = board;
		request.setAttribute("board", board);
		
		// 어느 페이지로 이동 할 것인지를 지정.
		RequestDispatcher view = 
				request.getRequestDispatcher("getBoard.jsp");
		
		// forword로 이동하면서 현재 사용중인
		// request와 response 객체를 전달한다.
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		doGet(request, response);
	}

}
