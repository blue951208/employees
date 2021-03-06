package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBHelper;

public class DeptManagerDao{
	public int selectDeptManagerCount() {//count를 리턴 메소드
		//쿼리문 ,변수,객체 선언
		final String sql="select count(*) from dept_manager";//테이블 전체 행 출력
		int count=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =null;
			//오류검사,db접속
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				//count에 행의수 복사,컬럼에 첫번째 값을 가져온다
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
