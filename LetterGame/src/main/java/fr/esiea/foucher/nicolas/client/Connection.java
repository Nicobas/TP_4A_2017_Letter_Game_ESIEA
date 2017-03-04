package fr.esiea.foucher.nicolas.client;

import fr.esiea.foucher.nicolas.server.game.dictionary.StringOperation;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Connection implements Runnable {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String message;
    private Scanner sc;

    public Connection(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sc = new Scanner(System.in);

            while (true) {
                message = in.readLine();

                if (message.equals("%#!CLIENTREQUEST%#!")) {
                    message = StringOperation.sansAccents(sc.nextLine()).toLowerCase();
                    out.println(message);
                    out.flush();
                } else
                    System.out.println(message);
            }
        } catch (Exception e) {
            System.err.println("Le serveur ne répond pas");
        }
    }

}
