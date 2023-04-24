package com.acheh.minesweeper.ui.desktop.panel;

import com.acheh.minesweeper.model.GameBoardTemplate;
import com.acheh.minesweeper.model.PredefinedGameBoardTemplates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * This class represents the board template selection panel for GUI
 */
public class BoardTemplateSelectPanel extends JPanel implements ItemListener {

    private final JCheckBox beginnerCheckBox;
    private final JCheckBox intermediateCheckBox;
    private final JCheckBox expertCheckBox;

    private GameBoardTemplate selectedBoard;

    public BoardTemplateSelectPanel() {
        this.setSize(700, 500);
        setLayout(new GridLayout(3, 2));
        this.beginnerCheckBox = new JCheckBox("Beginner");
        this.intermediateCheckBox = new JCheckBox("Intermediate");
        this.expertCheckBox = new JCheckBox("Expert");

        this.beginnerCheckBox.addItemListener(this);
        this.intermediateCheckBox.addItemListener(this);
        this.expertCheckBox.addItemListener(this);

        add(this.beginnerCheckBox);
        add(this.intermediateCheckBox);
        add(this.expertCheckBox);
    }

    /**
     * Validates the input.
     * @return true if the input is valid, false otherwise.
     */
    public boolean validateInput() {
        return this.beginnerCheckBox.isSelected() || this.intermediateCheckBox.isSelected() || this.expertCheckBox.isSelected();
    }

    /**
     * Returns the selected board template.
     * @return the selected board template.
     */
    public GameBoardTemplate getSelectedBoard() {
        return this.selectedBoard;
    }

    /**
     * This method is called when the user selects a board template.
     * @param event the item event
     */
    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            if (event.getSource() == this.beginnerCheckBox) {
                this.intermediateCheckBox.setSelected(false);
                this.expertCheckBox.setSelected(false);
                this.selectedBoard = PredefinedGameBoardTemplates.BEGINNER.getBoardTemplate();
            } else if (event.getSource() == this.intermediateCheckBox) {
                this.beginnerCheckBox.setSelected(false);
                this.expertCheckBox.setSelected(false);
                this.selectedBoard = PredefinedGameBoardTemplates.INTERMEDIATE.getBoardTemplate();
            } else if (event.getSource() == this.expertCheckBox) {
                this.beginnerCheckBox.setSelected(false);
                this.intermediateCheckBox.setSelected(false);
                this.selectedBoard = PredefinedGameBoardTemplates.EXPERT.getBoardTemplate();
            }
        }
    }

}
