import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.unican.is2.GUI.VistaFuncionario;
import es.unican.is2.Business.GestionImpuestoCirculacion;
import es.unican.is2.DAOH2.ContribuyentesDAO;
import es.unican.is2.DAOH2.VehiculosDAO;

class VistaFuncionarioIT {
    private FrameFixture window;

    @BeforeEach
    void setUp() {
    	ContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();
		VehiculosDAO vehiculosDAO = new VehiculosDAO();
		
		// Componentes capa negocio
		GestionImpuestoCirculacion negocio = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);

        VistaFuncionario gui = new VistaFuncionario(negocio);
        window = new FrameFixture(gui);
        gui.setVisible(true);
    }

    @AfterEach
    void tearDown() {
        window.cleanUp();
    }
    
    @Test
    public void test() {
    	 // Comprobamos que el botón tiene el texto correcto
        window.button("btnBuscar").requireText("Buscar");

        // Escribimos un DNI válido en el campo de texto
        window.textBox("txtDniContribuyente").enterText("11111111A");

        // Pulsamos el botón de buscar
        window.button("btnBuscar").click();

        // Comprobamos que el campo de nombre tiene el valor esperado
        window.textBox("txtNombreContribuyente").requireText("Juan Pérez Lopez");

        // Comprobamos que el total a pagar es correcto
        window.textBox("txtTotalContribuyente").requireText("206.75");
	}

    @Test
    void testConsultaContribuyenteValido() {
        window.textBox("txtDniContribuyente").enterText("11111111A");
        window.button("btnBuscar").click();
        
        assertThat(window.textBox("txtNombreContribuyente").text()).isEqualTo("Juan Pérez Lopez");
        assertThat(window.textBox("txtTotalContribuyente").text()).isEqualTo("206.75");
        assertThat(window.list("listMatriculasVehiculos").contents()).containsExactly("1111AAA", "1111BBB","1111CCC");
    }

    @Test
    void testConsultaContribuyenteNoExistente() {
        window.textBox("txtDniContribuyente").enterText("87654321B");
        window.button("btnBuscar").click();
        
        assertThat(window.textBox("txtNombreContribuyente").text()).isEqualTo("DNI Incorrecto");
        assertThat(window.textBox("txtTotalContribuyente").text()).isEqualTo("0");
        assertThat(window.list("listMatriculasVehiculos").contents()).isEmpty();
    }

    @Test
    void testErrorBaseDeDatos() {
        window.textBox("txtDniContribuyente").enterText("11111111C");
        window.button("btnBuscar").click();
        
        assertThat(window.textBox("txtNombreContribuyente").text()).isEqualTo("Error BBDD");
    }
}
