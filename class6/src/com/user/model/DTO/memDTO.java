package com.user.model.DTO;

import java.sql.Date;

public class memDTO {

	private String memId;		// ���̵�(20)
    private String memPw;		// ��й�ȣ(20)
    private int memNo;			// ȸ����ȣ
    private String memName;		// �̸�(40)
    private String memGen;		// ����(4)
    private String memBirth;	// �������(8)
    private String memMail;		// �̸���(40)
    private int memPnum;		// ��ȭ(11)
    private Date memIpD;		// �Է�����
    private String memIpN;		// �Է���(40)
    private Date memUpD;		// ��������
    private String memUpN;		// ������(40)
	
	public memDTO() {
		
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemGen() {
		return memGen;
	}

	public void setMemGen(String memGen) {
		this.memGen = memGen;
	}

	public String getMemBirth() {
		return memBirth;
	}

	public void setMemBirth(String memBirth) {
		this.memBirth = memBirth;
	}

	public String getMemMail() {
		return memMail;
	}

	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}

	public int getMemPnum() {
		return memPnum;
	}

	public void setMemPnum(int memPnum) {
		this.memPnum = memPnum;
	}

	public Date getMemIpD() {
		return memIpD;
	}

	public void setMemIpD(Date memIpD) {
		this.memIpD = memIpD;
	}

	public String getMemIpN() {
		return memIpN;
	}

	public void setMemIpN(String memIpN) {
		this.memIpN = memIpN;
	}

	public Date getMemUpD() {
		return memUpD;
	}

	public void setMemUpD(Date memUpD) {
		this.memUpD = memUpD;
	}

	public String getMemUpN() {
		return memUpN;
	}

	public void setMemUpN(String memUpN) {
		this.memUpN = memUpN;
	}

}
