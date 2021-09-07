INSERT INTO PEDIDO (FECHA,
 CODIGO_CLIENTE,
 CODIGO_PRODUCTO,
 DIRECCION_DOMICILIO, 
 PLACA_VEHICULO, 
 PRECIO_COMPRA,
 PORCENTAJE_DESCUENTO,
 PORCENTAJE_PROMOCION,
 PRECIO_TOTAL
 )
VALUES (:fecha,
:codigoCliente,
:codigoProducto,
:direccionDomicilio,
:placaVehiculo,
:precioCompra,
:porcentajeDescuento,
:porcentajePromocion,
:precioTotal );