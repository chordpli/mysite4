-- guestBook table 持失
create table guestbook(
    no number
    ,name varchar2(80)
    ,password varchar2(20)
    ,content varchar2(2000)
    ,reg_date date
    ,primary key (no)
);

-- table 持失
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

-- Users Table 持失

create table users(
        no number
        ,id varchar2(20) unique not null
        ,password varchar(20) not null
        ,name varchar(20)
        ,gender varchar2(10)
        ,primary key(no)
);

-- users no 獣碇什 持失
create sequence seq_users_no
increment by 1
start with 1
nocache;

-- board table 持失
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

-------------- gallery table 持失
create table gallery(
        no number
        ,user_no number
        ,content varchar2(1000)
        ,file_path varchar2(500)
        ,org_name varchar2(200)
        ,file_size number
        ,primary key(no)
        ,constraint gallery_no_fk foreign key(user_no)
        references users(no)
);

drop table gallery;
-- board table 持失
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
