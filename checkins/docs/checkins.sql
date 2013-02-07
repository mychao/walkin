#checkins database define
create database checkins character set utf8;

USE `checkins`;

#checkins info
create table checkins(
	id int not null auto_increment comment 'auto generated id',
	uid int not null comment 'user id',
	checkinTime datetime not null comment 'checkin time',
	geolocation varchar(50) not null comment 'geo location: latitude.longitude',
	
	primary key(id),
	key (uid, checkinTime)
)engine=innodb;

#administrator info
create table administrator(
	id int not null auto_increment comment 'manager id',
	name varchar(30) not null comment 'user name',
	password varchar(30) not null comment 'user password',
	
	primary key (id),
	unique key (name)
)engine=innodb;

#place info
create table place (
	id int not null auto_increment comment 'generated id',
	geolocation varchar(50) not null comment 'geo location : latitude.longitude',
	address varchar(128) not null comment 'address info',
	
	primary key(id),
	unique key(geolocation)
)engine=innodb;