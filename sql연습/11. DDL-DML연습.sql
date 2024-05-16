-- DDL/DML 연습

create table member(
	no int not null auto_increment,
    email varchar(200) not null default '',
    password varchar(64) not null,
    name varchar(50) not null,
    department varchar(100),
    
    primary key(no)
);

desc member;

drop table member;



alter table member add column juminbunho char(13) not null;

alter table member drop juminbunho;

alter table member add column juminbunho char(13) not null after email;

desc member;

alter table member change column department dept varchar(100) not null;

alter table member add column self_intro text;
desc member;

alter table member drop juminbunho;
desc member;

-- insert
insert into member values(null,'asd@naver.com',password('qwer1234'),'세세세','개발팀',null);

select * from member;

insert into member(no,email,name,dept,password) values(null,'asd2@naver.com','세세세세','개발팀',password('qwer1234'));

select * from member;

-- update
update member
	set email='asd33@naver.com',password=password("qwer4321")
where no=2;

select * from member;

-- delete
delete from member where no=2;

select * from member;


-- transaction

select * from member;

select @@autocommit; -- 1

insert into member values(null,'asd2@naver.com',password('qwer1234'),'세세세세','개발팀',null);

select * from member;


-- transaction begin

set autocommit=0;
select @@autocommit; -- 0


insert into member values(null,'asd3@naver.com',password('qwer1234'),'세세세3','개발팀',null);

select * from member;

-- transaction end
commit;




