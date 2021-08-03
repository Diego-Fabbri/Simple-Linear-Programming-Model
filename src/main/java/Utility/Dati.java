
package Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;


/**
 *
 * @author diego
 */
public class Dati {// questa classe permette di leggere il file Esercizio_PL_Prova_autonoma.txt  e salvare il contenuto in un Vector
    
    public static Vector<Double> DatiI (File filelettura) throws IOException {
        
            BufferedReader reader = new BufferedReader(new FileReader(filelettura));// diamo inzio alla lettura
            String line = reader.readLine().trim();// per ogni riga compattiamo gli elementi
            String[] elements = line.split("\\s+");// compattiamo elementi in un vettore di stringhe eliminando gli spazi
// il trim elimina tutto ciò che è ininfluente a sinistra e destra rispettivamente 
//del primo e dell'ultimo carattere significativo
    Vector<Double> data = new Vector<Double>();// quin inseriamo il dati relativi ai costi (prime 5 poszioni) e ai termini noti ultime tre posizioni
    double c1 =Double.parseDouble(elements[0]);// nella prima riga del file il primo elemento (posizioe 0)  è il costo c1=-10, il parse fa avvenire la conversione da stringa a double
    data.add(c1);// aggiungiamo il costi c1 al vector
    double c2 =Double.parseDouble(elements[1]);// nella prima riga del file il secondo elemento (posizioe 1)  è il costo c2, il parse fa avvenire la conversione da stringa a double
    data.add(c2);// aggiungiamo il costi c2 al vector
    double c3 =Double.parseDouble(elements[2]);// nella prima riga del file il terzo elemento (posizioe 2)  è il costo c3, il parse fa avvenire la conversione da stringa a double
    data.add(c3);// aggiungiamo il costi c3 al vector
    double c4 =Double.parseDouble(elements[3]);// nella prima riga del file il quarto elemento (posizioe 3)  è il costo c4, il parse fa avvenire la conversione da stringa a double
    data.add(c4);// aggiungiamo il costi c4 al vector
    double c5 =Double.parseDouble(elements[4]);// nella prima riga del file il quinto elemento (posizioe 4)  è il costo c5, il parse fa avvenire la conversione da stringa a double
    data.add(c5);// aggiungiamo il costi c5 al vector
    
    // abbiamo letto la prima riga del file relativa ai costi c
    line = reader.readLine().trim();// andiamo alla riga successiva del file (ovvero la seconda)
    // qui leggiamo i termini noti
    String[] elements2 = line.split("\\s+");
    data.add(Double.parseDouble(elements2[0]));//aggiungiamo al vector il primo elemento (posizione 0) della seconda riga
    data.add(Double.parseDouble(elements2[1]));//aggiungiamo al vector il secondo elemento (posizione 1) della seconda riga
    data.add(Double.parseDouble(elements2[2]));//aggiungiamo al vector il terzo elemento (posizione 2) della seconda riga
    return data;
    }
public static float[][] Coefficienti (File filelettura) throws IOException {// in questa matrice leggiamo il file e salviamo i coefficienti del problema
        int m=5;// rappresenta il numero di variabili (colonne matrice)
        int n=3; // rappresenta il numero di vincoli (righe matrice)
        
        
            BufferedReader reader = new BufferedReader(new FileReader(filelettura));// diamo inzio alla lettura
            String line = reader.readLine().trim();// per ogni riga compattiamo gli elementi
            String[] elements = line.split("\\s+");// compattiamo elementi in un vettore di stringhe eliminando gli spazi
            // il trim elimina tutto ciò che è ininfluente a sinistra e destra rispettivamente 
            //del primo e dell'ultimo carattere significativo

            line = reader.readLine().trim();// siamo alla prima riga del file( che contiene i costi c) andiamo alla riga successiva (ovvero la seconda contenente i termini noti b)
           
            // dobbiamo operare sulla TERZA RIGA DEL FILE TXT che contiene da qui in poi (fino alla quinta) i coefficienti delle variabili nei vincoli
            float[][] A =new float[n][m];// creiamo la matrice dove salveremo i coefficienti
            
            
             line = reader.readLine().trim();// siamo alla seconda riga del file, e andiamo alla terza (che contiene i termini a_1j del primo vincolo)
             String[] elements3 = line.split("\\s+");// salviamo elementi della terza riga in un vettore di stringhe
                     for(int j=0;j<m;j++){
                    A[0][j]= Float.parseFloat(elements3[j]);//// effettuiamo assegnamento alla prima riga della matrice (riga 0 secondo java)
                   }
            
            line = reader.readLine().trim();// siamo alla terza riga del file, e andiamo alla quarta (che contiene i termini a_2j del secondo vincolo)
             String[] elements4 = line.split("\\s+");// salviamo elementi della quarta riga in un vettore di stringhe
                     for(int j=0;j<m;j++){
                    A[1][j]= Float.parseFloat(elements4[j]);//// effettuiamo assegnamento alla seconda riga della matrice (riga 1 secondo java)
                   }
            
             line = reader.readLine().trim();// siamo alla quarta riga del file, e andiamo alla quintaa (che contiene i termini a_3j del terzo vincolo)
             String[] elements5 = line.split("\\s+");// salviamo elementi della quarta riga in un vettore di stringhe
              for(int j=0;j<m;j++){
              A[2][j]= Float.parseFloat(elements5[j]);//// effettuiamo assegnamento alla terza riga della matrice A (riga 2 secondo java)
                   }
            // abbiamo finito la lettura dei dato 
           return A;
}
     public static void printdata(Vector<Double>d){
        System.out.println("I costi ed i termini noti sono:");
        for(int i=0;i<d.size();++i){// nelle prime 5 posizioni
               
            if(i>=0 & i<=4){
                
                int pos=i+1;
                System.out.println("c"+"_"+pos+"="+d.get(i));}
            
            
            // dalla sesta poszione in poi ci sono i termini noti
            else{
                int pos=i-4;
                System.out.println("b"+"_"+pos+"="+d.get(i));}
                                   }
}
     public static void stampaMatrice(float[][] x){
         System.out.println("I coefficienti sono:");
         for(int i=0;i<x.length;i++){
            for(int j=0;j<x[0].length;j++){
            int pos1=i+1;
            int pos2=j+1;
        System.out.print("a["+pos1+"]["+pos2+"]"+"="+x[i][j]+" ");
	System.out.println();

	}
}
     }
}