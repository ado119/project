package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.AgoraDAO;
import model.dao.CommDAO;
import model.vo.AgoraVO;
import model.vo.CommVO;


@WebServlet("/agora")
public class AgoraServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AgoraDAO dao = new AgoraDAO();
		CommDAO comm_dao = new CommDAO();
		String link = null;
		String agora_nums = request.getParameter("agora_num");
		String action = request.getParameter("action");
		String page_1 = request.getParameter("page_n");
		
		if(page_1 != null) {
			int page_now = Integer.parseInt(page_1);
			System.out.println("지금은 " + page_1 + "입니다.");
			ArrayList<AgoraVO> list = dao.listAll(page_now);
			int paging = dao.paging();
			System.out.println(paging + "진짜진짜 1");
			if(list.size() != 0) {
				request.setAttribute("list", list);
				request.setAttribute("paging", paging);
			}
			link = "/codingBooster/jsp/agora_main.jsp";
		}
		
		
		System.out.println("doget에 들어왔어요!");
		if(agora_nums != null && action.equals("showcontent")) {
			System.out.println("showcontent에 들어왔어요!");
			int agora_numi = Integer.parseInt(agora_nums);
			AgoraVO vo = dao.listOne(agora_numi);
			ArrayList<CommVO> comm_list = comm_dao.listAll_comm(agora_numi);
			if(comm_list.size() != 0) {
				request.setAttribute("comm_vo", comm_list);
			}
			request.setAttribute("vo", vo);
			link = "/codingBooster/jsp/agora_showcontent.jsp"; 
		}else if(agora_nums != null && action.equals("delete")){
			System.out.println("delete 실행문 들어옴");
			int agora_numi = Integer.parseInt(agora_nums);
			boolean result = dao.delete(agora_numi);
			if(result) {
				ArrayList<AgoraVO> list = dao.listAll(1);
				int paging = dao.paging();
				if(list.size() != 0) {
					request.setAttribute("list", list);
					request.setAttribute("paging", paging);
				}
				link ="/codingBooster/jsp/agora_main.jsp";
				System.out.println("성공");
			}else {
				System.out.println("실패 ㅠㅠ");
			}
			link = "/codingBooster/jsp/agora_main.jsp";
		}else {
			System.out.println("main에 들어왔어요!");
			int page_now;
			if(page_1==null) {
				 page_now =1;
			}else {
				page_now = Integer.parseInt(page_1);
			}
			ArrayList<AgoraVO> list = dao.listAll(page_now);
			int paging = dao.paging();
			System.out.println(paging + "진짜진짜 2");
			if(list.size() != 0) {
				request.setAttribute("list", list);
				request.setAttribute("paging", paging);
			}
			link = "/codingBooster/jsp/agora_main.jsp";
		}
		request.getRequestDispatcher(link).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		request.setCharacterEncoding("UTF-8");
		String link = null;
		//String agora_nums = request.getParameter("agora_num");
		//String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String loginUser = (String)session.getAttribute("loginUser"); //ID session 통해 받기
		String comm = request.getParameter("comm");
		String comment_sc = request.getParameter("comment_sc");
		String agora_selects = request.getParameter("agora_select");
		AgoraVO vo = new AgoraVO();
		CommVO vo2 = new CommVO();
		
		vo.setId(loginUser);
		vo.setTitle(request.getParameter("title_wr"));
		vo.setContent(request.getParameter("content_wr"));
		
		
		vo2.setComm(request.getParameter("comm"));
		AgoraDAO dao = new AgoraDAO();
		CommDAO dao2 = new CommDAO();
		if(comm.equals("comment")) {
			int agora_selecti = Integer.parseInt(agora_selects);
			vo2.setAgora_num(agora_selecti);
			vo2.setId(loginUser);
			vo2.setComm(comment_sc);
			link = "/codingBooster/jsp/login_error.jsp";
			boolean result = dao2.insert(vo2);
			if(result) {
				vo = dao.listOne(agora_selecti);
				ArrayList<CommVO> comm_list = dao2.listAll_comm(agora_selecti);
				if(comm_list.size() != 0) {
					request.setAttribute("comm_vo", comm_list);
				}
				request.setAttribute("vo", vo);
				link = "/codingBooster/jsp/agora_showcontent.jsp";
			}
		}else {
			boolean result = dao.insert(vo);
			if(result) {
				ArrayList<AgoraVO> list = dao.listAll(1);
				int paging = dao.paging();
				if(list.size() != 0) {
					request.setAttribute("list", list);
					request.setAttribute("paging", paging);
				}
				link ="/codingBooster/jsp/agora_main.jsp";
				System.out.println("성공");
			}else {
				System.out.println("실패 ㅠㅠ");
			}
		}
		request.getRequestDispatcher(link).forward(request, response);
	}


}
