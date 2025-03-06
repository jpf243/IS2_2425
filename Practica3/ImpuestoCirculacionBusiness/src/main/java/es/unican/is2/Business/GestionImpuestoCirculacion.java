package es.unican.is2.Business;

import es.unican.is2.Common.Contribuyente;
import es.unican.is2.Common.DataAccessException;
import es.unican.is2.Common.IContribuyentesDAO;
import es.unican.is2.Common.IGestionContribuyentes;
import es.unican.is2.Common.IGestionVehiculos;
import es.unican.is2.Common.IInfoImpuestoCirculacion;
import es.unican.is2.Common.IVehiculosDAO;
import es.unican.is2.Common.OperacionNoValidaException;
import es.unican.is2.Common.Vehiculo;
import es.unican.is2.DAOH2.ContribuyentesDAO;
import es.unican.is2.DAOH2.VehiculosDAO;

public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion{
	
	private IContribuyentesDAO contribuyentesDAO;
    private IVehiculosDAO vehiculosDAO;
    
    public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentesDAO, IVehiculosDAO vehiculosDAO) {
        this.contribuyentesDAO = contribuyentesDAO;
        this.vehiculosDAO = vehiculosDAO;
    }
	
	/**
	 * Registra un nuevo contribuyente
	 * @param c Contribuyente que desea registrar
	 * @return El contribuyente registrado
	 * 		   null si no se registra porque ya existe un 
	 *              contribuyente con el mismo dni
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException {
		return contribuyentesDAO.creaContribuyente(c);
	}
	
	/**
	 * Elimina el contribuyente cuyo dni se indica
	 * @param dni DNI del contribuyente que se quiere eliminar
	 * @return El contribuyente eliminado
	 * 		   null si no se elimina porque no se encuentra 
	 * @throws OperacionNoValidaException si el contribuyente existe 
	 *         pero tiene vehiculos a su nombre
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValidaException, DataAccessException {
		Contribuyente contribuyente = contribuyentesDAO.contribuyente(dni);
        if (contribuyente == null) {
            return null;
        }
        if (!contribuyente.getVehiculos().isEmpty()) {
            throw new OperacionNoValidaException("El contribuyente tiene vehículos a su nombre");
        }
        return contribuyentesDAO.eliminaContribuyente(dni);
	}
	
	/**
	 * Retorna el vehiculo cuya matricula se indica
	 * @param matricula Matricula del vehiculo buscado
	 * @return El vehiculo correspondiente a la matricula
	 * 	       null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo vehiculo(String matricula) throws DataAccessException {
		return vehiculosDAO.vehiculoPorMatricula(matricula);
	}
	
	/**
	 * Retorna el contribuyente cuyo dni se indica
	 * @param dni DNI del contribuyente buscado
	 * @return El contribuyente correspondiente al dni
	 * 		   null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Contribuyente contribuyente(String dni) throws DataAccessException {
		return contribuyentesDAO.contribuyente(dni);
	}
	
	/**
	 * Registra un nuevo vehiculo y lo asocia al contribuyente con el dni indicado
	 * @param v Vehiculo que desea registrar
	 * @param dni DNI del contribuyente
	 * @return El vehiculo ya registrado
	 * 		   null si no se registra porque no se encuentra el contribuyente
	 * @throws OperacionNoValidaException si ya existe un vehiculo con la misma matricula
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValidaException, DataAccessException {
		if (vehiculosDAO.vehiculoPorMatricula(v.getMatricula()) != null) {
            throw new OperacionNoValidaException("Ya existe un vehículo con la misma matrícula");
        }
        Contribuyente contribuyente = contribuyentesDAO.contribuyente(dni);
        if (contribuyente == null) {
            return null;
        }
        contribuyente.getVehiculos().add(v);
        vehiculosDAO.creaVehiculo(v);
        contribuyentesDAO.actualizaContribuyente(contribuyente);
        return v;
	}
	
	/**
	 * Elimina el vehiculo cuya matricula se indica y 
	 * que pertenece al contribuyente cuyo dni se indica
	 * @param matricula Matricula del coche a eliminar
	 * @param dni DNI del propietario del vehiculo
 	 * @return El vehiculo eliminado
 	 *         null si el vehiculo o el propietario no existen
 	 * @throws OperacionNoValidaException si el vehiculo no pertenece al dni indicado
 	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValidaException, DataAccessException {
		Vehiculo vehiculo = vehiculosDAO.vehiculoPorMatricula(matricula);
		Contribuyente propietario = contribuyentesDAO.contribuyente(dni);
        if (vehiculo == null || propietario == null) {
            return null;
        }
        vehiculosDAO.eliminaVehiculo(matricula);
        propietario.getVehiculos().remove(vehiculo);
        contribuyentesDAO.actualizaContribuyente(propietario);
        return vehiculo;
	}
	
	/**
	 * Cambia el propietario del vehiculo indicado
	 * @param matricula Matricula del vehiculo
	 * @param dniActual DNI del propietario actual del vehiculo
	 * @param dniNuevo DNI del nuevo propietario del vehiculo
	 * @return true si se realiza el cambio
	 *         false si no realiza el cambio porque el vehiculo o los contribuyentes no existen
	 * @throws OperacionNoValidaException si el vehiculo no pertenece al dni indicado
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo)
			throws OperacionNoValidaException, DataAccessException {
		Vehiculo vehiculo = vehiculosDAO.vehiculoPorMatricula(matricula);
        Contribuyente actual = contribuyentesDAO.contribuyente(dniActual);
        Contribuyente nuevo = contribuyentesDAO.contribuyente(dniNuevo);
        
        if (vehiculo == null || actual == null || nuevo == null) {
            return false;
        }
        if (actual.buscaVehiculo(matricula) == null) {
            throw new OperacionNoValidaException("El vehículo no pertenece al contribuyente indicado");
        }
        
        actual.getVehiculos().remove(vehiculo);
        nuevo.getVehiculos().add(vehiculo);
        
        contribuyentesDAO.actualizaContribuyente(actual);
        contribuyentesDAO.actualizaContribuyente(nuevo);
        vehiculosDAO.actualizaVehiculo(vehiculo);
        
        return true;
    }

}
