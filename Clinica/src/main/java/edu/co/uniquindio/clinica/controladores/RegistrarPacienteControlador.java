package edu.co.uniquindio.clinica.controladores;

import edu.co.uniquindio.clinica.Servicios.PacienteServicio;
import edu.co.uniquindio.clinica.model.Enums.TipoSuscripcion;
import edu.co.uniquindio.clinica.model.factory.Suscripcion;
import edu.co.uniquindio.clinica.model.factory.SuscripcionAdultoMayor;
import edu.co.uniquindio.clinica.model.factory.SuscripcionBasica;
import edu.co.uniquindio.clinica.model.factory.SuscripcionPremium;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.util.StringConverter;
import edu.co.uniquindio.clinica.model.Clinica;


import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarPacienteControlador implements Initializable {

    private final Clinica clinica = ControladorPrincipal.getInstancia().getClinica();

    PacienteServicio pacienteServicio = new PacienteServicio();




    //Metodo e Instancia de Lista Paciente Controlador para poder actualizar la tabla de la interfaz de ListaPacientes
    private ListaPacientesControlador controladorLista;

    public void setControladorListaPacientes(ListaPacientesControlador controladorLista) {
        this.controladorLista = controladorLista;
    }

    ControladorPrincipal ins;

    @FXML
    private ComboBox<TipoSuscripcion> cb_suscripcion;

    @FXML
    private TextField tf_cedula;

    @FXML
    private TextField tf_correo;

    @FXML
    private TextField tf_nombre;

    @FXML
    private TextField tf_telefono;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cb_suscripcion.getItems().addAll(TipoSuscripcion.values());

    }

    /**
     * Método que registra un paciente en la clínica
     * @param event click en registrar
     * @throws Exception si alguno de los campos está vacío, o si el paciente ya está registrado
     */
    @FXML
    void onRegistrar(ActionEvent event) throws Exception {
        try {
            if (pacienteServicio.registrarPaciente(
                    tf_telefono.getText(),
                    tf_nombre.getText(),
                    tf_cedula.getText(),
                    tf_correo.getText(),
                    cb_suscripcion.getValue())) {
                mostrarAlerta("Registro exitoso", "El paciente ha sido registrado exitosamente");
                if (controladorLista != null) {
                    controladorLista.actualizarPacientes();
                }
            }
        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage());
        }
    }

    /**
     * Método que muestra una alerta
     * @param titulo
     * @param mensaje
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

