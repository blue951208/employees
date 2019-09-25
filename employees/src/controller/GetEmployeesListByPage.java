package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeesDao;
import vo.Employees;

@WebServlet("/employees/getEmployeesListByPage")
public class GetEmployeesListByPage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp에 넘길 list를 받기위해 list생성
		List<Employees> list = new ArrayList<Employees>();
		//jsp에서 넘기 currentPage값을 받는다
		int currentPage = 1;
		int rowPerPage =10;
		//currentPage가 0이 아닐때 currentPage는 받아온 값을 복사한다.
			if(request.getParameter("currentPage")!=null) {
				currentPage=Integer.parseInt(request.getParameter("currentPage"));
			}
			System.out.println("Servlet currentPage:>>"+currentPage);
		//employeesDao에 페이징method를 호출하기 위해+lastPage값을 받아오기 위해서 객체 생성
		EmployeesDao employeesDao = new EmployeesDao();
		int lastPage = employeesDao.selectLastPage(rowPerPage);
		list = employeesDao.selectEmployeeListByPage(currentPage, rowPerPage);
		
		
		//request에 list,lastPage,currentPage를 set
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		//jsp로 forward
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesListByPage.jsp").forward(request, response);;
	}
}
