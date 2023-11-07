package com.example;

import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       try{
            // metto il server in ascolto sulla porta 3000 per poter acquisire e creare la socket
           ServerSocket server = new ServerSocket(3000);
           System.out.println("il server è in ascolto");
           int biglietti = 5;//si puo anche mettere un numero di bilgietti random
           ArrayList<Cliente> a = new ArrayList<Cliente>();


           while(true){

        
           Cliente c = new Cliente(biglietti,server.accept(),a);
           a.add(c);

           //faccio partire il run() del Client
            c.start();


           
           }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }
}
