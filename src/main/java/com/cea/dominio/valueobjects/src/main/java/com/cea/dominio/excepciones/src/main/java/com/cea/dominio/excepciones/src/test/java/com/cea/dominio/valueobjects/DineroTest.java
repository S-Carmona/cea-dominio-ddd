package com.cea.dominio.valueobjects;

import com.cea.dominio.excepciones.ValorMonetarioInvalidoException;
import com.cea.dominio.excepciones.MonedasDistintasException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de dominio para el Value Object Dinero.
 */
class DineroTest {

    @Test
    void noPermiteValoresNegativos() {
        assertThrows(ValorMonetarioInvalidoException.class, () -> {
            Dinero.de(-100, "COP");
        });
    }

    @Test
    void sumarDosMontosEnMismaMoneda() {
        Dinero a = Dinero.de(1500, "COP");
        Dinero b = Dinero.de(500, "COP");

        Dinero resultado = a.sumar(b);

        assertEquals(Dinero.de(2000, "COP"), resultado);
        // Verifica inmutabilidad: el objeto 'a' original no cambia
        assertEquals(Dinero.de(1500, "COP"), a);
    }

    @Test
    void noPermiteSumarMonedasDistintas() {
        Dinero cop = Dinero.de(10, "COP");
        Dinero usd = Dinero.de(10, "USD");

        assertThrows(MonedasDistintasException.class, () -> {
            cop.sumar(usd);
        });
    }
}
