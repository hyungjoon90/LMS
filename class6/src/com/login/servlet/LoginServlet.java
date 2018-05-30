package com.login.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.action.Action;
import com.login.controller.ActionList;

//userservlet���� login��û�� ������ ���� servlet���� ��û�� �н���.
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login servlet");
		String list=null;
		String temp=request.getServletPath();//��û ����Ȯ��
		
		String memId = request.getParameter("memId");//id ����
		System.out.println(memId+"���̵�");
		String memPw = request.getParameter("memPw");//pw ����
		System.out.println(memPw+"���̵�");
		
		System.out.println("temp-"+temp);
		String[] temp2=temp.split("/");//guest,member,admin ������ ����
		int leng=0;
		if(temp2[2].contains(".com")){//.com ���Ź�
			leng=temp2[2].length()-4;
			list=temp2[2].substring(0,leng);
		}
		System.out.println("list: "+list);
		// �⺻ ����Ʈ �湮 �� default Ŀ�ǵ�� main����.
		if(list==null||list.equals("index")){
			list="main";
		}
		System.out.println("Servlet : list�� - " + list);			
		
		if(temp2[1].equals("user")){//user�� �����ϸ� 
			
		}else if(temp2[1].equals("Login")){//login���� �����ϸ� 
			ActionList al=new ActionList();
			Action action=al.getAction(list);
			
			if(action!=null){
				action.execute(request, response);
			}
		}else if(temp2[1].equals("admin")){//admin���� �����ϸ�
			
		}else{
			
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}

