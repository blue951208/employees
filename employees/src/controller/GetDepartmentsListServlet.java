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

import model.DepartmentsDao;
import vo.Departments;

/**
 * Servlet implementation class GetDepartmentsListServlet
 */
@WebServlet("/departments/getDepartmentsList")//url지정
public class GetDepartmentsListServlet extends HttpServlet {
	DepartmentsDao departmentsDao = null;
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		HttpSession session = request.getSession();
		//로그인 상태X,session값이 없을때
		if(session.getAttribute("sessionEmpNo")==null) {
		response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
 		departmentsDao = new DepartmentsDao();
 		//list 객체 생성
 		List<Departments> list = new ArrayList<Departments>();
 		//list에 departmentsDao 메소드에 리턴값인 list를 복사
 		list = departmentsDao.selectDepartmentsDao();
 		//request에 값을 저장
 		request.setAttribute("list", list);
 		//jsp에 forward
 		request.getRequestDispatcher("/WEB-INF/views/departments/departmentsList.jsp").forward(request, response);
 	}

}
