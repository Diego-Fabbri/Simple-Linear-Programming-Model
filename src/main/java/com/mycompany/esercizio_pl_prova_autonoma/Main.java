//  Si consideri il seguente problema di programmazione lineare:
//  min −10x1 −x2
//  x1 + x2 + x3 = 10
//  x1 + x4 = 6
// 1/2x1 + x2 + x5 = 8
// x ≥ 0 
//  Scrivere il problema duale e facendo uso delle condizioni di ortogonalit`a, dimostrare che la soluzione ottima è x1*=6 e x2*=4 x5*=1
package com.mycompany.esercizio_pl_prova_autonoma;

import Utility.Dati;
import ilog.concert.IloException;
import java.io.File;
import java.io.FileNotFoundException;// permette di gestire le eccezioni se non si trova il file che si passa in lettura
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

/**
 *
 * @author diego
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, IloException {
        System.setOut(new PrintStream(args[0]));// questo comando permette di stampare in output un file .log con il risultato
        //Il file deve essere scritto in arguments
        //clicca sul nome progetto, tasto destro, Proprierties, RUN, Arguments, in prima posizione (posizione 1)c'è il nome del file .log

        Vector<Double> DATA = Dati.DatiI(new File(args[1]));// questo Vector contiene i costi(nelle prime 5 posizioni) e i termini noti (nelle successive 3 posizioni)
        Dati.printdata(DATA);// stampo i dati
        float[][] A = Dati.Coefficienti(new File(args[1]));// in questa matrice mettiamo i termini noti a_ij dei vincoli
        Dati.stampaMatrice(A);// stampiamo

        Modello_PL cplex = new Modello_PL(DATA, A);// creiamo in modello e diamo in input vector dei dati e matrice dei coefficienti
        cplex.risolviModello();// imponiamo di risolvere il problema

    }
}
