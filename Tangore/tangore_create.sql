drop table TESTTABLE;
CREATE TABLE TESTTABLE 
(
row_id number,
hiragana varchar2(30) not null, 
hanja varchar2(30), 
meaning varchar2(30) not null,
image blob not null,
 CONSTRAINT PK_TESTTABLE PRIMARY KEY(row_id)
);

drop SEQUENCE SEQ_TESTTABLE;
CREATE SEQUENCE SEQ_TESTTABLE START WITH 1 INCREMENT BY 1 MAXVALUE 99;

INSERT INTO TESTTABLE ( sequence_number, hiragana,meaning ) 
VALUES ( SEQ_TESTTABLE.NEXTVAL , '히라가나','뜻' );
select * from TESTTABLE;
desc TESTTABLE;