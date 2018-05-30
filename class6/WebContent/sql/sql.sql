/* ȸ�� */
DROP TABLE member 
	CASCADE CONSTRAINTS;

/* ���� */
DROP TABLE lechure 
	CASCADE CONSTRAINTS;

/* ȸ���Խ��� */
DROP TABLE bbsMem 
	CASCADE CONSTRAINTS;

/* ���Խ��� */
DROP TABLE bbsQna 
	CASCADE CONSTRAINTS;

/* �⼮ */
DROP TABLE chk 
	CASCADE CONSTRAINTS;

/* ������ */
DROP TABLE class 
	CASCADE CONSTRAINTS;

/* ���� */
DROP TABLE grade 
	CASCADE CONSTRAINTS;

/* �系�Խ��� */
DROP TABLE bbsTeam 
	CASCADE CONSTRAINTS;

/* ��û */
DROP TABLE submit 
	CASCADE CONSTRAINTS;

/* �������� */
DROP TABLE bbs 
	CASCADE CONSTRAINTS;

/* �������� */
DROP TABLE privacy 
	CASCADE CONSTRAINTS;

/* ȸ�� */
CREATE TABLE member (
	memId VARCHAR2(20) NOT NULL, /* ���̵� */
	memPw VARCHAR2(20) NOT NULL, /* ��й�ȣ */
	memCate VARCHAR2(20), /* �з� */
	memIpD DATE, /* �Է��Ͻ� */
	memIpN VARCHAR2(20) /* �Է��� */
);

CREATE UNIQUE INDEX PK_member
	ON member (
		memId ASC
	);

ALTER TABLE member
	ADD
		CONSTRAINT PK_member
		PRIMARY KEY (
			memId
		);

/* ���� */
CREATE TABLE lechure (
	lecNo NUMBER NOT NULL, /* ���ǹ�ȣ */
	lecName VARCHAR2(100) NOT NULL, /* �����̸� */
	lecStart DATE NOT NULL, /* ���ǽ����� */
	lecEnd DATE NOT NULL, /* ���������� */
	lecCon LONG, /* ���Ǽ��� */
	teaId VARCHAR2(20) NOT NULL, /* ���� */
	classNo NUMBER NOT NULL, /* ������ */
	classNum NUMBER, /* �����ο� */
	lecIpD DATE NOT NULL, /* �Է��Ͻ� */
	lecIpN VARCHAR2(20) NOT NULL /* �Է��� */
);

CREATE UNIQUE INDEX PK_lechure
	ON lechure (
		lecNo ASC
	);

ALTER TABLE lechure
	ADD
		CONSTRAINT PK_lechure
		PRIMARY KEY (
			lecNo
		);

/* ȸ���Խ��� */
CREATE TABLE bbsMem (
	bbsMemNo NUMBER NOT NULL, /* �۹�ȣ */
	bbsMemCate VARCHAR2(20) NOT NULL, /* �з� */
	bbsMemName VARCHAR2(100) NOT NULL, /* ������ */
	bbsMemCon LONG, /* �۳��� */
	memId VARCHAR2(20) NOT NULL, /* �۾��� */
	bbsMemDate DATE NOT NULL, /* ��¥ */
	bbsMemView NUMBER /* ��ȸ�� */
);

CREATE UNIQUE INDEX PK_bbsMem
	ON bbsMem (
		bbsMemNo ASC
	);

ALTER TABLE bbsMem
	ADD
		CONSTRAINT PK_bbsMem
		PRIMARY KEY (
			bbsMemNo
		);

/* ���Խ��� */
CREATE TABLE bbsQna (
	bbsQnaNo NUMBER NOT NULL, /* �۹�ȣ */
	bbsQnaPw VARCHAR2(20) NOT NULL, /* ��й�ȣ */
	bbsQnaCate VARCHAR2(20) NOT NULL, /* �з� */
	bbsQnaName VARCHAR2(100) NOT NULL, /* ������ */
	bbsQnaCon LONG, /* �۳��� */
	bbsQnaW VARCHAR2(20) NOT NULL, /* �۾��� */
	bbsQnaIpD DATE NOT NULL, /* ��¥ */
	bbsQnaView NUMBER /* ��ȸ�� */
);

CREATE UNIQUE INDEX PK_bbsQna
	ON bbsQna (
		bbsQnaNo ASC
	);

ALTER TABLE bbsQna
	ADD
		CONSTRAINT PK_bbsQna
		PRIMARY KEY (
			bbsQnaNo
		);

/* �⼮ */
CREATE TABLE chk (
	memId VARCHAR2(20) NOT NULL, /* ���̵� */
	chkIpD DATE NOT NULL, /* �Է��Ͻ� */
	chkUdD DATE, /* �����Ͻ� */
	teaId VARCHAR2(20) /* ������� */
);

