<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.util.List" %>
    <%@ page import="com.user.model.DTO.memDTO" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>class6-LMS</title>
		<link href="../css/member.css" rel="stylesheet" type="text/css">
		<jsp:include page="${realpath }/layout/Header.jsp" />
		<script type="text/javascript" src="${subpath }/js/memInfo.js"></script>
				<div class="main" style="overflow: auto">
					<!-- 메인화면 -->
		       		<c:forEach items="${infoList }" var="list"> 
			        <h2 style="float:left;">회원정보</h2>
			       <table class="infoBox">
			            <tr>
			                <td id="title">아이디</td>
			                <td>${list.memId }</td>
			            </tr>
			            <tr>
			                <td id="title">이름</td>
			                <td>${list.memName }</td>
			            </tr>
			                    
			            <tr>
			                <td id="title">생년월일</td>
			                <td>${list.memBirth }</td>
			            </tr>
			                    
			            <tr>
			                <td id="title">성별</td>
			                <td>${list.memGen }</td>
			            </tr>
			                    
			            <tr>
			                <td id="title">이메일</td>
			                <td>${list.memMail }</td>
			            </tr>
			                    
			            <tr>
			                <td id="title">휴대전화</td>
			                <td>${list.memPnum }</td>
			            </tr>
			        </table>
			        <br>
			        <div class="btnBox">
				        <button name="">수정</button>
				        <button name="back">뒤로</button>
			        </div>
		      		</c:forEach>
				</div>
				<jsp:include page="${realpath }/layout/Footer.jsp" />
			</div>
		</div>
	</body>
</html>