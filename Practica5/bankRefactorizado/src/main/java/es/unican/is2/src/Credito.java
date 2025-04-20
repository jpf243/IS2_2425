package es.unican.is2.src;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Credito extends Tarjeta {
	
	public static final double COMISION = 0.05;
	private double credito;
	private List<Movimiento> movimientosMensuales;
	private List<Movimiento> historicoMovimientos;
	
	// WMC = 1
	// CCOG = 0
	public Credito(String numero, String titular, String cvc,
			// CBO + 1
			CuentaAhorro cuentaAsociada, double credito) {
		super(numero, titular, cvc, cuentaAsociada);
		this.credito = credito;
	}
	
	// WMC = 4
	// CCOG = 3
    @Override
    public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
        if (x < 0) { // CCOG +1 - WMC +1
        	// CBO + 1
        	throw new datoErroneoException("No se puede retirar una cantidad negativa");
        }
        double total = x + x * COMISION;
        if (getGastosAcumulados() + total > credito) { // CCOG +1 - WMC +1
        	// CBO + 1
        	throw new saldoInsuficienteException("Credito insuficiente");
        } else { // CCOG +1 - WMC +1
            registrarMovimientoMensual("Retirada en cajero", -total);
        }
    }
    
    // WMC = 3
 	// CCOG = 2
    @Override
    public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
        if (x < 0) { // CCOG +1 - WMC +1
            throw new datoErroneoException("No se puede retirar una cantidad negativa");
        }
        if (getGastosAcumulados() + x > credito) { // CCOG +1 - WMC +1
            throw new saldoInsuficienteException("Saldo insuficiente");
        }
        registrarMovimientoMensual("Compra a credito en: " + datos, -x);
    }
    // WMC = 1
 	// CCOG = 0
    private void registrarMovimientoMensual(String concepto, double importe) {
    	// CBO + 1
    	Movimiento m = crearMovimiento(concepto, importe);
        movimientosMensuales.add(m);
    }
    // WMC = 1
  	// CCOG = 0
    private Movimiento crearMovimiento(String concepto, double importe) {
    	Movimiento m = new Movimiento();
        m.setF(LocalDateTime.now());
        m.setC(concepto);
        m.setI(importe);
        return m;
    }
    // WMC = 2
  	// CCOG = 1
    private double getGastosAcumulados() {
        double r = 0.0;
        for (int i = 0; i < this.movimientosMensuales.size(); i++) { // CCOG +1 - WMC +1
        	Movimiento m = movimientosMensuales.get(i);
            r += m.getI();
        }
        return r;
    }
    // WMC = 1
  	// CCOG = 0
    public LocalDate getCaducidadCredito() {
        return this.cuentaAsociada.getCaducidadCredito();
    }

    // WMC = 3
  	// CCOG = 2
    public void liquidar() {
        Movimiento liq = crearMovimiento("Liquidacion de operaciones tarjeta credito", 0);
        double r = 0.0;
        for (int i = 0; i < this.movimientosMensuales.size(); i++) { // CCOG +1 - WMC +1
            Movimiento m = movimientosMensuales.get(i);
            r += m.getI();
        }
        liq.setI(-r);

        if (r != 0) // CCOG +1 - WMC +1
            cuentaAsociada.addMovimiento(liq);

        historicoMovimientos.addAll(movimientosMensuales);
        movimientosMensuales.clear();
    }
    // WMC = 1
  	// CCOG = 0
    public List<Movimiento> getMovimientosMensuales() {
        return movimientosMensuales;
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
    // WMC = 1
  	// CCOG = 0
    @Override
    public void actualizarCaducidad() {
        cuentaAsociada.setCaducidadCredito(getCaducidadCredito());
    }
}