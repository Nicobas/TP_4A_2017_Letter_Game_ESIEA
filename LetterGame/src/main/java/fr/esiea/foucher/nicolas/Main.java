package fr.esiea.foucher.nicolas;

import fr.esiea.foucher.nicolas.client.Client;
import fr.esiea.foucher.nicolas.server.Server;
import fr.esiea.foucher.nicolas.server.game.Game;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by nicobas on 03/03/17.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int res;
        while (true) {
            System.out.println("Que souhaitez vous lancer ?");
            System.out.println("1 - Serveur");
            System.out.println("2 - Client");

            try {
                res = sc.nextInt();

                if (res != 1 && res != 2)
                    System.err.println("Vous devez entrer 1 ou 2");
                else
                    break;
            } catch (InputMismatchException e) {
                System.err.println("Vous devez entrer un nombre");
                sc.next();
            }
        }

        if (res == 1)
            Server.run();
        else
            Client.run();
    }
}
