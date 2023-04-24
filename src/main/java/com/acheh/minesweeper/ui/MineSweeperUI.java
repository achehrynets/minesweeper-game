package com.acheh.minesweeper.ui;

import com.acheh.minesweeper.model.GameBoard;
import com.acheh.minesweeper.model.GameBoardTemplate;
import com.acheh.minesweeper.model.GameStatus;
import com.acheh.minesweeper.ui.action.Action;

/**
 * This interface defines the contract for the UI of the game.
 */
public interface MineSweeperUI {

    /**
     * This method displays a welcome banner to the user.
     */
    void showWelcomeBanner();

    /**
     * This method gets the game board template from the user.
     * @return the game board template
     */
    GameBoardTemplate getGameBoardTemplateInput();

    /**
     * This method gets the user input from the user.
     * @return the user input
     */
    Action getUserActionInput();

    /**
     * This method renders the game board to the user.
     * @param board the game board
     */
    void showGameBoard(GameBoard board);

    /**
     * This method displays the game result to the user.
     * @param gameStatus the game status
     */
    void showGameResult(GameStatus gameStatus);

    /**
     * This method gets the user input to play again.
     * @return true if the user wants to play again, false otherwise
     */
    boolean getPlayAgainInput();

}
