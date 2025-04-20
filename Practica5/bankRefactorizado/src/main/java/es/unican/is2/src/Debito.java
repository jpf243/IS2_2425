package es.unican.is2.src;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;
	

	// WMC = 1
	// CCOG = 0												// CBO + 1
	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) {
		super(numero, titular, cvc, cuentaAsociada);
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	

	// WMC = 2
	// CCOG = 1
	@Override															// CBO + 1
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) { // CCOG +1 - WMC +1
			// CBO + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero", x);
		saldoDiarioDisponible-=x;
	}
	

	// WMC = 2
	// CCOG = 1
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) { // CCOG +1 - WMC +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	

	// WMC = 1
	// CCOG = 0
	public LocalDate getCaducidadDebito() {
		return this.cuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Metodo invocado automaticamente a las 00:00 de cada dia
	 */

	// WMC = 1
	// CCOG = 0
	public void restableceSaldo() {
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	

	// WMC = 1
	// CCOG = 0
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	}
	// WMC = 1
	// CCOG = 0
	@Override
	public void actualizarCaducidad() {
	    cuentaAsociada.setCaducidadDebito(getCaducidadDebito());
	}
}