package fr.esiea.foucher.nicolas.dictionary.client;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Connection implements Runnable {

    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String message = null;
    private Scanner sc = null;

    public Connection(Socket s) {
        socket = s;
    }

    public void run() {

        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sc = new Scanner(System.in);

            System.out.println("Je suis connecté ");

            while (true) {
                message = in.readLine();

                if (message.equals("%#!GETMSG%#!")) {
                    message = sc.nextLine();
                    out.println(message);
                    out.flush();
                } else
                    System.out.println(message);
            }
        } catch (IOException e) {

            System.err.println("Le serveur ne répond plus ");
        }
    }

}
