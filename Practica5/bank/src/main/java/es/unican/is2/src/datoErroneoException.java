package es.unican.is2.src;

@SuppressWarnings("serial")
public class datoErroneoException extends RuntimeException {
	
	// CBO = Sumatorio Clases donde se lanza la excepción
	public datoErroneoException (String mensaje) {
		super(mensaje);
	}

}
