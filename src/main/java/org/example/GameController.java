package org.example;

// GameController.java
/**
 * GameController.java
 * Orchestration du cycle de vie de la simulation :
 * - initialise la grille via un Initializer
 * - affiche la grille via Renderer
 * - applique nextGeneration via RuleSet pour N générations
 */
public class GameController {

    private final Grid grid;
    private final RuleSet ruleSet;
    private final Initializer initializer;
    private final Renderer renderer;
    private final long delayMillis;

    public GameController(Grid grid, RuleSet ruleSet, Initializer initializer, Renderer renderer, long delayMillis) {
        this.grid = grid;
        this.ruleSet = ruleSet;
        this.initializer = initializer;
        this.renderer = renderer;
        this.delayMillis = delayMillis;
    }

    public void start(int maxGenerations) {
        initializer.initialize(grid);
        renderer.render(grid, 0);

        for (int gen = 1; gen <= maxGenerations; gen++) {
            grid.nextGeneration(ruleSet);
            renderer.render(grid, gen);
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Simulation interrompue.");
                break;
            }
        }
    }
}
