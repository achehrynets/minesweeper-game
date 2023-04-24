package com.acheh.minesweeper.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GameBoardTest {
    private final int ROWS = 10;
    private final int COLUMNS = 10;
    private final int BOMBS = 10;
    private GameBoard gameBoard;

    @Before
    public void setUp() {
        this.gameBoard = GameBoardFactory.createBoard(new GameBoardTemplate(COLUMNS, ROWS, BOMBS));
    }

    @Test
    public void testGetCell() {
        Cell cell = this.gameBoard.getCell(0, 0);
        assertNotNull(cell);
    }

    @Test
    public void testGetAmountOfBombs() {
        int actual = this.gameBoard.getAmountOfBombs();
        assertEquals(BOMBS, actual);
    }

    @Test
    public void testGetNumRevealedCells() {
        int expected = 0;
        int actual = this.gameBoard.getNumRevealedCells();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNumSafeCells() {
        int expected = ROWS * COLUMNS - BOMBS;
        int actual = this.gameBoard.getNumSafeCells();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNearbyCells() {
        Cell centerCell = this.gameBoard.getCell(5, 5);
        List<Cell> nearbyCells = this.gameBoard.getNearbyCells(5, 5);
        assertTrue(nearbyCells.contains(this.gameBoard.getCell(4, 4)));
        assertTrue(nearbyCells.contains(this.gameBoard.getCell(5, 4)));
        assertTrue(nearbyCells.contains(this.gameBoard.getCell(6, 4)));
        assertTrue(nearbyCells.contains(this.gameBoard.getCell(4, 5)));
        assertTrue(nearbyCells.contains(this.gameBoard.getCell(6, 5)));
        assertTrue(nearbyCells.contains(this.gameBoard.getCell(4, 6)));
        assertTrue(nearbyCells.contains(this.gameBoard.getCell(5, 6)));
        assertTrue(nearbyCells.contains(this.gameBoard.getCell(6, 6)));
        assertFalse(nearbyCells.contains(centerCell));
        assertFalse(nearbyCells.contains(this.gameBoard.getCell(0, 0)));
    }

    @Test
    public void testGetCells() {
        List<Cell> cells = this.gameBoard.getCells();
        assertEquals(ROWS * COLUMNS, cells.size());
    }

    @Test
    public void testGetRows() {
        int actual = this.gameBoard.getRows();
        assertEquals(ROWS, actual);
    }

    @Test
    public void testGetColumns() {
        int actual = this.gameBoard.getColumns();
        assertEquals(COLUMNS, actual);
    }

}