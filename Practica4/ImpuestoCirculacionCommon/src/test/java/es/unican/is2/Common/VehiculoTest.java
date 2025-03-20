package es.unican.is2.Common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VehiculoTest {

    private Turismo turismoNormal;
    private Turismo turismoElectrico;
    private Turismo turismoAntiguo;
    private Motocicleta motoNormal;
    private Motocicleta motoGasPrimerAno;
    
    @BeforeEach
    void setUp() {
        turismoNormal = new Turismo(1, "1234ABC", LocalDate.now().minusYears(5), TipoMotor.GASOLINA, 10);
        turismoElectrico = new Turismo(2, "5678DEF", LocalDate.now().minusYears(5), TipoMotor.ELECTRICO, 10);
        turismoAntiguo = new Turismo(3, "9101GHI", LocalDate.now().minusYears(30), TipoMotor.DIESEL, 10);
        motoNormal = new Motocicleta(4, "1111JKL", LocalDate.now().minusYears(5), TipoMotor.GASOLINA, 300);
        motoGasPrimerAno = new Motocicleta(5, "2222MNO", LocalDate.now(), TipoMotor.GAS, 300);
    }

    @Test
    void testTurismoSinBonificaciones() {
        assertEquals(67, turismoNormal.precioImpuesto());
    }

    @Test
    void testTurismoElectrico() {
        assertEquals(67 * 0.25, turismoElectrico.precioImpuesto());
    }

    @Test
    void testTurismoAntiguo() {
        assertEquals(0, turismoAntiguo.precioImpuesto());
    }

    @Test
    void testMotocicletaSinBonificaciones() {
        assertEquals(30, motoNormal.precioImpuesto());
    }

    @Test
    void testMotocicletaGasPrimerAno() {
        assertEquals(30 * 0.5, motoGasPrimerAno.precioImpuesto());
    }
}



