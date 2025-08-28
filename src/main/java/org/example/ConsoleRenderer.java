package org.example;

// ConsoleRenderer.java
/**
 * ConsoleRenderer.java
 * Simple rendu ASCII sur la console.
 */
public class ConsoleRenderer implements Renderer {

    @Override
    public void render(Grid grid, int generation) {
        System.out.println("Génération " + generation + " :");
        grid.printGrid();
    }
}
