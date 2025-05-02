module edu.co.uniquindio.clinica {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.simplejavamail;
    requires org.simplejavamail.core;

    opens edu.co.uniquindio.clinica to javafx.fxml;
    exports edu.co.uniquindio.clinica;
    exports edu.co.uniquindio.clinica.controladores;
    opens edu.co.uniquindio.clinica.controladores to javafx.fxml;

}