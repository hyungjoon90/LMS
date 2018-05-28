<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ page import="java.util.List" %>
<%@ page import="com.user.model.DTO.bbsMemDTO" %>     
<html>
<head>
    <title>��ü �Խñ�</title>
    
    <style type="text/css">
        #wrap {
            width: 800px;
            margin: 0 auto 0 auto;
        }
        #topForm{
            text-align :right;
        }
        #board, #pageForm, #searchForm{
            text-align :center;
        }
        
        #bList{
            text-align :center;
        }
        #bbsW{
			float: right;
			margin: 10px;
        }
    </style>
    <script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
   <!--  <script type="text/javascript">
        function writeForm(){
            location.href="BoardWriteForm.bo";
        }
    </script> -->
    <script type="text/javascript">
    $(function(){
		$('#bbsW').click(function(){
	        location.href="./memBbsW.com";
	     });
	});
	</script>
</head>
<body>    
 
<div id="wrap">
    <br>
    <div id="topForm">
       <%--  <c:if test="${sessionScope.sessionID!=null}">
            <input type="button" value="�۾���" onclick="writeForm()">
        </c:if>   --%>  
    </div>
    <br>
    <div id="board">
    <h1>ȸ���Խ���</h1>
    	<button id="bbsW">�۾���</button>
        <table id="bList" width="800" border="3" bordercolor="lightgray">
            <tr heigh="30">
                <td>�۹�ȣ</td>
                <td>�з�</td>
                <td>����</td>
                <td>�۾���</td>
                <td>��ȸ��</td>
                <td>�ۼ���</td>
            </tr>    
            <c:forEach items="${list }" var="list"> 
            <tr>
                <td>${list.bbsMemNo }</td>
                <td>${list.bbsMemCate }</td>
                <td>${list.bbsMemName }</td>
                <td>${list.memId }</td>
                <td>${list.bbsMemView }</td>
                <td>${list.bbsMemDate }</td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <br>
    <div id="pageForm">
        ������ ��ȣ
    </div>
    <br>
    <div id="searchForm">
        <form>
            <select name="opt">
                <option value="0">����</option>
                <option value="1">����</option>
                <option value="2">����+����</option>
                <option value="3">�۾���</option>
            </select>
            <input type="text" size="20" name="condition"/>&nbsp;
            <input type="submit" value="�˻�"/>
        </form>    
    </div>
</div>    
 
</body>
</html>