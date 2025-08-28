package org.example;

// CellInterface.java
/**
 * Interface représentant une cellule minimale : on peut lire et écrire son état.
 */
public interface CellInterface {
    boolean isAlive();
    void setAlive(boolean alive);
}
