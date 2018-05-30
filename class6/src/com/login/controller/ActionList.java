package com.login.controller;

import com.login.action.Action;
import com.login.action.Add;
import com.login.action.AddForm;
import com.login.action.LoginAction;
import com.login.action.Logout;
import com.login.action.findForm;
import com.login.action.idChk;
import com.login.action.loginForm;

public class ActionList {
	
	public Action getAction(String list){
		Action action=null;
			// �α�.			
			System.out.println("ActionList : "+list);
			
		if(list.equals("LoginForm")){//�α��� ������
			action=new loginForm();
		}else if(list.equals("AddForm")){//ȸ������ ������
			action=new AddForm();
		}else if(list.equals("findForm")){//id,pw ã�� ü����
			action=new findForm();
		}else if(list.equals("idChk")){//�ߺ��˻� ������
			action=new idChk();
		}else if(list.equals("Login")){//id,pw�˻��� �α��ν��� ������
			action=new LoginAction();
		}else if(list.equals("Logout")){//�α׾ƿ� ������
			action=new Logout();
		}else if(list.equals("Add")){//�α׾ƿ� ������
			action=new Add();
		}
		return action;
	}
}
