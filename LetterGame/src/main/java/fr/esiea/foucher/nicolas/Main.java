package fr.esiea.foucher.nicolas;

import fr.esiea.foucher.nicolas.client.Client;
import fr.esiea.foucher.nicolas.server.Server;
import fr.esiea.foucher.nicolas.server.game.Game;

import java.util.Scanner;

/**
 * Created by nicobas on 03/03/17.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Que souhaitez vous lancer ?");
        System.out.println("1 - Serveur");
        System.out.println("2 - Client");

        int res = sc.nextInt();

        if (res == 1)
            Server.run();
        else
            Client.run();
    }
}
