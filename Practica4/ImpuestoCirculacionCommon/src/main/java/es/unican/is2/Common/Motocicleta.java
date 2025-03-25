package es.unican.is2.Common;

import java.time.LocalDate;
import java.time.Year;

/**
 * Clase que representa un vehiculo de tipo motocicleta
 */
public class Motocicleta extends Vehiculo {

    private int cilindrada;

    public Motocicleta(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, int cilindrada) {
        super(id, matricula, fechaMatriculacion, motor);

        // Validaciones
        if (fechaMatriculacion == null) {
            throw new NullPointerException("La fecha de matriculación no puede ser nula");
        }
        if (motor == null) {
            throw new NullPointerException("El tipo de motor no puede ser nulo");
        }
        if (fechaMatriculacion.isAfter(LocalDate.now())) {
            throw new DatoNoValidoException("La fecha de matriculación no puede ser posterior a hoy");
        }
        if (cilindrada <= 0) {
            throw new DatoNoValidoException("La cilindrada debe ser mayor a 0");
        }

        this.cilindrada = cilindrada;
    }

    /**
     * Retorna la cilindrada en CC de la motocicleta.
     */
    public int getCilindrada() {
        return cilindrada;
    }

    @Override
    public double precioImpuesto() {
        double precio = 0;

        // Determinar el impuesto base según la cilindrada
        if (cilindrada < 126) {
            precio = 8;
        } else if (cilindrada < 251) {
            precio = 15;
        } else if (cilindrada < 501) {
            precio = 30;
        } else if (cilindrada < 1001) {
            precio = 60;
        } else {
            precio = 120;
        }

        // Calcular la antigüedad del vehículo
        int añosVehiculo = Year.now().getValue() - super.getFechaMatriculacion().getYear();
        if (LocalDate.now().isBefore(super.getFechaMatriculacion().plusYears(añosVehiculo))) {
            añosVehiculo--;
        }

        // Aplicar descuentos o exenciones
        if (añosVehiculo >= 25) {
            return 0;  // Exento de impuesto
        } 
        if (super.getMotor() == TipoMotor.ELECTRICO) {
            return precio * 0.25;  // 75% de descuento
        }
        if (super.getMotor() == TipoMotor.HIBRIDO && añosVehiculo <= 4) {
            return precio * 0.25;  // 75% de descuento
        }
        if (super.getMotor() == TipoMotor.GAS && añosVehiculo <= 1) {
            return precio * 0.5;  // 50% de descuento
        }

        return precio;
    }
}
