package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DBHelper;
import vo.Employees;

public class EmployeesDao {
	//login 메소드 입력한 정보와 데이터베이스에 일치 여부 확인
	public int login(Employees employees) {
		//객체 선언
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		System.out.println("employees Dao:>>>"+employees);
		int sessionEmpNo = 0;
		//입력 받은 emp_no에 first_name,last_name을 가져온다
		String sql = "select first_name,last_name,emp_no from employees where emp_no=?";
			try {
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
					stmt.setInt(1, employees.getEmpNo());
				rs = stmt.executeQuery();
				rs.next();
				//입력받은 값과 데이터베이스에 값과 동일한 경우
					if((employees.getFirstName().equals(rs.getString("first_name")))&&employees.getLastName().equals(rs.getString("last_name"))) {
						sessionEmpNo=employees.getEmpNo();
					}System.out.println("sessionEmpNo dao:>>"+sessionEmpNo);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBHelper.close(rs,stmt,conn);
			}
		return sessionEmpNo;
	}


	//lastIndex 가져오기
	public int selectLastIndex(int rowPerIndex) {
		EmployeesDao employeesDao = new EmployeesDao();
		int lastIndex=employeesDao.selectLastPage(rowPerIndex);
		if(lastIndex%10!=0) {
			lastIndex = lastIndex+1;
		}
		return lastIndex;
	}
	
