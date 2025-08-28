package org.example;

import java.util.Scanner;

// Classe gérant la grille et la logique du jeu
// FixedGrid.java
/**
 * FixedGrid.java
 * Implémentation 5x5 de la grille (mais taille paramétrable via constructeur).
 * Utilise Cell (implémentation de CellInterface) en interne.
 */
public class FixedGrid implements Grid {

    private final int size;
    private final CellInterface[][] cells;

    public FixedGrid() {
        this(5); // taille par défaut 5x5 comme demandé
    }

    public FixedGrid(int size) {
        if (size <= 0) throw new IllegalArgumentException("size must be positive");
        this.size = size;
        this.cells = new CellInterface[size][size];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(false);
            }
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public CellInterface getCell(int row, int col) {
        validateIndices(row, col);
        return cells[row][col];
    }

    @Override
    public void setCell(int row, int col, boolean alive) {
        validateIndices(row, col);
        cells[row][col].setAlive(alive);
    }

    private void validateIndices(int r, int c) {
        if (r < 0 || r >= size || c < 0 || c >= size) {
            throw new IndexOutOfBoundsException("Indices hors bornes: " + r + "," + c);
        }
    }

    @Override
    public int countLiveNeighbors(int x, int y) {
        int count = 0;
        for (int i = Math.max(0, x - 1); i <= Math.min(size - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(size - 1, y + 1); j++) {
                if (!(i == x && j == y) && cells[i][j].isAlive()) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public void nextGeneration(RuleSet ruleSet) {
        // On calcule dans une nouvelle matrice pour ne pas perturber le calcul des voisins.
        CellInterface[][] newCells = new CellInterface[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int liveNeighbors = countLiveNeighbors(i, j);
                boolean current = cells[i][j].isAlive();
                boolean nextState = ruleSet.nextState(current, liveNeighbors);
                newCells[i][j] = new Cell(nextState);
            }
        }
        // Remplace la référence interne par la nouvelle génération
        for (int i = 0; i < size; i++) {
            System.arraycopy(newCells[i], 0, this.cells[i], 0, size);
        }
    }

    @Override
    public void printGrid() {
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < size; j++) {
                sb.append(cells[i][j].isAlive() ? "X " : ". ");
            }
            System.out.println(sb.toString().trim());
        }
        System.out.println();
    }

    // Méthode utilitaire pour réinitialiser toute la grille (toutes mortes)
    public void reset() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                cells[i][j].setAlive(false);
    }
}
