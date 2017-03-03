package fr.esiea.foucher.nicolas.server;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {
    private static List<ClientInstance> clients = new ArrayList<ClientInstance>();
    public static int currentClientId = 0;

    public static void addClient(ClientInstance ci){
        clients.add(ci);
        currentClientId++;
    }

    public static List<ClientInstance> getClients() {
        return clients;
    }

    public static void sendBroadcast(String msg, ClientInstance toIgnore) {
        for (ClientInstance ci : clients) {
            if (ci == toIgnore)
                continue;

            ci.sendMsg(msg);
        }

        System.out.println(msg);
    }

    public static void sendBroadcast(String msg) {
        sendBroadcast(msg, null);
    }
}
