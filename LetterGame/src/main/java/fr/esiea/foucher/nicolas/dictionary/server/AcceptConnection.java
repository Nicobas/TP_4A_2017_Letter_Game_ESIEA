package fr.esiea.foucher.nicolas.dictionary.server;

import fr.esiea.foucher.nicolas.dictionary.server.game.ClientPlayer;
import fr.esiea.foucher.nicolas.dictionary.server.game.Game;

import java.io.*;
import java.net.*;

public class AcceptConnection implements Runnable {

    private ServerSocket socketserver;
    private Socket socket;
    private Game game;

    public AcceptConnection(ServerSocket ss, Game game) {
        this.socketserver = ss;
        this.game = game;
    }

    public void run() {

        try {
            while (true) {

                this.socket = this.socketserver.accept();
                String login = "J" + ClientManager.currentClientId;

                ClientInstance ci = new ClientInstance(socket, login);
                ClientManager.addClient(ci);
                this.game.addPlayer(new ClientPlayer(ci));

                System.out.println(login + " vien de se connecter");
                ClientManager.sendBroadcast(login + " vien de se connecter", ci);
            }
        } catch (IOException e) {

            System.err.println("Erreur serveur");
        }

    }
}
