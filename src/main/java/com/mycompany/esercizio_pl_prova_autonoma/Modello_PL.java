/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esercizio_pl_prova_autonoma;

import ilog.concert.*;
import ilog.cplex.*;
import java.util.Vector;

/**
 *
 * @author diego
 */
public class Modello_PL {

    protected IloCplex model;

    protected Vector<Double> d;// qui ci salviamo i costi e i termini noti
    protected IloNumVar[] x;// vettore di variabili intere

    public Modello_PL(Vector<Double> data, float[][] A) throws IloException {// il costruttore modello prende in input il Vector dei dati e la matrice dei coefficienti
        int n = A.length;// numero di vincoli
        int m = A[0].length;// numero di variabili 
        this.d = data;// assegno il vector a quello in input con i dati
        x = new IloNumVar[A[0].length];// aremp tante variabili quante le colonne della matrice dei coefficienti a_ij
        this.model = new IloCplex();// assegnamo il modello del costruttore ad un nuovo modello

        // AGGIUNTA VARIABILI
        for (int i = 0; i < m; i++) {// con questo ciclo popoliamo il vettore delle variabili
            int pos_i = i + 1;
            x[i] = model.numVar(0, Double.POSITIVE_INFINITY, IloNumVarType.Float, "x[" + pos_i + "]");// definiamo campo esistenza variabili
        }
        // AGGIUNTA VINCOLI

        // for(int i=0;i<n;i++){// per ogni vincolo
        //    IloLinearNumExpr espressionevincolo = model.linearNumExpr();// creo un oggetto espressione vincolo
        //    for(int j=0;j<m;j++){
        //    espressionevincolo.addTerm(x[j], A[i][j]); // creiamo il vincolo nella parte destra 
        //     model.addEq(espressionevincolo, data.get(i+5));// aggiungiamo il vincolo
        //          }
        //    }
        for (int i = 0; i < n; i++) {
            IloLinearNumExpr espressionevincolo = model.linearNumExpr();// creo un oggetto espressione vincolo 
            for (int j = 0; j < m; j++) {
                espressionevincolo.addTerm(x[j], A[i][j]); // creiamo il vincolo nella parte destra 
            }
            model.addEq(espressionevincolo, data.get(i + 5));// aggiungiamo il vincolo 
        }

        // CURIAMO LA FUNZIONE OBIETTIVO ADESSO   
        IloLinearNumExpr obiettivo = model.linearNumExpr();// creiamo un oggetto espressione che contenga la funzione obiettivo
        for (int j = 0; j < m; j++) {
            obiettivo.addTerm(x[j], data.get(j));// stiamo aggiungendo i termini c*x
        }
        IloObjective Obj = model.addObjective(IloObjectiveSense.Minimize, obiettivo);
    }

    public void risolviModello() throws IloException {

        model.exportModel("ModelloPL.lp");

        model.solve();// questo metodo risolve il problema
        System.out.println();
        System.out.println("Solution status = "+ model.getStatus());
        System.out.println();

        System.out.println("Il valore di funzione obiettivo e':" + model.getObjValue());

        System.out.println("Il valore delle variabili e':");

        for (int i = 0; i < x.length; i++) {
            int pos_i = i + 1;
            System.out.print("x[" + pos_i + "]=" + model.getValue(x[i]));
            System.out.println();
        }
    }

}
