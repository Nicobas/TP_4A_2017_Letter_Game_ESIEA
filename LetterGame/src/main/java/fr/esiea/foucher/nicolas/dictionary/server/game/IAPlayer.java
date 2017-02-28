package fr.esiea.foucher.nicolas.dictionary.server.game;

import fr.esiea.foucher.nicolas.dictionary.server.ClientManager;

public class IAPlayer extends AbstractPlayer {
    static int currentIAPlayerId = 0;
    private String name;
    private float difficultyCoefficient;

    public IAPlayer(float difficultyCoefficient) throws Exception {
        super();
        this.name = "IA" + currentIAPlayerId;
        currentIAPlayerId++;

        if (difficultyCoefficient < 0 || difficultyCoefficient > 1)
            throw new NumberFormatException("Le coefficient de difficulté doit être comris entre 0 et 1");

        this.difficultyCoefficient = difficultyCoefficient;
    }

    @Override
    public Letter generateRandomLetter(BoardGame bg) {
        Letter l = super.generateRandomLetter(bg);
        ClientManager.sendBroadcast(this.getName() + " a tiré la lettre \"" + l + "\"");
        return l;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String findWord(BoardGame bg) {
        return null;
    }

    @Override
    public void startRound() {
        ClientManager.sendBroadcast(this.getName() + " commence son tour");
    }

    @Override
    public void endRound() {
        ClientManager.sendBroadcast(this.getName() + " a terminé son tour");
    }
}
