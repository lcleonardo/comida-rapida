CREATE TABLE `pedido` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`fecha` DATETIME NULL DEFAULT NULL,
	`id_conductor` INT(11) NULL DEFAULT NULL,
	`id_producto` INT(11) NULL DEFAULT NULL,
	`nombre_completo_cliente` VARCHAR(250) NULL DEFAULT '' COLLATE 'utf8mb4_0900_ai_ci',
	`direccion_domicilio` VARCHAR(100) NULL DEFAULT '' COLLATE 'utf8mb4_0900_ai_ci',
	`precio_domicilio` DOUBLE NOT NULL DEFAULT '0',
	`precio_total_compra` DOUBLE NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `id_conductor` (`id_conductor`) USING BTREE,
	INDEX `id_producto` (`id_producto`) USING BTREE
);