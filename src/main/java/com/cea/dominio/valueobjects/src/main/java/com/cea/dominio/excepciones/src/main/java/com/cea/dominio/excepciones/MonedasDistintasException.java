package com.cea.dominio.excepciones;

/**
 * Excepcion de dominio que se lanza al intentar operar con distintas monedas.
 */
public class MonedasDistintasException extends RuntimeException {
    public MonedasDistintasException(String mensaje) {
        super(mensaje);
    }
}
