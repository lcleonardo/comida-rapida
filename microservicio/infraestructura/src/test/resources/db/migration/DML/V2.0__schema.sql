INSERT INTO DESCUENTO (ID,FECHA, PORCENTAJE)
VALUES
(1,'2021-08-26', 1),
(2,'2021-08-26', 5),
(3,'2021-08-26', 10);

INSERT INTO PEDIDO (
 ID,
 FECHA,
 CODIGO_CLIENTE,
 CODIGO_PRODUCTO,
 DIRECCION_DOMICILIO, 
 PLACA_VEHICULO, 
 PRECIO_DOMICILIO,
 PORCENTAJE_DESCUENTO, 
 PRECIO_COMPRA,
 PRECIO_TOTAL,
 APLICA_PROMOCION
 ) 
VALUES
(1,'2021-08-24', '1094', '0001', 'VEREDA SAN JUAN','VKH234', 1000, 5, 10000, 9000, 0),
(2,'2021-08-25', '1094', '0001', 'VEREDA SAN JUAN','VKH234', 1000, 10, 10000, 9000, 0),
(3,'2021-09-22', '1094', '0001', 'VEREDA SAN JUAN','VKH234', 1000, 0, 0, 0, 0);


