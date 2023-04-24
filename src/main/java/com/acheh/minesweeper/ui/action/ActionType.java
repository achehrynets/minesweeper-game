package com.acheh.minesweeper.ui.action;

import com.acheh.minesweeper.exception.MinesweeperException;

public enum ActionType {

    FLAG("f"), REVEAL("r"), QUIT("q");

    private final String shortName;

    ActionType(String action) {
        this.shortName = action;
    }

    public static ActionType fromShortName(String shortName) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.shortName.equalsIgnoreCase(shortName)) {
                return actionType;
            }
        }
        throw new MinesweeperException("No action type found for short name: " + shortName);
    }

}
