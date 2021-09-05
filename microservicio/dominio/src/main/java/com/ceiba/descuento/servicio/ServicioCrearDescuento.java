package com.ceiba.descuento.servicio;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearDescuento {

    private static final String YA_EXISTE_UN_DESCUENTO_ASIGNADO_A_LA_FECHA = "Ya existe un descuento asignado a la fecha.";

    private RepositorioDescuento repositorioDescuento;

    public ServicioCrearDescuento(RepositorioDescuento repositorioDescuento) {
        this.repositorioDescuento = repositorioDescuento;
    }

    public Long ejecutar(Descuento descuento) {
        validarSiExisteUnDescuentoCreado(descuento);
        return this.repositorioDescuento.crear(descuento);
    }

    private void validarSiExisteUnDescuentoCreado(Descuento descuento) {
        Double porcentaje = this.repositorioDescuento.obtenerPorcentajePorFecha(descuento.getFecha());
        if (porcentaje > 0) {
            throw new ExcepcionDuplicidad(YA_EXISTE_UN_DESCUENTO_ASIGNADO_A_LA_FECHA);
        }
    }

}
