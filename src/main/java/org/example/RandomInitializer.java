package org.example;

// GliderInitializer.java

import java.util.Random;
/**
 * GliderInitializer.java
 * Initialise un "glider" (planeur) à une position donnée (top-left).
 * Les positions dépassant la grille seront ignorées silencieusement.
 */

/**
 * RandomInitializer.java
 *
 * Initialise la grille aléatoirement selon une probabilité aliveProbability ∈ [0.0, 1.0].
 * Si un seed est fourni, le générateur est reproductible (utile pour tests).
 *
 * La grille est réinitialisée (toutes les cellules mises à false) avant l'initialisation.
 */
public class RandomInitializer implements Initializer {

    private final double aliveProbability;
    private final Random random;

    /**
     * Constructeur principal.
     *
     * @param aliveProbability probabilité qu'une cellule soit vivante (0.0 .. 1.0)
     * @throws IllegalArgumentException si aliveProbability hors intervalle
     */
    public RandomInitializer(double aliveProbability) {
        this(aliveProbability, new Random());
    }

    /**
     * Constructeur avec seed pour reproductibilité.
     *
     * @param aliveProbability probabilité qu'une cellule soit vivante (0.0 .. 1.0)
     * @param seed seed pour Random
     */
    public RandomInitializer(double aliveProbability, long seed) {
        this(aliveProbability, new Random(seed));
    }

    // Constructeur privé utilisé par les deux publics
    private RandomInitializer(double aliveProbability, Random random) {
        if (aliveProbability < 0.0 || aliveProbability > 1.0) {
            throw new IllegalArgumentException("aliveProbability doit être dans [0.0, 1.0]");
        }
        this.aliveProbability = aliveProbability;
        this.random = random;
    }

    @Override
    public void initialize(Grid grid) {
        // Réinitialiser la grille d'abord (mise à false)
        if (grid instanceof FixedGrid) {
            ((FixedGrid) grid).reset();
        } else {
            int size = grid.getSize();
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    grid.setCell(r, c, false);
                }
            }
        }

        // Remplir aléatoirement en fonction de aliveProbability
        int size = grid.getSize();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                boolean alive = random.nextDouble() < aliveProbability;
                grid.setCell(r, c, alive);
            }
        }
    }

    /**
     * Retourne la probabilité utilisée (utile pour debug/tests).
     */
    public double getAliveProbability() {
        return aliveProbability;
    }
}

