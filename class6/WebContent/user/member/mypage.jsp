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
		<jsp:include page="${realpath }/layout/Header.jsp" />
			<script type="text/javascript" src="${subpath }/js/mypage.js"></script>
			<script type="text/javascript">
			$(document).ready(function(){
				$('button[name="chk"]').click(function(){
					var form = document.createElement('form');

					var objs;

					objs = document.createElement('input');

					objs.setAttribute('type', 'hidden');

					objs.setAttribute('name', 'id');

					objs.setAttribute('value', $('label[name="id"]').text());
					
					form.appendChild(objs);

					form.setAttribute('method', 'post');

					form.setAttribute('action', "../user/chk.com");

					document.body.appendChild(form);

					form.submit();
				});
			});
			</script>
				<div class="main" style="overflow: auto">
					<!-- 메인화면 -->
					<h2 style="float:left;">마이페이지</h2>
					<div style="float: right;margin: 10px;">
					<button class="mpbtn" id="memBbs">회원게시판</button>
					<button class="mpbtn" id="memInfo">회원정보</button>
					</div>
					<div id=memInfoBox>
					<h3>나의 학습현황</h3>
					<c:forEach items="${list }" var="list"> 
					<div class=memInfoBoxIn><label>내강의실</label>
						<div class=memInfoBoxInCon>
						
							<table>
								<tr>
									<td>강의명</td>
									<td>${list.lecName}</td>
								</tr>
								<tr>
									<td>수업일정</td>
									<td>${list.lecStart }</td>
								</tr>
								<tr>
									<td>수업일정</td>
									<td>${list.lecEnd }</td>
								</tr>
							</table>
							
						</div>
					</div>
					<div class=memInfoBoxIn>
						<label>출석현황</label>
						<button name="chk">출석체크</button>
						<div class=memInfoBoxInCon>
							<table>
									<tr>
										<td>Today</td>
										<td>
										<jsp:useBean id="toDay" class="java.util.Date" />
										<fmt:formatDate value="${toDay}" pattern="yyyy-MM-dd"/>
										</td>
									</tr>
									<tr>
										<td>출석현황</td>
										<td>${list.chk }</td>
									</tr>
									<tr>
										<td>출석이수율</td>
										<td></td>
									</tr>
								</table>
						</div>
					</div>
					<div class=memInfoBoxIn><label>성적확인</label>
						<div class=memInfoBoxInCon>
							<table>
								<tr>
									<th class=th>JAVA</th>
									<th class=th>WEB</th>
									<th class=th>DB</th>
								</tr>
								<tr>
									<td class=th>${list.graJava }점</td>
									<td class=th>${list.graWeb }점</td>
									<td class=th>${list.graDb }점</td>
								</tr>
							</table>
						</div>
					</div>
					</c:forEach>
					<div class=memInfoBoxIn><label>빈칸</label>
						<div class=memInfoBoxInCon>
					
						</div>
					</div>
					</div>
				</div>
		<jsp:include page="${realpath }/layout/Footer.jsp" />
	</body>
</html>