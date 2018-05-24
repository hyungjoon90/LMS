<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <%
        // 인코딩 처리
        request.setCharacterEncoding("UTF-8"); 
    %>
    <title>로그인 화면</title>

    <script type="text/javascript">
    
        function checkValue()
        {
            addMem = eval("document.loginInfo");
            if(!addMem.memId.value)
            {
                alert("아이디를 입력하세요");    
                addMem.memId.focus();
                return false;
            }
            if(!addMem.memPw.value)
            {
                alert("비밀번호를 입력하세요");    
                addMem.memPw.focus();
                return false;
            }
        }
    
        // 회원가입 버튼 클릭시 회원가입 화면으로 이동
        function goAddForm() {
            location.href="AddForm.jsp";
        }    
        
        // 아이디, 비밀번호 찾기 클릭시 찾는 id/pw 찾는 페이지로 이동
        function goFindform() {
			location.href="findForm.jsp";
		}
    </script>
 
</head>
<body>
    <div id="wrap">
        <form name="loginInfo" method="post" action="../mem/LoginForm.jsp" onsubmit="return checkValue()">
            <br><br>
            
            <table>
                <tr>
                    <td bgcolor="skyblue">아이디</td>
                    <td><input type="text" name="memId" maxlength="20"></td>
                </tr>
                <tr>
                    <td bgcolor="skyblue">비밀번호</td>
                    <td><input type="password" name="memPw" maxlength="20"></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="로그인"/>
            <input type="button" value="아이디/비밀번호 찾기" onclick="goFindform()"/>
            <input type="button" value="회원가입" onclick="goAddForm()" />
        </form>
        
        <% 
            // 아이디, 비밀번호가 틀릴경우 화면에 메시지 표시
            // memLogin.jsp에서 로그인 처리 결과에 따른 메시지를 보낸다.
            String msg=request.getParameter("msg");
            
            if(msg!=null && msg.equals("0")) 
            {
                out.println("<br>");
                out.println("<font color='red' size='5'>비밀번호를 확인해 주세요.</font>");
            }
            else if(msg!=null && msg.equals("-1"))
            {    
                out.println("<br>");
                out.println("<font color='red' size='5'>아이디를 확인해 주세요.</font>");
            }
        %>    
    </div>    
</body>
</html>