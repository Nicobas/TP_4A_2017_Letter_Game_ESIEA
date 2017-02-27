package fr.esiea.foucher.nicolas.dictionary.server.game;

import fr.esiea.foucher.nicolas.dictionary.server.ClientInstance;

public class ClientPlayer implements IPlayer {
    private ClientInstance client;

    public ClientPlayer(ClientInstance client) {
        this.client = client;
    }

    @Override
    public void playRound() {

    }
}
