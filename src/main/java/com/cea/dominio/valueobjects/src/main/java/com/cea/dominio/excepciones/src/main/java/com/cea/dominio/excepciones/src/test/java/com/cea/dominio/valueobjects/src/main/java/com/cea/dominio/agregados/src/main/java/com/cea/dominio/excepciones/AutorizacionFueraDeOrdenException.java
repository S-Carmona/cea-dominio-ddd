package com.cea.dominio.excepciones;

/**
 * Excepcion que se lanza cuando el Director Financiero intenta autorizar antes que el Jefe de area.
 */
public class AutorizacionFueraDeOrdenException extends RuntimeException {
    public AutorizacionFueraDeOrdenException(String mensaje) {
        super(mensaje);
    }
}
