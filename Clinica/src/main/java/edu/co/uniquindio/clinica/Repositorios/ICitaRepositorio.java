package edu.co.uniquindio.clinica.Repositorios;

import edu.co.uniquindio.clinica.model.Cita;
import edu.co.uniquindio.clinica.model.Servicio;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ICitaRepositorio {


    public boolean registrarCita(String cedulaPaciente, LocalDate fecha, String hora, Servicio servicio) throws Exception;

    public boolean cancelarCita(Cita cita) throws Exception;


    }
