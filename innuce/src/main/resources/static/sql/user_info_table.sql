create table user_info(
	user_key int NOT NULL auto_increment primary key,
	user_id varchar(20)	NOT NULL,
	user_pw varchar(20)	NOT NULL,
	user_name varchar(20) NOT NULL,
	email varchar(50) NOT NULL,
	phone varchar(20) NOT NULL,
	gender varchar(1) NOT NULL,
	user_isdeleted bool NOT NULL DEFAULT false,
	user_deleted_time datetime default NULL,
	birthday datetime NOT NULL,
	regdate datetime NOT NULL DEFAULT now(),
    address varchar(50) Not null,
    is_admin boolean NOT NULL DEFAULT false
);

select * from user_info;
