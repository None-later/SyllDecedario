/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contalletres;

/**
 *
 * @author jesus
 */
public class FilterStrings {

    private final String type;    //word or sentence
    private String cadena;
    private final String language;

    private static final String ESPACIOS = "[\\p{Cntrl}&&[^\r\n\t]]";       //UE --> {english, castillian, valencian}
                                                                            //ES --> {castillian, valencian}
    private static final String MAY_UE = "\\x41-\\x5a";
    private static final String MAY_VAL = "\\xc7";
    private static final String MAY_CAS = "\\xd1";

    private static final String MIN_UE = "\\x61-\\x7a";
    private static final String MIN_VAL = "\\xe7";
    private static final String MIN_CAS = "\\xf1";

    private static final String MAY_ACENTO_ES = "\\xc1,\\xc9,\\xcd,\\xd3,,\\xda,\\xdc";
    private static final String MAY_ACENTO_VAL = "\\xc0,\\xc8,\\xcc,\\xd2,\\xd9";

    private static final String MIN_ACENTO_ES = "\\xe9,\\xed,\\xf3,\\xfa,\\xfc";
    private static final String MIN_ACENTO_VAL = "\\xe8,\\xec,\\xf2,\\xf9";

    private static final String PUNTUACION_ES = "\\xa8,\\xaa,\\xb4";    //dieresis,ª,acento agudo
    private static final String PUNTUACION_CAS = "\\xba"; //º
    private static final String PUNTUACION_VAL = "\\x60,\\xb7,\\u010f,\\u013d-\\u0140,\\u149,\\u165";
    private static final String PUNTUACION_EN = "\\u010f,\\u013d,\\u013e,\\u149";

    private static final String PUNTUACION_FRASES_UE = "\\x21,\\x22,\\x27-\\x29,\\x2c-\\x2e,\\x3a,\\x3b,\\x3f,\\x5f";
    private static final String PUNTUACION_FRASES_CAS = "\\xa1,\\xbf";  //¡,¿

    public FilterStrings(String type, String cadena, String language) {
        this.type = type;
        this.cadena = cadena;
        this.language = language;
    }

    public String filterLanguage() {
        switch (language) {
            case "spanish":
                return filterSpanish();
            case "english":
                return filterEnglish();
            case "valencian":
                return filterValencian();
            default:
                return cadena;  //no filter
        }
    }

    private String filterSpanish() {
        if (type.equals("word")) {
            cadena = cadena.replaceAll(ESPACIOS, "");
            cadena = cadena.replaceAll("[^" + MAY_UE + "," + MAY_CAS + ","
                    + MIN_UE + "," + MIN_CAS + ","
                    + MAY_ACENTO_ES + "," + MIN_ACENTO_ES + ","
                    + PUNTUACION_ES + "," + PUNTUACION_CAS + "]", "");
        } else if (type.equals("sentence")) {
            cadena = cadena.replaceAll("[^" + MAY_UE + "," + MAY_CAS + ","
                    + MIN_UE + "," + MIN_CAS + ","
                    + MAY_ACENTO_ES + "," + MIN_ACENTO_ES + ","
                    + PUNTUACION_ES + "," + PUNTUACION_CAS + ","
                    + PUNTUACION_FRASES_UE + "," + PUNTUACION_FRASES_CAS + "]", "");
        }
        return cadena;
    }

    private String filterValencian() {
        if (type.equals("word")) {
            cadena = cadena.replaceAll(ESPACIOS, "");
            cadena = cadena.replaceAll("[^" + MAY_UE + "," + MAY_VAL + ","
                    + MIN_UE + "," + MIN_VAL + ","
                    + MAY_ACENTO_ES + "," + MAY_ACENTO_VAL + ","
                    + MIN_ACENTO_ES + "," + MIN_ACENTO_VAL + ","
                    + PUNTUACION_ES + "," + PUNTUACION_VAL + "]", "");
        } else if (type.equals("sentence")) {
            cadena = cadena.replaceAll("[^" + MAY_UE + "," + MAY_VAL + ","
                    + MIN_UE + "," + MIN_VAL + ","
                    + MAY_ACENTO_ES + "," + MAY_ACENTO_VAL + ","
                    + MIN_ACENTO_ES + "," + MIN_ACENTO_VAL + ","
                    + PUNTUACION_ES + "," + PUNTUACION_VAL + ","
                    + PUNTUACION_FRASES_UE + "]", "");
        }
        return cadena;
    }

    private String filterEnglish() {
        if (type.equals("word")) {  //si está mal se elimina la letra (mejor informar del error)
            cadena = cadena.replaceAll(ESPACIOS, "");
            cadena = cadena.replaceAll("[^" + MAY_UE + "," + MIN_UE + "]", "");
        } else if (type.equals("sentence")) {
            cadena = cadena.replaceAll("[^" + MAY_UE + "," + MIN_UE + "," + PUNTUACION_EN + "," + PUNTUACION_FRASES_UE + "]", "");
        }
        return cadena;
    }
}
