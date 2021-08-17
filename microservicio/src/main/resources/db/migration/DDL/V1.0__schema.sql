SET FOREIGN_KEY_CHECKS = 0;


drop   TABLE IF EXISTS pedido;
drop   TABLE IF EXISTS conductor;
drop   TABLE IF EXISTS producto;
drop   TABLE IF EXISTS usuario;


SET FOREIGN_KEY_CHECKS = 1;

create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);

