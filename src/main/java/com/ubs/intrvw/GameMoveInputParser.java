package com.ubs.intrvw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMoveInputParser {

    private Pattern inputCharPattern;

    private static final String INPUT_CHAR_PATTERN_REGEX = "((\\b[1-8][a-h]\\b)|(\\b[a-h][1-8])\\b)";
    private static final int INPUT_COLUMN_OFFSET_ASCII_VALUE = 'a';

    public GameMoveInputParser() {
        inputCharPattern = Pattern.compile(INPUT_CHAR_PATTERN_REGEX);
    }

    public Move parseToGameMove(String moveInput, Board board, Player player) {
        if (!isValidInput(moveInput)) {
            return null;
        }
        char[] moveInputChars = moveInput.toCharArray();
        int rowIndex = 0;
        int colIndex = 1;
        if (Character.isDigit(moveInputChars[1])) {
            rowIndex = 1;
            colIndex = 0;
        }

        int row = Character.getNumericValue(moveInputChars[rowIndex]) - 1;
        int col = moveInputChars[colIndex] - INPUT_COLUMN_OFFSET_ASCII_VALUE;
        return new Move(row, col, player.getTargetCellStatus(), board);
    }

    private boolean isValidInput(String moveInput) {
        Matcher moveInputMatcher = inputCharPattern.matcher(moveInput);
        return moveInputMatcher.matches();
    }
}
