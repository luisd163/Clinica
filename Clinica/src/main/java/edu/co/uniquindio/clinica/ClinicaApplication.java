package edu.co.uniquindio.clinica;

import edu.co.uniquindio.clinica.Servicios.CitaServicio;
import edu.co.uniquindio.clinica.Servicios.PacienteServicio;
import edu.co.uniquindio.clinica.Servicios.ServicioServicios;
import edu.co.uniquindio.clinica.model.Clinica;
import edu.co.uniquindio.clinica.model.Enums.TipoSuscripcion;
import edu.co.uniquindio.clinica.model.Servicio;
import edu.co.uniquindio.clinica.model.factory.SuscripcionBasica;
import edu.co.uniquindio.clinica.model.factory.SuscripcionPremium;
import edu.co.uniquindio.clinica.utils.GeneradorCodigos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

public class ClinicaApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClinicaApplication.class.getResource("/panel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        PacienteServicio pacienteServicio = new PacienteServicio();
        ServicioServicios servicioServicios= new ServicioServicios();
        CitaServicio citaServicio= new CitaServicio();
        Clinica clinica = new Clinica();
        Servicio odontologia = Servicio.builder().
                precio(40000).
                id(GeneradorCodigos.generarCodigoDecimalStr(5)).
                nombre("Odontologia").
                build();
        Servicio pediatria = Servicio.builder()
                .precio(50000)
                .id(GeneradorCodigos.generarCodigoDecimalStr(5))
                .nombre("Pediatria")
                .build();
        Servicio oftalmologia = Servicio.builder()
                .precio(60000)
                .id(GeneradorCodigos.generarCodigoDecimalStr(5))
                .nombre("Oftalmologia")
                .build();
        Servicio consultaGeneral = Servicio.builder()
                .precio(20000)
                .id(GeneradorCodigos.generarCodigoDecimalStr(5))
                .nombre("Consulta General")
                .build();
        Servicio ortopedia = Servicio.builder()
                .precio(60000)
                .id(GeneradorCodigos.generarCodigoDecimalStr(5))
                .nombre("Ortopedia")
                .build();

        SuscripcionPremium suscripcionPremium = new SuscripcionPremium();
        SuscripcionBasica suscripcionBasica = new SuscripcionBasica();


        servicioServicios.registrarServicios(odontologia);
        servicioServicios.registrarServicios(pediatria);
        servicioServicios.registrarServicios(oftalmologia);
        servicioServicios.registrarServicios(consultaGeneral);
        servicioServicios.registrarServicios(ortopedia);
        pacienteServicio.registrarPaciente("1234567890", "Juan", "123456789", "luisdanielgomez23@gmail.com", TipoSuscripcion.PREMIUM);
        //citaServicio.registrarCita("123456789", LocalDate.now(), "10:00", consultaGeneral);
        launch();
    }
}