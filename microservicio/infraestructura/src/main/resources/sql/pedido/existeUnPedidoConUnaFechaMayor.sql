SELECT 1 FROM PEDIDO
WHERE FECHA > (SELECT FECHA FROM PEDIDO WHERE ID =:id)
AND CODIGO_CLIENTE = (SELECT CODIGO_CLIENTE FROM PEDIDO WHERE ID =:id)
LIMIT 1