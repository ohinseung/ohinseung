--�ۼ��� : 2017-05-27.
/*�ۼ��� : ���϶�,���ο�,�̽���.
 * ����� id�� �����ϴ� table.
 * hr�������� �����ߴ�.
 * */
drop table user_id;

create table user_id
(
nickname varchar2(20) primary key,
status number(1) not null,
your_id varchar2(20),
correct_number varchar2(10)
);