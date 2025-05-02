package edu.co.uniquindio.clinica.Servicios;

import edu.co.uniquindio.clinica.Repositorios.IFacturaRepositorio;
import edu.co.uniquindio.clinica.model.Clinica;
import edu.co.uniquindio.clinica.model.Factura;
import edu.co.uniquindio.clinica.model.Paciente;
import edu.co.uniquindio.clinica.model.Servicio;

import java.time.LocalDate;
import java.util.LinkedList;

public class FacturaServicio implements IFacturaRepositorio {
    Clinica clinica= new Clinica();
    LinkedList<Paciente> pacientes= clinica.getPacientes();
    LinkedList<Servicio> servicios= clinica.getServicios();

    /**
     * Método para generar una factura de cobro de un servicio
     * @param paciente paciente que sale en la factura
     * @param servicio servicio por el cual se genera la factura
     * @return factura generada
     * @throws Exception si el paciente o el servicio son nulos, o si el paciente no esta registrado
     */
    public Factura generarFactura(Paciente paciente, LocalDate fecha, String id, Servicio servicio) throws Exception {
        if( paciente == null ){
            throw new Exception ("El paciente no puede estar vacio");
        }

        if( servicio == null ){
            throw new Exception ("El servicio no puede estar vacio");
        }

        if( !pacientes.contains(paciente) ){
            throw new Exception ("El paciente no esta registrado");
        }

        if( !servicios.contains(servicio) ){
            throw new Exception ("El servicio no esta registrado");
        }

        return paciente.getSuscripcion().generarFacturaCobro(fecha, id, servicio);
    }
}
