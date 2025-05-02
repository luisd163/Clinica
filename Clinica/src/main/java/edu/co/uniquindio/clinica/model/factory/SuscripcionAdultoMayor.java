package edu.co.uniquindio.clinica.model.factory;

import edu.co.uniquindio.clinica.Servicios.ServicioServicios;
import edu.co.uniquindio.clinica.model.Factura;
import edu.co.uniquindio.clinica.model.Servicio;

import java.time.LocalDate;
import java.util.List;

public class SuscripcionAdultoMayor implements Suscripcion {

    ServicioServicios servicio = new ServicioServicios();

    /**
     * Método que lista los servicios disponibles de la suscripcion adulto mayor
     * @return lista de servicios disponibles
     */
    @Override
    public List<Servicio> getServiciosDisponibles() {
        return servicio.getServiciosDisponibles(this);
    }

    /**
     * Método que genera una factura de cobro para la suscripcion adulto mayor
     * @param fecha
     * @param id
     * @param servicio servicio al cual se accede y genera la factura
     * @return factura generada con los datos correspondientes al servicio adquirido
     */
    @Override
    public Factura generarFacturaCobro(LocalDate fecha, String id, Servicio servicio) {
        return Factura.builder()
                .fecha(fecha)
                .id(id)
                .subtotal(servicio.getPrecio())
                .total(0)
                .build();
    }
}
