package org.example;

// Grid.java
/**
 * Grid.java
 * Interface représentant la grille et les opérations de base nécessaires pour le Jeu de la Vie.
 */
public interface Grid {
    int getSize();
    CellInterface getCell(int row, int col);
    void setCell(int row, int col, boolean alive);
    int countLiveNeighbors(int row, int col);
    void nextGeneration(RuleSet ruleSet);
    void printGrid();
}
