package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SalariesDao;


@WebServlet("/salaries/getSalariesStatistics")
public class GetSalariesStatisticsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.에 담을 map을 생성
		Map<String, Long> map = new HashMap<String, Long>(); 
		//selectSalariesStatistics 사용을 위한객체 생성
		SalariesDao salariesDao = new SalariesDao();
		//map에 return값을 복사
		map = salariesDao.selectSalariesStatistics();
		//request에 set
		request.setAttribute("map", map);
		//jsp로 forward
		request.getRequestDispatcher("/WEB-INF/views/salaries/salariesStatistics.jsp").forward(request, response);
	}
}	
