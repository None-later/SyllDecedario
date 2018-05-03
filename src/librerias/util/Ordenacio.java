/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librerias.util;

/**
 *
 * @author jesus
 */
@SuppressWarnings("unchecked")
public class Ordenacio {

    public static <T extends Comparable<T>> void insercioDirecta(T v[]) {
        for (int i = 0; i < v.length; i++) {
            T elem = v[i];
            int pIns = i;
            for (; pIns > 0 && elem.compareTo(v[pIns - 1]) < 0; pIns--) {
                v[pIns] = v[pIns - 1];
            }
            v[pIns] = elem;
        }
    }

    public static <T extends Comparable<T>> void seleccioDirecta(T v[]) {
        for (int j = 0; j < v.length - 1; j++) {
            int posmin = j;
            T valmin = v[j];
            for (int i = j + 1; i < v.length; i++) {
                if (v[i].compareTo(valmin) < 0) {
                    valmin = v[i];
                    posmin = i;
                }
            }
            v[posmin] = v[j];
            v[j] = valmin;
        }
    }

    private static <T extends Comparable<T>> void merge(T vec[], int ini, int meitat, int fi) {

        T[] aux = (T[]) new Comparable[fi - ini + 1];
        int i = ini, j = meitat + 1, k = 0;

        while (i <= meitat && j <= fi) {
            if (vec[i].compareTo(vec[j]) < 0) {
                aux[k++] = vec[i++];
            } else {
                aux[k++] = vec[j++];
            }
        }

        while (i <= meitat) {
            aux[k++] = vec[i++];
        }

        while (j <= fi) {
            aux[k++] = vec[j++];
        }

        for (k = 0; k < aux.length; k++) {
            vec[ini + k] = aux[k];
        }
    }

    public static <T extends Comparable<T>> void mergeSort(T vec[], int ini, int fi) {

        if (ini < fi) {
            int meitat = (fi + ini) / 2; //dividir

            mergeSort(vec, ini, meitat); //vencer
            mergeSort(vec, meitat + 1, fi); //vencer

            merge(vec, ini, meitat, fi); // combinar
        }
    }

    public static <T extends Comparable<T>> void mergeSort(T vec[]) {
        mergeSort(vec, 0, vec.length - 1);
    }

    private static <T extends Comparable<T>> int particio(T[] vec, int ini, int fi) {
        int i = ini - 1;
        int j = fi + 1;
        int pos = (int) (Math.random() * (fi - ini + 1) + ini); //triar pos aleat entre ini i fi
        //intercanviar el contingut amb ini
        T aux1 = vec[ini];
        vec[ini] = vec[pos];
        vec[pos] = aux1;

        T piv = vec[ini];

        do {
            do {
                i++;
            } while (vec[i].compareTo(piv) < 0);

            do {
                j--;
            } while (vec[j].compareTo(piv) > 0);

            //intercanviar
            if (i < j) {
                T aux = vec[i];
                vec[i] = vec[j];
                vec[j] = aux;
            }
        } while (i < j);

        return j;
    }

    public static <T extends Comparable<T>> void quickSort(T vec[], int ini, int fi) {
        if (ini < fi) {
            int pos = particio(vec, ini, fi);
            quickSort(vec, ini, pos);
            quickSort(vec, pos + 1, fi);
        }
    }

    public static <T extends Comparable<T>> void quickSort(T vec[]) {
        quickSort(vec, 0, vec.length - 1);
    }

    public static <T extends Comparable<T>> T selection(T vec[], int kessim, int ini, int fi) {

        if (ini == fi) //un element, cas base
        {
            return vec[ini];
        } else {
            int pos = particio(vec, ini, fi);
            if (kessim <= pos) { // esta a l'esquerra
                return selection(vec, kessim, ini, pos);
            } else {
                return selection(vec, kessim, pos + 1, fi);
            }
        }
    }

    public static <T extends Comparable<T>> T selection(T vec[], int kessim) {
        return selection(vec, kessim, 0, vec.length - 1);
    }
}
