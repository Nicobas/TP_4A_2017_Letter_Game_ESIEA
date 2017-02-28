package fr.esiea.foucher.nicolas.dictionary.server.game;

import fr.esiea.foucher.nicolas.dictionary.server.ClientManager;

public class IAPlayer extends AbstractPlayer {
    static int currentIAPlayerId = 0;
    private String name;

    public IAPlayer() {
        super();
        this.name = "IA" + currentIAPlayerId;
        currentIAPlayerId++;
    }

    @Override
    public void playRound(BoardGame bg) {

    }

    @Override
    public Letter getLetter(BoardGame bg) {
        Letter l = super.getLetter(bg);
        ClientManager.sendBroadcast(this.getName() + " a tiré la lettre \"" + l + "\"");
        return l;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
