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
$ cd target
$ java -jar LetterGame-0.0.1-SNAPSHOT.jar
```

## Règles du Jeu
Les règles du Jeu et qui sont aussi notre cahier des charges sont consultables [ici](https://github.com/MLabusquiere/TP_4A_2017_Letter_Game/blob/master/Readme.md).

## Screens
### Choix entre client ou serveur
![alt text](https://github.com/Nicobas/TP_4A_2017_Letter_Game_ESIEA/blob/master/LetterGame/src/main/resources/choix%20client-serveur.PNG "Client - serveur")
### Démarrage du serveur
![alt text](https://github.com/Nicobas/TP_4A_2017_Letter_Game_ESIEA/blob/master/LetterGame/src/main/resources/serveur.PNG "Serveur")
### Connection du client
![alt text](https://github.com/Nicobas/TP_4A_2017_Letter_Game_ESIEA/blob/master/LetterGame/src/main/resources/connection%20client.PNG "Connection client")

## Structure du code
Nous avons deux packages principaux et deux sous-packages dans le package server regroupant l'ensemble de nos classes :
### Package client
- Client -> Connection client au serveur
- Connection -> Classe s'occupant des E/S du client avec le serveur

### Package server
- AcceptConnection -> S'occupe de recevoir les connections des clients
- ClientInstance -> Gère les connections avec le client une fois établies
- ClientManager -> Gère la multiplicité des clients (pour jouer en multijoueur)
- Server -> Création du serveur et mise à l'écoute d'un port pour chercher des clients

#### Package game (dans server)
- AbstractPlayer -> Classe abstraite de client avec gestion du tour d'un client
- BoardGame -> Chargement du dictionnaire en mémoire, gestion du pot commun de lettres et de l'acceptation des mots
- ClientPlayer -> Autre classe pour gérer le tour d'un client
- Game -> Déroulement de la partie entre chaque tour de joueur
- IAPlayer -> IA pour le mode solo
- Letter -> Gestion et génération random de lettres pour le pot commun
	
##### Package dictionary (dans game)
- IDictionary -> Interface dictionnaire

Le dictionnaire se situe dans le dossier "ressources" du projet (src/main/ressources) et l'ensemble des tests se situent dans le dossier "tests" (src/test/java).

## Principe de développement
Nous avons respecté les principes SOLID vues en cours en limitant les dépendances entres les différentes classes (*Dependency Inversion*) et le principe de ségrégation des interfaces (*Interface Segregation*).
Ainsi, quelqu'un qui voudrait envisager une ré-architecture de notre programme pourrait le faire et très simplement.

## Consignes
- [x] Réaliser un programme en langage Java
- [x] Implémenter toutes les fonctionnalités attendues par l'enseignant
- [x] Réaliser des tests unitaires (sous JUnit)
- [x] Utilisation de Maven pour compiler et lancer le programme

## Fonctionnalités
- [x] Mode Multi-joueurs avec gestion client-serveur
- [x] Mode IA | Solo
- [x] Tests unitaires JUnit
- [x] Possibilité de voler un mot à l'adversaire ou à soi-même (en complétant avec une/plusieurs lettres du pot commun) ou en anagramme
