package com.acheh.minesweeper.ui.desktop.panel;

import com.acheh.minesweeper.model.Cell;
import com.acheh.minesweeper.model.GameBoard;
import com.acheh.minesweeper.ui.action.Action;
import com.acheh.minesweeper.ui.action.ActionType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.CountDownLatch;

/**
 * This class represents the game board panel for GUI
 */
public class GameBoardPanel extends JPanel implements MouseListener {

    private final GameBoard board;
    private final CellPanel[][] cellPanels;
    private final CountDownLatch countDownLatch;
    private Action action;
    private boolean userInput;

    public GameBoardPanel(GameBoard gameBoard) {
        this.action = null;
        this.board = gameBoard;
        this.countDownLatch = new CountDownLatch(1);
        setLayout(new GridLayout(gameBoard.getRows(), gameBoard.getColumns()));
        this.cellPanels = new CellPanel[this.board.getRows()][this.board.getColumns()];
        for (int row = 0; row < this.board.getRows(); row++) {
            for (int column = 0; column < this.board.getColumns(); column++) {
                CellPanel cellPanel = new CellPanel(this.board.getCell(row, column));
                add(cellPanel);
                cellPanels[row][column] = cellPanel;
                cellPanel.addMouseListener(this);
            }
        }
    }

    /**
     * This method renders the game board
     */
    public void renderGameBoard() {
        for (int row = 0; row < this.board.getRows(); row++) {
            for (int column = 0; column < this.board.getColumns(); column++) {
                cellPanels[row][column].setText(this.board.getCell(row, column).toString());
            }
        }
    }

    /**
     * This method returns the user action when it happens
     * @return  the user action
     */
    public Action getUserAction() {
        synchronized (this) {
            while (!userInput) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // nothing to do
                }
            }
            userInput = false;
        }
        return action;
    }

    /**
     * This method is called when the user clicks on a cell panel.
     * @param event the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        synchronized (this) {
            CellPanel cellPanel = (CellPanel) event.getSource();
            Cell cell = cellPanel.getCell();
            if (SwingUtilities.isLeftMouseButton(event)) {
                this.action = new Action(cell.getRow(), cell.getColumn(), ActionType.REVEAL);
            } else if (SwingUtilities.isRightMouseButton(event)) {
                this.action = new Action(cell.getRow(), cell.getColumn(), ActionType.FLAG);
            }
            userInput = true;
            notifyAll();
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        // do nothing for think mouse action
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        // do nothing for think mouse action
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        // do nothing for think mouse action
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        // do nothing for think mouse action
    }

    /**
     * This class represents the cell panel for GUI
     */
    private static class CellPanel extends JPanel {
        private final Cell cell;
        private final JLabel label;

        public CellPanel(Cell cell) {
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.cell = cell;
            this.label = new JLabel(cell.toString());
            add(label);
        }

        /**
         * Gets the cell
         * @return the cell
         */
        public Cell getCell() {
            return cell;
        }

        /**
         * Sets the text of the label
         * @param text the text to set
         */
        public void setText(String text) {
            this.label.setText(text);
            this.repaint();
        }
    }

}
