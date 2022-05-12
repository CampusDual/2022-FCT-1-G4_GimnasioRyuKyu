create table CLASS ( 
ID INTEGER generated always as identity,
NAME varchar(30) not null,
DESCRIPTION varchar(100) not null,

constraint PK_ID_CLASS primary key (ID)
);