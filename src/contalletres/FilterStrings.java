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

    public FilterStrings(String type, String cadena) {
        this.type = type;
        this.cadena = cadena;
    }

    public String filterSpanish() {
        if(type.equals("word")){
            cadena = cadena.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
            cadena = cadena.replaceAll("[^\\x21-\\x7a,\\xc0-\\xc1,\\xc7-\\xc9,\\xcc,\\xcd,\\xd2,\\xd3,"
                    + "\\xd9,\\xda,\\xdc,\\xe0,\\xe1,\\xe8,\\xe9,\\xed,\\xf1,\\xf3,\\xfa,\\xfc,"
                    + "\\u013e,\\u0140]", "");
        }else if(type.equals("sentence")){
            cadena = cadena.replaceAll("[^\\x21-\\x7a,\\xc0-\\xc1,\\xc7-\\xc9,\\xcc,\\xcd,\\xd2,\\xd3,"
                    + "\\xd9,\\xda,\\xdc,\\xe0,\\xe1,\\xe8,\\xe9,\\xed,\\xf1,\\xf3,\\xfa,\\xfc,"
                    + "\\u013e,\\u0140]", "");
        }
        return cadena;
    }

    public String filterValencian() {
        return cadena;
    }

    public String filterEnglish() {
        return cadena;
    }
}
