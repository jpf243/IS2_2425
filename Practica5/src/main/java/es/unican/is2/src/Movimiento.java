package es.unican.is2.src;

import java.time.LocalDateTime;

public class Movimiento {
	private String concepto;
	private LocalDateTime fecha;
	private double importe;
	
	// WMC = 1
	// CCOG = 0
	public double getI() {
		return importe;
	}

	// WMC = 1
	// CCOG = 0
	public void setI(double newMImporte) {
		importe = newMImporte;
	}
	
	// WMC = 1
	// CCOG = 0
	public String getC() {
		return concepto;
	}
	
	// WMC = 1
	// CCOG = 0
	public void setC(String newMConcepto) {
		concepto = newMConcepto;
	}
	
	// WMC = 1
	// CCOG = 0
	public LocalDateTime getF() {
		return fecha;
	}
	
	// WMC = 1
	// CCOG = 0
	public void setF(LocalDateTime newMFecha) {
		fecha = newMFecha;
	}

	// WMC = 1
	// CCOG = 0
	@Override
	public boolean equals(Object obj) {
		Movimiento other = (Movimiento)obj;
		return (concepto.equals(other.concepto) && fecha.equals(other.fecha)&& importe==other.importe);
	}
	
}