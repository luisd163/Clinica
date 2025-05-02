package edu.co.uniquindio.clinica.utils;

import java.util.Random;

public class GeneradorCodigos {

    private static final String HEXADECIMAL = "0123456789ABCDEF";
    private static final String DECIMAL = "0123456789";
    private static final Random random = new Random();

    public static void validarLongitudPositiva(int longitud) {
        if (longitud <= 0) {
            throw new IllegalArgumentException("La longitud debe ser mayor que 0");
        }
    }

    public static long convertirBuilderToLong(StringBuilder codigo, int base){
        try {
            return Long.parseLong(codigo.toString(), base);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error al convertir el código generado a número", e);
        }
    }

    public static long generarCodigoHexadecimal(int longitud) {
        validarLongitudPositiva(longitud);
        StringBuilder codigo = generarCodigo(longitud, HEXADECIMAL);
        return convertirBuilderToLong(codigo, 16);
    }

    public static long generarCodigoDecimal(int longitud) {
        validarLongitudPositiva(longitud);
        StringBuilder codigo = generarCodigo(longitud, DECIMAL);
        return convertirBuilderToLong(codigo, 10);
    }

    public static String generarCodigoHexadecimalStr(int longitud) {
        validarLongitudPositiva(longitud);
        return generarCodigo(longitud, HEXADECIMAL).toString();
    }

    public static String generarCodigoDecimalStr(int longitud) {
        validarLongitudPositiva(longitud);
        return generarCodigo(longitud, DECIMAL).toString();
    }

    private static StringBuilder generarCodigo(int longitud, String caracteres) {
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(indice));
        }
        return codigo;
    }
}
