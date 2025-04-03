package es.unican.is2.src;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) {
		super(numero, titular, cvc, cuentaAsociada);
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}

	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero", x);
		saldoDiarioDisponible-=x;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	public LocalDate getCaducidadDebito() {
		return this.cuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Metodo invocado automaticamente a las 00:00 de cada dia
	 */
	public void restableceSaldo() {
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	}

}