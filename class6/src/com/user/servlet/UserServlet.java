package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.action.Action;
import com.user.controller.ActionList;

@WebServlet(
		urlPatterns={"/user/index",
				"/user/index.com",
				"*.com"
		})
		
public class UserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String list=null;
		String temp=request.getServletPath();
		System.out.println(temp);
		
		String temp1=temp.substring(6);
		int leng=0;
		if(temp1.contains(".com")){
			leng=temp1.length()-4;
			list=temp1.substring(0,leng);
		}
		
		System.out.println(list);
		// �⺻ ����Ʈ �湮 �� default Ŀ�ǵ�� main����.
		if(list==null||list.equals("index")){
			list="main";
		}
		// �α�
		System.out.println("-----------------------------");
		System.out.println("Servlet : list�� - " + list);			
	
		ActionList al=new ActionList();
		Action action=al.getAction(list);
		
		if(action!=null){
			action.execute(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}

