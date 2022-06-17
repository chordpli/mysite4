-- guestBook table 积己
create table guestbook(
    no number
    ,name varchar2(80)
    ,password varchar2(20)
    ,content varchar2(2000)
    ,reg_date date
    ,primary key (no)
);

-- Users Table 积己

create table users(
        no number
        ,id varchar2(20) unique not null
        ,password varchar(20) not null
        ,name varchar(20)
        ,gender varchar2(10)
        ,primary key(no)
);

-- users no 矫啮胶 积己
create sequence seq_users_no
increment by 1
start with 1
nocache;

-- board table 积己
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
