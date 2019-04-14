package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.JoinDAO;
import model.vo.JoinVO;

@WebServlet("/pro_join")
public class JoinServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String member = request.getParameter("member"); 
		if(member.equals("logout")) { //로그아웃 기능
			HttpSession session = request.getSession();
			session.invalidate(); //session 전체 삭제!
			response.sendRedirect("/mvc/codingBooster/index.jsp");
		}else if(member.equals("update")) { //회원 정보 수정
			RequestDispatcher rd = request.getRequestDispatcher("/codingBooster/jsp/pro_modify.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/codingBooster/jsp/login_error.jsp");
			rd.forward(request, response);			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		JoinVO vo = new JoinVO();
		JoinDAO dao = new JoinDAO();
		String member = request.getParameter("member");
		String id = request.getParameter("join_id");
		String password = request.getParameter("join_pass");
		String email = request.getParameter("join_email");
		String name = request.getParameter("join_name");
		
		vo.setId(id);
		vo.setPassword(password);
		vo.setEmail(email);
		vo.setName(name);
		if(member.equals("join")) { //회원가입 기능
			boolean result = dao.insert(vo);
			if(result) {
				request.setAttribute("msg", "성공");
				System.out.println("성공");
			}
			else {
				request.setAttribute("msg", "실패");
				System.out.println("실패");
			}
			request.getRequestDispatcher("/codingBooster/jsp/pro_login.jsp").forward(request, response);
		}
		
		if(member.equals("login")) { //로그인 기능
			int login = dao.login(id, password);
			String url ="/codingBooster/jsp/login_error.jsp";
			if(login == 1) {
				url = "/mvc/codingBooster/index.jsp";
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", id); //session 아이디 저장
				response.sendRedirect(url);
			}else if(login == 0) {
				request.setAttribute("message", "비밀번호 에러");
				request.getRequestDispatcher(url).forward(request, response);
			}else if(login == -1) {
				request.setAttribute("message", "존재하지 않는 회원");
				request.getRequestDispatcher(url).forward(request, response);
			}else {
				System.out.println("뭐지?");
				request.setAttribute("message", "왜 안될까?");
				request.getRequestDispatcher(url).forward(request, response);
				
			}
			
		}
		
		if(member.equals("modify")) {
			boolean result = dao.update(vo);
			if(result) {
				request.setAttribute("msg", "수정성공");
				System.out.println("성공");
			}
			else {
				request.setAttribute("msg", "수정 실패");
				System.out.println("실패");
			}
			String url = "/mvc/codingBooster/index.jsp";
			response.sendRedirect(url);
		}
		
	}

}
