# Jeu de la vie version console (5×5) par Franck DJISSOU HAPPI - Développeur fullstack.

## Sommaire

* [Présentation](#présentation)
* [Installation et exécution](#installation-et-exécution)

    * [Compilation (avec package `org.example` dans `src/main/java`)](#compilation-avec-package-orgexample)
* [Utilisation](#utilisation)

    * [Options d'initialisation](#options-dinitialisation)
    * [Paramètres modifiables](#paramètres-modifiables)
* [Choix tecniques et architecturaux (résumé)](#choix-architecturaux-résumé)
* [Structure des fichiers / classes principales](#structure-des-fichiers--classes-principales)

---

## Présentation

Auteur : **Franck Sean**

Ce projet est une implémentation console du **Jeu de la Vie** (Conway) adaptée à une grille fixe 5×5. L'objectif est d'avoir une version claire, modulaire et testable, permettant :

* initialisation manuelle ou automatique (planeur, aléatoire) ;
* simulation pas à pas avec affichage ASCII dans la console ;
* architecture séparant clairement stockage, règles, initialisation et rendu.

---

## Installation et exécution

### Compilation (avec package `org.example`)

```bash
mvn compile
```
puis

```bash
mvn install
```
Cela générera le fichier `.jar` dans le repertoire `target`.


---

## Utilisation

### Options d'initialisation

Le programme est intéractif : il demande une option d'initialisation (manuelle ou aléatoire) puis la probabilité si vous choisissez l'initialisation aléatoire (si vous avez ajouté cette option) puis le nombre de générations à simuler.
* **Manuelle** : entrez `size` lignes (ici 5) contenant `X` pour cellule vivante ou `.` pour cellule morte. Exemple pour 5×5 :

  ```
  ..X..
  ...X.
  .XXX.
  .....
  .....
  ```
* **Aléatoire** : si vous utilisez `RandomInitializer`, vous pouvez fournir une probabilité (réel entre 0 et 1) pour qu'une cellule soit vivante lors de l'initialisation.

Le programme affiche ensuite chaque génération et marque les cellules vivantes par `X` et les cellules mortes par `.`.

### Paramètres modifiables

* **Taille de la grille** : `FixedGrid` a un constructeur qui accepte la taille. Par défaut `new FixedGrid()` produit 5×5. Pour un autre n, utilisez `new FixedGrid(n)`.
* **Délai entre générations** : modifiable dans le contrôleur (`GameController`) via le paramètre `delayMillis`.
* **Règles du jeu** : encapsulées dans l'interface `RuleSet`. L'implémentation par défaut est `StandardConwayRuleSet`. Pour d'autres règles, créez une nouvelle classe implémentant `RuleSet`.

---

## Choix techniques et architecturaux (résumé)

Ici l'outil de buil urtilisé est **Maven** pour sa simplicité d'utilisation (compilation)

L'architecture vise la **séparation des responsabilités** et la **facilité d'extension** :

* **Interfaces centrales**

    * `CellInterface` : abstraction d'une cellule (lecture/écriture). Permet d'échanger différentes représentations internes si nécessaire.
    * `Grid` : interface pour la grille (accès aux cellules, comptage des voisins, génération suivante, affichage). Permet d'avoir plusieurs implémentations (taille fixe, grille torique, grille éparse).
    * `RuleSet` : encapsule la logique de transition d'une cellule. Permet de remplacer les règles (Conway, HighLife, etc.) sans modifier la grille.
    * `Initializer` : stratégie d'initialisation (manuel, motif, aléatoire). Facilite l'ajout de nouvelles sources d'état initial.
    * `Renderer` : abstraction pour l'affichage (console, GUI, export), ici `ConsoleRenderer` est fournie.
    * `GameController` : orchestre le cycle de vie de la simulation (initialisation, itérations, rendu, délai).
---

## Structure des fichiers / classes principales

* `CellInterface`, `Cell` : représentation d'une cellule.
* `Grid` (interface) : contrat pour la grille.
* `FixedGrid` : implémentation 5×5 (réinitialisation, comptage voisins, génération suivante).
* `RuleSet`, `StandardConwayRuleSet` : règles du jeu.
* `Initializer`, `UserInputInitializer`, `RandomInitializer` : stratégies d'initialisation.
* `Renderer`, `ConsoleRenderer` : affichage.
* `GameController` : boucle de simulation.
* `GameOfLife` (`Main`) : point d'entrée, lecture interactive et orchestration.
