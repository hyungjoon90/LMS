//AddForm.jsp
        // �ʼ� �Է� ������ �ԷµǾ����� Ȯ���ϴ� �Լ�(���̵� �ߺ�, ��ȿ�� �˻� ��)
        // checkValue ����
        function checkValue()
        {
        	var form = document.memInfo;
            
            if(!form.memId.value){
                alert("���̵� �Է��ϼ���.");
                return false;
            }
            
            if(form.idDupl.value != "idCheck"){
                alert("���̵� �ߺ�üũ�� ���ּ���.");
                return false;
            }
            
            if(!form.memPw.value){
                alert("��й�ȣ�� �Է��ϼ���.");
                return false;
            }
            
            if(!form.memName.value){
                alert("�̸��� �Է��ϼ���.");
                return false;
            }
            
            if(!form.memBirth.value){
                alert("��������� �Է��ϼ���.");
                return false;
            }
            
            if(isNaN(form.memBirth.value)){
                alert("��������� ���ڸ� �Է°����մϴ�.");
                return false;
            }
           
            if(!form.memMail1.value){
                alert("���� �ּҸ� �Է��ϼ���.");
                return false;
            }
            
                        
            if(!form.memPnum.value){
                alert("��ȭ��ȣ�� �Է��ϼ���.");
                return false;
            }
            
            if(isNaN(form.memPnum.value)){
                alert("��ȭ��ȣ�� - ������ ���ڸ� �Է����ּ���.");
                return false;
            }

        }// checkValue ��
        
        // ��� ��ư Ŭ���� �α��� ȭ������ �̵�
        function goMain() {
        	location.href="../user/index.com";
        }
		
        // id �ߺ�üũ ȭ��
        function idChk() {
        	var id = document.memInfo.memId.value;
        	if(id=="")
        		alert("�Էµ� ���̵� �����ϴ�.");
			else 
				/* open(�˾�â �ּ�, �˾�â �̸�, �˾�â ����) */
				window.open("../Login/idChk.com?memId="+id,"chkForm", "width=500, height=300, resizable = no, scrollbars = no");
		}
        
//LoginForm.jsp   
        
        
        