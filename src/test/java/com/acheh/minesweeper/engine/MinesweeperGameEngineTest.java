package com.acheh.minesweeper.engine;

import com.acheh.minesweeper.model.Cell;
import com.acheh.minesweeper.model.CellState;
import com.acheh.minesweeper.model.GameBoard;
import com.acheh.minesweeper.model.GameBoardFactory;
import com.acheh.minesweeper.model.GameStatus;
import com.acheh.minesweeper.model.PredefinedGameBoardTemplates;
import com.acheh.minesweeper.ui.action.Action;
import com.acheh.minesweeper.ui.action.ActionType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinesweeperGameEngineTest {
    
    private GameBoard board;
    private MinesweeperGameEngine engine;
    
    @Before
    public void setUp() {
        this.board = GameBoardFactory.createBoard(PredefinedGameBoardTemplates.BEGINNER.getBoardTemplate());
        this.engine = new MinesweeperGameEngine(board);
    }
    
    @Test
    public void testPerformActionWithDifferentScenarios() {
        assertEquals(GameStatus.PLAYING, this.engine.getGameState());

        // Reveal a cell that is not a bomb
        this.board.getCell(0, 0).setBomb(false);
        this.engine.performAction(new Action( 0, 0, ActionType.REVEAL));
        assertEquals(GameStatus.PLAYING, this.engine.getGameState());

        // Flag a cell that already revealed
        this.engine.performAction(new Action(0, 0, ActionType.FLAG));
        assertEquals(this.board.getAmountOfBombs(), this.engine.getAmountOfFlags());

        // Reveal a cell that is a bomb
        Cell cell = this.board.getCell(1, 1);
        cell.setBomb(true);
        cell.setState(CellState.HIDDEN);
        this.engine.performAction(new Action( 1, 1, ActionType.REVEAL));
        assertEquals(GameStatus.LOST, this.engine.getGameState());
    }

    @Test
    public void testMinesweeperGameEngineWhenAllBombsWereRevealed() {
        // Test initial game state
        assertEquals(GameStatus.PLAYING, this.engine.getGameState());
        // Test game state after revealing all safe cells
        for (int row = 0; row < this.board.getColumns(); row++) {
            for (int column = 0; column < this.board.getRows(); column++) {
                if (!this.board.getCell(row, column).isBomb()) {
                    this.engine.performAction(new Action(row, column, ActionType.REVEAL));
                }
            }
        }
        assertEquals(GameStatus.WON, this.engine.getGameState());
    }

    @Test
    public void testMinesweeperGameEngineWhenAllBombsWereFlaggedAndOtherCellsWereRevealed() {
        // Test initial game state
        assertEquals(GameStatus.PLAYING, this.engine.getGameState());
        // Test game state after flagging all bomb cells
        for (int row = 0; row < this.board.getColumns(); row++) {
            for (int column = 0; column < this.board.getRows(); column++) {
                if (this.board.getCell(row, column).isBomb()) {
                    this.engine.performAction(new Action(row, column, ActionType.FLAG));
                }
            }
        }
        // even if all bombs were flagged, the game is still in playing state
        assertEquals(GameStatus.PLAYING, this.engine.getGameState());
        // Test game state after revealing all safe cells
        for (int row = 0; row < this.board.getColumns(); row++) {
            for (int column = 0; column < this.board.getRows(); column++) {
                if (!this.board.getCell(row, column).isBomb()) {
                    this.engine.performAction(new Action(row, column, ActionType.REVEAL));
                }
            }
        }
        assertEquals(GameStatus.WON, this.engine.getGameState());
    }

}