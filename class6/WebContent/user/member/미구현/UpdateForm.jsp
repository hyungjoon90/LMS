<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>ȸ�� ���� ���� ȭ��</title>
</head>
	<script type="text/javascript">
		function checkValue() {
			var form = document.memInfo;
			
			var pw = form.memPw;
			var name = form.memName;
			var birth = form.memBirth;
			var mail = form.memMail;
			var pn = form.memPnum;
			
			if(!pw.value){
				alert("��й�ȣ�� �Է��ϼ���");
				return false;
			}
			
			if(!name.value){
				alert("�̸��� �Է��ϼ���");
				return false;
			}
		
			if(!birth.value){
				alert("��������� �Է��ϼ���");
				return false;
			}
			
			if(!mail.value){
				alert("�̸����� �Է��ϼ���");
				return false;
			}
			
			if(!pn.value){
				alert("��ȭ��ȣ�� �Է��ϼ���");
				return false;
			}
				
		}
	</script>
<body>
<%
	String id = (String)session.getAttribute("sessionID");
	memDAO dao = memDAO.getInstance();
	memDTO dto = dao.getUserInfo(id);
	try{
%>
<form method="post" action="../mem/Update.jsp" onsubmit="return checkValue(this)">
	<table>
		<tr>
			<th>���� ���� ����</th>
		</tr>
		
		<tr>
			<td id="title">���̵�</td>
			<td><%=dto.getMemId() %></td>
		</tr>
		
		<tr>
			<td id="title">��й�ȣ</td>
			<td>
				<input type="password" name="memPw" maxlength="20" value="<%=dto.getMemPw() %>" />
			</td>
		</tr>
		
		<tr>
			<td id="title">�̸�</td>
			<td>
				<input type="text" name="memName" maxlength="40" value="<%=dto.getMemName() %>" />
			</td>
		</tr>
		
		<tr>
			<td id="title">����</td>
			<td>
				<input type="radio" name="memGen" value="����" checked>����
                <input type="radio" name="memGen" value="����" >����
			</td>
		</tr>
		
		<tr>
			<td id="title">�������</td>
			<td>
				<input type="text" name="memBirth" maxlength="8" value="<%=dto.getMemBirth() %>" />
			</td>
		</tr>
		
		<tr>
			<td id="title">�̸���</td>
			<td>
				<input type="email" name="memMail" maxlength="40" value="<%=dto.getMemMail() %>"/>
			</td>
		</tr>
		
		<tr>
			<td  id="title">�޴���ȭ</td>
			<td>
				<input type="number" name="memPnum" maxlength="11" value="<%=dto.getMemPnum() %>"/>
            </td>
		</tr>
	</table>
	<input type="submit" value="����" />
	<!-- ��� Ŭ���� ������������ �̵� -->
	<input type="button" value="���" onclick="main.jsp" />
</form>
<%
	}catch(Exception e){}
%>
</body>
</html>