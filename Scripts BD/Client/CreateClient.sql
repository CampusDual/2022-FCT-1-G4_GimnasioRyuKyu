create table CLIENT ( 
ID INTEGER generated always as identity,
NAME varchar(30) not null,
LASTNAME varchar(60) not null,
DNI varchar(9) not null,
GENDER CHAR null,
BIRTHDAY DATE not null, 
REGISTRATION_DATE DATE not null,
PHONE varchar(9) not null,
EMAIL varchar(255) not null,
ACTIVE BOOLEAN not null,

constraint PK_ID_CLIENT primary key (ID),
constraint UQ_DNI_CLIENT unique (DNI),
--constraint CK_DNI_CLIENT check (DNI like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][a-zA-Z]'),--
--constraint CK_PHONE_CLIENT check (PHONE like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),--
constraint CK_GENDER_CLIENT check (GENDER in ('F', 'M', 'O')),
--FEMALE-MALE-OTHER--
constraint CK_EMAIL_CLIENT check (EMAIL like '%_@__%.__%')
);