
//LoginForm.jsp   
        
        
        function checkValue()
        {
            addMem = eval("document.loginInfo");
            if(!addMem.memId.value)
            {
                alert("���̵� �Է��ϼ���");    
                addMem.memId.focus();
                return false;
            }
            if(!addMem.memPw.value)
            {
                alert("��й�ȣ�� �Է��ϼ���");    
                addMem.memPw.focus();
                return false;
            }
        }
    
        // ȸ������ ��ư Ŭ���� ȸ������ ȭ������ �̵�
        function goAddForm() {
            location.href="../Login/AddForm.com";
        }    
        
        // ���̵�, ��й�ȣ ã�� Ŭ���� ã�� id/pw ã�� �������� �̵�
        function goFindform() {
			location.href="../Login/findForm.com";
		}
        
        