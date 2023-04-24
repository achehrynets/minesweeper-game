package com.acheh.minesweeper.ui.console;

import com.acheh.minesweeper.exception.MinesweeperException;
import com.acheh.minesweeper.model.GameBoard;
import com.acheh.minesweeper.model.GameBoardTemplate;
import com.acheh.minesweeper.model.GameStatus;
import com.acheh.minesweeper.model.PredefinedGameBoardTemplates;
import com.acheh.minesweeper.ui.MineSweeperUI;
import com.acheh.minesweeper.ui.action.Action;
import com.acheh.minesweeper.ui.action.ActionType;
import com.acheh.minesweeper.ui.console.util.ConsoleReaderHelper;

import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.System.out;

/**
 * This class implements the {@link MineSweeperUI} interface and provides a console based UI for the game.
 */
public class ConsoleMinesweeperUI implements MineSweeperUI {

    private final ConsoleReaderHelper consoleReaderHelper;

    public ConsoleMinesweeperUI() {
        this.consoleReaderHelper = new ConsoleReaderHelper(new Scanner(System.in));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showWelcomeBanner() {
        out.println("Welcome to Minesweeper!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameBoardTemplate getGameBoardTemplateInput() {
        out.println("You can select a game complexity");
        out.println("    Type '1' for BEGINNER");
        out.println("        Note: 9x9 board with 10 mines");
        out.println("    Type '2' for INTERMEDIATE");
        out.println("        Note: 16x16 board with 40 mines");
        out.println("    Type '3' for EXPERT");
        out.println("        Note: 30x16 board with 99 mines");
        out.println("    Type '4' for CUSTOM (you will be asked to enter the number of rows, columns and mines)");
        GameBoardTemplate template;
        int userChoice = this.consoleReaderHelper.readInt("Please enter a valid number between 1 and 4", 1, 4);
        if (userChoice == 4) {
            out.println("Please enter the number of rows:");
            int rows = this.consoleReaderHelper.readInt("Please enter a valid number between 1 and 99", 1, 99);
            out.println("Please enter the number of columns:");
            int columns = this.consoleReaderHelper.readInt("Please enter a valid number between 1 and 99", 1, 99);
            out.println("Please enter the number of mines:");
            int mines = this.consoleReaderHelper.readInt("Please enter a valid number between 1 and 99", 1, 99);
            template = new GameBoardTemplate(columns, rows, mines);
        } else {
            template = PredefinedGameBoardTemplates.getPredefinedGameBoardTemplate(userChoice).getBoardTemplate();
        }
        out.println(template);
        return template;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getUserActionInput() {
        out.println("Please enter your action:");
        out.println("    action format should be in the following format: 'row,column,action'");
        out.println("    where 'row' is the row number, 'column' is the column number and 'action' is the action to perform");
        out.println("    'action' can be either 'r - reveal' or 'f - flag/unflag' or 'q - quit'");
        out.println("    for example: '1,2,r' will reveal the cell in row 1 and column 2");
        String userInput = this.consoleReaderHelper.readString("Please enter a valid action", "^(\\d+,\\d+,(r|f|q))|q$");
        String[] userInputParts = userInput.split(",");
        if (userInputParts.length == 1) {
            return new Action(0, 0, ActionType.QUIT);
        } else if (userInputParts.length == 3) {
            return new Action(Integer.parseInt(userInputParts[0]), Integer.parseInt(userInputParts[1]), ActionType.fromShortName(userInputParts[2]));
        }
        throw new MinesweeperException("Invalid user input: " + userInput);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showGameBoard(GameBoard board) {
        out.print(" ");
        IntStream.range(0, board.getColumns()).forEach(i -> out.print(" " + i));
        out.println();
        for (int row = 0; row < board.getRows(); row++) {
            out.print(row + " ");
            for (int column = 0; column < board.getColumns(); column++) {
                out.print(board.getCell(row, column) + " ");
            }
            out.println();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showGameResult(GameStatus gameStatus) {
        if (gameStatus == GameStatus.WON) {
            out.println("Congratulations! You won!");
        } else {
            out.println("Sorry, you lost!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getPlayAgainInput() {
        String userInput = this.consoleReaderHelper.readString("Do you want to play again? (y/n)", "^(y|n)$");
        return userInput.equalsIgnoreCase("y");
    }

}
