<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ�� ���� ���� ó�� JSP</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
%>
	<jsp:useBean id="dto" class="memDTO">
        <jsp:setProperty name="dto" property="*" />
    </jsp:useBean>
    
<%
    String id = (String)session.getAttribute("memId");
    dto.setMemId(id);

    memDAO dao = memDAO.getInstance();
    dao.updateMem(dto);
    
%>

	<table>
	    <tr> 
	        <td>
	            ȸ�������� �����Ǿ����ϴ�.
	        </td>
	    </tr>
	    
	    <tr>
	        <td> 
	            �Է��Ͻ� ������ ������ �Ϸ�Ǿ����ϴ�.
	        </td>
	    </tr>
    </table>
    	<!-- Ȯ�� Ŭ���� ������������ �̵� -->
    	<input type="button" value="Ȯ��" onclick="main.jsp" />
</body>
</html>