package fr.esiea.foucher.nicolas.server.game;

import fr.esiea.foucher.nicolas.server.ClientManager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {
    static int numberOfWordsToWin = 10;

    private List<AbstractPlayer> players;
    private BoardGame boardgame;

    public Game() {
        this.players = new ArrayList<AbstractPlayer>();
        this.boardgame = new BoardGame();
    }

    public void run() {
        ClientManager.sendBroadcast(this.getIntro());

        // Pour chaque joueurs, on tire une lettre pour désigner l'ordre et on l'ajoute au pot commun
        for (AbstractPlayer player : this.players) {
            player.generateFirstLetter(this.boardgame);
        }

        // Liste des joueurs par ordre de jeu
        List<AbstractPlayer> orderedPlayers = new ArrayList<AbstractPlayer>();

        // On remplit la liste des joueus dans l'ordre
        while (this.players.size() > 0) {
            char c = '{'; //-- En ascii, '{' est le caractère jusre àprès le 'z'
            AbstractPlayer p = null;
            for (AbstractPlayer player : this.players) {
                if (player.getFirstLetter().getChar() < c) {
                    p = player;
                    c = player.getFirstLetter().getChar();
                }
            }

            orderedPlayers.add(p);
            this.players.remove(p);
        }

        // On remplace la liste des joueurs non triés par la liste des joueurs triés
        this.players = orderedPlayers;

        ClientManager.sendBroadcast("L'ordre de jeu est le suivant :");
        for (AbstractPlayer player : this.players) {
            ClientManager.sendBroadcast(player.getName());
        }
        ClientManager.sendBroadcast("");

        while (true) {
            for (AbstractPlayer player : this.players) {
                player.playRound(this.boardgame);

                if (player.hasWin()) {
                    ClientManager.sendBroadcast(player.getName() + " gagne le jeu !");
                    return;
                }
            }
        }
    }

    public void addPlayer(AbstractPlayer player) {
        this.players.add(player);
    }

    public String getIntro() {
        String s = "\r\n";
        s += " -- Début de la partie --" + "\r\n";
        s += this.players.size() + " joueurs sont connectés" + "\r\n";
        s += "Règles du jeu ..." + "\r\n";

        return s;
    }

    public void init() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Que voulez vous faire ? :");
            System.out.println("1 - Ajouter un joueur IA");
            System.out.println("2 - Commencer la partie");

            int c;
            try {
                c = sc.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Vous devez entrer un nombre");
                sc.next();
                continue;
            }

            if (c == 1) {
                while (true) {
                    try {
                        System.out.println("Entrer une difficulté entre 0.1 (faible) et 1 (fort) :");
                        float f = sc.nextFloat();
                        IAPlayer iaPlayer = new IAPlayer(f);
                        this.addPlayer(iaPlayer);
                        break;
                    } catch (InputMismatchException e) {
                        System.err.println("Vous devez saisir un nombre");
                        sc.next();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            } else if (c == 2) {
                if (this.countPlayer() > 0)
                    break;

                System.err.println("Il n'y a aucun joueur");
            } else
                System.err.println("Vous devez entrer 1 ou 2");
        }
    }

    public int countPlayer() {
        return this.players.size();
    }
}
