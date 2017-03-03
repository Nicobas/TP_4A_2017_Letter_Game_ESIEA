package fr.esiea.foucher.nicolas.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static Socket socket;
    public static Thread t;
    public static String adress;
    public static int port;

    public static void run() {

        adress = "127.0.0.1";
        port = 2017;

        Scanner sc = new Scanner((System.in));
        System.out.println("Entrez l'adresse du serveur [" + adress + "] :");
        String a = sc.nextLine();
        if (a != null && !a.trim().equals(""))
            adress = a;

        try {

            System.out.println("Demande de connexion");
            socket = new Socket(adress, port);

            t = new Thread(new Connection(socket));
            t.start();

        } catch (UnknownHostException e) {
            System.err.println("Impossible de se connecter à l'adresse " + adress);
        } catch (IOException e) {
            System.err.println("Aucun serveur à l'écoute du port " + port);
        }
    }
}
