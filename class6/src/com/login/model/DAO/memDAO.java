package com.login.model.DAO;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import util.DB;

import com.login.model.DTO.memDTO;

public class memDAO //ȸ������ �� �˻� DAO
{
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private memDTO member,mem;
	private String sql1,sql2;
    
    // �̱��� ����
   /* private memDAO(){}
    public static memDAO getInstance(){
        if(instance==null)
            instance=new memDAO();
        return instance;
    }*/
    
    // String -> Date�� �����ϴ� �޼���
    // ���ڿ��ε� ��������� Date�� �����ϱ� ���� �ʿ�
    // java.util.DateŬ�����δ� ����Ŭ�� Date���İ� ���� �Ұ�
    // Oracle�� date���İ� �����Ǵ� java�� Date�� java.sql.Date Ŭ����
    public Date stringToDate(memDTO member)
    {
        // ��¥�� yyyymmdd �������� �ԷµǾ��� ��� Date�� �����ϴ� �޼���
        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
        
        // Date�� �����ϱ� ���ؼ��� ��¥ ������ yyyy-mm-dd�� �����ؾ� �Ѵ�.
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
        
        java.util.Date tempDate = null;
        
        try {
            // ���� yyyymmdd�ε� ��¥ �������� java.util.Date��ü�� �����.
            tempDate = beforeFormat.parse(member.getMemBirth());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        // java.util.Date�� yyyy-mm-dd �������� �����Ͽ� String�� ��ȯ�Ѵ�.
        String transDate = afterFormat.format(tempDate);
        
        // ��ȯ�� String ���� Date�� �����Ѵ�.
        Date d = Date.valueOf(transDate);
        
        return d;
    } // end stringToDate()
    
    // �α��� üũ
    // ���̵�/��й�ȣ üũ
    public int loginCheck(String id, String pw) 
    {

    	String dbPW = ""; // db���� ���� ��й�ȣ�� ���� ����
        int x = -1;
 
        try {
            // ���� - ���� �Էµ� ���̵�� DB���� ��й�ȣ�� ��ȸ
            sql1 = "SELECT MEMPW FROM MEMBER WHERE MEMID=?";
 
            conn = DB.getConnction();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
 
            if (rs.next()) // �Էµ� ���̵� �ش��ϴ� ��� �������
            {
                dbPW = rs.getString("memPw"); // ����� ������ �ִ´�.
 
                if (dbPW.equals(pw)) 
                    x = 1; // �Ѱܹ��� ����� ������ ��� ��. ������ ��������
                else                  
                    x = 0; // DB�� ��й�ȣ�� �Է¹��� ��й�ȣ�� �ٸ���, ��������
                
            } else {
                x = -1; // �ش� ���̵� ���� ���
            }
 
            return x;
 
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    } // end loginCheck()
	
    
	
 // ȸ�� ���� ��� ������( X )
 	public ArrayList<memDTO> getMemList() {
 		ArrayList<memDTO> memberList = new ArrayList<memDTO>();
         
         try {
         	
             sql1 = "SELECT MEMID, MEMPW FROM MEMBER";
             sql2 = "SELECT * FROM PRIVACY";
             
             conn = DB.getConnction();
             pstmt = conn.prepareStatement(sql1.toString());
             rs = pstmt.executeQuery();
             
             while (rs.next()) 
             {
                 member = new memDTO();
                 member.setMemId(rs.getString("memId"));
                 member.setMemPw(rs.getString("memPw"));
                 member.setMemName(rs.getString("memName"));
                 member.setMemGen(rs.getString("memGen"));
                 member.setMemBirth(rs.getDate("memBirth").toString());
                 member.setMemMail(rs.getString("memMail"));
                 member.setMemPnum(Integer.parseInt(rs.getString("memPnum")));
                 
                 memberList.add(member);
             }
             
             return memberList;
             
         } catch (Exception sqle) {
             throw new RuntimeException(sqle.getMessage());
         } finally {
             // Connection, PreparedStatement�� �ݴ´�.
             try{
                 if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                 if ( conn != null ){ conn.close(); conn=null; }
             }catch(Exception e){
                 throw new RuntimeException(e.getMessage());
             }
         }
     } // end getMemList

	// ���̵� �ߺ� �˻�
	public boolean idChk(String memId) {
		
		boolean x= false;
        
        try {
            // ����
            String sql = "SELECT * FROM MEMBER WHERE MEMID=?";
                        
            conn = DB.getConnction();
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, memId);            
            rs = pstmt.executeQuery();
            
            if(rs.next())
            	
            	x= true; //�ش� ���̵� ����
            
            return x;
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null; }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    } // end idChk()
	
	// ȸ�� ���� �Է�(ȸ�� ����)
		public void addMem(memDTO member) throws SQLException {
	        String sql1=null;
	        String sql2=null;
	        System.out.println("addMem ����");
	        try {
	            // Ŀ�ؼ��� �����´�.
	            conn = DB.getConnction();
	            
	            // �ڵ� Ŀ���� false�� �Ѵ�.
	            conn.setAutoCommit(false);
	            
	            // ���� ����
	            // �ۼ����� ��� �ڵ����� ���õǰ� �ϱ� ���� sysdate�� ���
	            sql1 = "INSERT INTO MEMBER VALUES (?, ?, ?, sysdate, '')";
	            // ('abc',1234,'�л�',sysdate,'');
	            
	            sql2 = "INSERT INTO PRIVACY VALUES (?, privacy_seq.nextval, '', ?, ?, ?, ?, ?)";
	            // ('abc',privacy_seq.nextval,1,'�л��̸�','��',sysdate,'abc@gamil.com',01012345678);
	            Date date=stringToDate(member);

	            // ȸ�� ���� (���̵�, �н�����)
	            // StringBuffer�� ��� ���� �������� toString()�޼��带 ����ؾ� ��
	            pstmt = conn.prepareStatement(sql1.toString());
	            pstmt.setString(1, member.getMemId());
	            pstmt.setString(2, member.getMemPw());
	            pstmt.setString(3, "�л�");
	           
	            // ���� ����
	            pstmt.executeUpdate();
	            // �Ϸ�� Ŀ��
	            conn.commit(); 
	            
	            // privacy (���� �� ����)
	            pstmt = conn.prepareStatement(sql2.toString());
	            pstmt.setString(1, member.getMemId());
	            pstmt.setString(2, member.getMemName());
	            pstmt.setString(3, member.getMemGen());
	            pstmt.setDate(4, date);
	            pstmt.setString(5, member.getMemMail());
	            pstmt.setInt(6, member.getMemPnum());
	            
	            // ���� ����
	            pstmt.executeUpdate();
	            
	            // �Ϸ�� Ŀ��
	            conn.commit(); 
	            
	        } catch (SQLException e) {
	            // ������ �ѹ�
	            conn.rollback();             
	            throw new RuntimeException(e.getMessage());
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
	            // Connection, PreparedStatement�� �ݴ´�.
	            try{
	                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
	                if ( conn != null ){ conn.close(); conn=null; }
	            }catch(Exception e){
	                throw new RuntimeException(e.getMessage());
	            }
	        } 
	    }// end addMem
	
	/*// ���̵� ã��
	public memDTO findId(String name, String mail) {
			
		try {
			member = new memDTO();
			
			conn=DB.getConnction();
			
			sb1 = new StringBuffer();
			sb1.append("select memId from member where memName=? and memMail=?");

			pstmt = conn.prepareStatement(sb1.toString());
			pstmt.setString(1, name);
			pstmt.setString(2, mail);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				member = new memDTO();
                member.setMemId(rs.getString("memId"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            // Connection, PreparedStatement�� �ݴ´�.
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
		}
		return null;
	}// end findId
	
	// ��� ã��
	public memDTO findPw(String id, String name, String mail) {
			
		try {
			member = new memDTO();
			
			conn=DB.getConnction();
			
			sb1 = new StringBuffer();
			sb1.append("select memPw from member where memId=? and memName=? and memMail=?");
			
			pstmt = conn.prepareStatement(sb1.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, mail);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				member = new memDTO();
                member.setMemPw(rs.getString("memPw"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            // Connection, PreparedStatement�� �ݴ´�.
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
		}
		return null;
	}// end findPw
*/
}// end memDAO
