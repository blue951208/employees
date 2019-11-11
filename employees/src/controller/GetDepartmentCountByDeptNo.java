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
import javax.servlet.http.HttpSession;

import model.DepartmentsDao;

@WebServlet("/departments/getDepartmentCountByDeptNo")
//각 부서별 부서이름과 직원수를 출력
public class GetDepartmentCountByDeptNo extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//로그인 상태X,session값이 없을때
		if(session.getAttribute("sessionEmpNo")==null) {
		response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		//method 호출을 위한 객체 생성
		DepartmentsDao departmentsDao = new DepartmentsDao();
		//list에 return값을 복사하기 위해 생성
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = departmentsDao.selectDepartmentsCountByDeptNo();
		//request 에 값을 담는다
		request.setAttribute("list", list);
		//jsp로 forward
		request.getRequestDispatcher("/WEB-INF/views/departments/departmentsCountByDeptNo.jsp").forward(request, response);
	}

}
