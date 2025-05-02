package edu.co.uniquindio.clinica.model.factory;
import edu.co.uniquindio.clinica.model.Factura;
import edu.co.uniquindio.clinica.model.Servicio;

import java.time.LocalDate;
import java.util.List;

public interface Suscripcion{

    /**
     * Método para obtener los servicios disponibles de la suscripcion
     * @return lista con los servicios disponibles
     */
    public List<Servicio> getServiciosDisponibles();

    /**
     * Método para generar la factura de cobro de un servicio
     * @param servicio servicio al cual se accede y genera la factura
     * @return factura generada
     */
    public Factura generarFacturaCobro(LocalDate fecha, String id, Servicio servicio);

}
