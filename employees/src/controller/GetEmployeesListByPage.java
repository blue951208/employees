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

@WebServlet("/employees/getEmployeesListByPage")//페이징 적용,직원 리스트
public class GetEmployeesListByPage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//로그인 상태X,session값이 없을때
		if(session.getAttribute("sessionEmpNo")==null) {
			//session에서 sessionEmpNo키워드를 가진 변수에 값을 출력 했을때 null일 경우 
		response.sendRedirect(request.getContextPath()+"/login");//login페이지로 이동
			return;
		}
		//jsp에 넘길 list를 받기위해 list생성
		List<Employees> list = new ArrayList<Employees>();
		//jsp에서 넘긴 currentPage값을 받는다
		int currentPage = 1;
		int rowPerPage =10;
		int rowPerIndex = 10;
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
