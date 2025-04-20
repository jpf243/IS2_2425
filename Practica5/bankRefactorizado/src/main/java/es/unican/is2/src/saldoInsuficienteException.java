package es.unican.is2.src;

@SuppressWarnings("serial")
public class saldoInsuficienteException extends RuntimeException {
	
	// CBO = Sumatorio Clases donde se lanza la excepción
	public saldoInsuficienteException (String mensaje) {
		super(mensaje);
	}
}