CREATE UNIQUE INDEX PK_chk
	ON chk (
		memId ASC
	);

ALTER TABLE chk
	ADD
		CONSTRAINT PK_chk
		PRIMARY KEY (
			memId
		);

/* ������ */
CREATE TABLE class (
	classNo NUMBER NOT NULL, /* �������ȣ */
	className VARCHAR2(40) NOT NULL, /* ������� */
	classAddress VARCHAR2(40), /* ��������ġ */
	classIpD DATE NOT NULL, /* �Է��Ͻ� */
	classIpN VARCHAR2(20) NOT NULL /* �Է��� */
);

CREATE UNIQUE INDEX PK_class
	ON class (
		classNo ASC
	);

ALTER TABLE class
	ADD
		CONSTRAINT PK_class
		PRIMARY KEY (
			classNo
		);

/* ���� */
CREATE TABLE grade (
	memId VARCHAR2(20) NOT NULL, /* ���̵� */
	graName VARCHAR2(40), /* ȸ���̸� */
	graJava NUMBER(3,0), /* JAVA */
	graWeb NUMBER(3,0), /* WEB */
	graDb NUMBER(3,0), /* DB */
	graIpD DATE NOT NULL, /* �Է��Ͻ� */
	teaId VARCHAR2(20) NOT NULL, /* �Է��� */
	teaUdD DATE, /* �����Ͻ� */
	teaUdN VARCHAR2(20) /* �����Է� */
);

CREATE UNIQUE INDEX PK_grade
	ON grade (
		memId ASC
	);

ALTER TABLE grade
	ADD
		CONSTRAINT PK_grade
		PRIMARY KEY (
			memId
		);

/* �系�Խ��� */
CREATE TABLE bbsTeam (
	bbsTeamNo NUMBER NOT NULL, /* �۹�ȣ */
	bbsTeamCate VARCHAR2(20) NOT NULL, /* �з� */
	bbsTeamName VARCHAR2(100) NOT NULL, /* ������ */
	bbsTeamCon LONG, /* �۳��� */
	bbsTeamW VARCHAR2(20) NOT NULL, /* �۾��� */
	bbsTeamDate DATE NOT NULL, /* ��¥ */
	bbsTeamView NUMBER /* ��ȸ�� */
);

CREATE UNIQUE INDEX PK_bbsTeam
	ON bbsTeam (
		bbsTeamNo ASC
	);

ALTER TABLE bbsTeam
	ADD
		CONSTRAINT PK_bbsTeam
		PRIMARY KEY (
			bbsTeamNo
		);

/* ��û */
CREATE TABLE submit (
	lecNo NUMBER NOT NULL, /* ���ǹ�ȣ */
	memId VARCHAR2(20) NOT NULL /* ��û�� */
);

CREATE UNIQUE INDEX PK_submit
	ON submit (
		lecNo ASC
	);

ALTER TABLE submit
	ADD
		CONSTRAINT PK_submit
		PRIMARY KEY (
			lecNo
		);

/* �������� */
CREATE TABLE bbs (
	bbsNo NUMBER NOT NULL, /* �۹�ȣ */
	bbsCate VARCHAR2(20) NOT NULL, /* �з� */
	bbsName VARCHAR2(100) NOT NULL, /* ������ */
	bbsCon LONG, /* �۳��� */
	bbsW VARCHAR2(20) NOT NULL, /* �۾��� */
	bbsDate DATE NOT NULL, /* ��¥ */
	bbsView NUMBER, /* ��ȸ�� */
	bbsUdD DATE /* �����Ͻ� */
);

CREATE UNIQUE INDEX PK_bbs
	ON bbs (
		bbsNo ASC
	);

ALTER TABLE bbs
	ADD
		CONSTRAINT PK_bbs
		PRIMARY KEY (
			bbsNo
		);

/* �������� */
CREATE TABLE privacy (
	memId VARCHAR2(20) NOT NULL, /* ���̵� */
	memNo NUMBER NOT NULL, /* ȸ����ȣ */
	lecNo NUMBER, /* ���ǹ�ȣ */
	memName VARCHAR2(40) NOT NULL, /* �̸� */
	memGen VARCHAR2(4) NOT NULL, /* ���� */
	memBirth DATE NOT NULL, /* ������� */
	memMail VARCHAR2(40) NOT NULL, /* �̸��� */
	memPnum NUMBER(11,0) NOT NULL /* ��ȭ��ȣ */
);

CREATE UNIQUE INDEX PK_privacy
	ON privacy (
		memId ASC
	);

ALTER TABLE privacy
	ADD
		CONSTRAINT PK_privacy
		PRIMARY KEY (
			memId
		);

ALTER TABLE lechure
	ADD
		CONSTRAINT FK_member_TO_lechure
		FOREIGN KEY (
			teaId
		)
		REFERENCES member (
			memId
		);

