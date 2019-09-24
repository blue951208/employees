package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeesDao;


@WebServlet("/employees/getEmployeesCountByGender")
public class GetEmployeesCountByGender extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//메소드에 리턴값을 받기 위한 list생성
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//메소드를 쓰기 위해 객체 생성
		EmployeesDao employeesDao = new EmployeesDao();
		//list에 method에 return값을 복사
				list = employeesDao.selectEmployeesCountGroupByGender();
		//request에 list를 담는다
		request.setAttribute("list",list);
		//jsp로 forward
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesCountByGender.jsp").forward(request, response);
	}

}
