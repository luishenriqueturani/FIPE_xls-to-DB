create database fipe;
use fipe;

create table categories(
	id varchar(36) not null primary key default uuid(),
	label varchar(100) not null,
	created_at datetime default current_timestamp(),
	updated_at datetime default null on update current_timestamp(),
	deleted_at datetime default null
);

create table brands(
	id varchar(36) not null primary key default uuid(),
	label varchar(100) not null,
	created_at datetime default current_timestamp(),
	updated_at datetime default null on update current_timestamp(),
	deleted_at datetime default null
);

create table models(
	id varchar(36) not null primary key default uuid(),
	id_brand varchar(36) not null,
	id_category varchar(36) not null,
	label varchar(100) not null,
	created_at datetime default current_timestamp(),
	updated_at datetime default null on update current_timestamp(),
	deleted_at datetime default null,
	foreign key (id_brand) references brands(id),
	foreign key (id_category) references categories(id)
);

create table versions(
	id varchar(36) not null primary key default uuid(),
	id_model varchar(36) not null,
	label varchar(100) not null,
	created_at datetime default current_timestamp(),
	updated_at datetime default null on update current_timestamp(),
	deleted_at datetime default null,
	foreign key (id_model) references models(id)
);

create table year_versions_prices(
	id varchar(36) not null primary key default uuid(),
	id_version varchar(36) not null,
	price float not null,
	year int not null,
	cod_fipe varchar(20) not null,
	created_at datetime default current_timestamp(),
	updated_at datetime default null on update current_timestamp(),
	deleted_at datetime default null,
	foreign key (id_version) references versions (id)
);