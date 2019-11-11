package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeesDao;
import vo.Employees;


@WebServlet("/employees/getEmployeesListBetween")//Servlet url지정, 
//index 페이지에서 원하는 범위를 입력 후 그 범위내에 직원 리스트를 출력
public class GetEmployeesListBetweenServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();//session 생성
		//로그인 상태X,session값이 없을때
		if(session.getAttribute("sessionEmpNo")==null) {
		response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		//min &  max값을 받아온다
		int begin = Integer.parseInt(request.getParameter("begin"));
		int end = Integer.parseInt(request.getParameter("end"));
		//메소드에 리턴값을 받기위한 list생성
		List<Employees> list = new ArrayList<Employees>();
		//method사용을 위한 객체 생성
		EmployeesDao employeesDao = new EmployeesDao();
		list = employeesDao.selectEmployeesListBetween(begin, end);
		//request에 list를 담는다
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesListBetween.jsp").forward(request, response);
	}

}
