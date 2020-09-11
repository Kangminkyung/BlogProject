create table spring_myBatisTest
(no number
,name varchar2(20)
,email  varchar2(30)
,tel  varchar2(20)
,addr varchar2(200)
,writeday date default  sysdate
,constraint PK_spring_myBatisTest_no primary key(no)
);

create sequence seq_myBatisTest
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

select *
from spring_myBatisTest
order by no desc ;

