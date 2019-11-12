package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DepartmentsDao;
import model.DeptEmpDao;
import model.DeptManagerDao;
import model.EmployeesDao;
import model.SalariesDao;
import model.TitlesDao;

@WebServlet({"/","/index"})//url 처리(maping), index페이지 (HOME 화면) 각테이블에 행에수를 출력
public class IndexServlet extends HttpServlet {
	 //dao객체 선언
	private EmployeesDao employeesDao;
	private DepartmentsDao departmentsDao;
	private TitlesDao titlesDao;
	private SalariesDao salariesDao;
	private DeptEmpDao deptEmpDao;
	private DeptManagerDao deptManagerDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//로그인 상태X,session값이 없을때
		if(session.getAttribute("sessionEmpNo")==null) {
		response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		//new 객체 생성 
		employeesDao = new EmployeesDao();
		departmentsDao = new DepartmentsDao();
		titlesDao = new TitlesDao();
		salariesDao = new SalariesDao();
		deptEmpDao = new DeptEmpDao();
		deptManagerDao = new DeptManagerDao();
		//method에 return 값을 변수에 복사
			int employeesRowCount = employeesDao.selectEmployeesCount();
			int minEmpNo = employeesDao.selectEmpNo("min");
			int maxEmpNo = employeesDao.selectEmpNo("max");
			int departmentsRowCount = departmentsDao.selectDepartmentsCount();
			int titlesRowCount = titlesDao.selectTitlesCount();
			int salariesRowCount = salariesDao.selectSalariesCount();
			int deptEmpRowCount = deptEmpDao.selectDeptEmpCount();
			int deptManagerRowCount = deptManagerDao.selectDeptManagerCount();
		
		//request Attribute안에 값을 담아 보냄
		request.setAttribute("minEmpNo", minEmpNo);
		request.setAttribute("maxEmpNo", maxEmpNo);
		request.setAttribute("employeesRowCount", employeesRowCount);
		request.setAttribute("departmentsRowCount", departmentsRowCount);
		request.setAttribute("titlesRowCount", titlesRowCount);
		request.setAttribute("salariesRowCount", salariesRowCount);
		request.setAttribute("deptEmpRowCount", deptEmpRowCount);
		request.setAttribute("deptManagerRowCount", deptManagerRowCount);
		
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		//index.jsp로 포워딩
	}

}
