CREATE TABLE PEDIDO (
	ID INT(10) NOT NULL AUTO_INCREMENT,
	FECHA DATETIME NULL DEFAULT NULL,
	CODIGO_CLIENTE VARCHAR(255) NULL DEFAULT NULL,
	CODIGO_PRODUCTO VARCHAR(255) NULL DEFAULT NULL,
	DIRECCION_DOMICILIO VARCHAR(255) NULL DEFAULT NULL,
	PLACA_VEHICULO VARCHAR(10) NULL DEFAULT NULL,
	PRECIO_DOMICILIO DOUBLE NULL DEFAULT NULL,
	PORCENTAJE_DESCUENTO DOUBLE NULL DEFAULT NULL,
	PRECIO_COMPRA DOUBLE NULL DEFAULT NULL,
	PRECIO_TOTAL DOUBLE NULL DEFAULT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE DESCUENTO (
	ID INT(10) NOT NULL AUTO_INCREMENT,
	FECHA DATETIME NULL DEFAULT NULL,
	PORCENTAJE DOUBLE NULL DEFAULT NULL,
	PRIMARY KEY (ID)
);
