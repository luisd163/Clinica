package edu.co.uniquindio.clinica.controladores;

import edu.co.uniquindio.clinica.Servicios.PacienteServicio;
import edu.co.uniquindio.clinica.model.Paciente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import edu.co.uniquindio.clinica.model.Clinica;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ListaPacientesControlador implements Initializable {
    private final Clinica clinica = ControladorPrincipal.getInstancia().getClinica();
    private ObservableList<Paciente> pacientes;
    ControladorPrincipal insControladorPrincipal= ControladorPrincipal.getInstancia();
    PacienteServicio pacienteServicio = new PacienteServicio();

    @FXML
    private TableView<Paciente> tablaPacientes;

    @FXML
    private TableColumn<Paciente, String> tbc_correo;

    @FXML
    private TableColumn<Paciente, String> tbc_identificacion;

    @FXML
    private TableColumn<Paciente, String> tbc_nombre;

    @FXML
    private TableColumn<Paciente, String> tbc_suscripcion;

    @FXML
    private TableColumn<Paciente, String> tbc_telefono;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Asignar las propiedades de la nota a las columnas de la tabla
        tbc_correo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        tbc_identificacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        tbc_nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbc_telefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        tbc_suscripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSuscripcion().getClass().getSimpleName()));


        //Inicializar lista observable y cargar las notas
        pacientes = FXCollections.observableArrayList();
        cargarPacientes();

    }

    /**
     * Método que carga los pacientes en la tabla
     */
    public void cargarPacientes() {
        List<Paciente> listaPacientes = clinica.getPacientes();

        if (listaPacientes == null) {
            listaPacientes = List.of(); // Evita que sea null
        }

        pacientes.setAll(listaPacientes);
        tablaPacientes.setItems(pacientes);
    }


    /**
     * Método que elimina un paciente de la lista de la clinica
     * @param event click en eliminar
     * @throws Exception si el paciente es nulo o si no esta registrado
     */
    @FXML
    void onEliminarPaciente(ActionEvent event) throws Exception {
        if (pacienteServicio.eliminarPaciente(tablaPacientes.getSelectionModel().getSelectedItem())){
            mostrarAlerta("Paciente eliminado", "El paciente ha sido eliminado correctamente");
            actualizarPacientes();
        }
    }

    public void actualizarPacientes() {
        pacientes.setAll(insControladorPrincipal.getClinica().getPacientes());
    }

    /**
     * Método que muestra una alerta
     * @param titulo
     * @param mensaje
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}

