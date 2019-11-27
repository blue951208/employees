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
import vo.Departments;

public class DepartmentsDao {
	//group by having 이용 부서별 인원수 구하기
	public List<Map<String, Object>> selectDepartmentsCountByDeptNo() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//dep_emp,departments join,dept group by 
		String sql = "select de.dept_no,d.dept_name,count(d.dept_no) as cnt from departments d inner join dept_emp de on d.dept_no=de.dept_no group by d.dept_no order by dept_no";
			try {
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				 while(rs.next()) {
					 Map<String, Object> map = new HashMap<String, Object>();
					 
					 map.put("deptNo", rs.getString("de.dept_no"));
					 map.put("deptName", rs.getString("d.dept_name"));
					 map.put("cnt", rs.getInt("cnt"));
					 
					 list.add(map);
				 }
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBHelper.close(rs, stmt, conn);
			}
		
		return list;
	}
	//return 타입 List<Departments, 메소드 -부서리스트 보기
	public List<Departments> selectDepartmentsDao(){
		//list 객체 생성
		List<Departments> list = new ArrayList<Departments>();
		//db 접속
		//객체 초기화;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//부서 번호,이름 출력 쿼리문 
		String sql = "select dept_no,dept_name from departments";
		//예외 처리
			try {
				//db 접속,쿼리 실행
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees","root","java1234");
				System.out.println(conn);
				stmt = conn.prepareStatement(sql);
				System.out.println(stmt);
				rs = stmt.executeQuery();
				System.out.println(rs);
					while(rs.next()) {
						//departments 객체 생성
						Departments departments = new Departments();
						//값 set
						departments.setDeptNo(rs.getString("dept_no"));
						departments.setDeptName(rs.getString("dept_name"));
						//list 에 추가
						list.add(departments);
					}
			}catch(Exception e) {//오류출력
				e.printStackTrace();
			}finally {
				//conn,stmt,rs종료 오류검사\
				try {
					rs.close();
					stmt.close();
					conn.close();
				}catch(Exception e) {
				e.printStackTrace();
				//확인
			}System.out.println(list);
		}
		//리턴
		return list;
	}
	public int selectDepartmentsCount() {//count를 리턴 메소드
		//쿼리문 ,변수,객체 선언
		final String sql="select count(*) from departments";
		int count=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =null;
			//오류검사,db접속
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees","root","java1234");
			System.out.println(conn);
			stmt = conn.prepareStatement(sql);
			System.out.println(stmt);
			rs = stmt.executeQuery();
			System.out.println(rs);
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
