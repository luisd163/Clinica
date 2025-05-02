package edu.co.uniquindio.clinica.Repositorios;

import edu.co.uniquindio.clinica.model.Factura;
import edu.co.uniquindio.clinica.model.Paciente;
import edu.co.uniquindio.clinica.model.Servicio;

import java.time.LocalDate;

public interface IFacturaRepositorio {
    public Factura generarFactura(Paciente paciente, LocalDate fecha, String id, Servicio servicio) throws Exception;

    }
