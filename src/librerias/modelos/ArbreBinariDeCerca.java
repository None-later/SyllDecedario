package librerias.modelos;

public interface ArbreBinariDeCerca<String extends Comparable<String>> {

    void inserir(String e);

    void borrar(String e);

    boolean cercar(String e);

    boolean esBuit();

//    String preOrdre();

    String inOrdre();

//    String postOrdre();
//
    String max();

    String min();
//
//    String successor(String e);
    //String predecessor(String e);
}
