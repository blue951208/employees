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

import model.DeptEmpDao;


@WebServlet("/deptEmp/getDeptEmpJoinList")
public class GetDeptEmpJoinList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//메소드에 리턴값으로 받을 list 생성
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//메소드 호출을 위해 객체 생성
		DeptEmpDao deptEmpDao = new DeptEmpDao();
		list = deptEmpDao.selectDeptEmpJoinList();
		//request에 list를 담는다
		request.setAttribute("list", list);
		//jsp로 forward
		request.getRequestDispatcher("/WEB-INF/views/deptEmp/deptEmpJoinList.jsp").forward(request, response);
	}

}
