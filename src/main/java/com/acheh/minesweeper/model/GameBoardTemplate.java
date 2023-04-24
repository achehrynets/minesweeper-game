package com.acheh.minesweeper.model;

/**
 * This class represents the template of the game board.
 * It contains the number of columns, rows and bombs.
 * It is used to create a new game board.
 */
public class GameBoardTemplate {

    /**
     * The number of columns.
     */
    private final int columns;
    /**
     * The number of rows.
     */
    private final int rows;
    /**
     * The number of bombs.
     */
    private final int amountOfBombs;

    public GameBoardTemplate(int columns, int rows, int amountOfBombs) {
        this.columns = columns;
        this.rows = rows;
        this.amountOfBombs = amountOfBombs;
    }

    /**
     * This method returns the number of columns for the game board.
     * @return the number of columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * This method returns the number of rows for the game board.
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * This method returns the number of bombs for the game board.
     * @return the number of bombs
     */
    public int getAmountOfBombs() {
        return amountOfBombs;
    }

    @Override
    public String toString() {
        return "Your board is " + columns + "x" + rows + " with " + amountOfBombs + " mines";
    }
}
