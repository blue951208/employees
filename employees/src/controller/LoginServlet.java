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

	request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);
	}
	//login action 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//로그인 폼으로 부터 값 받기
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			int empNo = Integer.parseInt(request.getParameter("empNo"));
			System.out.println("디버깅:>>"+firstName+" ,"+lastName+", "+empNo);

			Employees employees = new Employees();
			employees.setFirstName(firstName);
			employees.setLastName(lastName);
			employees.setEmpNo(empNo);
			EmployeesDao employeesDao = new EmployeesDao();
			int sessionEmpNo = 0;
			HttpSession session = request.getSession();
			//입력한 값이 데이터베이스상에 있는지 여부를 확인 없으면 0이,존재하면 1이 리턴된다
				if(employeesDao.login(employees)==0) {
					if(empNo==1) {//test용 설정 emp값만 1이면 로그인 가능하다.
						//리턴값이 0이 아닐경우 session에 직원번호 값을 저장한다.
						sessionEmpNo=employeesDao.login(employees);
						session.setAttribute("sessionEmpNo", sessionEmpNo);
						request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
						return ;
					}
					System.out.println("LOGIN 실패 다시입력");
					response.sendRedirect(request.getContextPath()+"/login");
				}else{
					//리턴값이 0이 아닐경우 session에 직원번호 값을 저장한다.
					sessionEmpNo=employeesDao.login(employees);
					session.setAttribute("sessionEmpNo", sessionEmpNo);
					request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
				}
			System.out.println("doPost>"+employees);
	}

}
