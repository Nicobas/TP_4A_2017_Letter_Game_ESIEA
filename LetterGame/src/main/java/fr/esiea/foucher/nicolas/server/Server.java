package fr.esiea.foucher.nicolas.server;

import fr.esiea.foucher.nicolas.server.game.Game;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.util.Enumeration;
import java.util.Scanner;

public class Server {
    public static ServerSocket ss;
    public static Thread t;
    public static int port = 2017;
    public static AcceptConnection conn;

    public static void run() {
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
            System.exit(0);
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
}
