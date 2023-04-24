package com.acheh.minesweeper.model;

/**
 * Represents the state of the game.
 * The game can be in one of the following states:
 * <ul>
 *     <li>PLAYING: the game is in progress</li>
 *     <li>WON: the player has won the game</li>
 *     <li>LOST: the player has lost the game</li>
 * </ul>
 */
public enum GameStatus {

    PLAYING, WON, LOST

}
