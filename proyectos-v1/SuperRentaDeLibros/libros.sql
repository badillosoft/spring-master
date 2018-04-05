create database RentaDeLibros;
use RentaDeLibros;


create table suscriptores(
	id int primary key auto_increment,
    nombre varchar(128) not null,
    correo varchar(128) not null
);

create table libros(
	id int primary key auto_increment,
    nombre varchar(128) not null,
    autor varchar(128) not null,
    editorial varchar(128) not null
);

create table vendedor(
	id int primary key auto_increment,
    nombre varchar(128) not null,
    correo varchar(128)
);

create table cobro(
	id int primary key auto_increment,
    total double,
    token varchar(255),
    completado tinyint(1)
);


create table pago(
	id int primary key auto_increment,
	idCobro int,
	completado tinyint(1)
);

create table stand(
	id int primary key auto_increment,
    nombre varchar(128),
    localizacion varchar(128)
);

create table membresia(
	id int primary key auto_increment,
    idCliente int,
    vigencia date
);