package org.example;

// UserInputInitializer.java
import java.util.Scanner;

/**
 * UserInputInitializer.java
 * Initialise la grille en lisant 5 lignes (ou size lignes) de l'utilisateur :
 * 'X' ou 'x' pour vivant, '.' pour mort.
 */
public class UserInputInitializer implements Initializer {

    private final Scanner scanner;

    public UserInputInitializer(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void initialize(Grid grid) {
        int size = grid.getSize();
        System.out.println("Configurez l'état initial (" + size + "x" + size + "):");
        System.out.println("Entrez " + size + " lignes de " + size + " caractères (X pour vivant, . pour mort)");
        int row = 0;
        // Consommer éventuelle ligne vide laissée par un nextInt précédent est géré à l'appelant (Main).
        while (row < size) {
            String line = scanner.nextLine();
            if (line == null) line = "";
            if (line.length() < size) {
                System.out.println("Ligne trop courte, utilisez " + size + " caractères. Réessayez la ligne " + (row + 1));
                continue;
            }
            for (int col = 0; col < size; col++) {
                char c = line.charAt(col);
                grid.setCell(row, col, (c == 'X' || c == 'x'));
            }
            row++;
        }
    }
}
