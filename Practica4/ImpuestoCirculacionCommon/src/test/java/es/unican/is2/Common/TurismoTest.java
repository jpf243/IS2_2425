package es.unican.is2.Common;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurismoTest {
    private Turismo sut;

    @BeforeEach
    public void setUp() throws Exception {
        sut = new Turismo(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, 8);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, sut.getId());
        assertEquals("1234ABC", sut.getMatricula());
        assertEquals(LocalDate.now(), sut.getFechaMatriculacion());
        assertEquals(TipoMotor.GASOLINA, sut.getMotor());
        assertEquals(8, sut.getPotencia());
    }

    @Test
    public void testPrecioImpuesto() {
        // Casos válidos
        assertEquals(12.5, new Turismo(2, "5678DEF", LocalDate.now(), TipoMotor.GAS, 1).precioImpuesto());
        assertEquals(33.5, new Turismo(3, "9101GHI", LocalDate.now().minusDays(30), TipoMotor.GAS, 10).precioImpuesto());
        assertEquals(71.5, new Turismo(4, "1121JKL", LocalDate.now().minusYears(1), TipoMotor.GAS, 14).precioImpuesto());
        assertEquals(6.25, new Turismo(5, "1314MNO", LocalDate.now(), TipoMotor.HIBRIDO, 5).precioImpuesto());
        assertEquals(35.75, new Turismo(6, "1516PQR", LocalDate.now().minusYears(1), TipoMotor.HIBRIDO, 15).precioImpuesto());
        assertEquals(55.75, new Turismo(7, "1718STU", LocalDate.now().minusYears(4), TipoMotor.HIBRIDO, 20).precioImpuesto());
        assertEquals(67, new Turismo(8, "1920VWX", LocalDate.now(), TipoMotor.DIESEL, 11).precioImpuesto());
        assertEquals(178, new Turismo(9, "2122YZA", LocalDate.now().minusYears(5), TipoMotor.GASOLINA, 16).precioImpuesto());
        assertEquals(223, new Turismo(10, "2324BCD", LocalDate.now().minusYears(25).plusDays(1), TipoMotor.HIBRIDO, 50).precioImpuesto());
        assertEquals(0, new Turismo(11, "2425EFG", LocalDate.now().minusYears(25).minusDays(1), TipoMotor.ELECTRICO, 7).precioImpuesto());
        assertEquals(0, new Turismo(12, "2526HIJ", LocalDate.now().minusYears(28), TipoMotor.GASOLINA, 19).precioImpuesto());
        assertEquals(67, new Turismo(13, "2627KLM", LocalDate.now(), TipoMotor.GASOLINA, 8).precioImpuesto());
        assertEquals(0, new Turismo(14, "2728NOP", LocalDate.now().minusYears(28), TipoMotor.ELECTRICO, 12).precioImpuesto());
        assertEquals(178, new Turismo(15, "2829QRS", LocalDate.now().minusYears(1), TipoMotor.DIESEL, 17).precioImpuesto());

        // Casos no válidos
        assertThrows(DatoNoValidoException.class, () -> new Turismo(16, "2930TUV", LocalDate.now().minusDays(1), TipoMotor.GASOLINA, 0));
        assertThrows(NullPointerException.class, () -> new Turismo(17, "3031WXY", null, TipoMotor.DIESEL, 19));

        assertThrows(NullPointerException.class, () -> new Turismo(19, "3233CDE", LocalDate.now().minusYears(28), null, 1));
        assertThrows(DatoNoValidoException.class, () -> new Turismo(20, "3334FGH", LocalDate.now(), TipoMotor.DIESEL, -1));
        assertThrows(NullPointerException.class, () -> new Turismo(21, "3435IJK", LocalDate.now().minusYears(1), TipoMotor.HIBRIDO, (Double) null));
        
    }
}
