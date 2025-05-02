package edu.co.uniquindio.clinica.model;

import java.util.LinkedList;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Clinica {
    LinkedList<Cita> citas;
    LinkedList<Servicio> servicios;
    LinkedList<Paciente> pacientes;

    /**
     * Método constructor de la clase clinica
     */
    public Clinica(){
        this.citas = new LinkedList<>();
        this.servicios = new LinkedList<>();
        this.pacientes = new LinkedList<>();
    }

}
