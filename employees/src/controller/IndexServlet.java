package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeesDao;

@WebServlet("/index")//url 처리(maping)
public class IndexServlet extends HttpServlet {
	private EmployeesDao employeesDao; //dao객체 선언
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/index URL 요령");
		//model 호출
		this.employeesDao = new EmployeesDao();
		int employeesRowCount = employeesDao.selectEmployeesCount();
		//WEB-INF/views/index.jsp
		//RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/index.jsp");
		//rd.forward.(request,response); 
		//request Attribute안에 값을 담아 보냄
		request.setAttribute("employeesRowCount", employeesRowCount);
		
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		//index.jsp로 포워딩
	}

}
