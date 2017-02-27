package fr.esiea.foucher.nicolas.dictionary.client;

import java.io.*;
import java.net.*;

public class Client {

    public static Socket socket = null;
    public static Thread t1;

    public static void main(String[] args) {

        try {

            System.out.println("Demande de connexion");
            socket = new Socket("127.0.0.1", 2009);

            t1 = new Thread(new Connection(socket));
            t1.start();

        } catch (UnknownHostException e) {
            System.err.println("Impossible de se connecter à l'adresse " + socket.getLocalAddress());
        } catch (IOException e) {
            System.err.println("Aucun serveur à l'écoute du port " + socket.getLocalPort());
        }


    }

}
