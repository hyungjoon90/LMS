package com.user.model.DTO;

import java.sql.Date;

public class bbsMemDTO 
{
	private int bbsMemNo;  		// �۹�ȣ
	private String bbsMemCate; 	// �� �з�
	private String bbsMemName; 	// �� ����
	private String bbsMemCon; 		// �� ����
	private String memId; 			// �۾���
	private Date bbsMemDate; 		// �ۼ���
	private int bbsMemView; 		// ��ȸ��
	private Date bbsMemUdD; 		// ������
	
	public int getBbsMemNo() {
		return bbsMemNo;
	}
	public void setBbsMemNo(int bbsMemNo) {
		this.bbsMemNo = bbsMemNo;
	}
	public String getBbsMemCate() {
		return bbsMemCate;
	}
	public void setBbsMemCate(String bbsMemCate) {
		this.bbsMemCate = bbsMemCate;
	}
	public String getBbsMemName() {
		return bbsMemName;
	}
	public void setBbsMemName(String bbsMemName) {
		this.bbsMemName = bbsMemName;
	}
	public String getBbsMemCon() {
		return bbsMemCon;
	}
	public void setBbsMemCon(String bbsMemCon) {
		this.bbsMemCon = bbsMemCon;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public Date getBbsMemDate() {
		return bbsMemDate;
	}
	public void setBbsMemDate(Date bbsMemDate) {
		this.bbsMemDate = bbsMemDate;
	}
	public int getBbsMemView() {
		return bbsMemView;
	}
	public void setBbsMemView(int bbsMemView) {
		this.bbsMemView = bbsMemView;
	}
	public Date getBbsMemUdD() {
		return bbsMemUdD;
	}
	public void setBbsMemUdD(Date bbsMemUdD) {
		this.bbsMemUdD = bbsMemUdD;
	}
	

}
