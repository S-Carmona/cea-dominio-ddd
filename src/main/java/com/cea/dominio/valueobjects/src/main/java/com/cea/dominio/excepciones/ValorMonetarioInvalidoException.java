package com.cea.dominio.excepciones;

/**
 * Excepcion de dominio que se lanza cuando se intenta crear un monto negativo o nulo.
 */
public class ValorMonetarioInvalidoException extends RuntimeException {
    public ValorMonetarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}
