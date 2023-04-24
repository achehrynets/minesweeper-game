package com.acheh.minesweeper.engine;

import com.acheh.minesweeper.exception.MinesweeperException;
import com.acheh.minesweeper.model.Cell;
import com.acheh.minesweeper.model.CellState;
import com.acheh.minesweeper.model.GameBoard;
import com.acheh.minesweeper.model.GameStatus;
import com.acheh.minesweeper.ui.action.Action;

/**
 * The game engine that controls the game logic.
 * It contains the game board and the game status.
 * It also contains the amount of flags.
 */
public class MinesweeperGameEngine {

    /**
     * The game board.
     */
    private final GameBoard board;

    /**
     * The game status.
     */
    private GameStatus status;

    /**
     * The amount of flags.
     */
    private int amountOfFlags;

    public MinesweeperGameEngine(GameBoard board) {
        this.board = board;
        this.status = GameStatus.PLAYING;
        this.amountOfFlags = board.getAmountOfBombs();
    }

    /**
     * Performs the given action on the game board.
     * @param action the action to perform
     */
    public void performAction(Action action) {
        switch (action.getActionType()) {
            case REVEAL:
                revealCell(action.getRow(), action.getColumn());
                break;
            case FLAG:
                flagCell(action.getRow(), action.getColumn());
                break;
            default:
                throw new MinesweeperException("Invalid action type");
        }
    }

    /**
     * Reveals the cell at the given row and column.
     * @param row the row of the cell
     * @param column the column of the cell
     */
    private void revealCell(int row, int column) {
        Cell cell = this.board.getCell(row, column);
        if (cell.isRevealed() || cell.isFlagged()) {
            return;
        }

        if (cell.isBomb()) {
            this.status = GameStatus.LOST;
            this.revealAllBombs();
        } else {
            this.revealCell(cell);
            if (this.board.getNumRevealedCells() == this.board.getNumSafeCells()) {
                this.status = GameStatus.WON;
            }
        }
    }

    /**
     * Flags or unflags the cell at the given row and column.
     * @param row the row of the cell
     * @param column the column of the cell
     */
    private void flagCell(int row, int column) {
        Cell cell = board.getCell(row, column);
        // do nothing if the cell is already revealed or user has no more flags
        if (cell.isRevealed()) {
            return;
        }

        if (cell.isFlagged()) {
            cell.setState(CellState.HIDDEN);
            amountOfFlags++;
        } else {
            cell.setState(CellState.FLAGGED);
            amountOfFlags--;
        }
    }

    /**
     * Reveals the given cell and recursively reveals nearby cells if the given cell has no nearby bombs.
     * @param cell the cell to reveal
     */
    private void revealCell(Cell cell) {
        cell.setState(CellState.REVEALED);
        // Check if the revealed cell has adjacent bombs
        if (cell.getNumberOfBombsNearby() == 0) {
            // Recursively reveal adjacent cells until we hit cells with adjacent bombs
            for (Cell adjacentCell : this.board.getNearbyCells(cell.getRow(), cell.getColumn())) {
                if (!adjacentCell.isRevealed()) {
                    revealCell(adjacentCell);
                }
            }
        }
    }

    /**
     * Reveals all the bombs on the game board.
     */
    private void revealAllBombs() {
        for (Cell cell : this.board.getCells()) {
            if (cell.isBomb()) {
                cell.setState(CellState.REVEALED);
            }
        }
    }

    /**
     * Returns the game status.
     * @return the game status
     */
    public GameStatus getGameState() {
        return status;
    }

    public int getAmountOfFlags() {
        return amountOfFlags;
    }

}
