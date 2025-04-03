package es.unican.is2.src;

import java.time.LocalDateTime;

public class Movimiento {
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	public double getI() {
		return importe;
	}

	public void setI(double newMImporte) {
		importe = newMImporte;
	}
	
	public String getC() {
		return concepto;
	}

	public void setC(String newMConcepto) {
		concepto = newMConcepto;
	}

	public LocalDateTime getF() {
		return fecha;
	}

	public void setF(LocalDateTime newMFecha) {
		fecha = newMFecha;
	}

	
	@Override
	public boolean equals(Object obj) {
		Movimiento other = (Movimiento)obj;
		return (concepto.equals(other.concepto) && fecha.equals(other.fecha)&& importe==other.importe);
	}
	
}