package edu.co.uniquindio.clinica.Repositorios;

import edu.co.uniquindio.clinica.model.Enums.TipoSuscripcion;
import edu.co.uniquindio.clinica.model.Paciente;
import edu.co.uniquindio.clinica.model.factory.Suscripcion;

public interface IPacienteRepositorio {

    public boolean registrarPaciente(String telefono, String nombre, String cedula, String email, TipoSuscripcion tipoSuscripcion) throws Exception;

    public boolean eliminarPaciente(Paciente paciente) throws Exception;

    public Paciente buscarPaciente(String cedula);

}
