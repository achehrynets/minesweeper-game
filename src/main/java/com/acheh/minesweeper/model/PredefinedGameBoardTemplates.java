package com.acheh.minesweeper.model;

import com.acheh.minesweeper.exception.MinesweeperException;

/**
 * Represents the predefined game board templates.
 * The game can be in one of the following states:
 * <ul>
 *     <li>BEGINNER: 9x9 board with 10 bombs</li>
 *     <li>INTERMEDIATE: 16x16 board with 40 bombs</li>
 *     <li>EXPERT: 30x16 board with 99 bombs</li>
 * </ul>
 * The predefined game board templates are used to create a new game.
 */
public enum PredefinedGameBoardTemplates {

    BEGINNER(9, 9, 10),
    INTERMEDIATE(16, 16, 40),
    EXPERT(30, 16, 99);

    /**
     * The number of columns of the game board.
     */
    private final int columns;
    /**
     * The number of rows of the game board.
     */
    private final int rows;

    /**
     * The number of bombs of the game board.
     */
    private final int amountOfBombs;

    PredefinedGameBoardTemplates(int columns, int rows, int amountOfBombs) {
        this.columns = columns;
        this.rows = rows;
        this.amountOfBombs = amountOfBombs;
    }

    /**
     * This method returns the predefined game board template based on the difficulty.
     * @param difficulty the difficulty of the game
     * @return the predefined game board template
     */
    public static PredefinedGameBoardTemplates getPredefinedGameBoardTemplate(int difficulty) {
        switch (difficulty) {
            case 1:
                return BEGINNER;
            case 2:
                return INTERMEDIATE;
            case 3:
                return EXPERT;
            default:
                throw new MinesweeperException("Invalid difficulty");
        }
    }

    /**
     * This method returns the game board template.
     * @return the game board template
     */
    public GameBoardTemplate getBoardTemplate() {
        return new GameBoardTemplate(columns, rows, amountOfBombs);
    }

}
