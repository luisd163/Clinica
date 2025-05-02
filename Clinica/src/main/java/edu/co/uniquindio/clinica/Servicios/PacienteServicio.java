package edu.co.uniquindio.clinica.Servicios;

import edu.co.uniquindio.clinica.Repositorios.IPacienteRepositorio;
import edu.co.uniquindio.clinica.model.Clinica;
import edu.co.uniquindio.clinica.model.Enums.TipoSuscripcion;
import edu.co.uniquindio.clinica.model.Paciente;
import edu.co.uniquindio.clinica.model.factory.FactorySuscripcion;
import edu.co.uniquindio.clinica.model.factory.Suscripcion;

import java.util.LinkedList;

public class PacienteServicio implements IPacienteRepositorio {
    Clinica clinica= new Clinica();
    LinkedList<Paciente> pacientes = clinica.getPacientes();

    /**
     * Método para registrar un paciente a la lista de la clinica
     * @param telefono telefono del paciente
     * @param nombre nombre del paciente
     * @param cedula cedula del paciente
     * @param email email del paciente
     * @param tipoSuscripcion suscripcion que tiene el paciente
     * @throws Exception si alguno de los campos esta vacio, o si el paciente ya esta registrado
     */
    public boolean registrarPaciente(String telefono, String nombre, String cedula, String email, TipoSuscripcion tipoSuscripcion) throws Exception{

        if( telefono == null || telefono.isEmpty() ){
            throw new Exception ("El telefono no puede estar vacio");
        }

        if( nombre == null || nombre.isEmpty() ){
            throw new Exception ("El nombre no puede estar vacio");
        }

        if( cedula == null || cedula.isEmpty() ){
            throw new Exception ("La cedula no puede estar vacio");
        }

        if( email == null || email.isEmpty() ){
            throw new Exception ("El email no puede estar vacio");
        }

        Suscripcion suscripcion = FactorySuscripcion.crearSuscripcion(tipoSuscripcion);

        Paciente paciente = new Paciente(telefono, nombre, cedula, email, suscripcion);
        pacientes.add(paciente);
        return true;
    }

    /**
     * Método que elimina un paciente de la lista de la clinica
     * @param paciente paciente a eliminar
     * @throws Exception si el paciente es nulo o si no esta registrado
     */
    public boolean eliminarPaciente(Paciente paciente) throws Exception{
        if ( paciente == null ){
            throw new Exception ("El paciente no puede estar vacio");
        }
        if( !pacientes.contains(paciente) ){
            throw new Exception ("El paciente no esta registrado");
        }
        pacientes.remove(paciente);
        return true;
    }

    /**
     * Método que busca un paciente en la lista de la clinica usando un numero de cedula
     * @param cedula cedula del paciente a buscar
     * @return paciente encontrado
     */
    public Paciente buscarPaciente(String cedula) {

        for (Paciente paciente : pacientes) {
            if (paciente != null) {
                if (paciente.getCedula().equals(cedula)) {
                    return paciente;
                }
            }
        }
        return null;
    }
}
