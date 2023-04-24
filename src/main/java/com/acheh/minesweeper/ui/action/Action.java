package com.acheh.minesweeper.ui.action;

/**
 * This class represents an action that can be performed on the game board.
 */
public class Action {

    private final int row;
    private final int column;
    private final ActionType actionType;

    public Action(int row, int column, ActionType actionType) {
        this.row = row;
        this.column = column;
        this.actionType = actionType;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
