package es.unican.is2.Common;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MotocicletaTest {
    private Motocicleta moto;

    @BeforeEach
    public void setUp() throws Exception {
        moto = new Motocicleta(1, "1234ABC", LocalDate.now(), TipoMotor.GASOLINA, 125);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, moto.getId());
        assertEquals("1234ABC", moto.getMatricula());
        assertEquals(LocalDate.now(), moto.getFechaMatriculacion());
        assertEquals(TipoMotor.GASOLINA, moto.getMotor());
        assertEquals(125, moto.getCilindrada());
    }

    @SuppressWarnings("null")
	@Test
    public void testPrecioImpuesto() {
        // Casos válidos
        assertEquals(4, new Motocicleta(2, "5678DEF", LocalDate.now(), TipoMotor.GAS, 1).precioImpuesto());
        assertEquals(7.5, new Motocicleta(3, "9101GHI", LocalDate.now().minusDays(30), TipoMotor.GAS, 200).precioImpuesto());
        assertEquals(15, new Motocicleta(4, "1121JKL", LocalDate.now().minusYears(1), TipoMotor.GAS, 300).precioImpuesto());
        assertEquals(2, new Motocicleta(5, "1314MNO", LocalDate.now(), TipoMotor.HIBRIDO, 100).precioImpuesto());
        assertEquals(7.5, new Motocicleta(6, "1516PQR", LocalDate.now().minusYears(1), TipoMotor.HIBRIDO, 500).precioImpuesto());
        assertEquals(30, new Motocicleta(7, "1718STU", LocalDate.now().minusYears(4), TipoMotor.HIBRIDO, 1001).precioImpuesto());
        assertEquals(15, new Motocicleta(8, "1920VWX", LocalDate.now(), TipoMotor.DIESEL, 250).precioImpuesto());
        assertEquals(60, new Motocicleta(9, "2122YZA", LocalDate.now().minusYears(5), TipoMotor.GASOLINA, 501).precioImpuesto());
        assertEquals(120, new Motocicleta(10, "2324BCD", LocalDate.now().minusYears(25).plusDays(1), TipoMotor.HIBRIDO, 1500).precioImpuesto());
        assertEquals(0, new Motocicleta(11, "2425EFG", LocalDate.now().minusYears(25).minusDays(1), TipoMotor.ELECTRICO, 125).precioImpuesto());
        assertEquals(0, new Motocicleta(12, "2526HIJ", LocalDate.now().minusYears(28), TipoMotor.GASOLINA, 1000).precioImpuesto());
        assertEquals(30, new Motocicleta(13, "2627KLM", LocalDate.now(), TipoMotor.GASOLINA, 251).precioImpuesto());
        assertEquals(0, new Motocicleta(14, "2728NOP", LocalDate.now().minusYears(28), TipoMotor.ELECTRICO, 126).precioImpuesto());
        assertEquals(60, new Motocicleta(15, "2829QRS", LocalDate.now().minusYears(1), TipoMotor.DIESEL, 700).precioImpuesto());

        // Casos no válidos
        assertThrows(DatoNoValidoException.class, () -> new Motocicleta(16, "2930TUV", LocalDate.now().plusDays(1), TipoMotor.GASOLINA, 8));
        assertThrows(NullPointerException.class, () -> new Motocicleta(17, "3031WXY", null, TipoMotor.DIESEL, 19));

        assertThrows(NullPointerException.class, () -> new Motocicleta(19, "3233CDE", LocalDate.now().minusYears(28), null, 1));
        assertThrows(DatoNoValidoException.class, () -> new Motocicleta(20, "3334FGH", LocalDate.now(), TipoMotor.DIESEL, 0));
        assertThrows(NullPointerException.class, () -> new Motocicleta(21, "3435IJK", LocalDate.now().minusYears(1), TipoMotor.HIBRIDO, (Integer) null));
    }
}

