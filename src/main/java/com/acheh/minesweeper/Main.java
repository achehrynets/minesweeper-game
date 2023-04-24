package com.acheh.minesweeper;

import com.acheh.minesweeper.ui.MineSweeperUI;
import com.acheh.minesweeper.ui.MinesweeperUIFactory;
import com.acheh.minesweeper.ui.UIType;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1 || (!args[0].equals("console") && !args[0].equals("gui"))) {
            out.println("Usage: java -jar minesweeper-game.jar <console|gui>");
            exit(1);
        }
        UIType uiType = UIType.valueOf(args[0].toUpperCase());
        MineSweeperUI ui = MinesweeperUIFactory.create(uiType);
        MinesweeperGame game = new MinesweeperGame(ui);
        try {
            game.start();
        } catch (Exception e) {
            out.println(e.getMessage());
            exit(1);
        }
        exit(0);
    }



}


