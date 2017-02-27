package fr.esiea.foucher.nicolas.dictionary.server;

import java.io.*;
import java.net.*;

public class AcceptConnection implements Runnable {

    private ServerSocket socketserver = null;
    private Socket socket = null;

    public AcceptConnection(ServerSocket ss) {
        socketserver = ss;
    }

    public void run() {

        try {
            while (true) {

                socket = socketserver.accept();
                String login = "J" + ClientManager.currentClientId;

                ClientInstance ci = new ClientInstance(socket, login);
                ClientManager.addClient(ci);

                System.out.println(login + " vien de se connecter");
                ClientManager.sendBroadcast(login + " vien de se connecter", ci);
            }
        } catch (IOException e) {

            System.err.println("Erreur serveur");
        }

    }
}
