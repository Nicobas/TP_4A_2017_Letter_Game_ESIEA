package fr.esiea.foucher.nicolas.dictionary.server;

import fr.esiea.foucher.nicolas.dictionary.MessageContainer;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static ServerSocket ss = null;
    public static Thread t;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            ss = new ServerSocket(2009);
            System.out.println("Le serveur est à l'écoute du port " + ss.getLocalPort());

            t = new Thread(new AcceptConnection(ss));
            t.start();

            System.out.println();
            System.out.println("Appuyez sur ENTER pour commencer la partie");
            sc.nextLine();

            t.interrupt(); //... voir comment faire pour refuser les clients

        } catch (IOException e) {
            System.err.println("Le port " + ss.getLocalPort() + " est déjà utilisé !");
        }


        ClientManager.sendBroadcast(MessageContainer.getIntro(ClientManager.countClients()));
    }
}
