package com.acheh.minesweeper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents the game board.
 * It contains the cells and the number of bombs.
 * It also contains the template of the game board.
 */
public class GameBoard {

    /**
     * The cells of the game board.
     */
    private final Cell[][] cells;

    /**
     * The number of bombs.
     */
    private final int amountOfBombs;

    GameBoard(Cell[][] cells, int amountOfBombs) {
        this.cells = cells;
        this.amountOfBombs = amountOfBombs;
    }

    /**
     * This method returns the cell for the given row and column.
     * @param row the row
     * @param column the column
     * @return the cell
     */
    public Cell getCell(int row, int column) {
        return this.cells[row][column];
    }

    /**
     * This method returns the number of bombs in the game board.
     * @return the number of bombs
     */
    public int getAmountOfBombs() {
        return amountOfBombs;
    }

    /**
     * This method calculates the number of revealed cells.
     * @return the number of revealed cells
     */
    public int getNumRevealedCells() {
        return Arrays.stream(this.cells)
                .flatMap(Arrays::stream)
                .mapToInt(cell -> cell.isRevealed() ? 1 : 0)
                .sum();
    }

    /**
     * This method calculates the number of safe cells.
     * @return the number of safe cells
     */
    public int getNumSafeCells() {
        return Arrays.stream(this.cells)
                .flatMap(Arrays::stream)
                .mapToInt(cell -> cell.isBomb() ? 0 : 1)
                .sum();
    }

    /**
     * This method finds the nearby cells of a given cell.
     * @param row the row of the cell
     * @param column the column of the cell
     * @return the list of nearby cells
     */
    public List<Cell> getNearbyCells(int row, int column) {
        List<Cell> nearbyCells = new ArrayList<>();
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i == row && j == column) {
                    continue;
                }

                if (isValidCell(i, j)) {
                    nearbyCells.add(cells[i][j]);
                }
            }
        }
        return nearbyCells;
    }

    /**
     * This method grabs all the cells of the game board.
     * @return the list of cells
     */
    public List<Cell> getCells() {
        return Arrays.stream(this.cells)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    /**
     * This method returns the number of rows of the game board.
     * @return the number of rows
     */
    public int getRows() {
        return this.cells.length;
    }

    /**
     * This method returns the number of columns of the game board.
     * @return the number of columns
     */
    public int getColumns() {
        return this.getRows() == 0 ? 0 : this.cells[0].length;
    }

    /**
     * This method checks if a given cell is valid which means that it's in range of game board.
     * @param row the row of the cell
     * @param column the column of the cell
     * @return true if the cell is valid, false otherwise
     */
    private boolean isValidCell(int row, int column) {
        return row >= 0 && row < this.getRows() && column >= 0 && column < this.getColumns();
    }

}
