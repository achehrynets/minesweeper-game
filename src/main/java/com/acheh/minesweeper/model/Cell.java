package com.acheh.minesweeper.model;

/**
 * This class represents a cell in the game board.
 * It contains the state of the cell and the number of bombs nearby.
 * It also contains the row and column of the cell.
 */
public class Cell {

    /**
     * The row of the cell.
     */
    private final int row;

    /**
     * The column of the cell.
     */
    private final int column;

    /**
     * Whether the cell contains a bomb.
     */
    private boolean bomb;

    /**
     * The number of bombs nearby.
     */
    private int numberOfBombsNearby;

    /**
     * The state of the cell.
     */
    private CellState state;

    /**
     * Creates a new cell.
     *
     * @param row    the row of the cell
     * @param column the column of the cell
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.state = CellState.HIDDEN;
    }

    /**
     * This method returns the row of the cell.
     * @return the row of the cell
     */
    public int getRow() {
        return row;
    }

    /**
     * This method returns the column of the cell.
     * @return the column of the cell
     */
    public int getColumn() {
        return column;
    }

    /**
     * This method returns the state of the cell.
     * @return the state of the cell
     */
    public boolean isBomb() {
        return bomb;
    }

    /**
     * This method sets the bomb of the cell.
     * @param bomb the bomb of the cell
     */
    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    /**
     * This method returns whether the cell is flagged.
     * @return whether the cell is flagged
     */
    public boolean isFlagged() {
        return state == CellState.FLAGGED;
    }

    /**
     * This method returns whether the cell is revealed.
     * @return whether the cell is revealed
     */
    public boolean isRevealed() {
        return state == CellState.REVEALED;
    }

    /**
     * This method
     * @param state
     */
    public void setState(CellState state) {
        this.state = state;
    }

    public int getNumberOfBombsNearby() {
        return numberOfBombsNearby;
    }

    public void setNumberOfBombsNearby(int numberOfBombsNearby) {
        this.numberOfBombsNearby = numberOfBombsNearby;
    }

    @Override
    public String toString() {
        if (state == CellState.REVEALED) {
            if (isBomb()) {
                return "*";
            } else {
                return numberOfBombsNearby == 0 ? " " : Integer.toString(numberOfBombsNearby);
            }
        }
        return this.state.toString();
    }

}
