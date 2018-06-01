package com.user.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DB;

import com.user.model.DTO.bbsDTO;


public class bbsDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
//���� �������װԽ��� 	
	public ArrayList<bbsDTO> view(){
		String sql="SELECT bbsNo,bbsname,bbsdate FROM BBS ORDER BY BBSNO DESC";
		
		ArrayList<bbsDTO> bbs=new ArrayList<bbsDTO>();
		
		try {
			try{
				conn=DB.getConnction();
				//conn=db.getConnction();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				bbsDTO view = new bbsDTO();
				view.setBbsNo(rs.getInt("bbsNo"));
				view.setBbsName(rs.getString("bbsname"));
				view.setBbsDate(rs.getDate("bbsdate"));
				bbs.add(view);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bbs;
	}

	
//���� ���Խ��� 	
	public ArrayList<bbsDTO> viewqna(){
		String sql="SELECT * FROM BBSQNA ORDER BY BBSQNAIPD DESC";
		
		ArrayList<bbsDTO> bbsqna=new ArrayList<bbsDTO>();
		
		try {
			try{
				conn=DB.getConnction();
				//conn=db.getConnction();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
		
			while(rs.next()){
				bbsDTO dto = new bbsDTO();
				dto.setBbsQnaNo(rs.getInt("BbsQnaNo"));
				dto.setBbsQnaName(rs.getString("BbsQnaName"));
				dto.setBbsQnaIpD(rs.getDate("BBSQNAIPD"));
				bbsqna.add(dto);
				System.out.println(dto.getBbsNo());
				System.out.println(dto.getBbsName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bbsqna;
	}
	
	
	
//�������� ����Ʈ������
	public ArrayList<bbsDTO> selectAll(){
		String sql="SELECT * FROM BBS ORDER BY BBSNO DESC";
		
		ArrayList<bbsDTO> list=new ArrayList<bbsDTO>();
		
		try {
			try{
				conn=DB.getConnction();
				//conn=db.getConnction();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				bbsDTO bean = new bbsDTO();
				bean.setBbsNo(rs.getInt("bbsNo"));
				bean.setBbsCate(rs.getString("bbsCate"));
				bean.setBbsName(rs.getString("bbsName"));
				bean.setBbsCon(rs.getString("bbsCon"));
				bean.setBbsW(rs.getString("bbsW"));
				bean.setBbsDate(rs.getDate("bbsDate"));
				bean.setBbsView(rs.getInt("bbsView"));
				bean.setBbsUdD(rs.getDate("bbsUdD"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
    
//�������� ��������	
	public bbsDTO selectOne(int bbsNo){
		String sql="SELECT * FROM BBS WHERE BBSNO=?";
		bbsDTO bean = new bbsDTO();
		
		try {
			try{
				conn=DB.getConnction();
				//conn=db.getConnction();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bbsNo);
			rs=pstmt.executeQuery();
			if(rs.next()){
				bean.setBbsNo(rs.getInt("bbsNo"));
				bean.setBbsName(rs.getString("bbsName"));
				bean.setBbsCon(rs.getString("bbsCon"));
				bean.setBbsW(rs.getString("bbsW"));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bean;
	}
	

	//�������� ��ȸ�� ����
	public int updatecnt(int bbsNo) {
		String sql ="update bbs set bbsView = bbsView +1 where bbsno=?";
		
		try {
			try{
				conn=DB.getConnction();
				//conn=db.getConnction();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bbsNo);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}		

	//�������� ����
	public int updateOne(int bbsNo, String bbsName,String bbsCon,String bbsW) {
		String sql ="UPDATE BBS SET BBSCON=? , BBSUDD =SYSDATE,BBSNAME=?,BBSW=? WHERE BBSNO=?";
		int result = 0;
		try {
			try{
				conn=DB.getConnction();
				//conn=db.getConnction();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bbsCon);
			pstmt.setString(2, bbsName);
			pstmt.setString(3, bbsW);
			pstmt.setInt(4, bbsNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}	

//����
	public void deleteOne(int bbsNo) {

		String sql="DELETE FROM BBS WHERE BBSNO=?";
		
	try {
			
		try{
			conn=DB.getConnction();
			//conn=db.getConnction();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bbsNo);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{

			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//�������� �Է�
	public void insertOne(String bbsName, String bbsCon) {
		String sql="insert into bbs values (bbsTeam_seq.nextval,'���',?,?,'E1',sysdate,1,sysdate)";
		
		try {
			
			try{
				conn=DB.getConnction();
				//conn=db.getConnction();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bbsName);
			pstmt.setString(2, bbsCon);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{

			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


    /**
     *  �˻�â
     **/
    public ArrayList<bbsDTO> getMemberlist(String keyField, String keyWord) {
       
        String sql ="select * from BBS ";
        
        if(keyWord != null && !keyWord.equals("") ){
            sql +="WHERE "+keyField.trim()+" LIKE '%"+keyWord.trim()+"%' order by bbsno";
        }else{//��� ���ڵ� �˻�
            sql +="order by bbsno desc";
        }
        
        System.out.println("sql = " + sql);
    	
        ArrayList<bbsDTO> sh = new ArrayList<bbsDTO>();
       
    	try {
    		try{
				conn=DB.getConnction();
				//conn=db.getConnction();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				bbsDTO view = new bbsDTO();
				view.setBbsNo(rs.getInt("bbsNo"));
				view.setBbsName(rs.getString("bbsname"));
				view.setBbsW(rs.getString("bbsW"));
				view.setBbsDate(rs.getDate("bbsdate"));
				view.setBbsView(rs.getInt("bbsView"));
				view.setBbsUdD(rs.getDate("bbsUdD"));
				sh.add(view);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        return sh;
    }  
    
    
}
