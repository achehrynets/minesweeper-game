package com.acheh.minesweeper.ui;

import com.acheh.minesweeper.exception.MinesweeperException;
import com.acheh.minesweeper.ui.console.ConsoleMinesweeperUI;
import com.acheh.minesweeper.ui.desktop.DesktopMinesweeperUI;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

public class MinesweeperUIFactoryTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testCreateConsoleUI() {
        MineSweeperUI ui = MinesweeperUIFactory.create(UIType.CONSOLE);
        assertTrue(ui instanceof ConsoleMinesweeperUI);
    }

    @Test
    public void testCreateGUI() {
        MineSweeperUI ui = MinesweeperUIFactory.create(UIType.GUI);
        assertTrue(ui instanceof DesktopMinesweeperUI);
    }

    @Test
    public void testNegativeScenarioWhenPassingNullValue() {
        exceptionRule.expect(MinesweeperException.class);
        exceptionRule.expectMessage("For input UIType: \"null\"");
        MinesweeperUIFactory.create(null);
    }
}