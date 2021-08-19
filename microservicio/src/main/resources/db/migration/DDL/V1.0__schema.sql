CREATE TABLE PEDIDO (
	ID INT AUTO_INCREMENT,
	FECHA DATETIME NULL DEFAULT NULL,
	CODIGO_CLIENTE VARCHAR(255),
	CODIGO_PRODUCTO VARCHAR(255),
	DIRECCION_DOMICILIO VARCHAR(255),
	PLACA_VEHICULO VARCHAR(255),
	PRECIO_DOMICILIO DOUBLE,
	PRECIO_TOTAL_COMPRA DOUBLE,
	PRIMARY KEY (ID)
);

