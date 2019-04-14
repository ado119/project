package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.R.RSurveyRe;

@WebServlet("/survey")
public class SurveyServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String link;
		String kind = request.getParameter("kind");
		String budgets = request.getParameter("budget");
		String spaces = request.getParameter("space");
		int budgeti = Integer.parseInt(budgets);
		int spacei = Integer.parseInt(spaces);
		int asset = budgeti/spacei*10000;
		HttpSession session = request.getSession();
		
		if(session.getAttribute("cnt")==null) {
			session.setAttribute("cnt", new int[1]);
		}
		
		RSurveyRe survey_re = new RSurveyRe();
		
		System.out.print("전달된 예산 : " + budgeti);
		System.out.println(", 전달된 평수 : " + spacei);
		
		
		if( asset < 70912) {
			link = "/codingBooster/jsp/survey_fail.jsp";

		}else {
			link = "/codingBooster/jsp/survey_success.jsp";
			ArrayList<String> survey_list = survey_re.survey_re(kind, asset);
			if(survey_list.size() != 0) {
				request.setAttribute("survey_list", survey_list);
			}
			request.setAttribute("kind", kind);
			request.setAttribute("budgets", budgets);
			request.setAttribute("spaces", spaces);	
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(link);
		rd.forward(request, response);
	}
	
}
