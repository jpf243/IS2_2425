package es.unican.is2.src;

import java.util.LinkedList;
import java.util.List;

public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	// WMC = 1
	// CCOG = 0
	public CuentaValores(String numCuenta) {
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}
	
	// WMC = 1
	// CCOG = 0
	public List<Valor> getValores() {
		return valores;
	}
	
	// WMC = 3
	// CCOG = 3
	// CBO + 1
	public boolean anhadeValor(Valor valor) {
		for (Valor v:valores) { // CCOG +1 - WMC +1
			if (v.getEntidad().equals(valor.getEntidad())) // CCOG +2 - WMC +1
				return false;
		}
		valores.add(valor);
		return true;
	}
	// WMC = 2
	// CCOG = 1
	@Override
	public double getSaldo() {
	    double saldo = 0.0;
	    for (Valor v : valores) { // CCOG +1 - WMC +1
	        saldo += v.getCotizacion() * v.getNumValores();
	    }
	    return saldo;
	}
	
}
