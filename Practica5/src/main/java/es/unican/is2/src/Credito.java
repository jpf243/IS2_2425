package es.unican.is2.src;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Credito extends Tarjeta {
	
	private double credito;
	private List<Movimiento> MovimientosMensuales;
	private List<Movimiento> historicoMovimientos;

	public Credito(String numero, String titular, String cvc,
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
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada en cajero");
		x += x * 0.05; // Comision por operacion con tarjetas credito
		m.setI(-x);
		
		if (getGastosAcumulados()+x > credito)
			throw new saldoInsuficienteException("Credito insuficiente");
		else {
			MovimientosMensuales.add(m);
		}
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > credito)
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Compra a credito en: " + datos);
		m.setI(-x);
		MovimientosMensuales.add(m);
	}
	
    private double getGastosAcumulados() {
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) {
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		return r;
	}
	
	
	public LocalDate getCaducidadCredito() {
		return this.cuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Metodo que se invoca automaticamente el dia 1 de cada mes
	 */
	public void liquidar() {
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setF(now);
		liq.setC("Liquidacion de operaciones tarjeta credito");
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) {
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		liq.setI(-r);
	
		if (r != 0)
			cuentaAsociada.addMovimiento(liq);
		
		historicoMovimientos.addAll(MovimientosMensuales);
		MovimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosMensuales() {
		return MovimientosMensuales;
	}
	
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() {
		return historicoMovimientos;
	}

}