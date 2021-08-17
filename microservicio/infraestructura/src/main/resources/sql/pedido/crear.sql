INSERT INTO pedido 
(fecha, 
id_conductor, 
id_producto, 
nombre_completo_cliente, 
direccion_domicilio, 
precio_domicilio, 
precio_total_compra) 
 VALUES (:fecha,
:idProducto ,
:idConductor ,
:nombreCompletoCliente ,
:direccionDomicilio ,
:precioDomicilio ,
:precioTotalCompra );