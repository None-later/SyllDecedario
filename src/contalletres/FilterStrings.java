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

    private final GameType gameType;    //word or sentence
    private String cadena;
    private final Languages language;

    private static final String ESPACIOS = "[\\p{Cntrl}&&[^\r\n\t]]";       //UE --> {inglés, castellano, valenciano}
    //ES --> {castellano, valenciano}
    private static final String MAY_UE = "\\x41-\\x5a"; //A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z
    private static final String MAY_VAL = "\\xc7";      //Ç
    private static final String MAY_CAS = "\\xd1";      //Ñ

    private static final String MIN_UE = "\\x61-\\x7a"; //a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z
    private static final String MIN_VAL = "\\xe7";      //ç
    private static final String MIN_CAS = "\\xf1";      //ñ

    private static final String MAY_ACENTO_ES = "\\xc9\\xcd\\xd3\\xda\\xdc";   //É,Í,Ó,Ú,Ü
    private static final String MAY_ACENTO_CAS = "\\xc1";                           //Á
    private static final String MAY_ACENTO_VAL = "\\xc0\\xc8\\xd2";               //À,È,Ò

    private static final String MIN_ACENTO_ES = "\\xe9\\xed\\xf3\\xfa\\xfc";    //é,í,ó,ú,ü
    private static final String MIN_ACENTO_CAS = "\\xe1";                           //á
    private static final String MIN_ACENTO_VAL = "\\xe0\\xe8\\xf2";               //à,è,ò

    private static final String PUNTUACION_ES = "\\xa8\\xaa\\xb4";                //dieresis,ª,acento agudo
    private static final String PUNTUACION_CAS = "\\xba";                           //º
    private static final String PUNTUACION_VAL = "\\x60\\xb7\\u010f\\u0149\\u0165\\u013d-\\u0140\\x2d\\x27";   //`,·,d','n,t',,L',l',L·,l·,-,'
    private static final String PUNTUACION_EN = "\\u010f\\u013d\\u013e\\u0149";   //d',L',l','n

    private static final String PUNTUACION_FRASES_UE = "\\x21\\x22\\x27-\\x29\\x2c-\\x2e\\x3a\\x3b\\x3f\\x5b\\x5e\\x5f";   //!"'(),-.:;?[]_
    private static final String PUNTUACION_FRASES_CAS = "\\xa1\\xbf";              //¡,¿

    public FilterStrings(GameType gameType, String cadena, Languages language) {
        this.gameType = gameType;
        this.cadena = cadena;
        this.language = language;
    }

    public String filterLanguage() {
        switch (language) {
            case SPANISH:
                return filterSpanish();
            case ENGLISH:
                return filterEnglish();
            case VALENCIAN:
                return filterValencian();
            default:
                return cadena;  //no filter
        }
    }

    private String filterSpanish() {
        if (gameType.equals(GameType.WORDS)) {
            cadena = cadena.replaceAll(ESPACIOS, "");
            cadena = cadena.replaceAll("[^"
                    + MAY_UE + "," + MAY_CAS + ","
                    + MIN_UE + "," + MIN_CAS + ","
                    + MAY_ACENTO_ES + "," + MAY_ACENTO_CAS + ","
                    + MIN_ACENTO_ES + "," + MIN_ACENTO_CAS + ","
                    + PUNTUACION_ES + "," + PUNTUACION_CAS + "]", "");
        } else if (gameType.equals(GameType.SENTENCES)) {
            cadena = cadena.replaceAll("[^"
                    + MAY_UE + "," + MAY_CAS + ","
                    + MIN_UE + "," + MIN_CAS + ","
                    + MAY_ACENTO_ES + "," + MAY_ACENTO_CAS + ","
                    + MIN_ACENTO_ES + "," + MIN_ACENTO_CAS + ","
                    + PUNTUACION_ES + "," + PUNTUACION_CAS + ","
                    + PUNTUACION_FRASES_CAS + "]", "");
        }
        return cadena;
    }

    private String filterValencian() {
        if (gameType.equals(GameType.WORDS)) {
            cadena = cadena.replaceAll(ESPACIOS, "");
            //cadena = cadena.replaceAll("[\\xbf\\xa1\\x30-\\x39\\p{Punct}&&[^-']]", "**");
            cadena = cadena.replaceAll("[^"
                    + MAY_UE + MAY_VAL
                    + MIN_UE + MIN_VAL
                    + MAY_ACENTO_ES + MAY_ACENTO_VAL
                    + MIN_ACENTO_ES + MIN_ACENTO_VAL
                    + PUNTUACION_ES + PUNTUACION_VAL + "]", "");
            if (cadena.startsWith("-")) {         //...-La veu de Joan sonava un tant llunyana. ...   //AÇÒ PODRIA CLAVAR-HO EN FILTERSTRING
                cadena = cadena.replaceFirst("-", "");
            }
            if (cadena.endsWith("-")) {    //...-el to de despreci es feu patent-...
                cadena = cadena.substring(0, cadena.length() - 1);
            }
            if (cadena.length() > 1 && cadena.startsWith("'")) {    //...'llingüístic'...
                cadena = cadena.substring(1, cadena.length());        //no s'accepta ' soles. ejm: '70 en fs s'esborra el 70 i queda ' asoles
            }                                                   //per açò seria bo clavar-ho tot a filterString fs
            if (cadena.length() > 1 && cadena.endsWith("'")) {
                cadena = cadena.substring(0, cadena.length() - 1);
            }
        } else if (gameType.equals(GameType.SENTENCES)) {
            cadena = cadena.replaceAll("[^"
                    + MAY_UE + "," + MAY_VAL + ","
                    + MIN_UE + "," + MIN_VAL + ","
                    + MAY_ACENTO_ES + "," + MAY_ACENTO_VAL + ","
                    + MIN_ACENTO_ES + "," + MIN_ACENTO_VAL + ","
                    + PUNTUACION_ES + "," + PUNTUACION_VAL + ","
                    + PUNTUACION_FRASES_UE + "]", "");
        }
        return cadena;
    }

    private String filterEnglish() {
        if (gameType.equals(GameType.WORDS)) {  //si está mal se elimina la letra (mejor informar del error)
            cadena = cadena.replaceAll(ESPACIOS, "");
            cadena = cadena.replaceAll("[^"
                    + MAY_UE + "," + MIN_UE + "]", "");
        } else if (gameType.equals(GameType.SENTENCES)) {
            cadena = cadena.replaceAll("[^"
                    + MAY_UE + "," + MIN_UE + ","
                    + PUNTUACION_EN + ","
                    + PUNTUACION_FRASES_UE + "]", "");
        }
        return cadena;
    }
}
