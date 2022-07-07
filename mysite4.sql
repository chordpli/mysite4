-- guestBook table ����
create table guestbook(
    no number
    ,name varchar2(80)
    ,password varchar2(20)
    ,content varchar2(2000)
    ,reg_date date
    ,primary key (no)
);

-- table ����
create table files(
    no number
    ,org_name varchar2(100)
    ,save_name varchar2(100)
    ,file_path varchar2(100)
    ,file_size number(38)
    ,primary key(no)
);

select *
from files;

create sequence seq_files_no
increment by 1
start with 1
nocache;

-- Users Table ����

create table users(
        no number
        ,id varchar2(20) unique not null
        ,password varchar(20) not null
        ,name varchar(20)
        ,gender varchar2(10)
        ,primary key(no)
);

-- users no ������ ����
create sequence seq_users_no
increment by 1
start with 1
nocache;

-- board table ����
create table board(
        no number
        ,title varchar2(50)
        ,content varchar2(4000)
        ,hit number default 0
        ,reg_date date not null
        ,user_no number not null
        ,primary key(no)
        ,constraint user_no_fk foreign key(user_no)
        references users(no)
);

drop table board;

create sequence seq_board_no
increment by 1
start with 1
nocache;


-- GuestBook Setting
select *
from guestbook;

select no
        ,name
        ,password
        ,content
        ,to_char(reg_date, 'yyyy-mm-dd hh24:mi:ss') day
from guestbook;

insert into guestbook
values (seq_guestbook_no, name, password, content, sysdate);


-- Users Setting
select *
from users;

update users
set passowrd = #{password}
    ,name = #{name}
    ,gender = #{gender}
where id = #{id};

-------------- gallery table ����
create table gallery(
        no number
        ,user_no number
        ,content varchar2(1000)
        ,file_path varchar2(500)
        ,org_name varchar2(200)
        ,save_name varchar2(500)
        ,file_size number
        ,primary key(no)
        ,constraint gallery_no_fk foreign key(user_no)
        references users(no)
);

create sequence seq_gallery_no
increment by 1
start with 1
nocache;

drop table gallery;

select g.no
        ,g.user_no
        ,g.content
        ,g.save_name
        ,u.name
from gallery g, users u
where g.user_no = u.no;



-- board table ����
create table board(
        no number
        ,title varchar2(50)
        ,content varchar2(4000)
        ,hit number default 0
        ,reg_date date not null
        ,user_no number not null
        ,primary key(no)
        ,constraint user_no_fk foreign key(user_no)
        references users(no)
);

-- rBoard table create
create table rboard(
    no number
    ,user_no number not null
    ,title varchar2(500)
    ,content varchar2(4000)
    ,hit number
    ,reg_date date
    ,group_no number
    ,order_no number
    ,depth number
    ,primary key(no)
    ,constraint rboard_user_no_fk foreign key(user_no)
    references users(no)
);

create sequence seq_rboard_no
increment by 1
start with 1
nocache;

select r.no no
					,r.user_no userNo
					,r.title title
					,r.content content
					,r.hit hit
					,r.reg_date redDate
					,r.group_no groupNo
					,r.order_no orderNo
					,r.depth depth
					,u.name name
			from rboard r, users u
			where u.no = r.user_no;
            
            
create sequence seq_rboard_group
increment by 1
start with 1
nocache;

drop sequence seq_rboard_group;
drop table rboard;


-- rownum
			select ort.rn
			        ,ort.no
			        ,ort.userNo
			        ,ort.title
			        ,ort.hit
			        ,ort.regDate
			        ,ort.groupNo
			        ,ort.orderNo
			        ,ort.depth
			        ,ort.userName
			from (select rownum rn
			            ,ot.no
			            ,ot.userNo
			            ,ot.title
			            ,ot.hit
			            ,ot.regDate
			            ,ot.groupNo
			            ,ot.orderNo
			            ,ot.depth
			            ,ot.userName
			        from(   select r.no no
			                        ,r.user_no userNo
			                        ,r.title title
			                        ,r.hit hit
			                        ,to_char(r.reg_date, 'yyyy-mm-dd hh24:mi:ss') regDate
			                        ,r.group_no groupNo
			                        ,r.order_no orderNo
			                        ,r.depth depth
			                        ,u.name userName
			                from rboard r, users u
			                where u.no = r.user_no
			                order by no desc) ot
			        )ort
			where rn>=1
			and rn<=10;
            
select count(*) count
from rboard r, users u
where r.user_no = u.no;