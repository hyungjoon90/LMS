package com.login.model.DAO;

import java.sql.*;
import java.util.ArrayList;

import util.DB;

import com.login.model.DTO.memDTO;

public class memDAO 
{
	
	private static memDAO instance;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private memDTO member,mem;
	private StringBuffer sb1,sb2;
    
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
        String birth = member.getMemBirth();
        Date birthday = Date.valueOf(birth);
        
        return birthday;
        
    } // end stringToDate()
    
    // �α��� üũ
    // ���̵�/��й�ȣ üũ
    public int loginCheck(String id, String pw) 
    {

    	String dbPW = ""; // db���� ���� ��й�ȣ�� ���� ����
        int x = -1;
 
        try {
            // ���� - ���� �Էµ� ���̵�� DB���� ��й�ȣ�� ��ȸ
            sb1 = new StringBuffer();
            sb1.append("SELECT MEMPW FROM MEMBER WHERE MEMID=?");
 
            conn = DB.getConnction();
            pstmt = conn.prepareStatement(sb1.toString());
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
 
            if (rs.next()) // �Է��� ���̵� �ش��ϴ� ��� �������
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
	
    // ȸ�� ���� ��ȸ
    public memDTO getUserInfo(String id) {

		try {
			// ����
			sb1 = new StringBuffer();
			sb1.append("SELECT * FROM MEMBER WHERE MEMID=?");

			conn = DB.getConnction();
			pstmt = conn.prepareStatement(sb1.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) // ȸ�������� DTO�� ��´�.
			{
				// �ڹٺ� ������ ��´�.
				member = new memDTO();
				member.setMemId(rs.getString("memId"));
				member.setMemPw(rs.getString("memPw"));
				member.setMemName(rs.getString("memName"));
				member.setMemGen(rs.getString("memGen"));
				member.setMemBirth(rs.getString("memBirth"));
				member.setMemMail(rs.getString("memMail"));
				member.setMemPnum(Integer.parseInt(rs.getString("memPnum")));
			}

			return member;

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			// Connection, PreparedStatement�� �ݴ´�.
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}
	}// end getUserInfo
    
    // ȸ�� ���� ����
	public void updateMem(memDTO member) throws SQLException {

		try {

			sb1 = new StringBuffer();
			sb1.append("UPDATE MEMBER SET MEMPW=?, MEMMAIL=?, MEMPNUM=? WHERE MEMID=?");

			conn = DB.getConnction();
			pstmt = conn.prepareStatement(sb1.toString());

			// �ڵ� Ŀ���� false��
			conn.setAutoCommit(false);
			
			pstmt.setString(1, member.getMemPw());
			pstmt.setString(2, member.getMemMail());
			pstmt.setInt(3, member.getMemPnum());
			pstmt.setString(4, member.getMemId());

			pstmt.executeUpdate();
			// �Ϸ�� Ŀ��
			conn.commit(); 
						
		} catch (Exception e) {
			conn.rollback(); // ������ �ѹ�
			throw new RuntimeException(e.getMessage());
		} finally {
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null; }
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}		
	}// end updateMem
	
	// ȸ�� ���� ����	 
	public int delMem(String id, String pw) {

		String dbpw = ""; // DB���� ��й�ȣ�� ��Ƶ� ����
		int x = -1;

		try {
			// ��й�ȣ ��ȸ
			sb1 = new StringBuffer();
			sb1.append("SELECT MEMPW FROM MEMBER WHERE MEMID=?");

			// ȸ�� ����
			sb2 = new StringBuffer();
			sb2.append("DELETE FROM MEMBER WHERE MEMID=?");

			conn = DB.getConnction();

			// �ڵ� Ŀ���� false�� �Ѵ�.
			conn.setAutoCommit(false);
			
			// 1. ���̵� �ش��ϴ� ��й�ȣ�� ��ȸ�Ѵ�.
			pstmt = conn.prepareStatement(sb1.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) 
			{
				dbpw = rs.getString("memPw");
				if (dbpw.equals(pw)) // �Էµ� ��й�ȣ�� DB��� ��
				{
					// ������� ȸ������ ����
					pstmt = conn.prepareStatement(sb2.toString());
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					conn.commit(); 
					x = 1; // ���� ����
				} else {
					x = 0; // ��й�ȣ �ٸ� - ���� ����
				}
			}

			return x;

		} catch (Exception sqle) {
			try {
				conn.rollback(); // ������ �ѹ�
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null; }
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}
	}// end delMem
	
	// ȸ�� ���� ��� ������
	public ArrayList<memDTO> getMemList() {
		ArrayList<memDTO> memberList = new ArrayList<memDTO>();
        
        try {
            sb1 = new StringBuffer();
            sb1.append("SELECT * FROM MEMBER");
            
            conn = DB.getConnction();
            pstmt = conn.prepareStatement(sb1.toString());
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
	public boolean idChk(String id) {

		boolean x= false;
        
        try {
            // ����
            sb1 = new StringBuffer();
            sb1.append("SELECT MEMID FROM MEMBER WHERE MEMID=?");
                        
            conn = DB.getConnction();
            pstmt = conn.prepareStatement(sb1.toString());
            pstmt.setString(1, id);
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
    } // end duplicateIdCheck()
	
	// ȸ�� ���� �Է�(ȸ�� ����)
	public int addMem(memDTO member) throws SQLException {
        
        try {
            // Ŀ�ؼ��� �����´�.
            conn = DB.getConnction();
            
            // �ڵ� Ŀ���� false�� �Ѵ�.
            conn.setAutoCommit(false);
            
            // ���� ����
            // �������� ��� �ڵ����� ���õǰ� �ϱ� ���� sysdate�� ���
            sb1 = new StringBuffer();
            sb1.append("INSERT INTO MEMBER ");
            // sb1.append("INSERT INTO MEMBER (memId,memPw,memName,memGen,memBirth,memMail,memPnum,memIpD)");
            sb1.append("VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE)");        
            stringToDate(member);

            // StringBuffer�� ��� ���� �������� toString()�޼��带 ����ؾ� ��
            pstmt = conn.prepareStatement(sb1.toString());
            pstmt.setString(1, member.getMemId());
            pstmt.setString(2, member.getMemPw());
            pstmt.setString(3, member.getMemName());
            pstmt.setString(4, member.getMemGen());
            pstmt.setString(5, member.getMemBirth());
            pstmt.setString(6, member.getMemMail());
            pstmt.setInt(7, member.getMemPnum());
            pstmt.setDate(8, stringToDate(member));
            
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
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        } 
		return 0;
    }// end addMem
	
	// ���̵� ã��
	public memDTO findId(String name, String mail) {
			
		try {
			mem = new memDTO();
			
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
                mem = member;
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
			mem = new memDTO();
			
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
                mem = member;
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

}// end memDAO
