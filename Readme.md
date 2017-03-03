# TP Architecture Logicielle Java / INF4043 - 2017 - Jeux de Lettres
*À rendre pour le 03/03/2017 23h*

## Composition de l'équipe
- **Théo FOUCHER** & **Bastien NICOLAS**
- Étudiant 4A | ESIEA | CFA

## Préambule
**LetterGame** est un projet Java, développé sous InteliJ IDEA *2016.3.4*, dans le cadre du cours d'architecture logiciel dispensé par **M. Labusquiere** (*TP*) et **M. Ledoyen** (*Cours théoriques*).


## But du Jeu
Tour à tour, vous devez faire le plus de mots possibles avant votre adversaire. Le premier qui arrive à dix remporte la partie. Mais attention, vous pouvez facilement vous faire voler des mots si votre rival arrive à ajouter des lettres du pot commun dans l'un de vos mots !

## Comment lancer le programme Java ?
- Il faut au préalable avoir une version Java récente installée sur votre ordinateur ainsi que **Maven** pour compiler le projet. **(JRE 8 de préférence)**
- Ensuite, il vous suffit de saisir la commande suivante dans votre terminal pour lancer le Jeu après avoir importé le projet sur votre ordinateur.

```
$ cd LetterGame
$ mvn package
$ java -jar LetterGame-0.0.1-SNAPSHOT.jar
```

## Règles du Jeu
Les règles du Jeu et qui sont notre cahier des charges sont consultables [ici](https://github.com/MLabusquiere/TP_4A_2017_Letter_Game/blob/master/Readme.md).

## Structure du code
Nous avons deux packages principaux et deux sous-packages dans le package server regroupant l'ensemble de nos classes :
### package client
- Client
- Connection

### package server
- AcceptConnection
- ClientInstance
- ClientManager
- Server

	#### package game (dans server)
-AbstractPlayer
-BoardGame
-ClientPlayer
-Game
-IAPlayer
-Letter

	##### package dictionary (dans game)
-IDictionary

Le dictionnaire se situe dans le dossier "ressources" du projet (src/main/ressources) et l'ensemble des tests se situent dans le dossier "tests" (src/test/java).

