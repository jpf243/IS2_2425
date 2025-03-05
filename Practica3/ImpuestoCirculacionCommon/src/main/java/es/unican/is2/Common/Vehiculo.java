package es.unican.is2.Common;
import java.time.LocalDate;

/**
 * Clase abstracta que representa un vehiculo. 
 * Cada vehiculo tiene una matricula unica.
 */
public abstract class Vehiculo {

	// Clave primaria autogenerada
	private long id;

	private String matricula;
	private LocalDate fechaMatriculacion;
	private TipoMotor motor;

	/**
	 * Contructor de la clase
	 * @param id id del vehiculo
	 * @param matricula matricula del vehiculo
	 * @param fechaMatriculacion fecha en la que se matriculo el vehiculo
	 * @param motor motor que tiene el vehiculo
	 */
	public Vehiculo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor) {
		this.id = id;
	    this.matricula = matricula;
	    this.fechaMatriculacion = fechaMatriculacion;
	    this.motor = motor;
	}
	
	/**
	 * Retorna la matricula del vehiculo.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Retorna la fecha de primera matriculacion del vehiculo.
	 */
	public LocalDate getFechaMatriculacion() {
		return fechaMatriculacion;
	}

	/**
	 * Retorna el tipo de motor del vehiculo.
	 */
	public TipoMotor getMotor() {
		return motor;
	}

	/**
	 * Retorna el identificador del vehiculo.
	 */
	public long getId() {
		return id;
	}
	

	// TODO
	public abstract double precioImpuesto();
	
}
