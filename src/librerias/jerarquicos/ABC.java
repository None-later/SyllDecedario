package librerias.jerarquicos;

import java.util.NoSuchElementException;
import librerias.modelos.ArbreBinariDeCerca;

public class ABC<E extends Comparable<E>> implements ArbreBinariDeCerca<String> {

    private NodeABC<String> arrel;

    public ABC() {
        arrel = null;
    }

    @Override
    public void borrar(String e) {

    }

    @Override
    public void inserir(String e) {
        NodeABC<String> aux = arrel;
        NodeABC<String> ant = null;

        while (aux != null && e.compareTo(aux.getValor()) != 0) {
            aux.increaseTalla();
            if (e.compareTo(aux.getValor()) < 0) {
                ant = aux;
                aux = aux.esq;
            } else {
                ant = aux;
                aux = aux.dret;
            }
        }

        if (ant == null) {
            arrel = new NodeABC(e);
        } else {
            if (e.compareTo(ant.getValor()) < 0) {
                ant.esq = new NodeABC(e);
            } else if (e.compareTo(ant.getValor()) > 0) {
                ant.dret = new NodeABC(e);
            }

        }
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    public boolean cercar(String e) {
        NodeABC<String> aux = arrel;
        while (aux != null && e.compareTo(aux.getValor()) != 0) {
            if (e.compareTo(aux.getValor()) < 0) {
                aux = aux.esq;
            } else {
                aux = aux.dret;
            }
        }
        return aux != null;
    }

    public String getSubstring(String e) throws NoSuchElementException {
        System.out.println("ABC getSubstring " + e);
        NodeABC<String> aux = arrel;
        String word = aux.getValor().substring(0, aux.getValor().indexOf("="));
        String syllables = aux.getValor().substring(aux.getValor().indexOf("=") + 1);
        while (aux != null && e.compareTo(word) != 0) {
            System.out.println(syllables);
            if (e.compareTo(word) < 0) {
                aux = aux.esq;
            } else {
                aux = aux.dret;
            }
            if (aux != null) {
                word = aux.getValor().substring(0, aux.getValor().indexOf("="));
                syllables = aux.getValor().substring(aux.getValor().indexOf("=") + 1);
            }
        }
        if (word.compareTo(e) != 0) {
            throw new NoSuchElementException();
        }
        return syllables;
    }

    @Override
    public boolean esBuit() {
        return arrel == null;
    }

    @Override
    public String inOrdre() {
        return inOrdre(arrel);
    }

    private String inOrdre(NodeABC<String> node) {
        String aTornar = "";
        if (node != null) {
            aTornar += inOrdre(node.esq);
            aTornar += node.getValor() + " ";
            aTornar += inOrdre(node.dret);
        }
        return aTornar;
    }

    @Override
    public String max() {
        NodeABC<String> aux = arrel;
        NodeABC<String> auxAnt = null;
        while (aux != null) {
            auxAnt = aux;
            aux = aux.dret;
        }
        return auxAnt.getValor();
    }

    @Override
    public String min() {
        NodeABC<String> aux = arrel;
        NodeABC<String> auxAnt = null;
        while (aux != null) {
            auxAnt = aux;
            aux = aux.esq;
        }
        return auxAnt.getValor();
    }

    public int getSize() {
        return arrel.getTalla();
    }
}
