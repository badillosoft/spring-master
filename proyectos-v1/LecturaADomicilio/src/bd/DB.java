/*
create database super_lecturadomicilio;
use super_lecturadomicilio;

create table clientes(
	id int primary key auto_increment,
    nombre varchar(128),
    correo varchar(128),
    direccion varchar(255) not null);
    
create table oradores(
	id int primary key auto_increment,
    nombre varchar (128),
    correo varchar(128),
    tipo varchar(128),
    precio varchar(128));
    
create table cobros(
	id int primary key auto_increment,
    total float,
    token_paypal text,
    completado tinyint(1));

create table pagos_plataforma(
	id int primary key auto_increment,
    token_paypal text,
    completado tinyint(1));

create table pagos_orador(
	id int primary key auto_increment,
    token_paypal text,
    completado tinyint(1));

create table estatus(
	id int primary key auto_increment,
    descripcion varchar(255));
    
create table ordenes(
	id int primary key auto_increment,
    cliente int not null,
    orador int not null,
    cobro int,
    pagoPlataforma int,
    pagoOrador int,
    estatus int not null);

*/