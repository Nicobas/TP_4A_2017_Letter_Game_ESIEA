package fr.esiea.foucher.nicolas.dictionary.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientInstance {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String login;

    public ClientInstance(Socket s, String log) {
        socket = s;
        login = log;

        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {

            System.err.println("Le serveur ne répond plus ");
        }
    }

    public void sendMsg(String msg) {
        out.println(msg);
        out.flush();
    }

    public String receiveMsg() {
        this.sendMsg("%#!CLIENTREQUEST%#!");

        try {
            return in.readLine();

        } catch (IOException e) {
            System.err.println("Impossible de communiquer avec le client " + login);
            return null;
        }
    }

    public String getLogin() {
        return this.login;
    }
}
