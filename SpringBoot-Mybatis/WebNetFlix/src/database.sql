Create database WebNetFlix;
use WebNetFlix;

create table User(
	id bigint primary key not null auto_increment,
    full_name nvarchar(100),
    user_name varchar(50),
    user_password nvarchar(50),
    email varchar(100),
    phone varchar(12)
);