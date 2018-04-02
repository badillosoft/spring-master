create database super_cupcakes;

use super_cupcakes;

create table clientes (
	id int primary key auto_increment,
	nombre varchar(128) not null,
	correo varchar(128) not null
);

create table vendedores (
	id int primary key auto_increment,
	nombre varchar(128) not null,
	correo varchar(128) not null
);

create table cocinas (
	id int primary key auto_increment,
	nombre varchar(128) not null,
	direccion varchar(255) not null
);

create table estatus (
	id int primary key auto_increment,
	descripcion varchar(255) not null
);

create table cupcakes (
	id int primary key auto_increment,
	tipo varchar(255) not null,
	precio float
);

create table cobros (
	id int primary key auto_increment,
	total float,
	token_paypal text,
	completado tinyint(1)
);

create table pagos (
	id int primary key auto_increment,
	token_paypal text,
	completado tinyint(1)
);


create table ordenes (
	id int primary key auto_increment,
	cliente int not null,
	vendedor int not null,
	cocina int,
	cobro int,
	pago int,
	estatus int not null	
);

create table orden_cupcakes (
	id int primary key auto_increment,
	orden int not null,
	cupcake int not null,
	multiplicador int	
);

show tables;

describe cupcakes;

insert into cupcakes (tipo, precio) values ('chocolate', 10.0);
insert into cupcakes (tipo, precio) values ('fresa', 10.0);
insert into cupcakes (tipo, precio) values ('vainilla', 10.0);

select * from cupcakes;

insert into clientes (nombre, correo) values ('pepe', 'pepe@gmail.com');
insert into clientes (nombre, correo) values ('paco', 'paco@hotmail.com');

insert into vendedores (nombre, correo) values ('ana v', 'ana@gmail.com');
insert into vendedores (nombre, correo) values ('maria v', 'maria@hotmail.com');

insert into cocinas (nombre, direccion) values ('condesa', 'Amsterdam 265');
insert into cocinas (nombre, direccion) values ('roma', 'Medellín 500');

insert into estatus (descripcion) values ('El cliente inicia la orden');
insert into estatus (descripcion) values ('El cliente selecciona cupcakes');
insert into estatus (descripcion) values ('El cliente envia la orden al vendedor');
insert into estatus (descripcion) values ('El vendedor solicita cobro al cliente');
insert into estatus (descripcion) values ('El cliente ajusta datos de cobro');
insert into estatus (descripcion) values ('El vendedor ajusta datos de pago (procesa el pago)');
insert into estatus (descripcion) values ('El vendedor selecciona la cocina');
insert into estatus (descripcion) values ('El vendedor envia la orden a la cocina');
insert into estatus (descripcion) values ('El vendedor la cocina termina de preparar los cupcakes');
insert into estatus (descripcion) values ('El vendedor la cocina envia la orden al vendedor');
insert into estatus (descripcion) values ('El vendedor el vendedor envia la orden al cliente');
insert into estatus (descripcion) values ('La orden se cierra');

-- Test: Comprobar el flujo

-- 1. Cliente inicia la orden

insert into ordenes (cliente, vendedor, estatus) values (1, 2, 1);

-- 2. Cliente selecciona cupcakes

insert into orden_cupcakes (orden, cupcake, multiplicador) values (1, 2, 3);
insert into orden_cupcakes (orden, cupcake, multiplicador) values (1, 3, 5);

update ordenes set estatus=2 where id=1;

-- 3. Cliente envia orden a vendedor

update ordenes set estatus=3 where id=1;

-- 4. Vendedor solicita cobro a cliente

update ordenes set estatus=4 where id=1;

-- 5. Cliente ajusta cobro

select 
	orden_cupcakes.id,
	orden_cupcakes.orden,
	orden_cupcakes.cupcake,
	orden_cupcakes.multiplicador,
	cupcakes.precio,
	cupcakes.tipo,
	orden_cupcakes.multiplicador * cupcakes.precio as subtotal
from orden_cupcakes
left join cupcakes
on orden_cupcakes.cupcake=cupcakes.id
where orden_cupcakes.orden=1;

select 
	sum(orden_cupcakes.multiplicador * cupcakes.precio) as total
from orden_cupcakes
left join cupcakes
on orden_cupcakes.cupcake=cupcakes.id
where orden_cupcakes.orden=1;

insert into cobros (total, token_paypal, completado) values (80, 'ABC123', 1);

update ordenes set estatus=5 where id=1;









