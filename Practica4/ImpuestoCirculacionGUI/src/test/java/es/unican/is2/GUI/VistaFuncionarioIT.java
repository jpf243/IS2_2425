package es.unican.is2.GUI;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;

import es.unican.is2.Common.*;
import es.unican.is2.DAOH2.ContribuyentesDAO;
import es.unican.is2.DAOH2.H2ServerConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class VistaFuncionarioIT {

    private FrameFixture window;
    private VistaFuncionario vista;
    private IInfoImpuestoCirculacion servicio;

    @BeforeAll
    public static void setupDatabase() {
        // Iniciar conexión y crear datos de prueba en la BD
        Connection con = H2ServerConnectionManager.getConnection();
        try (Statement stmt = con.createStatement()) {
            stmt.execute("DELETE FROM Contribuyentes"); // Limpiar datos previos
            stmt.execute("INSERT INTO Contribuyentes (dni, nombre, apellido1, apellido2) VALUES ('00000000T', 'Juan', 'López', 'Pérez')");
            stmt.execute("INSERT INTO Contribuyentes (dni, nombre, apellido1, apellido2) VALUES ('12345678Z', 'Ana', 'García', 'Martínez')");
            stmt.execute("INSERT INTO Contribuyentes (dni, nombre, apellido1, apellido2) VALUES ('99999999R', 'Carlos', 'Fernández', 'Sánchez')");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al inicializar la BD");
        }
    }

    @BeforeEach
    public void setUp() {
        servicio = new ContribuyentesDAO(); // Usar DAO real con H2
        vista = new VistaFuncionario(servicio);
        window = new FrameFixture(vista);
        vista.setVisible(true);
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }

    @AfterAll
    public static void cleanupDatabase() {
        try (Connection con = H2ServerConnectionManager.getConnection()) {
            if (con == null || con.isClosed()) {
                System.err.println("Error: No se pudo obtener la conexión a la base de datos.");
                return;
            }
            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate("DELETE FROM Vehiculos");
                stmt.executeUpdate("DELETE FROM Contribuyentes");
            }
        } catch (SQLException e) {
            System.err.println("Error al limpiar la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // -------------------- CASOS VÁLIDOS --------------------

    @Test
    @GUITest
    public void testDniValido1() {
        window.textBox("txtDniContribuyente").setText("00000000T");
        window.button(JButtonMatcher.withText("Buscar")).click();
        window.textBox("txtNombreContribuyente").requireText("Juan Pérez López");
    }

    @Test
    @GUITest
    public void testDniValido2() {
        window.textBox("txtDniContribuyente").setText("12345678Z");
        window.button(JButtonMatcher.withText("Buscar")).click();
        window.textBox("txtNombreContribuyente").requireText("Ana Martínez García");
    }

    @Test
    @GUITest
    public void testDniValido3() {
        window.textBox("txtDniContribuyente").setText("99999999R");
        window.button(JButtonMatcher.withText("Buscar")).click();
        window.textBox("txtNombreContribuyente").requireText("Carlos Sánchez Fernández");
    }

    // -------------------- CASOS NO VÁLIDOS --------------------

    @Test
    @GUITest
    public void testDniInvalido1() {
        window.textBox("txtDniContribuyente").setText("12345Z");
        window.button(JButtonMatcher.withText("Buscar")).click();
        window.textBox("txtNombreContribuyente").requireText("DNI Incorrecto");
        window.textBox("txtTotalContribuyente").requireText("0");
    }

    @Test
    @GUITest
    public void testDniInvalido2() {
        window.textBox("txtDniContribuyente").setText("123456789X");
        window.button(JButtonMatcher.withText("Buscar")).click();
        window.textBox("txtNombreContribuyente").requireText("DNI Incorrecto");
    }

    @Test
    @GUITest
    public void testDniInvalido3() {
        window.textBox("txtDniContribuyente").setText("12A45678B");
        window.button(JButtonMatcher.withText("Buscar")).click();
        window.textBox("txtNombreContribuyente").requireText("DNI Incorrecto");
    }

    @Test
    @GUITest
    public void testDniInvalido4() {
        window.textBox("txtDniContribuyente").setText("12345678A");
        window.button(JButtonMatcher.withText("Buscar")).click();
        window.textBox("txtNombreContribuyente").requireText("DNI Incorrecto");
    }

    @Test
    @GUITest
    public void testDniInvalido5() {
        window.textBox("txtDniContribuyente").setText("12345678");
        window.button(JButtonMatcher.withText("Buscar")).click();
        window.textBox("txtNombreContribuyente").requireText("DNI Incorrecto");
    }

    @Test
    @GUITest
    public void testDniInvalido6() {
        window.textBox("txtDniContribuyente").setText("1234-5678Z");
        window.button(JButtonMatcher.withText("Buscar")).click();
        window.textBox("txtNombreContribuyente").requireText("DNI Incorrecto");
    }

    @Test
    @GUITest
    public void testDniNulo() {
        window.textBox("txtDniContribuyente").setText(null);
        window.button(JButtonMatcher.withText("Buscar")).click();
        window.textBox("txtNombreContribuyente").requireText("Error BBDD");
    }
}
