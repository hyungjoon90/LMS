package com.login.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.model.DAO.memDAO;
import com.login.model.DTO.memDTO;



public class idChk implements Action {//id�ߺ��˻� ������ �̿ϼ�

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="../login/idChk.jsp";
		String memId = request.getParameter("memId");
		System.out.println("���̵�üũ"+memId);
		
		//memDAO dao=new memDAO();
		boolean result;
		//result = dao.idChk(memId);
		
		//request.setAttribute("memId", result);
		
		String realpath="../..";
		String subpath="..";
		request.setAttribute("realpath",realpath );
		request.setAttribute("subpath",subpath );
		request.setAttribute("memId", memId);
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
