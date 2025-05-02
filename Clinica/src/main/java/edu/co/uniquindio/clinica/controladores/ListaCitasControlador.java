package edu.co.uniquindio.clinica.controladores;
import edu.co.uniquindio.clinica.Servicios.CitaServicio;
import edu.co.uniquindio.clinica.model.Cita;
import edu.co.uniquindio.clinica.model.Clinica;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;


public class ListaCitasControlador implements Initializable {

    private final Clinica clinica = ControladorPrincipal.getInstancia().getClinica();

    private ControladorPrincipal instancia= ControladorPrincipal.getInstancia();

    private CitaServicio citaServicio= new CitaServicio();

    private ObservableList<Cita> citas;

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, String> tbc_cedulaPaciente;

    @FXML
    private TableColumn<Cita, String> tbc_fecha;

    @FXML
    private TableColumn<Cita, String> tbc_id;

    @FXML
    private TableColumn<Cita, String> tbc_nombrePaciente;

    @FXML
    private TableColumn<Cita, String> tbc_servicio;

    @FXML
    private TableColumn<Cita, String> tbc_suscripcionPaciente;

    @FXML
    private TableColumn<Cita, String> tbc_total;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Asignar las propiedades de la nota a las columnas de la tabla
        tbc_nombrePaciente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getNombre()));
        tbc_cedulaPaciente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getCedula()));
        tbc_fecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        tbc_id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        tbc_servicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServicio().getNombre()));
        tbc_total.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFactura().getTotal())));
        tbc_suscripcionPaciente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getSuscripcion().getClass().getSimpleName()));


        //Inicializar lista observable y cargar las notas
        citas = FXCollections.observableArrayList();
        cargarCitas();

    }

    /**
     * Método que carga las citas en la tabla
     */
    public void cargarCitas() {
        List<Cita> listaCitas = instancia.getClinica().getCitas();

        if (listaCitas == null) {
            listaCitas = List.of(); // Evita que sea null
        }

        citas.setAll(listaCitas);
        tablaCitas.setItems(citas);
    }

    public void actualizarCitas() {
        citas.setAll(instancia.getClinica().getCitas());
    }

    /**
     * Método que cancela una cita previamente creada
     * @param event click en cancelar
     * @throws Exception si la cita no esta registrada
     */
    @FXML
    void onCancelarCita(ActionEvent event) throws Exception {
        if (citaServicio.cancelarCita(tablaCitas.getSelectionModel().getSelectedItem())){
            mostrarAlerta("Cita cancelada", "La cita ha sido cancelada correctamente");
            actualizarCitas();
        }
    }

    /**
     * Método que muestra una alerta
     * @param titulo titulo que muestra la alerta
     * @param mensaje mensaje que muestra la alerta
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
