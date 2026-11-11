package com.cea.dominio.agregados;

import com.cea.dominio.valueobjects.Dinero;
import com.cea.dominio.excepciones.AutorizacionFueraDeOrdenException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolicitudCompra {

    public enum EstadoSolicitud {
        BORRADOR,
        AUTORIZADA_POR_JEFE,
        AUTORIZADA_POR_DIRECTOR
    }

    private String id;
    private EstadoSolicitud estado;
    private List<String> items;

    public SolicitudCompra(String id) {
        this.id = id;
        this.estado = EstadoSolicitud.BORRADOR;
        this.items = new ArrayList<>();
    }

    public void agregarItem(String bien) {
        if (this.estado != EstadoSolicitud.BORRADOR) {
            throw new IllegalStateException("No se pueden modificar solicitudes autorizadas.");
        }
        this.items.add(bien);
    }

    public void autorizarPorJefe() {
        this.estado = EstadoSolicitud.AUTORIZADA_POR_JEFE;
    }

    public void autorizarPorDirectorFinanciero() {
        if (this.estado != EstadoSolicitud.AUTORIZADA_POR_JEFE) {
            throw new AutorizacionFueraDeOrdenException("El Director Financiero no puede autorizar antes que el Jefe de área.");
        }
        this.estado = EstadoSolicitud.AUTORIZADA_POR_DIRECTOR;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public String getId() {
        return id;
    }

    public List<String> getItems() {
        return Collections.unmodifiableList(items);
    }
}
