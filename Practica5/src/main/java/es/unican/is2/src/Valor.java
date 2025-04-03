package es.unican.is2.src;

/**
 * Clase que representa un valor en bolsa (paquete de acciones). 
 * Cada valor contiene un n�mero de acciones 
 * de una de las entidades del IBEX 35
 */
public class Valor {
	
	private String entidad;	
	private int numAcciones;
	private double cotizacion;
	
	public Valor(String entidad, int numAcciones, double cotizacionActual) {
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual;
	}
	
	public int getNumValores() {
		return numAcciones;
	}

	public void setNumValores(int numValores) {
		this.numAcciones = numValores;
	}

	public double getCotizacion() {
		return cotizacion;
	}
	
	public void setCotizacion(double cotizacion) {
		this.cotizacion = cotizacion;
	}

	public String getEntidad() {
		return entidad;
	}
	
	@Override
	public boolean equals(Object obj) {
		Valor other = (Valor)obj;
		return (entidad.equals(other.entidad) && numAcciones==other.numAcciones);

	}

}