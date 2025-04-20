package es.unican.is2.src;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	public String nombre;
	public String telefono;
	public String dni;
	private Direccion direccion;
	
    private List<Cuenta> cuentas = new LinkedList<Cuenta>();
    
    private List<Tarjeta> tarjetas = new LinkedList<Tarjeta>();
    
    // WMC = 1
    // CCOG = 0
 	public Cliente(String titular, Direccion direccion, String telefono, String dni) {  
		this.nombre = titular;
		this.direccion = direccion;
		this.telefono = telefono;
		this.dni = dni;
	}
	
 	// WMC = 1
 	// CCOG = 0
 	// CBO + 1
	public void cambiaDireccion(Direccion newDireccion) {
		this.direccion = newDireccion;
	}
	
	// WMC = 1
	// CCOG = 0
	// CBO + 1
	public void anhadeCuenta(Cuenta c) {
		cuentas.add(c);
	}
	
	// WMC = 1
	// CCOG = 0
	// CBO + 1
	public void anhadeTarjeta(Tarjeta t) {
	    tarjetas.add(t);
	    t.actualizarCaducidad();  // Ya no hace falta instanceof
	}
	
	// WMC = 2
	// CCOG = 1
	public double getSaldoTotal() {
	    double total = 0.0;
	    for (Cuenta cuenta : cuentas) {
	        total += cuenta.getSaldo();
	    }
	    return total;
	}
	
	// WMC = 1
	// CCOG = 0
	public String getNombre() {
		return nombre;
	}
	
	// WMC = 1
	// CCOG = 0
	public String getTelefono() {
		return telefono;
	}
	
	// WMC = 1
	// CCOG = 0
	public String getDni() {
		return dni;
	}
	
	
	
}