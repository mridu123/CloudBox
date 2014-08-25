summer project (Java Web application using Struts 1.3.10 Framework)
============

Working on making it live on cloud.


To run the application on your local machine

Install
1. JDK7
2. Netbeans 7.3
3. Glassfish Server 3.1.2
4. compile time library:  mysql-connector-java-5.1.18-bin.jar


5. The Database Queries that need to be run are:

create table SIGNUP
(
	FNAME VARCHAR(20) not null,
	LNAME VARCHAR(20) not null,
	PASS VARCHAR(10) not null,
	DAY VARCHAR(10) not null,
	MONTH VARCHAR(10) not null,
	YEAR VARCHAR(10) not null,
	PHONE VARCHAR(10) not null,
	UNAME VARCHAR(20) primary key,
	EMAIL VARCHAR(100) not null,
	GENDER VARCHAR(10) not null
)


create table files
(
id int auto_increment primary key,
uname varchar(20) not null,
filename varchar(100) not null,
filetype varchar(100) not null,
filesize varchar(100) not null,
content longblob not null,
sharing varchar(20) not null,
constraint fk1 foreign key(uname) references signup(uname)
)


create table friends
(
id int auto_increment primary key,
request_from varchar(20) not null,
request_to varchar(20) not null,
status varchar(20) not null,
constraint foreign key(request_from) references signup(uname),
constraint foreign key(request_to) references signup(uname)
)