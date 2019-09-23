package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeesDao;
import vo.Employees;


@WebServlet("/employees/getEmployeesListOrderBy")
public class GetEmployeesListOrderByServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//order 받아오기
		EmployeesDao employeesDao = new EmployeesDao();
		String order = request.getParameter("order");
		//list에 return값을 복사
		List<Employees> list =employeesDao.selectEmployeesListOrderBy(order);
		//request에 list를 담는다
		request.setAttribute("list", list);
		//jsp로 forward
		request.getRequestDispatcher("/WEB-INF/views/emplyoees/employeesListOrderBy.jsp");
	}
}
