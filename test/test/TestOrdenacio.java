/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author jesus
 */
import java.util.*;
import librerias.util.*;

public class TestOrdenacio {

    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);

        int quantitat = tec.nextInt();
        Integer[] vec = new Integer[quantitat];

        int i = 0;
        while (i < quantitat && tec.hasNext()) {
            vec[i] = tec.nextInt();
            i++;
        }

        long t0 = System.nanoTime();
        //Ordenacio.insercioDirecta(vec);
        //Ordenacio.seleccioDirecta(vec);
        //Ordenacio.mergeSort(vec);
        Ordenacio.quickSort(vec);
        long tf = System.nanoTime() - t0;
        System.out.println("temps = " + tf / 1000000000.0);


        /*	
	for (i = 0; i < vec.length; i++) {
	    System.out.println(vec[i]);

	}
         */
    }

}
