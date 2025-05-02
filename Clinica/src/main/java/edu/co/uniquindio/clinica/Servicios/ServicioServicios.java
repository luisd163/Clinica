package edu.co.uniquindio.clinica.Servicios;

import edu.co.uniquindio.clinica.Repositorios.IServicioRepositorio;
import edu.co.uniquindio.clinica.controladores.ControladorPrincipal;
import edu.co.uniquindio.clinica.model.Clinica;
import edu.co.uniquindio.clinica.model.Servicio;
import edu.co.uniquindio.clinica.model.factory.Suscripcion;
import edu.co.uniquindio.clinica.model.factory.SuscripcionAdultoMayor;
import edu.co.uniquindio.clinica.model.factory.SuscripcionBasica;
import edu.co.uniquindio.clinica.model.factory.SuscripcionPremium;

import java.util.LinkedList;
import java.util.List;

public class ServicioServicios implements IServicioRepositorio {
    LinkedList<Servicio> servicios = ControladorPrincipal.getInstancia().getClinica().getServicios();

    /**
     * Método para registrar un servicio a la lista de la clinica
     * @param servicio servicio a registrar
     * @throws Exception si el servicio es nulo o si ya esta registrado
     */
    public void registrarServicios(Servicio servicio) throws Exception {
        if( servicio == null ){
            throw new Exception ("El servicio no puede estar vacio");
        }

        if( servicios.contains(servicio) ){
            throw new Exception ("El servicio ya esta registrado");
        }
        servicios.add(servicio);
    }

    /**
     * Método que devuelve una lista con los servicios disponibles
     * @return lista con los servicios disponibles
     */
    public List<Servicio> getServiciosDisponibles() {
        return servicios;
    }

    /**
     * Método para obtener los servicios disponibles de una suscripcion
     * @param suscripcion
     * @return lista con los servicios disponibles
     */
    public List<Servicio> getServiciosDisponibles(Suscripcion suscripcion) {
        LinkedList<Servicio> servicios = this.servicios;
        if(suscripcion instanceof SuscripcionBasica){
            for (Servicio servicioAux : servicios) {
                if (servicioAux.getNombre().equals("Odontologia") || servicioAux.getNombre().equals("Pediatria") || servicioAux.getNombre().equals("Ortopedia")){
                    servicios.remove(servicioAux);
                }
            }
        }
        if(suscripcion instanceof SuscripcionPremium){
            for (Servicio servicioAux : servicios) {
                if (servicioAux.getNombre().equals("Ortopedia")){
                    servicios.remove(servicioAux);
                }
            }
        }
        if(suscripcion instanceof SuscripcionAdultoMayor){
            return servicios;
        }
        return servicios;

    }
}
