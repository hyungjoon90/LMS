package com.login.model.DAO;

import java.sql.*;
import java.util.ArrayList;

import util.DBManager;


import com.login.model.DTO.memDTO;


public class memDAO 
{
    // �̱��� ����
	private static memDAO instance;
    
    // �̱��� ����
    private memDAO(){}
    public static memDAO getInstance(){
        if(instance==null)
            instance=new memDAO();
        return instance;
    }
    
    // String -> Date�� �����ϴ� �޼���
    // ���ڿ��ε� ��������� Date�� �����ϱ� ���� �ʿ�
    // java.util.DateŬ�����δ� ����Ŭ�� Date���İ� ������ �� ����.
    // Oracle�� date���İ� �����Ǵ� java�� Date�� java.sql.Date Ŭ�����̴�.
    public Date stringToDate(memDTO member)
    {
        String birth = member.getMemBirth();
        Date birthday = Date.valueOf(birth);
        
        return birthday;
        
    } // end stringToDate()
    
    // �α��ν� ���̵�, ��й�ȣ üũ �޼���
    // ���̵�, ��й�ȣ�� ���ڷ� �޴´�.
    public int loginCheck(String id, String pw) 
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
 
        String dbPW = ""; // db���� ���� ��й�ȣ�� ���� ����
        int x = -1;
 
        try {
            // ���� - ���� �Էµ� ���̵�� DB���� ��й�ȣ�� ��ȸ�Ѵ�.
            StringBuffer query = new StringBuffer();
            query.append("SELECT MEMPW FROM MEMBER WHERE MEMID=?");
 
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query.toString());
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
 
            if (rs.next()) // �Է��� ���̵� �ش��ϴ� ��� �������
            {
                dbPW = rs.getString("memPw"); // ����� ������ �ִ´�.
 
                if (dbPW.equals(pw)) 
                    x = 1; // �Ѱܹ��� ����� ������ ��� ��. ������ ��������
                else                  
                    x = 0; // DB�� ��й�ȣ�� �Է¹��� ��й�ȣ �ٸ�, ��������
                
            } else {
                x = -1; // �ش� ���̵� ���� ���
            }
 
            return x;
 
        } catch (Exception sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    } // end loginCheck()
	
    // ȸ�� ���� ������
    public memDTO getUserInfo(String id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		memDTO member = null;

		try {
			// ����
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM JSP_MEMBER WHERE ID=?");

			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query.toString());
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

		} catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
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
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			StringBuffer query = new StringBuffer();
			query.append("UPDATE MEMBER SET MEMPW=?, MEMMAIL=?, MEMPNUM=? WHERE MEMID=?");

			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query.toString());

			// �ڵ� Ŀ���� false�� �Ѵ�.
			conn.setAutoCommit(false);
			
			pstmt.setString(1, member.getMemPw());
			pstmt.setString(2, member.getMemMail());
			pstmt.setInt(3, member.getMemPnum());
			pstmt.setString(5, member.getMemId());

			pstmt.executeUpdate();
			// �Ϸ�� Ŀ��
			conn.commit(); 
						
		} catch (Exception sqle) {
			conn.rollback(); // ������ �ѹ�
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}		
	}// end updateMem
	
	// ȸ�� ���� ����	 
	@SuppressWarnings("resource")
	public int delMem(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dbpw = ""; // DB���� ��й�ȣ�� ��Ƶ� ����
		int x = -1;

		try {
			// ��й�ȣ ��ȸ
			StringBuffer query1 = new StringBuffer();
			query1.append("SELECT MEMPW FROM MEMBER WHERE MEMID=?");

			// ȸ�� ����
			StringBuffer query2 = new StringBuffer();
			query2.append("DELETE FROM MEMBER WHERE MEMID=?");

			conn = DBManager.getConnection();

			// �ڵ� Ŀ���� false�� �Ѵ�.
			conn.setAutoCommit(false);
			
			// 1. ���̵� �ش��ϴ� ��й�ȣ�� ��ȸ�Ѵ�.
			pstmt = conn.prepareStatement(query1.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) 
			{
				dbpw = rs.getString("memPw");
				if (dbpw.equals(pw)) // �Էµ� ��й�ȣ�� DB��� ��
				{
					// ������� ȸ������ ����
					pstmt = conn.prepareStatement(query2.toString());
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					conn.commit(); 
					x = 1; // ���� ����
				} else {
					x = 0; // ��й�ȣ �񱳰�� - �ٸ�
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
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}
	}// end delMem
	
	// ȸ�� ���� ��� ������
	public ArrayList<memDTO> getMemList() {
		ArrayList<memDTO> memberList = new ArrayList<memDTO>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        memDTO member = null;
        
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT * FROM MEMBER");
            
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query.toString());
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
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    } // end getMemList

	// �ߺ� �˻�
	public boolean duplicateIdCheck(String id) {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        boolean x= false;
        
        try {
            // ����
            StringBuffer query = new StringBuffer();
            query.append("SELECT MEMID FROM MEMBER WHERE MEMID=?");
                        
            conn = DBManager.getConnection();
            pstm = conn.prepareStatement(query.toString());
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            
            if(rs.next()) x= true; //�ش� ���̵� ����
            
            return x;
            
        } catch (Exception sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            try{
                if ( pstm != null ){ pstm.close(); pstm=null; }
                if ( conn != null ){ conn.close(); conn=null; }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    } // end duplicateIdCheck()
	
	// ȸ�� ���� �Է�
	public int addMem(memDTO member) throws SQLException {
		Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Ŀ�ؼ��� �����´�.
            conn = DBManager.getConnection();
            
            // �ڵ� Ŀ���� false�� �Ѵ�.
            conn.setAutoCommit(false);
            
            // ���� ����
            // �������� ��� �ڵ����� ���õǰ� �ϱ� ���� sysdate�� ���
            StringBuffer sql = new StringBuffer();
            sql.append("insert into MEMBER values");
            sql.append("(?, ?, ?, ?, ?, ?, ?, sysdate)");        
            stringToDate(member);

            // StringBuffer�� ��� ���� �������� toString()�޼��带 ����ؾ� ��
            pstmt = conn.prepareStatement(sql.toString());
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
            
        } catch (SQLException sqle) {
            // ������ �ѹ�
            conn.rollback(); 
            
            throw new RuntimeException(sqle.getMessage());
        } finally {
            // Connection, PreparedStatement�� �ݴ´�.
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        } // end try~catch 
		return 0;
    }// end addMem
	
	// ���̵� ã��
	public memDTO findId(String name, String mail) {
		
		return null;
	}// end findId
	
	// ��� ã��
	public memDTO findPw(String id, String name, String mail) {
		
		return null;
	}// end findPw

}// end memDAO
