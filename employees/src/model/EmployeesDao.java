package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.Employees;

public class EmployeesDao {
	//order값에 따라 desc,asc로 정렬 method
	public List<Employees> selectEmployeesListOrderBy(String order){
		//확인
		System.out.println("method order>>"+order);
		//list 생성
		List<Employees> list = new ArrayList<Employees>();
		//객체 생성,sql설정
		String sql=null;
		if(order.equals("asc")) {//order가asc일때는 오름차순 
			sql = "select emp_no,last_name,first_name,birth_date,hire_date,gender from employees order by first_name asc limit 50";
		}else if(order.equals("desc")) {
			sql = "select emp_no,last_name,first_name,birth_date,hire_date,gender from employees order by first_name desc limit 50";
		}System.out.println("sql==>"+sql);
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			//오류검사
			try {//db접속 + 쿼리문 입력,실행
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees","root","java123");
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
				}
			}
		//list리턴
		return list;
	}
	public List<Employees> selectEmployeesListByLimit(int limit){
		System.out.println("para Limit >>>"+limit);
		//list 객체 생성
		List<Employees> list = new ArrayList<Employees>();
		//객체 선언
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "select emp_no,first_name,last_name,hire_date,birth_date,gender from employees limit ?";
			//오류검사
				try {
					//db접속
					Class.forName("org.mariadb.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mariadb//localhost:3306/employees","root","java1234");
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
					}System.out.println(list);
				}
		return list;
	}
	public int selectEmployeesCount() {//count를 리턴 메소드
		//쿼리문 ,변수,객체 선언
		final String sql="select count(*) from employees";
		int count=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =null;
			//오류검사,db접속
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb//localhost:3306/employees","root","java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				//count에 행의수 복사
				count=rs.getInt(1);
			}
		}catch(Exception e){//자바의 변수 생명주기는 {}
			
		}finally {
			try {//종료
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();//서버에 오류 띄우기
			}System.out.println(count);
		}
		return count;
	}
}
