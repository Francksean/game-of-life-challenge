package org.example;


import java.util.Scanner;


// Classe principale avec point d'entrée du programme
// GameOfLife.java


/**
 * GameOfLife.java
 * Point d'entrée remanié pour utiliser les interfaces.
 */
public class GameOfLife {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Grid grid = new FixedGrid(); // 5x5 par défaut
        RuleSet ruleSet = new StandardConwayRuleSet();
        Renderer renderer = new ConsoleRenderer();

        System.out.println("Bienvenue dans le Jeu de la Vie!");
        System.out.println("Choisissez une option:");
        System.out.println("1. Configuration manuelle");
        System.out.println("2. Configuration automatique (aléatoire)");

        int choice = -1;
        // on lit en sécurité
        while (choice != 1 && choice != 2) {
            if (!scanner.hasNextInt()) {
                scanner.nextLine(); // consommer mauvaise entrée
                System.out.println("Entrez 1 ou 2.");
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consomme le '\n' restant avant de lire des lignes
            if (choice != 1 && choice != 2) {
                System.out.println("Choix invalide. Entrez 1 ou 2.");
            }
        }

        Initializer initializer;
        if (choice == 1) {
            initializer = new UserInputInitializer(scanner);
        } else {
            System.out.println("Quelle probabilité de vie voulez vous pour les cellules de l'intération initiale (entre 0 et 1)");
            System.out.println("Personnellement je vous recommande entre 0.4 et 0.7 pour que ce soit intéréssant");
            double aliveProb = 0.0;
            while (aliveProb <= 0.0 || aliveProb > 1) {
                if (!scanner.hasNextDouble()) {
                    scanner.nextLine();
                    System.out.println("Entrez un réel entre 0 et 1.");
                    continue;
                }
                aliveProb = scanner.nextDouble();
                scanner.nextLine();
                if (aliveProb <= 0.0) {
                    System.out.println("Entrez un réel strictement positif.");
                }
                if (aliveProb > 1.0) {
                    System.out.println("Entrez un réel inférieur à 1.");
                }
            }
            initializer = new RandomInitializer(aliveProb);
        }

        System.out.println("Combien de générations voulez-vous simuler?");
        int generations = 0;
        while (generations <= 0) {
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Entrez un entier positif.");
                continue;
            }
            generations = scanner.nextInt();
            scanner.nextLine();
            if (generations <= 0) {
                System.out.println("Entrez un entier strictement positif.");
            }
        }

        long delayMillis = 1000L;

        GameController controller = new GameController(grid, ruleSet, initializer, renderer, delayMillis);
        controller.start(generations);

        System.out.println("Simulation terminée.");

        scanner.close();
    }
}



