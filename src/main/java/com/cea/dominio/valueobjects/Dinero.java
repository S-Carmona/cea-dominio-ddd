package com.cea.dominio.valueobjects;

import com.cea.dominio.excepciones.ValorMonetarioInvalidoException;
import com.cea.dominio.excepciones.MonedasDistintasException;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Value Object inmutable que representa un monto monetario en el CEA.
 */
public final class Dinero {

    private final BigDecimal valor;
    private final String moneda;

    private Dinero(BigDecimal valor, String moneda) {
        this.valor = valor;
        this.moneda = moneda;
    }

    public static Dinero de(double valor, String moneda) {
        return de(BigDecimal.valueOf(valor), moneda);
    }

    public static Dinero de(BigDecimal valor, String moneda) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValorMonetarioInvalidoException("El valor monetario no puede ser negativo ni nulo.");
        }
        if (moneda == null || moneda.trim().isEmpty()) {
            throw new ValorMonetarioInvalidoException("La moneda es obligatoria.");
        }
        return new Dinero(valor, moneda.toUpperCase());
    }

    public Dinero sumar(Dinero otro) {
        if (!this.moneda.equals(otro.moneda)) {
            throw new MonedasDistintasException("No se pueden sumar montos de distintas monedas: " + this.moneda + " y " + otro.moneda);
        }
        return new Dinero(this.valor.add(otro.valor), this.moneda);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getMoneda() {
        return moneda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dinero dinero = (Dinero) o;
        return valor.compareTo(dinero.valor) == 0 && Objects.equals(moneda, dinero.moneda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, moneda);
    }

    @Override
    public String toString() {
        return valor + " " + moneda;
    }
}