ALTER TABLE lechure
	ADD
		CONSTRAINT FK_member_TO_lechure2
		FOREIGN KEY (
			lecIpN
		)
		REFERENCES member (
			memId
		);

ALTER TABLE lechure
	ADD
		CONSTRAINT FK_class_TO_lechure
		FOREIGN KEY (
			classNo
		)
		REFERENCES class (
			classNo
		);

ALTER TABLE bbsMem
	ADD
		CONSTRAINT FK_member_TO_bbsMem
		FOREIGN KEY (
			memId
		)
		REFERENCES member (
			memId
		);

ALTER TABLE bbsQna
	ADD
		CONSTRAINT FK_member_TO_bbsQna
		FOREIGN KEY (
			bbsQnaW
		)
		REFERENCES member (
			memId
		);

ALTER TABLE chk
	ADD
		CONSTRAINT FK_member_TO_chk
		FOREIGN KEY (
			memId
		)
		REFERENCES member (
			memId
		);

ALTER TABLE chk
	ADD
		CONSTRAINT FK_member_TO_chk2
		FOREIGN KEY (
			teaId
		)
		REFERENCES member (
			memId
		);

ALTER TABLE class
	ADD
		CONSTRAINT FK_member_TO_class
		FOREIGN KEY (
			classIpN
		)
		REFERENCES member (
			memId
		);

ALTER TABLE grade
	ADD
		CONSTRAINT FK_member_TO_grade
		FOREIGN KEY (
			teaId
		)
		REFERENCES member (
			memId
		);

ALTER TABLE grade
	ADD
		CONSTRAINT FK_member_TO_grade2
		FOREIGN KEY (
			memId
		)
		REFERENCES member (
			memId
		);

ALTER TABLE bbsTeam
	ADD
		CONSTRAINT FK_member_TO_bbsTeam
		FOREIGN KEY (
			bbsTeamW
		)
		REFERENCES member (
			memId
		);

ALTER TABLE submit
	ADD
		CONSTRAINT FK_lechure_TO_submit
		FOREIGN KEY (
			lecNo
		)
		REFERENCES lechure (
			lecNo
		);

ALTER TABLE submit
	ADD
		CONSTRAINT FK_member_TO_submit
		FOREIGN KEY (
			memId
		)
		REFERENCES member (
			memId
		);

ALTER TABLE bbs
	ADD
		CONSTRAINT FK_member_TO_bbs
		FOREIGN KEY (
			bbsW
		)
		REFERENCES member (
			memId
		);

ALTER TABLE privacy
	ADD
		CONSTRAINT FK_member_TO_privacy
		FOREIGN KEY (
			memId
		)
		REFERENCES member (
			memId
		);

ALTER TABLE privacy
	ADD
		CONSTRAINT FK_lechure_TO_privacy
		FOREIGN KEY (
			lecNo
		)
		REFERENCES lechure (
			lecNo
		);
		
drop sequence bbsQna_seq;
create sequence bbsQna_seq;

drop sequence privacy_seq;
create sequence privacy_seq;

drop sequence bbsMem_seq;
create sequence bbsMem_seq;

drop sequence cmt_seq;
create sequence cmt_seq;

drop sequence bbs_seq;
create sequence bbs_seq;

drop sequence bbsTeam_seq;
create sequence bbsTeam_seq;


/* ���� */
insert into member values('E1',1234,'����',sysdate,'');

/* ���� */
insert into member values('T1',1234,'����',sysdate,'E1');

/* ������ */
insert into class values(1,'�ڹٰ�����','6������',sysdate,'E1');

/* ���� */
insert into lechure values(1,'�ڹ�',sysdate,sysdate,'���ǳ���','T1',1,20,sysdate,'E1');

/* ȸ�� */
insert into member values('abc',1234,'�л�',sysdate,'');

/* �������� */
insert into privacy values('abc',privacy_seq.nextval,1,'�л��̸�','��',sysdate,'abc@gamil.com',01012345678);

/* ȸ���Խ��� */
insert into bbsMem values(bbsmem_seq.nextval,'���','ù��°��','���빫','abc',sysdate,1);

/* ���Խ��� */
insert into bbsQna values(bbsQna_seq.nextval,1234,'���','ù��°��','���빫','E1',sysdate,1);

/* �⼮ */
insert into chk values('abc',sysdate,'','');

/* ���� */
insert into grade values('abc','abc',100,100,100,sysdate,'T1','','');

/* �系�Խ��� */
insert into bbsTeam values(bbsTeam_seq.nextval,'���','ù��°��','���빫','1',sysdate,1);

/* ��û */
insert into submit values(1,'abc');

/* �������� */
insert into bbs values(bbs_seq.nextval,'���','ù��°��','���빫','E1',sysdate,1,'');


