--작성일 : 2017-05-27.
/*작성자 : 오하라,김인우,이승현.
 * 사용자 id를 보관하는 table.
 * hr계정에서 생성했다.
 * */
drop table user_id;

create table user_id
(
nickname varchar2(20) primary key,
status number(1) not null,
your_id varchar2(20),
correct_number varchar2(10)
);