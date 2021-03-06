CREATE TABLE DOMICILIO (
	ID INT(10) NOT NULL AUTO_INCREMENT,
	PLACA_VEHICULO VARCHAR(25) NULL DEFAULT NULL,
	FECHA DATE NULL DEFAULT NULL,
	PRECIO DOUBLE NULL DEFAULT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE PEDIDO (
	ID INT(10) NOT NULL AUTO_INCREMENT,
    FECHA DATE NULL DEFAULT NULL,
	CODIGO_CLIENTE VARCHAR(255) NULL DEFAULT NULL,
	CODIGO_PRODUCTO VARCHAR(255) NULL DEFAULT NULL,
	DIRECCION_DOMICILIO VARCHAR(255) NULL DEFAULT NULL,
	PLACA_VEHICULO VARCHAR(25) NULL DEFAULT NULL,
	PRECIO_COMPRA DOUBLE NULL DEFAULT NULL,
	PORCENTAJE_DESCUENTO DOUBLE NULL DEFAULT NULL,
	PORCENTAJE_PROMOCION DOUBLE NULL DEFAULT NULL,
	PRECIO_TOTAL DOUBLE NULL DEFAULT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE DESCUENTO (
	ID INT(10) NOT NULL AUTO_INCREMENT,
	FECHA DATE NULL DEFAULT NULL,
	PORCENTAJE DOUBLE NULL DEFAULT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE CURSO (
	ID BIGINT(19) NOT NULL AUTO_INCREMENT,
	NOMBRE VARCHAR(250) NULL DEFAULT '',
	DURACION DOUBLE NULL DEFAULT '0',
	PRIMARY KEY (ID),
	UNIQUE INDEX NOMBRE (NOMBRE)
);
