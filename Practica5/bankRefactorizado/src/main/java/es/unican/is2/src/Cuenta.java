package es.unican.is2.src;

public abstract class Cuenta {
	
	private String numCuenta;
	
	// WMC = 1
	// CCOG = 0
	public Cuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	
	// WMC = 1
	// CCOG = 0
	public String getNumCuenta() {
		return numCuenta;
	}
	/**
	 * metodo para saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */
	public abstract double getSaldo();
}
