package edu.co.uniquindio.clinica.controladores;

import edu.co.uniquindio.clinica.model.Clinica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class PanelControlador {

    private final Clinica clinica = ControladorPrincipal.getInstancia().getClinica();

    private final ControladorPrincipal instControladorPrincipal = ControladorPrincipal.getInstancia();

    @FXML
    private StackPane panelPrincipal;

    @FXML
    public void mostrarRegistroPaciente(ActionEvent actionEvent) {
        FXMLLoader listaLoader = new FXMLLoader(getClass().getResource("/listaPacientes.fxml"));
        Parent vistaLista = null;
        ListaPacientesControlador controladorLista = null;

        try {
            vistaLista = listaLoader.load();
            controladorLista = listaLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent vistaRegistro = cargarPanelPacientes("/registrarPaciente.fxml", controladorLista);
        panelPrincipal.getChildren().setAll(vistaRegistro);
    }

    @FXML
    public void mostrarListaPacientes(ActionEvent actionEvent) {
        Parent node = cargarPanel("/listaPacientes.fxml");
        panelPrincipal.getChildren().setAll(node);
    }

    @FXML
    public void mostrarRegistroCita(ActionEvent actionEvent) {
        FXMLLoader listaLoader = new FXMLLoader(getClass().getResource("/listaCitas.fxml"));
        Parent vistaLista = null;
        ListaCitasControlador controladorLista = null;

        try {
            vistaLista = listaLoader.load();
            controladorLista = listaLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent vistaRegistro = cargarPanelCitas("/registrarCita.fxml", controladorLista);
        panelPrincipal.getChildren().setAll(vistaRegistro);
    }

    @FXML
    public void mostrarListaCitas(ActionEvent actionEvent) {
        Parent node = cargarPanel("/listaCitas.fxml");
        panelPrincipal.getChildren().setAll(node);
    }

    private Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Parent cargarPanelPacientes(String fxmlFile, ListaPacientesControlador controladorLista) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();

            if (fxmlFile.equals("/registrarPaciente.fxml")) {
                RegistrarPacienteControlador controladorRegistro = loader.getController();
                controladorRegistro.setControladorListaPacientes(controladorLista);
            }

            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Parent cargarPanelCitas(String fxmlFile, ListaCitasControlador controladorCita) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();

            if (fxmlFile.equals("/registrarCita.fxml")) {
                RegistrarCitaControlador controladorRegistro = loader.getController();
                controladorRegistro.setControladorListaCitas(controladorCita);
            }

            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


