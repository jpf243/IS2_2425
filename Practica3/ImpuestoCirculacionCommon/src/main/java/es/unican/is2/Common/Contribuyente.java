package es.unican.is2.Common;
import java.util.LinkedList;

import java.util.List;

/**
 * Clase que representa un contribuyente del ayuntamiento.
 * Cada contribuyente debe tener un dni que lo identifica y 
 * puede tener vehiculos a su nombre
 */
public class Contribuyente {

	private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    
    private List<Vehiculo> vehiculos = new LinkedList<Vehiculo>();

    /**
     * Realiza el constructor de la clase
     * @param nombre nombre del contribuyente
     * @param apellido1 primer apellido del contribuyente
     * @param apellido2 segundo apellido del contribuyente
     * @param dni dni del contribuyente
     */
    public Contribuyente(String nombre, String apellido1, String apellido2, String dni) {
    	this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.vehiculos = new LinkedList<>();
	}
    
    /**
     * Retorna el dni del contribuyente.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Retorna el nombre del contribuyente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el primer apellido del contribuyente.
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Retorna el segundo apellido del contribuyente.
     */
    public String getApellido2() {
        return apellido2;
    }
    
    /**
     * Retorna la lista de vehiculos del contribuyente
     */
    public List<Vehiculo> getVehiculos() {
        if (vehiculos == null) {
        	vehiculos = new LinkedList<Vehiculo>();
        }
        return this.vehiculos;
    }
    
    /**
     * Retorna el valor total de impuesto de circulacion 
     * a pagar por el contribuyente debido a todos sus vehiculos
     * @return valor total del impuesto de circulaci�n
     */
    public double totalImpuestoCirculacion() {
    	double total=0.0;
    	for (Vehiculo v: vehiculos) {
    		total+=v.precioImpuesto();
    	}	
    	return total;
    }
    
    /**
     * Retorna el vehiculo cuya matricula se indica
     * @return El vehiculo si se encuentra en la lista de vehiculos
     * 		   null si no se encuentra el vehiculo
     */
    public Vehiculo buscaVehiculo(String matricula) {
    	for (Vehiculo v:vehiculos) {
    		if (v.getMatricula().equals(matricula))
    			return v;
    	}
    	return null;
    		
    }
}
