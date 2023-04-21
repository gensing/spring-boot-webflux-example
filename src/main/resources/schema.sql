drop table if exists members;
create table members (
 id BIGINT AUTO_INCREMENT NOT NULL,
 username VARCHAR(12),
 primary key (id)
);