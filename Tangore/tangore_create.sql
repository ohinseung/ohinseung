drop table TANGORE ;
CREATE TABLE TANGORE  
(
row_id number,
hiragana varchar2(30) not null, 
hanja varchar2(30), 
meaning varchar2(30) not null,
image blob not null,
CONSTRAINT PK_TANGORE PRIMARY KEY(row_id)
);

drop SEQUENCE SEQ_TANGORE ;
CREATE SEQUENCE SEQ_TANGORE  START WITH 1 INCREMENT BY 1 MAXVALUE 99;

INSERT INTO TANGORE ( sequence_number, hiragana,meaning ) 
VALUES ( SEQ_TANGORE.NEXTVAL , '히라가나','뜻' );
select * from TANGORE;
desc TANGORE;