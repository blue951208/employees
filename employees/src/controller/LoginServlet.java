package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeesDao;
import vo.Employees;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	request.getRequestDispatcher("/WEB-INF/views/login.jsp");
	}
	//login action 
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			int empNo = Integer.parseInt(request.getParameter("empNo"));
			System.out.println("디버깅:>>"+firstName+" ,"+lastName+", "+empNo);
			//디버깅(단위 테스트)
			Employees employees = new Employees();
			employees.setFirstName(firstName);
			employees.setLastName(lastName);
			employees.setEmpNo(empNo);
			EmployeesDao employeesDao = new EmployeesDao();
			int sessionEmpNo = 0;
			HttpSession session = request.getSession();
				if(employeesDao.login(employees)==0) {
					System.out.println("LOGIN 실패 다시입력");
					response.sendRedirect(request.getContextPath()+"/login");
				}else {
					sessionEmpNo=employeesDao.login(employees);
					session.setAttribute("sessionEmpNo", sessionEmpNo);
					request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
				}
			System.out.println("doPost>"+employees);
	}

}
