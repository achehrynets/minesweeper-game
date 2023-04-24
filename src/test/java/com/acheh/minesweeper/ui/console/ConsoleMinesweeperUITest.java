package com.acheh.minesweeper.ui.console;

import com.acheh.minesweeper.model.GameBoard;
import com.acheh.minesweeper.model.GameBoardFactory;
import com.acheh.minesweeper.model.GameBoardTemplate;
import com.acheh.minesweeper.model.GameStatus;
import com.acheh.minesweeper.ui.action.Action;
import com.acheh.minesweeper.ui.action.ActionType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleMinesweeperUITest {

    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    public void testShowWelcomeBanner() {
        ConsoleMinesweeperUI consoleMinesweeperUI = new ConsoleMinesweeperUI();
        consoleMinesweeperUI.showWelcomeBanner();
        Assert.assertEquals("Welcome to Minesweeper!\n", getOutput());
    }

    @Test
    public void testGetGameBoardTemplateInputBeginner() {
        provideInput("1");
        ConsoleMinesweeperUI consoleMinesweeperUI = new ConsoleMinesweeperUI();
        GameBoardTemplate template = consoleMinesweeperUI.getGameBoardTemplateInput();
        Assert.assertEquals(9, template.getRows());
        Assert.assertEquals(9, template.getColumns());
        Assert.assertEquals(10, template.getAmountOfBombs());
        Assert.assertTrue(getOutput().contains("Your board is 9x9 with 10 mines"));
    }

    @Test
    public void testGetGameBoardTemplateInputIntermediate() {
        provideInput("2");
        ConsoleMinesweeperUI consoleMinesweeperUI = new ConsoleMinesweeperUI();
        GameBoardTemplate template = consoleMinesweeperUI.getGameBoardTemplateInput();
        Assert.assertEquals(16, template.getRows());
        Assert.assertEquals(16, template.getColumns());
        Assert.assertEquals(40, template.getAmountOfBombs());
        Assert.assertTrue(getOutput().contains("Your board is 16x16 with 40 mines"));
    }

    @Test
    public void testGetGameBoardTemplateInputExpert() {
        provideInput("3");
        ConsoleMinesweeperUI consoleMinesweeperUI = new ConsoleMinesweeperUI();
        GameBoardTemplate template = consoleMinesweeperUI.getGameBoardTemplateInput();
        Assert.assertEquals(16, template.getRows());
        Assert.assertEquals(30, template.getColumns());
        Assert.assertEquals(99, template.getAmountOfBombs());
        Assert.assertTrue(getOutput().contains("Your board is 30x16 with 99 mines"));
    }

    @Test
    public void testGetGameBoardTemplateInputCustom() {
        provideInput("4\n3\n3\n2");
        ConsoleMinesweeperUI consoleMinesweeperUI = new ConsoleMinesweeperUI();
        GameBoardTemplate template = consoleMinesweeperUI.getGameBoardTemplateInput();
        Assert.assertEquals(3, template.getRows());
        Assert.assertEquals(3, template.getColumns());
        Assert.assertEquals(2, template.getAmountOfBombs());
        Assert.assertTrue(getOutput().contains("Your board is 3x3 with 2 mines"));
    }

    @Test
    public void testGetUserActionInput() {
        provideInput("1,2,r");
        ConsoleMinesweeperUI consoleMinesweeperUI = new ConsoleMinesweeperUI();
        Action action = consoleMinesweeperUI.getUserActionInput();
        Assert.assertEquals(1, action.getRow());
        Assert.assertEquals(2, action.getColumn());
        Assert.assertEquals(ActionType.REVEAL, action.getActionType());
    }

    @Test
    public void testShowGameBoard() {
        GameBoardTemplate template = new GameBoardTemplate(2, 2, 0);
        // create a sample game board
        GameBoard board = GameBoardFactory.createBoard(template);

        // invoke the method and check the output
        ConsoleMinesweeperUI ui = new ConsoleMinesweeperUI();
        ui.showGameBoard(board);
        String expectedOutput = "  0 1\n0 x x \n1 x x";
        Assert.assertTrue(getOutput().contains(expectedOutput));
    }

    @Test
    public void testShowGameResultWhenGameStatusIsLost() {
        // invoke the method with GameStatus.LOST and check the output
        ConsoleMinesweeperUI ui = new ConsoleMinesweeperUI();
        ui.showGameResult(GameStatus.LOST);
        String expectedOutput = "Sorry, you lost!";
        Assert.assertTrue(getOutput().contains(expectedOutput));
    }

    @Test
    public void testShowGameResultWhenGameStatusIsWon() {
        // invoke the method with GameStatus.WON and check the output
        ConsoleMinesweeperUI ui = new ConsoleMinesweeperUI();
        ui.showGameResult(GameStatus.WON);
        String expectedOutput = "Congratulations! You won!";
        Assert.assertTrue(getOutput().contains(expectedOutput));
    }

}