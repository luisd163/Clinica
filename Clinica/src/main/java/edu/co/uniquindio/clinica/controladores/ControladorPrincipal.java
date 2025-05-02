package edu.co.uniquindio.clinica.controladores;

import edu.co.uniquindio.clinica.model.Clinica;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class ControladorPrincipal {

    private static ControladorPrincipal instancia;
    private final Clinica clinica = new Clinica();

    // Constructor privado
    private ControladorPrincipal() {}

    public static ControladorPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }

    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void crearAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public Clinica getClinica() {
        return clinica;
    }
}


