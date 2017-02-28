package fr.esiea.foucher.nicolas.dictionary.server.game;

import fr.esiea.foucher.nicolas.dictionary.server.ClientInstance;
import fr.esiea.foucher.nicolas.dictionary.server.ClientManager;

public class ClientPlayer extends AbstractPlayer {

    private ClientInstance client;

    public ClientPlayer(ClientInstance client) {
        super();
        this.client = client;
    }

    @Override
    public void playRound(BoardGame bg) {

    }

    @Override
    public Letter getLetter(BoardGame bg) {
        Letter l = super.getLetter(bg);
        this.client.sendMsg("Vous avez tiré la lettre \"" + l + "\"");
        ClientManager.sendBroadcast(this.getName() + " a tiré la lettre \"" + l + "\"", this.getClient());
        return l;
    }

    @Override
    public String getName() {
        return this.getClient().getLogin();
    }

    public ClientInstance getClient() {
        return client;
    }
}
