package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TitlesDao;
import vo.Titles;


@WebServlet("/titles/getTitlesListDistinct")
public class GetTitlesListDistinctServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//리턴값을 위한 list생성
				List<Titles> list = new ArrayList<Titles>();
				//selectTitlesListDistinct() 호출을 위한 객체 생성
				TitlesDao titlesDao = new TitlesDao();
				//list에 method에 리턴값을 복사
				list = titlesDao.selectTitlesListDistinct();
			//request에 list값을 담는다
			request.setAttribute("list", list);
			//jsp로 forward
			request.getRequestDispatcher("/WEB-INF/views/titles/titlesListDistinct.jsp").forward(request, response);;
	}

}
