package com.login.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.model.DAO.memDAO;
import com.login.model.DTO.memDTO;
import com.user.model.DTO.bbsMemDTO;



public class idFind implements Action {//id,pw ã�� ������ �̿ϼ�

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="../login/findId.jsp";
		
		String memName = request.getParameter("memName");//id ����
		System.out.println(memName+"�̸�");
		String memMail = request.getParameter("memMail");//pw ����
		System.out.println(memMail+"����");
		
		memDAO dao=new memDAO();
		memDTO dto=new memDTO();
		dto=dao.findId(memName, memMail);
		
		
		if(!dto.getMemId().equals("x")){
			System.out.println("null�ƴ�");
			request.setAttribute("memId", dto.getMemId());
			request.setAttribute("memName", memName);
			request.setAttribute("memMail", memMail);
		}else{
			System.out.println("x����");
			String err="�̸� �Ǵ� �̸����� Ʋ�Ƚ��ϴ�.";
			dto.setMemId("");
			request.setAttribute("memId", dto.getMemId());
			request.setAttribute("memName", memName);
			request.setAttribute("er", err);
		}
		
		String realpath="../..";
		String subpath="..";
		request.setAttribute("realpath",realpath );
		request.setAttribute("subpath",subpath );
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
