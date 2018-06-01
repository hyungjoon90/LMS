package com.login.model.DAO;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
	
    
	
 // ȸ�� ����
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
	            
	            sql2 = "INSERT INTO PRIVACY VALUES (?, privacy_seq.nextval, '', ?, ?, ?, ?, ?,'����')";
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
	
	// ���̵� ã��
	public memDTO findId(String name, String mail) {
		try {
			member = new memDTO();
			
			conn=DB.getConnction();
			
			String sql1="SELECT MEMMAIL FROM PRIVACY WHERE MEMNAME=?";
			String sql2="SELECT MEMID FROM PRIVACY WHERE MEMNAME=? AND MEMMAIL=?";

			member.setMemName(name);
			member.setMemId("");
			member.setMemMail("");
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
                member.setMemMail(rs.getString("memMail"));
			}

			System.out.println(member.getMemMail()+1);
			System.out.println(member.getMemId()+2);
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, name);
			pstmt.setString(2, member.getMemMail());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
                member.setMemId(rs.getString("memId"));
			}
			
			System.out.println(member.getMemMail()+3);
			System.out.println(member.getMemId()+4);
			
			if(member.getMemId().equals("")){
				member.setMemId("x");
				member.setMemMail("x");
				System.out.println(member.getMemId());
			}
			
			System.out.println(member.getMemMail()+5);
			System.out.println(member.getMemId()+6);
			
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
		return member;
	}// end findId
	
	// ��� ã��
	public memDTO findPw(String id, String name, String mail) {
			
		try {
			member = new memDTO();
			
			conn=DB.getConnction();
			
			member.setMemPw("");
			member.setMemMail("");
			
			String sql1="select MEMMAIL FROM PRIVACY WHERE MEMNAME=?";
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
                member.setMemMail(rs.getString("memMail"));
			}
			
			if(member.getMemMail().equals(mail)){
				
				String sql2="SELECT MEMPW FROM MEMBER WHERE MEMID=(SELECT MEMID FROM PRIVACY WHERE MEMNAME=? AND MEMMAIL=?)";
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, name);
				pstmt.setString(2, member.getMemMail());
				rs = pstmt.executeQuery();
				
				if(rs.next()){
	                member.setMemPw(rs.getString("memPw"));
				}
			}
			
			if(member.getMemPw().equals("")){
				member.setMemPw("x");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
            // Connection, PreparedStatement�� �ݴ´�.
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
		}
		return member;
	}// end findPw


	//ȸ�� ���� ��ȸ admin Ȯ��
	public memDTO getUserInfo(String memId) throws SQLException {
		String sql="SELECT * FROM MEMBER WHERE MEMID=?";
		
		memDTO dto = new memDTO();
		try {
			try{
				conn=DB.getConnction();
				//conn=db.getConnction();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			//ȸ������
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dto.setMemCate(rs.getString("memCate"));
			}
			
			//conn.commit();
		}catch(SQLException e){
			//conn.rollback();
		}finally{
			conn.setAutoCommit(true);
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		System.out.println("�Ϸ�");
		return dto;
	}// end getUserInfo

}// end memDAO