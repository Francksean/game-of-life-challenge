package org.example;

// Renderer.java
/**
 * Renderer.java
 * Abstraction d'un renderer (ici ConsoleRenderer fourni).
 */
public interface Renderer {
    void render(Grid grid, int generation);
}
