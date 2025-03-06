package es.unican.is2.Common;
import java.time.LocalDate;

/**
 * Clase que representa un vehiculo de tipo turismo.
 */
public class Turismo extends Vehiculo {

	private double potencia;
	
	public Turismo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, double potencia) {
		super(id, matricula, fechaMatriculacion, motor);
		this.potencia = potencia;
	}

	/**
	 * Retorna la potencia en caballos fiscales del vehiculo.
	 */
	public double getPotencia() {
		return potencia;
	}

	@Override
	public double precioImpuesto() {
		double tarifa;
        if (potencia < 8) {
            tarifa = 25;
        } else if (potencia < 12) {
            tarifa = 67;
        } else if (potencia < 16) {
            tarifa = 143;
        } else if (potencia < 20) {
            tarifa = 178;
        } else {
            tarifa = 223;
        }
        
        int antiguedad = LocalDate.now().getYear() - getFechaMatriculacion().getYear();
        if (antiguedad > 25) {
            return 0;
        }
        
        return tarifa * (1 - getMotor().getDescuentoImpuesto());
    }

}
