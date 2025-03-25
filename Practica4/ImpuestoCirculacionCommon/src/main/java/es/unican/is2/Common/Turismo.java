package es.unican.is2.Common;

import java.time.LocalDate;
import java.time.Year;
import java.util.Objects;

/**
 * Clase que representa un vehiculo de tipo turismo.
 */
public class Turismo extends Vehiculo {

    private double potencia;

    public Turismo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, double potencia) {
        super(id, Objects.requireNonNull(matricula, "La matrícula no puede ser null"), 
                Objects.requireNonNull(fechaMatriculacion, "La fecha de matriculación no puede ser null"), 
                Objects.requireNonNull(motor, "El tipo de motor no puede ser null"));

        if (potencia <= 0) {
            throw new DatoNoValidoException("La potencia debe ser superior a 0");
        }
        if (fechaMatriculacion.isAfter(LocalDate.now())) {
            throw new DatoNoValidoException("La fecha de matriculación no puede ser futura");
        }

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
        double precio = 0;
        
        // Determinar el precio base según la potencia
        if (potencia < 8) {
            precio = 25;
        } else if (potencia < 12) {
            precio = 67;
        } else if (potencia < 16) {
            precio = 143;
        } else if (potencia < 20) {
            precio = 178;
        } else {
            precio = 223;
        }
        
        // Calcular los años del vehículo
        int añosVehiculo = Year.now().getValue() - super.getFechaMatriculacion().getYear();
        if (LocalDate.now().isBefore(super.getFechaMatriculacion().plusYears(añosVehiculo))) {
            añosVehiculo--;
        }
        
        // Aplicar descuentos o exenciones
        if (añosVehiculo >= 25) {
            return 0;
        } else if (super.getMotor() == TipoMotor.ELECTRICO) {
            return precio * 0.25; // 75% de descuento
        } else if (super.getMotor() == TipoMotor.HIBRIDO && añosVehiculo <= 4) {
            return precio * 0.25; // 75% de descuento
        } else if (super.getMotor() == TipoMotor.GAS && añosVehiculo <= 1) {
            return precio * 0.5; // 50% de descuento
        }

        return precio;
    }
}
