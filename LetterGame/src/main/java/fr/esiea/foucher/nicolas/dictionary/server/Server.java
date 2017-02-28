package fr.esiea.foucher.nicolas.dictionary.server;

import fr.esiea.foucher.nicolas.dictionary.server.game.AbstractPlayer;
import fr.esiea.foucher.nicolas.dictionary.server.game.Game;

import java.io.IOException;
import java.net.ServerSocket;
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
            System.out.println("Le serveur est à l'écoute du port " + ss.getLocalPort());

            t = new Thread(new AcceptConnection(ss, game));
            t.start();

            System.out.println();
            System.out.println("Appuyez sur ENTER pour commencer la partie");
            //... Gerer aussi l'ajout des IA
            //... bloquer si 0 user
            sc.nextLine();
            System.out.println();

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
