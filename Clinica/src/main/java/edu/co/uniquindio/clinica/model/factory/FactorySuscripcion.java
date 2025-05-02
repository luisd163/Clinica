package edu.co.uniquindio.clinica.model.factory;

import edu.co.uniquindio.clinica.model.Enums.TipoSuscripcion;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FactorySuscripcion {

    /**
     * Método que permite crear una suscripcion
     * @param tipoSuscripcion
     * @return suscripcion según el enum elegido
     */
    public static Suscripcion crearSuscripcion(TipoSuscripcion tipoSuscripcion) throws Exception {
        switch (tipoSuscripcion){
            case BASICA: return new SuscripcionBasica();
            case PREMIUM: return new SuscripcionPremium();
            case ADULTOMAYOR: return new SuscripcionAdultoMayor();
            default: throw new Exception("Suscripción no valida");
        }
    }



}
