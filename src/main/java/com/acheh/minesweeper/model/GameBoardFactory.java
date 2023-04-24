package com.acheh.minesweeper.model;

import java.util.Random;

/**
 * This class represents the factory of the game board.
 * It creates a new game board based on the template.
 */
public class GameBoardFactory {

    private GameBoardFactory() {
        // prevent instantiation
    }

    /**
     * Creates a new game board based on the template.
     *
     * @param template the template of the game board
     * @return the game board
     */
    public static GameBoard createBoard(GameBoardTemplate template) {
        Cell[][] cells = new Cell[template.getRows()][template.getColumns()];
        initializeCells(template, cells);
        initializeBombs(template, cells);
        initializeNumbersOfBombsNearbyForEachCell(template, cells);
        return new GameBoard(cells, template.getAmountOfBombs());
    }

    /**
     * Initialize the cells of the game board.
     *
     * @param template the template of the game board
     * @param cells    the cells of the game board
     */
    private static void initializeCells(GameBoardTemplate template, Cell[][] cells) {
        for (int row = 0; row < template.getRows(); row++) {
            for (int column = 0; column < template.getColumns(); column++) {
                cells[row][column] = new Cell(row, column);
            }
        }
    }

    /**
     * Initialize the bombs of the game board.
     * The bombs are randomly placed on the game board.
     *
     * @param template the template of the game board
     * @param cells    the cells of the game board
     */
    private static void initializeBombs(GameBoardTemplate template, Cell[][] cells) {
        Random random = new Random();
        int count = 0;
        while (count < template.getAmountOfBombs()) {
            int row = random.nextInt(template.getRows());
            int column = random.nextInt(template.getColumns());
            if (!cells[row][column].isBomb()) {
                cells[row][column].setBomb(true);
                count++;
            }
        }
    }

    /**
     * Initialize the number of bombs nearby for each cell.
     *
     * @param template the template of the game board
     * @param cells    the cells of the game board
     */
    private static void initializeNumbersOfBombsNearbyForEachCell(GameBoardTemplate template, Cell[][] cells) {
        int rows = template.getRows();
        int columns = template.getColumns();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (cells[row][column].isBomb()) {
                    continue;
                }
                int countBombs = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int r = row + i;
                        int c = column + j;
                        if (r >= 0 && r < rows && c >= 0 && c < columns && cells[r][c].isBomb()) {
                            countBombs++;
                        }
                    }
                }
                cells[row][column].setNumberOfBombsNearby(countBombs);
            }
        }
    }

}
