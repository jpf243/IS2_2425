package es.unican.is2.src;

public class Direccion {

	private String calle;
    private String zip;
    private String localidad;

    // WMC = 1
 	// CCOG = 0
    public Direccion(String calle, String zip, String localidad) {
        this.calle = calle;
        this.zip = zip;
        this.localidad = localidad;
    }
    
    // WMC = 1
 	// CCOG = 0
    public String getCalle() {
        return calle;
    }
    // WMC = 1
 	// CCOG = 0
    public String getZip() {
        return zip;
    }
    // WMC = 1
 	// CCOG = 0
    public String getLocalidad() {
        return localidad;
    }
    // WMC = 1
 	// CCOG = 0
    public void setCalle(String calle) {
        this.calle = calle;
    }
    // WMC = 1
 	// CCOG = 0
    public void setZip(String zip) {
        this.zip = zip;
    }
    // WMC = 1
 	// CCOG = 0
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
