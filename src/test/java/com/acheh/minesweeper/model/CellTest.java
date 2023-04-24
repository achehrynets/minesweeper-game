package com.acheh.minesweeper.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CellTest {

    @Test
    public void testGetRow() {
        Cell cell = new Cell(3, 5);
        assertEquals(3, cell.getRow());
    }

    @Test
    public void testGetColumn() {
        Cell cell = new Cell(3, 5);
        assertEquals(5, cell.getColumn());
    }

    @Test
    public void testSetAndGetBomb() {
        Cell cell = new Cell(0, 0);
        assertFalse(cell.isBomb());
        cell.setBomb(true);
        assertTrue(cell.isBomb());
    }

    @Test
    public void testIsFlagged() {
        Cell cell = new Cell(0, 0);
        assertFalse(cell.isFlagged());
        cell.setState(CellState.FLAGGED);
        assertTrue(cell.isFlagged());
    }

    @Test
    public void testIsRevealed() {
        Cell cell = new Cell(0, 0);
        assertFalse(cell.isRevealed());
        cell.setState(CellState.REVEALED);
        assertTrue(cell.isRevealed());
    }

    @Test
    public void testSetAndGetNumberOfBombsNearby() {
        Cell cell = new Cell(0, 0);
        assertEquals(0, cell.getNumberOfBombsNearby());
        cell.setNumberOfBombsNearby(3);
        assertEquals(3, cell.getNumberOfBombsNearby());
    }

    @Test
    public void testToStringHidden() {
        Cell cell = new Cell(0, 0);
        assertEquals(CellState.HIDDEN.toString(), cell.toString());
    }

    @Test
    public void testToStringRevealedNoBombs() {
        Cell cell = new Cell(0, 0);
        cell.setState(CellState.REVEALED);
        cell.setNumberOfBombsNearby(0);
        assertEquals(" ", cell.toString());
    }

    @Test
    public void testToStringRevealedBomb() {
        Cell cell = new Cell(0, 0);
        cell.setState(CellState.REVEALED);
        cell.setBomb(true);
        assertEquals("*", cell.toString());
    }

    @Test
    public void testToStringRevealedWithBombs() {
        Cell cell = new Cell(0, 0);
        cell.setState(CellState.REVEALED);
        cell.setNumberOfBombsNearby(3);
        assertEquals("3", cell.toString());
    }

}