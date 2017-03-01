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
    public static AcceptConnection conn;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Game game = new Game();

        startServeur(game);

        game.init();

        t.stop();

        game.run();

        sc.nextLine();
    }

    public static void startServeur(Game game) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Le port " + port + " est déjà utilisé !");
            return;
        }

        try {
            Enumeration<NetworkInterface> enu = NetworkInterface.getNetworkInterfaces();

            System.out.println("Adresses IPs du serveur :");
            while (enu.hasMoreElements()) {
                Enumeration<InetAddress> i = enu.nextElement().getInetAddresses();
                while (i.hasMoreElements()) {
                    InetAddress a = i.nextElement();
                    if ((a.isLoopbackAddress() || a.isSiteLocalAddress()) && !(a instanceof Inet6Address)) {
                        System.out.println(a.getHostAddress());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Impossible de récupérer les IPs du serveur");
        }

        System.out.println("Le serveur est à l'écoute du port " + ss.getLocalPort());

        conn = new AcceptConnection(ss, game);

        t = new Thread(conn);
        t.start();

        System.out.println();
    }

    //... TODO
    // gerer les mots avec -
    // gerer l'IA
    // tester les failles
    // empecher la commection apres le début du jeu
}
