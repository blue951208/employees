package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeesDao;
import vo.Employees;


@WebServlet("/employees/getEmployeesListOrderBy")//오름차순,내림차순 정렬 리스트 출력
public class GetEmployeesListOrderByServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//로그인 상태X,session값이 없을때
		if(session.getAttribute("sessionEmpNo")==null) {
		response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		//dao 객체 생성
		EmployeesDao employeesDao = new EmployeesDao();
		String order = request.getParameter("order");
		//오름차순일때 asc 내림차순일때 desc 
		System.out.println("servlet order:>>"+order);
		//list에 return값을 복사
		List<Employees> list =employeesDao.selectEmployeesListOrderBy(order);
		System.out.println("Servlet list:>>"+list);
		//request에 list를 담는다
		request.setAttribute("list", list);
		//jsp로 forward
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesListOrderBy.jsp").forward(request, response);
	}
}
