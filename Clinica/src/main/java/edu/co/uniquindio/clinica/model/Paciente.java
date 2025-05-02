package edu.co.uniquindio.clinica.model;

import edu.co.uniquindio.clinica.model.factory.Suscripcion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @Builder
public class Paciente {
    private String telefono,nombre,cedula,email;
    private Suscripcion suscripcion;

}
