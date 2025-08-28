package org.example;

// StandardConwayRuleSet.java
/**
 * StandardConwayRuleSet.java
 * Implémentation standard des règles de Conway :
 *  - Une cellule vivante avec 2 ou 3 voisins survit.
 *  - Une cellule morte avec exactement 3 voisins devient vivante.
 */
public class StandardConwayRuleSet implements RuleSet {

    @Override
    public boolean nextState(boolean currentState, int liveNeighbors) {
        if (currentState) {
            return (liveNeighbors == 2 || liveNeighbors == 3);
        } else {
            return (liveNeighbors == 3);
        }
    }
}
