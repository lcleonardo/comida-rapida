INSERT INTO PEDIDO (FECHA,
 CODIGO_CLIENTE,
 CODIGO_PRODUCTO,
 DIRECCION_DOMICILIO, 
 PLACA_VEHICULO, 
 PRECIO_DOMICILIO,
 PORCENTAJE_DESCUENTO, 
 PRECIO_COMPRA,
 PRECIO_TOTAL) 
VALUES (:fecha, :codigoCliente, :codigoProducto, :direccionDomicilio,
 :placaVehiculo, :precioDomicilio, :porcentajeDescuento, :precioCompra, :precioTotal );