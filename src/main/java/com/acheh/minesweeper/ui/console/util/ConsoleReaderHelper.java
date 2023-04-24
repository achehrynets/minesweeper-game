package com.acheh.minesweeper.ui.console.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.System.out;

/**
 * Helper class for reading from the console.
 */
public class ConsoleReaderHelper {

    private final BufferedReader input;

    public ConsoleReaderHelper(InputStream in) {
        this.input = new BufferedReader(new InputStreamReader(in));
    }

    /**
     * Reads an integer from the console.
     *
     * @param message the message to display to the user
     * @return the integer read from the console
     */
    public int readInt(String message, int min, int max) {
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                out.println(message);
                value = Integer.parseInt(input.readLine());
                if (value >= min && value <= max) {
                    validInput = true;
                } else {
                    out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException | IOException e) {
                out.println("Invalid input. Please enter a number.");
            }
        }

        return value;
    }

    /**
     * Reads a string from the console.
     *
     * @param message the message to display to the user
     * @param regex   the regex pattern to match
     * @return the string read from the console
     */
    public String readString(String message, String regex) {
        String userInput = "";
        boolean validInput = false;
        try {
            while (!validInput) {
                out.println(message);
                userInput = this.input.readLine();
                if (!userInput.isEmpty() && userInput.matches(regex)) {
                    validInput = true;
                } else {
                    out.println("Invalid input. Please enter a non-empty string that matches the pattern: " + regex);
                }
            }
        } catch (IOException e) {
            out.println("Invalid input. Please enter a non-empty string that matches the pattern: " + regex);
        }

        return userInput;
    }

}
