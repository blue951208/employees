package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import db.DBHelper;

public class SalariesDao {
	//salary에 count,max,min,avg,std,sum을 가져온다
	public Map<String, Long> selectSalariesStatistics(){
		//return을 위한 map 생성
		Map<String, Long> map = new HashMap<String, Long>();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "select count(salary),"
							+"sum(salary),"//합계
							+"avg(salary),"//평균
							+"max(salary),"//최대값
							+"min(salary),"//최소값
							+"std(salary) from salaries";//표준편차
				try {
					conn = DBHelper.getConnection();
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
						if(rs.next()) {
							map.put("count",rs.getLong("count(salary)"));
							map.put("sum",rs.getLong("sum(salary)"));
							map.put("avg",rs.getLong("avg(salary)"));
							map.put("max",rs.getLong("max(salary)"));
							map.put("min",rs.getLong("min(salary)"));
							map.put("std",rs.getLong("std(salary)"));
						}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					DBHelper.close(rs,stmt,conn);
				}
			return map;
	}
	public int selectSalariesCount() {//count를 리턴 메소드
		//쿼리문 ,변수,객체 선언
		final String sql="select count(*) from salaries";
		int count=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
			try {
				conn = DBHelper.getConnection();
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
