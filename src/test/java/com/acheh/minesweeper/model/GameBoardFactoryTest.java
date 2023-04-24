package com.acheh.minesweeper.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameBoardFactoryTest {

    @Test
    public void testCreateBoardWithZeroSize() {
        GameBoardTemplate template = new GameBoardTemplate(0, 0, 0);
        GameBoard gameBoard = GameBoardFactory.createBoard(template);
        assertEquals(0, gameBoard.getRows());
        assertEquals(0, gameBoard.getColumns());
        assertEquals(0, gameBoard.getAmountOfBombs());
    }

    @Test
    public void testCreateBoardWithSizeOne() {
        GameBoardTemplate template = new GameBoardTemplate(1, 1, 1);
        GameBoard gameBoard = GameBoardFactory.createBoard(template);
        assertEquals(1, gameBoard.getRows());
        assertEquals(1, gameBoard.getColumns());
        assertEquals(1, gameBoard.getAmountOfBombs());
    }

    @Test
    public void testCreateBoardWithBombs() {
        GameBoardTemplate template = new GameBoardTemplate(5, 5, 10);
        GameBoard gameBoard = GameBoardFactory.createBoard(template);
        assertEquals(5, gameBoard.getRows());
        assertEquals(5, gameBoard.getColumns());
        assertEquals(10, gameBoard.getAmountOfBombs());

        int bombsCount = 0;
        for (int row = 0; row < gameBoard.getRows(); row++) {
            for (int col = 0; col < gameBoard.getColumns(); col++) {
                if (gameBoard.getCell(row, col).isBomb()) {
                    bombsCount++;
                }
            }
        }
        assertEquals(10, bombsCount);
    }

    @Test
    public void testCreateBoardWithBombsAndNearbyCount() {
        GameBoardTemplate template = new GameBoardTemplate(10, 10, 20);
        GameBoard gameBoard = GameBoardFactory.createBoard(template);
        assertEquals(10, gameBoard.getRows());
        assertEquals(10, gameBoard.getColumns());
        assertEquals(20, gameBoard.getAmountOfBombs());

        for (int row = 0; row < gameBoard.getRows(); row++) {
            for (int col = 0; col < gameBoard.getColumns(); col++) {
                Cell cell = gameBoard.getCell(row, col);
                if (cell.isBomb()) {
                    continue;
                }
                int expectedNearbyCount = getExpectedNearbyCount(row, col, gameBoard);
                assertEquals(expectedNearbyCount, cell.getNumberOfBombsNearby());
            }
        }
    }

    private int getExpectedNearbyCount(int row, int col, GameBoard gameBoard) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int r = row + i;
                int c = col + j;
                if (r >= 0 && r < gameBoard.getRows() && c >= 0 && c < gameBoard.getColumns() && gameBoard.getCell(r, c).isBomb()) {
                    count++;
                }
            }
        }
        return count;
    }

}