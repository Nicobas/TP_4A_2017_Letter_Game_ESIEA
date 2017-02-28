package fr.esiea.foucher.nicolas.dictionary.server;

import fr.esiea.foucher.nicolas.dictionary.server.game.AbstractPlayer;
import fr.esiea.foucher.nicolas.dictionary.server.game.Game;
import fr.esiea.foucher.nicolas.dictionary.server.game.IAPlayer;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Server {
    public static ServerSocket ss = null;
    public static Thread t;
    public static int port = 2017;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Game game = new Game(); //... lancer le jeu dans un autre Thread ?

        try {
            ss = new ServerSocket(port);
            Enumeration<NetworkInterface> enu = NetworkInterface.getNetworkInterfaces();

            System.out.println("Adresses IPs du serveur :");
            while (enu.hasMoreElements()){
                Enumeration<InetAddress> i = enu.nextElement().getInetAddresses();
                while (i.hasMoreElements()){
                    InetAddress a = i.nextElement();
                    if ((a.isLoopbackAddress()||a.isSiteLocalAddress()) && !(a instanceof Inet6Address)) {
                        System.out.println(a.getHostAddress());
                    }
                }
            }

            System.out.println("Le serveur est à l'écoute du port " + ss.getLocalPort());

            t = new Thread(new AcceptConnection(ss, game));
            t.start();

            System.out.println();

            while (true) {
                System.out.println("Que voulez vous faire ? :");
                System.out.println("1 - Ajouter un joueur IA");
                System.out.println("2 - Commencer la partie");

                int c;
                try {
                    c = sc.nextInt();
                } catch (Exception e) {
                    System.err.println("Vous devez entrer un nombre");
                    sc.next();
                    continue;
                }

                if (c == 1) {
                    while(true) {
                        try {
                            System.out.println("Entrer une difficulté entre 0 (faible) et 1 (fort) :");
                            float f = sc.nextFloat();
                            IAPlayer iaPlayer = new IAPlayer(f);
                            game.addPlayer(iaPlayer);
                            break;
                        } catch (Exception e) {
                            System.err.println("Vous devez saisir un nombre entre 0 et 1");
                            sc.next();
                        }
                    }
                } else if (c == 2) {
                    if (game.countPlayer() > 0)
                        break;

                    System.err.println("Il n'y a aucun joueur");
                } else
                    System.err.println("Vous devez entrer 1 ou 2");
            }

            t.interrupt(); //... voir comment faire pour refuser les clients

        } catch (IOException e) {
            System.err.println("Le port " + port + " est déjà utilisé !");
            return;
        }

        AbstractPlayer winner = game.run();

        ClientManager.sendBroadcast(winner.getName() + " gagne le jeu !");
        sc.nextLine();
    }

    //... TODO
    // gerer les mots avec -
    // gerer l'IA
    // tester les failles
    // empecher la commection apres le début du jeu
}
