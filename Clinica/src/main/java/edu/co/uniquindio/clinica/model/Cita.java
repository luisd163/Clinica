package edu.co.uniquindio.clinica.model;

import java.time.LocalDate;
import java.util.LinkedList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @Builder
public class Cita {

    private Paciente paciente;
    private String id;
    private LocalDate fecha;
    private String hora;
    private Servicio servicio;
    private Factura factura;

}
