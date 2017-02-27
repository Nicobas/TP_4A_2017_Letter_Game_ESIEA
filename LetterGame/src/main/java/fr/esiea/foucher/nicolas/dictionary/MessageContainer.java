package fr.esiea.foucher.nicolas.dictionary;

public class MessageContainer {
    public static String getIntro(int nbClients) {
        String s = new String();
        s += " -- Début de la partie --" + "\r\n";
        s += nbClients + " joueurs sont connectés" + "\r\n";
        s += "Règles du jeu ..." + "\r\n";

        return s;
    }
}
