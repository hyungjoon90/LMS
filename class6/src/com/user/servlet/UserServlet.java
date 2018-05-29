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

@WebServlet(//���� ó������ ��û�� ����.
		urlPatterns={
				"/user/index",
				"/user/index.com",
				"*.com",
				"/Login"
		})
		
public class UserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String list=null;
		String temp=request.getServletPath();//��û�ּҸ� Ȯ��
		String temp4=request.getRequestURI();//�ӽ� Ȯ�ο�
		String temp5=request.getRemoteAddr();//�ӽ� Ȯ�ο�
		
		System.out.println("����"+temp+temp4+temp5);//�ӽ� Ȯ�ο�
		
		String[] temp2=temp.split("/");//guest,member,admin ������ ����
		int leng=0;
		if(temp2[2].contains(".com")){//�޺κ� �ּ��� .com�� ����
			leng=temp2[2].length()-4;
			list=temp2[2].substring(0,leng);
		}
		System.out.println("list: "+list);
		// �⺻ ����Ʈ �湮 �� default Ŀ�ǵ�� main����.
		if(list==null||list.equals("index")){
			list="main";
		}
		System.out.println("Servlet : list�� - " + list);//Ȯ�ο� ��¹�
		
		if(temp2[1].equals("user")){//user�� �����ϸ�
			ActionList al=new ActionList();//user�� actionList�� �ҷ��´�.
			Action action=al.getAction(list);//actionList���� ���ϴ� �ൿ�� ã�´�.
			
			if(action!=null){
				action.execute(request, response);//�ش��ϴ� action���� ������.
			}
		}else if(temp2[1].equals("Login")){//login���� �����ϸ�
			System.out.println("login servlet ����");
			
			LoginServlet ls=new LoginServlet();//login servlet ��ü ����
			ls.service(request, response);//login servlet���� ��û�� �ѱ��.
			
		}else if(temp2[1].equals("admin")){//admin���� �����ϸ�
			System.out.println("admin servlet ����");
			LoginServlet ls=new LoginServlet();//admin servlet ��ü ����
			ls.service(request, response);//admin servlet���� ��û�� �ѱ��.
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}

