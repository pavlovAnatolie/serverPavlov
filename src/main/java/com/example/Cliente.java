package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class Cliente extends Thread{
    private int biglietti;
    private Socket s;
    private ArrayList<Cliente> numThr;

    public Cliente(int b, Socket s, ArrayList<Cliente> a){
        biglietti = b;
        this.s = s;
        numThr = a;
    }

    public void run(){
        try {
            //apertura del stream input e output
           BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));//input
           DataOutputStream out = new DataOutputStream(s.getOutputStream());//output

            System.out.println("il dispositivo è stato collegato");

            boolean fine= false;

            do {

                

                //prendo il numero inserito dal utente sullo stream e lo converto
                String scelta = in.readLine();

                switch (scelta) {
                    
                        case "A":
                        case "a":
                        if(biglietti >0){
                            out.writeBytes("K"+"\n");
                            biglietti --;
                        }else{
                            out.writeBytes("N"+"\n");
                        }

                       /*  boolean fin = false;
                        if (biglietti == 0) {
                            fin = true;
                        }
                            for(int i = 0; i < numThr.size(); i++){
                                advert(numThr.get(i),fin);
                            }*/
                            
                            break;
                        case "D":
                        case "d":
                            out.writeBytes(biglietti+"\n");
                            break;

                        case "Q":
                        case "q":
                            fine = true;
                            break;
                    
                        default:
                        System.out.println("comand not accepted");
                            break;
                    
                }
                



            } while (!fine);
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    

    //invio messaggio broadcast
    public void advert(Cliente c, boolean fin){


        try {
            DataOutputStream out = new DataOutputStream(c.s.getOutputStream());//output


            if (fin) {
               out.writeBytes("fin" + "\n"); 
            }else{
                out.writeBytes("continue" + "\n");
            }

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
    }



}
