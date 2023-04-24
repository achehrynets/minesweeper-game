package com.acheh.minesweeper.ui;

import com.acheh.minesweeper.exception.MinesweeperException;
import com.acheh.minesweeper.ui.console.ConsoleMinesweeperUI;
import com.acheh.minesweeper.ui.desktop.DesktopMinesweeperUI;

/**
 * Factory class for creating MineSweeperUI instances for given UIType.
 */
public final class MinesweeperUIFactory {

    private MinesweeperUIFactory() {
        // prevent instantiation
    }

    /**
     * Creates a MineSweeperUI instance for given UIType.
     *
     * @param uiType the UIType to create a MineSweeperUI instance for
     * @return a MineSweeperUI instance for given UIType
     */
    public static MineSweeperUI create(UIType uiType) {
        if (uiType == null) {
            throw new MinesweeperException("For input UIType: \"null\"");
        }

        switch (uiType) {
            case CONSOLE:
                return new ConsoleMinesweeperUI();
            case GUI:
                return new DesktopMinesweeperUI();
            default:
                throw new MinesweeperException("Unknown type: " + uiType);
        }
    }

}
