package es.unican.is2.src;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> movimientos;
	private LocalDate caducidadDebito;
	private LocalDate caducidadCredito;
	private double limiteDebito;
	
	// WMC = 1
	// CCOG = 0
	public CuentaAhorro(String numCuenta)  throws datoErroneoException {
		super(numCuenta);
		// CBO + 1
		movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}
	
	// WMC = 1
	// CCOG = 0
	public void ingresar(double cantidad) throws datoErroneoException {
	    ingresar("Ingreso en efectivo", cantidad);
	}

	// WMC = 2
	// CCOG = 1
	public void ingresar(String concepto, double cantidad) throws datoErroneoException {
	    if (cantidad <= 0) { // CCOG +1 - WMC +1
	    	// CBO + 1
	        throw new datoErroneoException("No se puede ingresar una cantidad negativa");
	    }
	    registrarMovimiento(concepto, cantidad);
	}
	// WMC = 1
	// CCOG = 0
	public void retirar(double cantidad) throws saldoInsuficienteException, datoErroneoException {
	    retirar("Retirada de efectivo", cantidad);
	}
	// WMC = 3
	// CCOG = 2
	public void retirar(String concepto, double cantidad) throws saldoInsuficienteException, datoErroneoException {
	    if (cantidad <= 0) { // CCOG +1 - WMC +1
	        throw new datoErroneoException("No se puede retirar una cantidad negativa");
	    }
	    if (getSaldo() < cantidad) { // CCOG +1 - WMC +1
	    	// CBO + 1
	        throw new saldoInsuficienteException("Saldo insuficiente");
	    }
	    registrarMovimiento(concepto, -cantidad);
	}
	
	// WMC = 1
	// CCOG = 0
	/**
	 * registra los movimiento que se realizan en la cuentaAhorro
	 * @param concepto concepto del movimiento
	 * @param cantidad cantidad con la que se opera
	 */
	private void registrarMovimiento(String concepto, double cantidad) {
	    Movimiento m = new Movimiento();
	    m.setF(LocalDateTime.now());
	    m.setC(concepto);
	    m.setI(cantidad);
	    movimientos.add(m);
	}

	// WMC = 1
	// CCOG = 0
	public void addMovimiento(Movimiento m) {
		movimientos.add(m);
	}
	
	// WMC = 1
	// CCOG = 0
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}
	
	// WMC = 1
	// CCOG = 0
	public LocalDate getCaducidadDebito() {
		return caducidadDebito;
	}
	
	// WMC = 1
	// CCOG = 0
	public void setCaducidadDebito(LocalDate caducidadDebito) {
		this.caducidadDebito = caducidadDebito;
	}
	
	// WMC = 1
	// CCOG = 0
	public LocalDate getCaducidadCredito() {
		return caducidadCredito;
	}
	
	// WMC = 1
	// CCOG = 0
	public void setCaducidadCredito(LocalDate caducidadCredito) {
		this.caducidadCredito = caducidadCredito;
	}
	
	// WMC = 1
	// CCOG = 0
	public double getLimiteDebito() {
		return limiteDebito;
	}
	
	// WMC = 2
	// CCOG = 1
	@Override
	public double getSaldo() {
	    double saldo = 0.0;
	    for (Movimiento m : movimientos) { // CCOG +1 - WMC +1
	        saldo += m.getI();
	    }
	    return saldo;
	}

}