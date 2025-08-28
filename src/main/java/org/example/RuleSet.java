package org.example;

// RuleSet.java
/**
 * RuleSet.java
 * Interface décrivant la règle qui décide du nextState d'une cellule,
 * en fonction de son état courant et du nombre de voisins vivants.
 */
public interface RuleSet {
    boolean nextState(boolean currentState, int liveNeighbors);
}
