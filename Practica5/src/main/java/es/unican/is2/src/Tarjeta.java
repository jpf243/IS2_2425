package es.unican.is2.src;

public abstract class Tarjeta {
	
	protected String numero, titular, cvc;		
	protected CuentaAhorro cuentaAsociada;
	
	// WMC = 1
	// CCOG = 0
	public Tarjeta(String numero, String titular, String cvc,
			// CBO +1
			CuentaAhorro cuentaAsociada) {
		this.numero = numero;
		this.titular = titular;
		this.cvc = cvc;
		this.cuentaAsociada = cuentaAsociada;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	// WMC = 1
	// CCOG = 0
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	// WMC = 1
	// CCOG = 0
	public abstract void pagoEnEstablecimiento(String datos, double x)
			// CBO +2
			throws saldoInsuficienteException, datoErroneoException;
	
}