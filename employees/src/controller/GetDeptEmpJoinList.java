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
import model.DeptEmpDao;
import vo.Departments;


@WebServlet("/deptEmp/getDeptEmpJoinList")
public class GetDeptEmpJoinList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//로그인 상태X,session값이 없을때
		if(session.getAttribute("sessionEmpNo")==null) {
		response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		//------------------------------부서목록-------------------------------------------
		DepartmentsDao departmentsDao = new DepartmentsDao();
 		String paraDeptName = "Customer Service";
 			if(request.getParameter("deptName")!=null) {
 				paraDeptName = request.getParameter("deptName");
 				System.out.println("para DeptName :"+paraDeptName);
 			}
 		//부서 list 객체 생성
 		List<Departments> dept = new ArrayList<Departments>();
 		//list에 departmentsDao 메소드에 리턴값인 list를 복사
 		dept = departmentsDao.selectDepartmentsDao();
 		System.out.println("dept :"+dept);
		//-------------------------직원목록-----------------------------------------------
 	
		//메소드에 리턴값으로 받을 list 생성
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//메소드 호출을 위해 객체 생성
		DeptEmpDao deptEmpDao = new DeptEmpDao();
		int rowPerPage=10;
		int currentPage=1;
			if(request.getParameter("currentPage")!=null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
				System.out.println("Servlet currentPage:>>"+currentPage);
			}
		//jsp로 넘길 lastPage 저장
		int lastPage = deptEmpDao.selectLastPage(rowPerPage);
		list = deptEmpDao.selectDeptEmpJoinList(rowPerPage,currentPage);
		System.out.println("lastPage:>>"+lastPage);
		System.out.println("list:>>"+list);
		//request에 list,lastPage를 담는다
		//request에 dept값을 저장
 		request.setAttribute("dept", dept);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("list", list);
		//jsp로 forward
		request.getRequestDispatcher("/WEB-INF/views/deptEmp/deptEmpJoinList.jsp").forward(request, response);
	}

}
