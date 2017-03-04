package fr.esiea.foucher.nicolas.server.game;

import fr.esiea.foucher.nicolas.server.ClientManager;

import java.util.ArrayList;

public class IAPlayer extends AbstractPlayer {
    static int currentIAPlayerId = 0;
    private String name;
    private float difficultyCoefficient;

    public IAPlayer(float difficultyCoefficient) throws Exception {
        super();
        this.name = "IA" + currentIAPlayerId;
        currentIAPlayerId++;

        if (difficultyCoefficient < 0.1 || difficultyCoefficient > 1)
            throw new Exception("La difficulté doit être comrise entre 0.1 et 1");

        this.difficultyCoefficient = difficultyCoefficient;
    }

    public String checkIsWordAvailable(BoardGame bg) {
        for (String word : bg.getDictionary()) {
            boolean ok = true;

            ArrayList<Letter> tmpPot = new ArrayList<Letter>();
            for (Letter l : bg.getCommonPot())
                tmpPot.add(l);

            for (char c : word.toCharArray()) {
                if (c == '-')
                    continue;

                boolean ok2 = false;
                Letter toRemove = null;
                for (Letter l : tmpPot) {
                    if (l.getChar() == c) {
                        ok2 = true;
                        toRemove = l;
                        break;
                    }
                }

                if (!ok2) {
                    ok = false;
                    break;
                }

                tmpPot.remove(toRemove);
            }

            if (ok) {
                if (bg.isAlreadyFound(word)) {
                    continue;
                }

                return word;
            }
        }

        return null;
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
        if (Math.random() < this.difficultyCoefficient) {
            String foundWord = this.checkIsWordAvailable(bg);
            ClientManager.sendBroadcast(this.getName() + " a trouvé le mot \"" + foundWord + "\"");
            return foundWord;
        }
        return null;
    }

    @Override
    public void startRound() {
        ClientManager.sendBroadcast(this.getName() + " commence son tour");
    }

    @Override
    public void endRound() {
        ClientManager.sendBroadcast(this.getName() + " a terminé son tour. ("+this.getFoundWords().size()+" points)");
    }
}
