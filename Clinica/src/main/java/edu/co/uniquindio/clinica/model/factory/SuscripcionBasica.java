package edu.co.uniquindio.clinica.model.factory;

import edu.co.uniquindio.clinica.Servicios.ServicioServicios;
import edu.co.uniquindio.clinica.model.Factura;
import edu.co.uniquindio.clinica.model.Servicio;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class SuscripcionBasica implements Suscripcion {

    ServicioServicios servicio = new ServicioServicios();

    public SuscripcionBasica() {}

    /**
     * Método que lista los servicios disponibles para la suscripción basica
     * @return lista con los servicios de la clinica menos odontologia y pediatria
     */
    @Override
    public List<Servicio> getServiciosDisponibles() {
        return servicio.getServiciosDisponibles(this);
    }

    /**
     * Método que genera una factura de cobro para la suscripción basica
     * @param fecha fecha en la que se genero la factura
     * @param id id de la factura
     * @param servicio servicio al cual se accede y genera la factura
     * @return factura con los datos correspondientes al servicio adquirido
     */
    @Override
    public Factura generarFacturaCobro(LocalDate fecha, String id, Servicio servicio) {
        //Servicios inculidos en la suscripcion basica
        if (this.getServiciosDisponibles().contains(servicio)){
            return Factura.builder()
                    .fecha(fecha)
                    .id(id)
                    .subtotal(servicio.getPrecio())
                    .total(0)
                    .build();
        }
        //Se le hace un 50% de descuento en el servicio de odontologia
        if(servicio.getNombre().equals("Odontologia")){
            return Factura.builder()
                    .fecha(fecha)
                    .id(id)
                    .subtotal(servicio.getPrecio())
                    .total(servicio.getPrecio() * 0.5)
                    .build();
        }
        //Servicios no incluidos en la suscripcion basica
        return Factura.builder()
                .fecha(fecha)
                .id(id)
                .subtotal(servicio.getPrecio())
                .total(servicio.getPrecio())
                .build();
    }

}
