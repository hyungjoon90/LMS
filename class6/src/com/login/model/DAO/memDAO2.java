package com.login.model.DAO;

import java.sql.*;
import java.util.ArrayList;

import util.DB;

import com.login.model.DTO.memDTO;

public class memDAO2 //ȸ������ �� �˻� DAO
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
        String birth = member.getMemBirth();
        Date birthday = Date.valueOf(birth);
        
        return birthday;
        
    } // end stringToDate()
    
    
	
 // ȸ�� �Է� ���� ��ȸ
    public memDTO getUserInfo(String id) {

		try {
			// ����
			sql1 = "SELECT * FROM PRIVACY WHERE MEMID=?";

			conn = DB.getConnction();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) // ȸ�������� DTO�� ��´�.
			{
				// �ڹٺ� ������ ��´�.
				member = new memDTO();
				member.setMemId(rs.getString("memId"));
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
				if ( conn != null ){ conn.close(); conn=null; }
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}
	}// end getUserInfo()
	
	
	// ���̵� ã��
	public memDTO findId(String name, String mail) {
			
		try {
			mem = new memDTO();
			
			conn=DB.getConnction();
			
			sql1 = "SELECT MEMID FROM PRIVACY WHERE MEMNAME=? AND MEMMAIL=?";

			pstmt = conn.prepareStatement(sql1.toString());
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
                if ( conn != null ){ conn.close(); conn=null; }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
		}
		return null;
	}// end findId
	
	// ��� ã��
//	public memDTO findPw(String id, String name, String mail) {
	public memDTO findPw(String name, String mail) {
			
		try {
			mem = new memDTO();
			
			conn=DB.getConnction();
			
			sql1 = "SELECT MEMPW FROM MEMBER WHERE MEMID = (SELECT MEMID FROM PRIVACY WHERE MEMNAME=? AND MEMMAIL=?)";
//			sql1 = "SELECT MEMPW, MEMID FROM MEMBER WHERE MEMID = (SELECT MEMID FROM PRIVACY WHERE MEMNAME=? AND MEMMAIL=?)";
			
			pstmt = conn.prepareStatement(sql1.toString());
			pstmt.setString(1, name);
			pstmt.setString(2, mail);
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
                if ( conn != null ){ conn.close(); conn=null; }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
		}
		return null;
	}// end findPw

}// end memDAO
