package edu.co.uniquindio.clinica.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @Builder
public class Servicio {
    private double precio;
    private String nombre,id;
}
