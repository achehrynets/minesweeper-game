package com.acheh.minesweeper.model;

/**
 * Represents the state of a cell in the game.
 * The cell can be in one of the following states:
 * <ul>
 *     <li>REVEALED: the cell is revealed</li>
 *     <li>FLAGGED: the cell is flagged</li>
 *     <li>HIDDEN: the cell is hidden</li>
 * </ul>
 */
public enum CellState {
    REVEALED(" "), FLAGGED("⚑"), HIDDEN("□");

    /**
     * The representation of the cell state.
     */
    private final String representation;

    CellState(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
