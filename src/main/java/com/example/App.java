package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Socket s = new Socket("localhost", 3000);
            System.out.println("Connessione effettuata");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            Integer numeroBiglietti = 0;

            Scanner tastiera = new Scanner(System.in);
            
            String userString = "";
            do {
                System.out.println("> Digita D per visualizzare la disponibilità, A per acquistare un biglietto o Q per uscire: ");
                userString = tastiera.nextLine(); 
                out.writeBytes(userString +"\n");

                String stringaRicevuta = in.readLine();   
                numeroBiglietti = Integer.parseInt(stringaRicevuta);   

                if (numeroBiglietti != 0 && userString.equals("D")) {
                    System.out.println("> Disponibili " + numeroBiglietti + " biglietti");
                }
                
                else if (numeroBiglietti != 0 && userString.equals("A")) {
                        System.out.println("> Biglietto acquistato");
                }
            } while (!userString.equals("Q") || numeroBiglietti != 0);            
            
            if (numeroBiglietti == 0) {
                System.out.println("> Biglietti esauriti");
            }
            else{
                System.out.println("> Esci");
            }

            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong");
            System.exit(1);
        }
    }
}
