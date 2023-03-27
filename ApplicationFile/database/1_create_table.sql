use toeiconline;

create table user(
	userid bigint primary key auto_increment,
    name varchar(255) null,
    password varchar(255) null,
    fullname varchar(300) null,
    createddate timestamp null
);

create table role(
	roleid bigint primary key,
    name varchar(100) null
);