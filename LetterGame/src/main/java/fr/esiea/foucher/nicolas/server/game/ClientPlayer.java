package fr.esiea.foucher.nicolas.server.game;

import fr.esiea.foucher.nicolas.server.ClientInstance;
import fr.esiea.foucher.nicolas.server.ClientManager;

public class ClientPlayer extends AbstractPlayer {

    private ClientInstance client;

    public ClientPlayer(ClientInstance client) {
        super();
        this.client = client;
    }

    @Override
    public Letter generateRandomLetter(BoardGame bg) {
        Letter l = super.generateRandomLetter(bg);
        this.client.sendMsg("Vous avez tiré la lettre \"" + l + "\"");
        ClientManager.sendBroadcast(this.getName() + " a tiré la lettre \"" + l + "\"", this.getClient());
        return l;
    }

    @Override
    public String getName() {
        return this.getClient().getLogin();
    }

    @Override
    public String findWord(BoardGame bg) {
        this.client.sendMsg("Le pot commun contient les lettres suivantes : " + bg.getCommonPotString());

        if (bg.getFoundWords().size() > 0) {
            this.client.sendMsg("Mots déja trouvés :");
            for (String s : bg.getFoundWords())
                this.client.sendMsg(s);
        }

        String word;
        while (true) {
            this.client.sendMsg("Entrez un mot ($ pour terminer) :");
            word = this.client.receiveMsg();

            if (word.equals("$"))
                return null;

            if (!bg.isWord(word)) {
                this.client.sendMsg("Ce mot n'est pas dans le dictionnaire");
                continue;
            }

            if (bg.isAlreadyFound(word)) {
                this.client.sendMsg("Ce mot a déjà été trouvé");
                continue;
            }

            if (bg.isFoundWordsAnagram(word)) {
                this.client.sendMsg("Vous avez trouvé l'anagrame  \"" + word + "\"");
                ClientManager.sendBroadcast(this.getName() + " a trouvé l'anagrame \"" + word + "\"", this.client);

                return word;
            }

            if (!bg.isAccepted(word)) {
                this.client.sendMsg("Vous devez utiliser les lettres du pot commun");
                continue;
            }

            this.client.sendMsg("Vous avez trouvé le mot \"" + word + "\"");
            ClientManager.sendBroadcast(this.getName() + " a trouvé le mot \"" + word + "\"", this.client);

            if (bg.getUsedWords().size() > 0) {
                this.client.sendMsg("Vous avez utilisé les mot :");
                for (String s : bg.getUsedWords())
                    this.client.sendMsg(s);
            }

            return word;
        }
    }

    @Override
    public void startRound() {
        this.client.sendMsg("Vous commencez votre tour");
        ClientManager.sendBroadcast(this.getName() + " commence son tour", this.client);
    }

    @Override
    public void endRound() {
        this.client.sendMsg("Votre tour est terminé. ("+this.getFoundWords().size()+" points)");
        ClientManager.sendBroadcast(this.getName() + " a terminé son tour; . ("+this.getFoundWords().size()+" points)", this.client);
        ClientManager.sendBroadcast("");
    }

    public ClientInstance getClient() {
        return client;
    }
}
