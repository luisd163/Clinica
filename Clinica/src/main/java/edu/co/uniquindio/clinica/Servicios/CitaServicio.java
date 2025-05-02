package edu.co.uniquindio.clinica.Servicios;

import edu.co.uniquindio.clinica.Repositorios.ICitaRepositorio;
import edu.co.uniquindio.clinica.model.*;
import edu.co.uniquindio.clinica.controladores.ControladorPrincipal;
import edu.co.uniquindio.clinica.utils.EnvioEmail;
import edu.co.uniquindio.clinica.utils.GeneradorCodigos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;;

public class CitaServicio implements ICitaRepositorio {
    private Clinica clinica = ControladorPrincipal.getInstancia().getClinica();

    LinkedList<Cita> citas= clinica.getCitas();
    LinkedList<Paciente> pacientes= clinica.getPacientes();
    PacienteServicio pacienteServicio= new PacienteServicio();
    FacturaServicio facturaServicio= new FacturaServicio();

    /**
     * Método que registra una cita a la lista de la clinica
     * @param cedulaPaciente cedula del paciente
     * @param fecha fecha de la cita
     * @param servicio servicio al que se va a acceder en la cita
     * @throws Exception si la fecha, el servicio o la factura son nulos, o si el paciente no esta registrado, o si ya hay una cita registrada para esa fecha en el mismo servicio
     */
    public boolean registrarCita(String cedulaPaciente, LocalDate fecha, String hora, Servicio servicio) throws Exception {

        for (Cita cita : citas) {
            if (cita != null) {
                if (cita.getFecha().equals(fecha) && cita.getServicio().equals(servicio)) {
                    throw new Exception ("Ya existe una cita registrada para esa fecha");
                }
            }
        }

        String id = GeneradorCodigos.generarCodigoDecimalStr(10);
        Paciente paciente = pacienteServicio.buscarPaciente(cedulaPaciente);
        Factura factura = facturaServicio.generarFactura(paciente, LocalDate.now(), GeneradorCodigos.generarCodigoHexadecimalStr(10), servicio);

        if( fecha == null ){
            throw new Exception ("La fecha no puede estar vacia");
        }

        if(fecha.isBefore(LocalDate.now())){
            throw new Exception("La fecha de la cita no puede ser anterior a la fecha actual");
        }

        if( hora == null ){
            throw new Exception ("La hora no puede estar vacia");
        }

        LocalTime horaCita = LocalTime.parse(hora);
        if(!hayDisponibilidad(fecha, horaCita, servicio)){
            throw new Exception("Ya existe una cita registrada para esa fecha y hora");
        }

        if( servicio == null ){
            throw new Exception ("El servicio no puede estar vacio");
        }

        if( factura == null ){
            throw new Exception ("La factura no puede estar vacia");
        }

        if( !pacientes.contains(paciente) ){
            throw new Exception ("El paciente no esta registrado");
        }

        Cita cita = Cita.builder()
                .paciente(paciente)
                .id(id)
                .fecha(fecha)
                .hora(hora)
                .servicio(servicio)
                .factura(factura)
                .build();

        EnvioEmail.enviarNotificacion(paciente.getEmail(), "Cita agendada", paciente.getNombre() +
                ", Su cita identificada con el id " + id + " del servicio " + servicio.getNombre() +
                " ha sido agendada para el dia " + fecha + "a la hora"+ hora +", tiene un costo de " + factura.getTotal());
        citas.add(cita);
        clinica.getCitas().add(cita);
        return true;
    }

    /**
     * Método para cancelar una cita de la lista de la clinica
     * @param cita cita a cancelar
     * @throws Exception si la cita es nula o si no esta registrada
     */
    public boolean cancelarCita(Cita cita) throws Exception {
        if( cita == null ){
            throw new Exception ("La cita no puede estar vacia");
        }

        if( !citas.contains(cita) ){
            throw new Exception ("La cita no esta registrada");
        }

        citas.remove(cita);
        return true;
    }

    public static List<String> generarHorarios() {
        List<String> horarios = new ArrayList<>();

        for (int hora = 7; hora <= 19; hora++) {
            String horario = String.format("%02d:00", hora);
            horarios.add(horario);
        }

        return horarios;
    }

    /**
     * Método que valida la disponibilidad de una cita
     * @param fecha
     * @param hora
     * @param servicio
     * @return false si ya hay una cita programada del mismo servicio para esa misma fecha y hora
     */
    public Boolean hayDisponibilidad(LocalDate fecha, LocalTime hora, Servicio servicio) {
        for (Cita cita : citas) {
            if(cita.getServicio().equals(servicio) && cita.getFecha().equals(fecha) && LocalTime.parse(cita.getHora()).equals(hora)){
                return false;
            }
        }
        return true;
    }
}
