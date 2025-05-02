package edu.co.uniquindio.clinica.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @Builder
public class Factura {
    private LocalDate fecha;
    private String id;
    private double total, subtotal;
}
