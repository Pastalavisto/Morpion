# Morpion

# Structure de l'application

.
└── main/
    ├── java/
    │   ├── app/
    │   │   ├── Application.java
    │   ├── controllers/
    │   │   ├── ChoixSymboleController.java
    │   │   ├── FinDePartieController.java
    │   │   ├── MorpionController.java
    │   │   ├── ParametresController.java
    │   │   └── ReglesController.java
    │   ├── models/
    │   │   ├── Bot.java (classe abstraite)
    │   │   ├── BotNiveau1.java
    │   │   ├── BotNiveau2.java
    │   │   ├── Case.java
    │   │   ├── ChoixSymbole.java
    │   │   ├── Coord.java
    │   │   ├── FinDePartie.java
    │   │   ├── ImageSymbole.java
    │   │   ├── Joueur.java
    │   │   ├── Morpion.java
    │   │   ├── NomSymbole.java
    │   │   ├── ParametresPartie.java
    │   │   ├── SceneController.java
    │   │   ├── Symbole.java (interface)
    │   │   └── TexteSymbole.java
    │   └── module-info.java
    └── resources/
        ├── images/
        │   └── (toutes les images/gif ajoutés via l'application)
        └── view/
            ├── ChoixSymbole.fxml
            ├── FinDePartie.fxml
            ├── Morpion.fxml
            ├── Parametres.fxml
            └── Regles.fxml


# Fonctionnalités

Fonctionnalités obligatoires : 
- [OBL] Un titre sur chaque fenêtre
- [OBL] Plusieurs fenêtres (modales et non modales)
- [OBL] Une barre de menu permettant a minima de réinitialiser le jeu, de voir les règles du jeu et de quitter le jeu
- [OBL] Des éléments permettant de nommer les deux joueurs
- [OBL] Une interface permettant de choisir qui joue en premier : « Joueur 1 », « Joueur 2 », ou choix aléatoire
- [OBL] Dans la partie principale de la fenêtre principale : la zone de jeu avec 9 cases (3x3). Le type d’élément utilisé pour les cases est libre (boutons, canvas, ou autre : à vous de voir)
- [OBL] A chaque clic dans une case, celle-ci doit afficher le symbole du joueur dont c’est le tour : un O ou un X. Le changement de joueur doit s’effectuer automatiquement après chaque clic
- [OBL] A chaque tour de jeu, le joueur dont c’est le tour doit le savoir explicitement par une indication à l’écran (sous la forme de votre choix).
- [OBL] La fin de jeu doit se faire automatiquement : lorsqu’un joueur a aligné trois symboles, le jeu doit s’arrêter et indiquer (sous la forme de votre choix) le nom du joueur vainqueur. En cas de match nul (c’est-à-dire si toutes les cases ont été remplies sans avoir trois symboles identiques alignés), l’application doit le mentionner.
- [OBL] A la fin du jeu, une interface doit permettre de rejouer ou de quitter l’application. En choisissant « Rejouer », la grille de jeu doit se réinitialiser. Autre option à prendre en compte : garder les mêmes noms de joueurs ou changer.
- [OBL] A la fin de chaque partie, établir un tableau de scores montrant le nombre de victoires de chacun des joueurs.
Fonctionnalités optionnelles : 
- [OPT] A la fin de la partie, montrer l’alignement gagnant par l’effet de votre choix (surbrillance des cases, ligne qui « barre » l’alignement, … : à vous de voir)
- [OPT] A la place des symboles textuels X et O, utiliser des images
- [OPT] Permettre aux joueurs de personnaliser leur symbole (textuel avec éditeur de police et de couleur, image, autre...)
- [OPT] Implémenter un tableau de scores permanent accessible depuis le menu (indice : regardez du côté de la classe Java Properties)
- [OPT] Implémenter un mode « Un joueur », permettant de jouer seul face à l’ordinateur :
 - Niveau 1 : l’ordinateur joue aléatoirement dans une case libre
 - Niveau 2 : l’ordinateur choisit le meilleur coup pour tenter de gagner
- [OPT] Implémenter la possibilité de choisir la taille de la grille, pour jouer à des variantes à 16 cases, 25 cases, ou plus.
- [OPT] Générer un fichier JAR de votre application et l’ajouter à votre archive. Ce format permet de publier des applications Java (par exemple à des utilisateurs finaux).

Fonctionnalités optionnelles ajoutées : 
-Pouvoir jouer à plus de 2 joueurs
-Pouvoir lancer plusieurs parties 
