package edu.co.uniquindio.clinica.model.factory;

import edu.co.uniquindio.clinica.Servicios.ServicioServicios;
import edu.co.uniquindio.clinica.model.Factura;
import edu.co.uniquindio.clinica.model.Servicio;

import java.time.LocalDate;
import java.util.List;

public class SuscripcionPremium implements Suscripcion {

    ServicioServicios servicio = new ServicioServicios();

    /**
     * Método que lista los servicios disponibles de la suscripción premium
     * @return lista con los servicios disponibles
     */
    @Override
    public List<Servicio> getServiciosDisponibles() {
        return servicio.getServiciosDisponibles(this);
    }

    /**
     * Método que genera una factura de cobro
     * @param fecha fecha en la que se genero la factura
     * @param id id de la factura
     * @param servicio servicio al cual se accede y genera la factura
     * @return factura con los datos correspondientes al servicio adquirido
     */
    @Override
    public Factura generarFacturaCobro(LocalDate fecha, String id, Servicio servicio) {
        if(this.getServiciosDisponibles().contains(servicio)){
            return Factura.builder()
                    .fecha(fecha)
                    .id(id)
                    .subtotal(servicio.getPrecio())
                    .total(0)
                    .build();
        }
        return Factura.builder()
                .fecha(fecha)
                .id(id)
                .subtotal(servicio.getPrecio())
                .total(servicio.getPrecio())
                .build();
    }

}
