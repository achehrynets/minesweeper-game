package com.acheh.minesweeper.ui.desktop;

import com.acheh.minesweeper.exception.MinesweeperException;
import com.acheh.minesweeper.model.GameBoard;
import com.acheh.minesweeper.model.GameBoardTemplate;
import com.acheh.minesweeper.model.GameStatus;
import com.acheh.minesweeper.ui.MineSweeperUI;
import com.acheh.minesweeper.ui.action.Action;
import com.acheh.minesweeper.ui.desktop.panel.BoardTemplateSelectPanel;
import com.acheh.minesweeper.ui.desktop.panel.GameBoardPanel;

import javax.swing.*;

import java.awt.*;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This class implements the {@link MineSweeperUI} interface and provides a desktop based UI for the game.
 */
public class DesktopMinesweeperUI extends JFrame implements MineSweeperUI {

    private GameBoardPanel gameBoardPanel;

    public DesktopMinesweeperUI() throws HeadlessException {
        super("Minesweeper");
        this.setLayout(new BorderLayout());
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showWelcomeBanner() {
        showMessageDialog(null, "Welcome to Minesweeper!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameBoardTemplate getGameBoardTemplateInput() {
        BoardTemplateSelectPanel panel = new BoardTemplateSelectPanel();
        int result = showConfirmDialog(null, panel, "Please select a game board complexity", OK_CANCEL_OPTION);
        while (result == OK_OPTION && !panel.validateInput()) {
            showMessageDialog(null, "Please select a game board complexity");
            result = showConfirmDialog(null, panel, "Please select a game board complexity", OK_CANCEL_OPTION);
        }
        if (result == OK_OPTION) {
            return panel.getSelectedBoard();
        } else {
            throw new MinesweeperException("Board template not selected");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getUserActionInput() {
        return this.gameBoardPanel.getUserAction();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showGameBoard(GameBoard board) {
        if (this.gameBoardPanel == null) {
            this.gameBoardPanel = new GameBoardPanel(board);
            this.add(this.gameBoardPanel);
            this.repaint();
            this.setVisible(true);
        }
        this.gameBoardPanel.renderGameBoard();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showGameResult(GameStatus gameStatus) {
        if (gameStatus == GameStatus.WON) {
            showMessageDialog(null, "You won!");
        } else {
            showMessageDialog(null, "You lost!");
        }
        this.remove(this.gameBoardPanel);
        this.setVisible(false);
        this.gameBoardPanel = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getPlayAgainInput() {
        int result = showConfirmDialog(null, "Play again?", "Play again?", YES_NO_OPTION);
        return result == YES_OPTION;
    }

}
