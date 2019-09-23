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

/**
 * Servlet implementation class GetEmployeesListServlet
 */
@WebServlet("/employees/getEmployeesListServlet")
public class GetEmployeesListServlet extends HttpServlet {
		EmployeesDao employeesDao = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//객체생성\
		employeesDao = new EmployeesDao();
			int limit = 10;
				if(request.getParameter("limit")!=null) {
					limit = Integer.parseInt(request.getParameter("limit"));
				}System.out.println(limit);
			//list에 리턴값을 저장
			List<Employees> list = employeesDao.selectEmployeesListByLimit(limit);
			//request에 값을 저장
			request.setAttribute("list",list);
			//jsp로 forward
			request.getRequestDispatcher("/WEB-INF/views/employees/employeesList.jsp");
	}

}
