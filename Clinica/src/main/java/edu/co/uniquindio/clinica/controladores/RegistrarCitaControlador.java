package edu.co.uniquindio.clinica.controladores;

import edu.co.uniquindio.clinica.Servicios.CitaServicio;
import edu.co.uniquindio.clinica.model.Clinica;
import edu.co.uniquindio.clinica.model.Servicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

public class RegistrarCitaControlador {

    private final Clinica clinica = ControladorPrincipal.getInstancia().getClinica();
    private final CitaServicio citaServicio = new CitaServicio();

    private ListaCitasControlador controladorCitas;

    public void setControladorListaCitas(ListaCitasControlador controladorCitas) {
        this.controladorCitas = controladorCitas;
    }

    @FXML
    private ComboBox<Servicio> cb_servicio;

    @FXML
    private ComboBox<String> cb_Hora;

    @FXML
    private DatePicker dp_fecha;

    @FXML
    private TextField tf_cedulaCita;

    @FXML
    public void initialize() {
        cb_servicio.getItems().addAll(clinica.getServicios());
        cb_Hora.getItems().addAll(CitaServicio.generarHorarios());

        cb_servicio.setConverter(new StringConverter<>() {
            @Override
            public String toString(Servicio servicio) {
                return servicio != null ? servicio.getNombre() : "";
            }

            @Override
            public Servicio fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    void onRegistrar(ActionEvent event) throws Exception {
        if (citaServicio.registrarCita(tf_cedulaCita.getText(), dp_fecha.getValue(), cb_Hora.getValue(), cb_servicio.getValue())) {
            mostrarAlerta("Cita registrada", "La cita ha sido registrada correctamente");

            if (controladorCitas != null) {
                controladorCitas.actualizarCitas();
            }
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

