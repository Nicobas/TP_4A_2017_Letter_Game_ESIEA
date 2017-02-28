package fr.esiea.foucher.nicolas.dictionary.server;

import fr.esiea.foucher.nicolas.dictionary.MessageContainer;
import fr.esiea.foucher.nicolas.dictionary.server.game.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    public static ServerSocket ss = null;
    public static Thread t;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Game game = new Game(); //... lancer le jeu dans un autre Thread ?

        try {
            ss = new ServerSocket(2017);
            System.out.println("Le serveur est à l'écoute du port " + ss.getLocalPort());

            t = new Thread(new AcceptConnection(ss, game));
            t.start();

            System.out.println();
            System.out.println("Appuyez sur ENTER pour commencer la partie");
            //... Gerer aussi l'ajout des IA
            sc.nextLine();

            t.interrupt(); //... voir comment faire pour refuser les clients

        } catch (IOException e) {
            System.err.println("Le port " + ss.getLocalPort() + " est déjà utilisé !");
        }

        game.run();
    }
}