	//paging 때 lastPage를 가져온다
	public int selectLastPage(int rowPerPage) {
		//전체 행의 수를 구한다
		EmployeesDao employeesDao = new EmployeesDao();
		int endRow = employeesDao.selectEmployeesCount();
		int lastPage=0;
			if(endRow%rowPerPage!=0) {
				lastPage = (endRow/rowPerPage)+1;
			}else {
				lastPage = endRow/rowPerPage;
			}
		return lastPage;
	}
	//paging,10개씩 list를 가져온다
	public List<Employees> selectEmployeeListByPage(int currentPage,int rowPerPage){
		//return할 list생성
		List<Employees> list = new ArrayList<Employees>();
		String sql = "select "
				+"emp_no,gender,birth_date,hire_date,first_name,last_name "
				+"from employees limit ?,?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//오류검사
			try {
		//db접속
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
				int startRow = (currentPage-1)*rowPerPage;
					System.out.println("Dao StartRow"+startRow);
					stmt.setInt(1, startRow);
					stmt.setInt(2, rowPerPage);
				rs = stmt.executeQuery();
				while(rs.next()) {
					//employees객체 생성후 필드값을 set
					Employees employees = new Employees();
					employees.setEmpNo(rs.getInt("emp_no"));
					employees.setBirthDate(rs.getString("birth_date"));
					employees.setHireDate(rs.getString("hire_date"));
					employees.setFirstName(rs.getString("first_name"));
					employees.setLastName(rs.getString("last_name"));
					employees.setGender(rs.getString("gender"));
					//list에 추가
					list.add(employees);
				}
			}catch(Exception e) {
				//error발생시 error내용 출력
				e.printStackTrace();
			}
		return list;
	}
	//max,min을 구한다
	public int selectEmpNo(String str) {
		int empNo=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
			if(str.equals("min")) {
				sql = "select min(emp_no) from employees";
			}else if(str.equals("max")){
				sql = "select max(emp_no) from employees";
			}
			try {
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
					if(rs.next()) {
						empNo = rs.getInt(1);
					}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBHelper.close(rs, stmt, conn);
			}
		return empNo;
	}
	//begin~end사이에 List 를 보여준다
	public List<Employees> selectEmployeesListBetween(int begin,int end) {
		List<Employees> list = new ArrayList<Employees>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//begin~end사이에 list 가져오기
		String sql ="select "
				+"emp_no,last_name,first_name,gender,hire_date,birth_date "
				+"from employees where emp_no "
				+"between ? and ? order by emp_no asc";
			try {
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
					stmt.setInt(1, begin);
					stmt.setInt(2, end);
				rs = stmt.executeQuery();
					while(rs.next()) {//employees에 필드값을 set
						Employees employees = new Employees();
						employees.setEmpNo(rs.getInt("emp_no"));
						employees.setLastName(rs.getString("last_name"));
						employees.setFirstName(rs.getString("first_name"));
						employees.setGender(rs.getString("gender"));
						employees.setHireDate(rs.getString("hire_date"));
						employees.setBirthDate(rs.getString("birth_date"));
						//list에 객체 employees에 저장된 값들을 저장
						list.add(employees);
					}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBHelper.close(rs, stmt, conn);
			}
		return list;
	}
	//gender에 수를 보여준다
	public List<Map<String, Object>> selectEmployeesCountGroupByGender(){
		//return을 위한 list 생성
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			//객체 생성
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			//gender와 gender에 수를 알려주는 쿼리문 작성
			String sql = "select gender,count(gender) cnt from employees group by gender";
				try {
					conn = DBHelper.getConnection();
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
						while(rs.next()) {
							//결과값을 저장할 map생성
							Map<String, Object> map = new HashMap<String, Object>();
							//키값으로 저장
							map.put("gender",rs.getString("gender"));
							map.put("cnt",rs.getInt("cnt"));
							//list에 map을 넣는다
							list.add(map);
						}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					DBHelper.close(rs, stmt, conn);
				}
		return list;
	}
	//order값에 따라 desc,asc로 정렬 method
	public List<Employees> selectEmployeesListOrderBy(String order){
		//확인
		System.out.println("method order>>"+order);
		//list 생성
		List<Employees> list = new ArrayList<Employees>();
		//객체 생성,sql설정
		String sql=null;
		if(order.equals("asc")) {//order가asc일때는 오름차순 
			sql = "select "
					+"emp_no,last_name,first_name,birth_date,hire_date,gender "
					+"from employees order by first_name asc limit 50";
		}else if(order.equals("desc")) {
			sql = "select emp_no,last_name,first_name,birth_date,hire_date,gender from employees order by first_name desc limit 50";
		}System.out.println("sql==>"+sql);
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			//오류검사
			try {//db접속 + 쿼리문 입력,실행
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees","root","java1234");
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
					//employees에 값을 set
					while(rs.next()) {
						Employees employees = new Employees();
						employees.setEmpNo(rs.getInt("emp_no"));
						employees.setFirstName(rs.getString("first_name"));
						employees.setLastName(rs.getString("last_name"));
						employees.setBirthDate(rs.getString("birth_date"));
						employees.setHireDate(rs.getString("hire_date"));
						employees.setGender(rs.getString("gender"));
						//list에 담기
						list.add(employees);
					}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}System.out.println("list>>"+list);
			}
		//list리턴
		return list;
	}
	public List<Employees> selectEmployeesListByLimit(int limit){
		System.out.println("DAO para Limit >>>"+limit);
		//list 객체 생성
		List<Employees> list = new ArrayList<Employees>();
		//객체 선언
		String sql = "select "
				+ "emp_no,first_name,last_name,hire_date,birth_date,gender "
				+ "from employees limit ?";

			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			//오류검사
			try {//db접속 + 쿼리문 입력,실행
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees","root","java1234");
				stmt = conn.prepareStatement(sql);
						//?값 입력
					stmt.setInt(1, limit);
					rs = stmt.executeQuery();
						while(rs.next()) {
							//employees객체에 값 set
							Employees employees = new Employees();
								employees.setEmpNo(rs.getInt("emp_no"));
								employees.setFirstName(rs.getString("first_name"));
								employees.setLastName(rs.getString("last_name"));
								employees.setBirthDate(rs.getString("birth_date"));
								employees.setHireDate(rs.getString("hire_date"));
								employees.setGender(rs.getString("gender"));
								//list 에 추가
								list.add(employees);
						}	
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try{rs.close();
						stmt.close();
						conn.close();
					}catch(Exception e) {
						e.printStackTrace();
					}System.out.println("Dao list:>>"+list);
				}
		return list;
	}
	public int selectEmployeesCount() {//count를 리턴 메소드
		//쿼리문 ,변수,객체 선언
		final String sql="select count(*) from employees";//테이블 전체 행의 수 출력
		int count=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =null;
			//오류검사,db접속
		try {
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees","root","java1234");
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
			if(rs.next()) {
				//count에 행의수 복사
				count=rs.getInt(1);
				System.out.println("괄호안 count"+count);
			}
		}catch(Exception e){//자바의 변수 생명주기는 {}
			e.printStackTrace();
		}finally {
			try {//종료
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();//서버에 오류 띄우기
			}System.out.println("Dao Count:>>"+count);
		}
		return count;
	}
}
