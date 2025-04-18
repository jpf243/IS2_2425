package es.unican.is2.src;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Credito extends Tarjeta {
	
	private double credito;
	private List<Movimiento> MovimientosMensuales;
	private List<Movimiento> historicoMovimientos;
	
	// WMC = 1
	// CCOG = 0
	public Credito(String numero, String titular, String cvc,
			// CBO + 1
			CuentaAhorro cuentaAsociada, double credito) {
		super(numero, titular, cvc, cuentaAsociada);
		this.credito = credito;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	// WMC = 4
	// CCOG = 3
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0) // CCOG +1 - WMC +1
			// CBO + 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		// CBO + 1
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada en cajero");
		x += x * 0.05; // Comision por operacion con tarjetas credito
		m.setI(-x);
		
		if (getGastosAcumulados()+x > credito) // CCOG +1 - WMC +1
			// CBO + 1
			throw new saldoInsuficienteException("Credito insuficiente");
		else { // CCOG +1 - WMC +1
			MovimientosMensuales.add(m);
		}
	}
	
	// WMC = 3
	// CCOG = 2
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0)	// CCOG +1 - WMC +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > credito)	// CCOG +1 - WMC +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Compra a credito en: " + datos);
		m.setI(-x);
		MovimientosMensuales.add(m);
	}
	
	// WMC = 2
	// CCOG = 1
    private double getGastosAcumulados() {
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) { // CCOG +1 - WMC +1
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		return r;
	}
	
    // WMC = 1
 	// CCOG = 0
	public LocalDate getCaducidadCredito() {
		return this.cuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Metodo que se invoca automaticamente el dia 1 de cada mes
	 */
	// WMC = 3
	// CCOG = 2
	public void liquidar() {
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setF(now);
		liq.setC("Liquidacion de operaciones tarjeta credito");
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) { // CCOG +1 - WMC +1
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		liq.setI(-r);
	
		if (r != 0) // CCOG +1 - WMC +1
			cuentaAsociada.addMovimiento(liq);
		
		historicoMovimientos.addAll(MovimientosMensuales);
		MovimientosMensuales.clear();
	}
	// WMC = 1
	// CCOG = 0
	public List<Movimiento> getMovimientosMensuales() {
		return MovimientosMensuales;
	}
	
	// WMC = 1
	// CCOG = 0
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	}
	
	// WMC = 1
	// CCOG = 0
	public List<Movimiento> getMovimientos() {
		return historicoMovimientos;
	}

}