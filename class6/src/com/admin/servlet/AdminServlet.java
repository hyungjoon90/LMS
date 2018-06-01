package com.admin.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.action.Action;
import com.admin.controller.AdminList;
import com.admin.controller.ClaMainController;
import com.admin.controller.LecMainController;
import com.admin.controller.StuMainController;


//userservlet���� login��û�� ������ ���� servlet���� ��û�� �н���.
public class AdminServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("admin servlet");
		String list=null;
		String temp=request.getServletPath();//��û ����Ȯ��
		
		System.out.println(temp);
		
		String memId = request.getParameter("memId");//id ����
		System.out.println(memId+"���̵�");
		
		String[] temp2=temp.split("/");//admin ������ ����
		
		int leng=0;
		if(temp2[2].contains(".com")){//.com ���Ź�
			leng=temp2[2].length()-4;
			list=temp2[2].substring(0,leng);
		}else if(temp2[3].contains(".com")) {
			leng=temp2[3].length()-4;
			list=temp2[3].substring(0,leng);
		}
		System.out.println("list: "+list);
		// �⺻ ����Ʈ �湮 �� default Ŀ�ǵ�� main����.
		if(list==null||list.equals("index")){
			list="main";
		}
		System.out.println("Servlet : list�� - " + list);			
		
		if(temp2[1].equals("admin")){//admin���� �����ϸ�
			if(list=="main") {
				//main
				AdminList al=new AdminList();
				Action action=al.getAction(list);
				
				if(action!=null){
					try {
						action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else if(temp2[3]!=null) {
				
				if(temp2[2].equals("class")) {
					ClaMainController cmc=new ClaMainController();
					cmc.service(request, response);
				}else if(temp2[2].equals("lec")) {
					LecMainController lmc=new LecMainController();
					lmc.service(request, response);
				}else if(temp2[2].equals("stu")) {
					StuMainController smc=new StuMainController();
					smc.service(request, response);
				}else {
					//default ����
					AdminList al=new AdminList();
					Action action=al.getAction(list);
					
					if(action!=null){
						try {
							action.execute(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
			}else {
				//default ����
				AdminList al=new AdminList();
				Action action=al.getAction(list);
				
				if(action!=null){
					try {
						action.execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}

