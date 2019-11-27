package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.dbcp.dbcp2.PoolablePreparedStatement;

import db.DBHelper;

public class DeptEmpDao {
	//lastPage 구하기
	public int selectLastPage(int rowPerPage) {
		//전체 행의 수를 가져오기 위해 메소드 호출
		DeptEmpDao deptEmpDao = new DeptEmpDao();
		int length = deptEmpDao.selectDeptEmpCount();
		int lastPage = 0;
		if(length%rowPerPage==0) {
			lastPage = length/rowPerPage;
		}else {
			lastPage = (length/rowPerPage)+1;
		}
		
		return lastPage;
	}
	//deptemp테이블에서 list가져오기
	public List<Map<String,Object>> selectDeptEmpJoinList(int rowPerPage,int currentPage,String paraDeptName){
		//return값을 위한 객체 생성
		List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		
		int startRow = (currentPage-1)*rowPerPage;
		//객체 선언
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//직원 이름,부서이름,입사일,퇴사일을 10개씩 가져온다
		String sql = "select concat(e.first_name,'',e.last_name),d.dept_name,de.from_date,de.to_date "
				+ "from dept_emp de inner join employees e "
				+"inner join departments d "
				+"on de.dept_no=d.dept_no and e.emp_no=de.emp_no where d.dept_name=? limit ?,?";
			//db접속
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
				stmt.setString(1, paraDeptName);
				stmt.setInt(2, startRow);
				stmt.setInt(3, rowPerPage);
			rs = stmt.executeQuery();
			System.out.println("Dao rs:>>"+rs);
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				//map에 키워드와 값을 저장한다.
				map.put("name", rs.getString("concat(e.first_name,'',e.last_name)"));
				map.put("deptName",rs.getString("d.dept_name"));
				map.put("fromDate",rs.getString("from_date"));
				map.put("toDate",rs.getString("to_date"));
				list.add(map);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		
		return list;
	}
	public int selectDeptEmpCount() {//count를 리턴 메소드
		//쿼리문 ,변수,객체 선언
		final String sql="select count(*) from dept_emp";//dept_emp테이블에 행의 수 출력
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
