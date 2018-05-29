<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html><!-- header 공통부분 -->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
		<link href="../css/main.css" rel="stylesheet" type="text/css">
		<style type="text/css">					
			*{
				font-family: 'Nanum Gothic', sans-serif;
			}
		</style>
		<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			$('.menu2').mouseover(function(){
				$('.bitbutton').css("background", "rgba(255, 255, 255, 0.28)");
				$('.bit').css("display","inline");
			});					
			$('.menu2').mouseout(function(){
				$('.bitbutton').css("background", "rgba(255, 255, 255, 0)");
				$('.bit').css("display","none");
			});	
			$('.menu3').mouseover(function(){
				$('.clabutton').css("background", "rgba(255, 255, 255, 0.28)");
				$('.cla').css("display","inline");
			});					
			$('.menu3').mouseout(function(){
				$('.clabutton').css("background", "rgba(255, 255, 255, 0)");
				$('.cla').css("display","none");
			});	
			$(".cla>div, .bit>div").mouseover(function(){
				$(this).css("background", "rgba(0, 0, 0, 0.28)");
			});
			$(".cla>div, .bit>div").mouseout(function(){
				$(this).css("background", "rgba(0, 0, 0, 0)");
			});
			
			$('.menu1').mouseover(function(){
				$('.mainbutton').css("background", "rgba(255, 255, 255, 0.28)");
			});					
			$('.menu1').mouseout(function(){
				$('.mainbutton').css("background", "rgba(255, 255, 255, 0)");
			});	
			$('.menu7').mouseover(function(){
				$('.membutton').css("background", "rgba(255, 255, 255, 0.28)");
			});					
			$('.menu7').mouseout(function(){
				$('.membutton').css("background", "rgba(255, 255, 255, 0)");
			});	
			$('.menu4').mouseover(function(){
				$('.advice').css("background", "rgba(255, 255, 255, 0.28)");
			});					
			$('.menu4').mouseout(function(){
				$('.advice').css("background", "rgba(255, 255, 255, 0)");
			});	
			
			//로그인전
			$('#Login').click(function(){
				 location.href = "../Login/LoginForm.com";
			});
			$('#Add').click(function(){
				 location.href = "../Login/AddForm.com";
			});
			
			//로그인후
			$('div.login').replaceWith('');  
			$('#Logout').click(function(){
				location.href = "../Login/Logout.com";
			});
			
			//mypage 이동
			$("#btnList").click(function(){

				var form = document.createElement('form');

				var objs;

				objs = document.createElement('input');

				objs.setAttribute('type', 'hidden');

				objs.setAttribute('name', 'name');

				objs.setAttribute('value', '${sessionID }');

				form.appendChild(objs);

				form.setAttribute('method', 'post');

				form.setAttribute('action', "../user/mypage.com");

				document.body.appendChild(form);

				form.submit();

			});	



		});
		function mypage() {
			location.href="../user/mypage.com";
			
		}
		</script>
	</head>	
	<body>
		<div class="container">
			<div class="menuBar">
				<div class="logo">
					<a href="../user/index"><img src="../img/logo.JPG"/></a>
				</div>
				<div class="menu0" id="menu">
					<!-- 빈공간 -->
				</div>
				<div class="menu1" id="menu">
					<!-- 메인화면버튼 -->
					<div class="mainbutton">
						<a href="../user/index.com">메인화면</a>
					</div>
				</div>
				<div class="menu2" id="menu">
					<!-- 소개 -->
					<div class="bitbutton">
						<a href="../user/Intro.com">비트캠프구로지점</a>					
					</div>
					<div class="bit">
						<div class="bitintro">
							<a href="../user/Intro.com">비트캠프 소개</a>						
						</div>
						<div class="bitcla">
							<a href="../user/CompanyChart.com">조직도</a>
						</div>
						<div class="bittea">
							<a href="../user/tea.com">강사진 소개</a>
						</div>
						<div class="bitnavi">
							<a href="../user/way.com">오시는길</a>
						</div>
					</div>
				</div>				
				<div class="menu3" id="menu">
					<!-- 강의 -->
					<div class="clabutton">
						<a href="../user/LecIntro.com">강의 소개</a>					
					</div>
					<div class="cla">
						<div class="clajava">
							<a href="../user/Java.com">JAVA 과정</a>
						</div>
						<div class="clac">
							<a href="../user/Clanguage.com">C언어 과정</a>
						</div>
						<div class="clabig">
							<a href="../user/Bigdata.com">빅데이터 과정</a>
						</div>
						<div class="claand">
							<a href="../user/Android.com">안드로이드 과정</a>
						</div>
					</div>
				</div>
				<div class="menu4" id="menu">
					<!-- 상담 -->
					<div class="advice">
						<a href="../user/index.com">상담</a>
					</div>
				</div>
				<div class="menu5" id="menu">
					<!-- 빈공간 -->
				</div>
				<div class="menu6" id="menu">
					<!-- 빈공간 -->
				</div>
				<div class="menu7" id="menu">
					<!-- 회원페이지 -->
					<div class="membutton"><!-- 밑에다가 위에 펑션 붙일것 -->
						<a href="" onclick="mypage()">회원 페이지</a>
					</div>
				</div>
				<div class="menu8" id="menu">
					<!-- 빈공간 -->
				</div>		
			</div>
			<div class="page">
				<div class="top">
					<!-- 상단 로그인 -->
					<div class="logout">
						<button class="memberLogin" id="Login">로그인</button>
						<button class="memberLogin" id="Logout">로그아웃</button>
						<label name="id" style="float: right;margin: 6px 6px 0px 0px;">${sessionID }님 환영합니다</label>
					</div>
				</div>