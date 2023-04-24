package com.acheh.minesweeper;


import com.acheh.minesweeper.model.GameBoard;
import com.acheh.minesweeper.model.GameBoardFactory;
import com.acheh.minesweeper.model.GameBoardTemplate;
import com.acheh.minesweeper.model.GameStatus;
import com.acheh.minesweeper.engine.MinesweeperGameEngine;
import com.acheh.minesweeper.ui.action.Action;
import com.acheh.minesweeper.ui.MineSweeperUI;
import com.acheh.minesweeper.ui.action.ActionType;

/**
 * This class is the entry point of the game.
 * The MinesweeperGame class takes a MineSweeperUI object as a parameter in its constructor.
 * This UI object is used to display game-related messages, get user input, and render the game board.
*/
public class MinesweeperGame {

    /**
     * The UI object.
     */
    private final MineSweeperUI ui;

    public MinesweeperGame(MineSweeperUI ui) {
        this.ui = ui;
    }

    /**
     * This method starts the game which helps to incapsulate all entire game logic.
     * Logic of this method does the following:
     * <ul>
     *     <li>It displays a welcome banner to the user.</li>
     *     <li>It gets the game board template from the user.</li>
     *     <li>It creates a new game board based on the template.</li>
     *     <li>It creates a new game engine based on the game board.</li>
     *     <li>It renders the game board to the user.</li>
     *     <li>It gets the user input from the user.</li>
     *     <li>It performs the user action on the game board.</li>
     *     <li>It displays the game result to the user.</li>
     *     <li>It gets the user input to play again.</li>
     * </ul>
     */
    public void start() {
        this.ui.showWelcomeBanner();
        GameBoardTemplate template = this.ui.getGameBoardTemplateInput();
        while (true) {
            GameBoard board = GameBoardFactory.createBoard(template);
            MinesweeperGameEngine engine = new MinesweeperGameEngine(board);
            this.ui.showGameBoard(board);
            while (engine.getGameState() == GameStatus.PLAYING) {
                Action action = this.ui.getUserActionInput();
                if (action.getActionType() == ActionType.QUIT) {
                    break;
                }
                engine.performAction(action);
                this.ui.showGameBoard(board);
            }
            this.ui.showGameBoard(board);
            this.ui.showGameResult(engine.getGameState());

            if (!this.ui.getPlayAgainInput()) {
                return;
            }
        }
    }

}
