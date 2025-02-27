package es.unican.is2.Business;

import es.unican.is2.Common.Contribuyente;
import es.unican.is2.Common.DataAccessException;
import es.unican.is2.Common.IGestionContribuyentes;
import es.unican.is2.Common.IGestionVehiculos;
import es.unican.is2.Common.IInfoImpuestoCirculacion;
import es.unican.is2.Common.OperacionNoValidaException;
import es.unican.is2.Common.Vehiculo;
import es.unican.is2.DAOH2.ContribuyentesDAO;
import es.unican.is2.DAOH2.VehiculosDAO;

public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion{
	
	public GestionImpuestoCirculacion (ContribuyentesDAO contribuyentesDAO, VehiculosDAO vehiculosDAO) {
	}
	
	public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Vehiculo vehiculo(String matricula) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Contribuyente contribuyente(String dni) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo)
			throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

}
