package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBHelper;
import vo.Titles;

public class TitlesDao {
	//title중복값 제외 출력
	public List<Titles> selectTitlesListDistinct(){
		//리턴값을 위한 list생성
		List<Titles> list = new ArrayList<Titles>();
		String sql = "select distinct title from titles";//title 중복값 제외 출력
		//객체 초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
			try {//db접속
				conn = DBHelper.getConnection();
				//실행
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
					while(rs.next()) {
						Titles titles = new Titles();
						titles.setTitle(rs.getString("title"));
						list.add(titles);
					}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBHelper.close(rs, stmt, conn);
			}
		return list;
	}
	public int selectTitlesCount() {//count를 리턴 메소드
		//쿼리문 ,변수,객체 선언
		final String sql="select count(*) from titles";
		int count=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
			try {//db접속
				conn = DBHelper.getConnection();
				//실행
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
