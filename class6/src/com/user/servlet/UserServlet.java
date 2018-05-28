package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.servlet.LoginServlet;
import com.user.action.Action;
import com.user.controller.ActionList;

@WebServlet(
		urlPatterns={
				"/user/index",
				"/user/index.com",
				"*.com"
		})
		
public class UserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String list=null;
		String temp=request.getServletPath();
		
		String[] temp2=temp.split("/");//guest,member,admin ������ ����
		System.out.println("����");
		int leng=0;
		if(temp2[2].contains(".com")){
			leng=temp2[2].length()-4;
			list=temp2[2].substring(0,leng);
		}
		System.out.println("list: "+list);
		// �⺻ ����Ʈ �湮 �� default Ŀ�ǵ�� main����.
		if(list==null||list.equals("index")){
			list="main";
		}
		System.out.println("Servlet : list�� - " + list);			
		
		if(temp2[1].equals("user")){
			ActionList al=new ActionList();
			Action action=al.getAction(list);
			
			if(action!=null){
				action.execute(request, response);
			}
		}else if(temp2[1].equals("Login")){
			System.out.println("login servlet ����");
			
			LoginServlet ls=new LoginServlet();
			ls.service(request, response);
			
		}else if(temp2[1].equals("admin")){
			System.out.println("admin servlet ����");
			LoginServlet ls=new LoginServlet();
			ls.service(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}

