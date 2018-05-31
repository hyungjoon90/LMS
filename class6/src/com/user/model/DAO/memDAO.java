package com.user.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DB;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.user.model.DTO.memDTO;





import util.DB;

public class memDAO {//mypage DAO
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
		//mypage�� ǥ���� ���� ���
		public List<memDTO> getMyPage(String memId) throws SQLException {
		
		List<memDTO> list = new ArrayList<memDTO>();
		String sql1="SELECT LECNAME,LECSTART,LECEND FROM LECHURE WHERE LECNO=(SELECT LECNO FROM PRIVACY WHERE MEMID=?)";
		String sql2="SELECT GRAJAVA,GRAWEB,GRADB FROM GRADE WHERE MEMID=?";
		String sql3="SELECT COUNT(*) AS CNT FROM CHK WHERE MEMID=? AND (TO_CHAR( CHKIPD, 'YYYY-MM-DD' ) > SYSDATE-1)";
		
		System.out.println(memId);//id Ȯ�����
		memDTO mDTO = new memDTO();
		try{
			try{
				conn=DB.getConnction();
				//conn=db.getConnction();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//���ǿ� ���� ����
			pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, memId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				mDTO.setLecName(rs.getString("lecName"));
				mDTO.setLecStart(rs.getDate("lecStart"));
				mDTO.setLecEnd(rs.getDate("lecEnd"));
			}
			//������ ���� ����
			pstmt=conn.prepareStatement(sql2);
			pstmt.setString(1, memId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				mDTO.setGraJava(rs.getInt("graJava"));
				mDTO.setGraWeb(rs.getInt("graWeb"));
				mDTO.setGraDb(rs.getInt("graDb"));
			}
			//�⼮�� ���� ����
			pstmt=conn.prepareStatement(sql3);
			pstmt.setString(1, memId);
			rs=pstmt.executeQuery();
			String result;
			if(rs.next()){
				if(rs.getInt("cnt")>=1){result="�⼮�Ϸ�";}else{result="���⼮";};
				mDTO.setChk(result);
			}
			
			list.add(mDTO);
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
		return list;
		}

		// ȸ�� ���� ��ȸ
	    public List<memDTO> getUserInfo(String memId) throws SQLException {
	    	String sql="SELECT * FROM PRIVACY WHERE MEMID=?";
	    	
	    	List<memDTO> list = new ArrayList<memDTO>();
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
					dto.setMemId(rs.getString("memId"));
					dto.setMemName(rs.getString("memName"));
					dto.setMemGen(rs.getString("memGen"));
					dto.setMemBirth(rs.getDate("memBirth"));
					dto.setMemMail(rs.getString("memMail"));
					dto.setMemPnum(rs.getInt("memPnum"));
				}
				
				//ȸ������(��й�ȣ)
				String sql2="SELECT MEMPW FROM MEMBER WHERE MEMID=?";
				pstmt=conn.prepareStatement(sql2);
				pstmt.setString(1, memId);
				rs=pstmt.executeQuery();
				if(rs.next()){
					dto.setMemPw(rs.getString("memPw"));
				}
				
				list.add(dto);
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
			return list;
	    }// end getUserInfo


		//�⼮üũ
		public void putChk(String memId) throws SQLException {
			System.out.println("�⼮üũ ����");
			int result = 0;
			String sql="INSERT INTO CHK VALUES(CHK_SEQ.NEXTVAL,?,SYSDATE,'','')";
			
			memDTO mDTO = new memDTO();
			try {
				try{
					conn=DB.getConnction();
					//conn=db.getConnction();
				}catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				//�⼮�� ���� ����
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, memId);
				result=pstmt.executeUpdate();
				
				//conn.commit();
			}catch(SQLException e){
				//conn.rollback();
			}finally{
				conn.setAutoCommit(true);
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			System.out.println("�⼮�Ϸ�");
			}
		//�⼮üũ


	    // ȸ�� ���� ����
		public void update(memDTO member,Date d) throws SQLException {

			try {

				String sql1="UPDATE MEMBER SET MEMPW=? WHERE MEMID=?";
				String sql2="UPDATE PRIVACY SET MEMNAME=?,MEMGEN=?,MEMBIRTH=?, MEMMAIL=?,MEMPNUM=? WHERE MEMID=?";
				
				conn = DB.getConnction();
				pstmt = conn.prepareStatement(sql1);

				// �ڵ� Ŀ���� false��
				conn.setAutoCommit(false);
				
				pstmt.setString(1, member.getMemPw());
				pstmt.setString(2, member.getMemId());

				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, member.getMemName());
				pstmt.setString(2, member.getMemGen());
				pstmt.setDate(3, d);
				pstmt.setString(4, member.getMemMail());
				pstmt.setInt(5, member.getMemPnum());
				pstmt.setString(6, member.getMemId());
				
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
		/*
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
		}// end delMem*/
}
