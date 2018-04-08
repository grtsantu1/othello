package com.ubs.intrvw;

import static com.ubs.intrvw.CellStatus.EMPTY;

public class Move {
    private int movePositionX;
    private int movePositionY;
    private int destinationX;
    private int destinationY;
    private Board board;
    private CellStatus moveTargetCellStatus;
    private MoveDirection validFlipDirection;
    private int validFlipsCount;


    public Move(int movePositionX, int movePositionY, CellStatus moveTargetCellStatus, Board board) {
        this.movePositionX = movePositionX;
        this.movePositionY = movePositionY;
        this.moveTargetCellStatus = moveTargetCellStatus;
        this.board = board;
    }

    public boolean flipPiecesIfValidMove() {
        if (isValidMove()) {
            flipPieces();
            return true;
        } else {
            return false;
        }
    }

    private void flipPieces() {
        if (validFlipsCount == 0) {
            return;
        }
        resetDestinationToMovePosition();
        board.setCellStatus(movePositionX, movePositionY, moveTargetCellStatus);

        for (int i = 0; i < validFlipsCount; i++) {
            moveDestinationInRange(validFlipDirection);
            board.setCellStatus(destinationX, destinationY, moveTargetCellStatus);
        }
    }

    private boolean isValidMove() {
        if (!isMovePositionValid()) {
            return false;
        }
        boolean validDestinationFound = false;
        for (MoveDirection moveDirection : MoveDirection.values()) {
            resetDestinationToMovePosition();
            if (isValidDestinationFoundInDirection(moveDirection)) {
                validDestinationFound = true;
                break;
            }
        }
        return validDestinationFound;
    }

    private boolean isMovePositionValid() {
        return board.isCellWithinBoard(movePositionX, movePositionY) &&
                EMPTY == board.getCellStatus(movePositionX, movePositionY);
    }

    private boolean isValidDestinationFoundInDirection(MoveDirection moveDirection) {
        boolean moveAhead = moveDestinationInRange(moveDirection);
        int possibleFlipCount = 0;

        CellStatus currentDestCellStatus = null;
        while (moveAhead) {
            currentDestCellStatus = board.getCellStatus(destinationX, destinationY);
            if (currentDestCellStatus.isOppositeColor(moveTargetCellStatus)) {
                possibleFlipCount++;
                moveAhead = moveDestinationInRange(moveDirection);
            } else {
                moveAhead = false;
            }
        }

        if (possibleFlipCount > 0 && moveTargetCellStatus.isSameColor(currentDestCellStatus)) {
            validFlipDirection = moveDirection;
            validFlipsCount = possibleFlipCount;
            return true;
        } else {
            return false;
        }
    }

    private boolean moveDestinationInRange(MoveDirection moveDirection) {
        int newDestinationX = destinationX;
        int newDestinationY = destinationY;
        switch (moveDirection) {
            case LEFT:
                newDestinationY--;
                break;
            case LEFT_UP:
                newDestinationX--;
                newDestinationY--;
                break;
            case UP:
                newDestinationX--;
                break;
            case RIGHT_UP:
                newDestinationX--;
                newDestinationY++;
                break;
            case RIGHT:
                newDestinationY++;
                break;
            case RIGHT_DOWN:
                newDestinationX++;
                newDestinationY++;
                break;
            case DOWN:
                newDestinationX++;
                break;
            case LEFT_DOWN:
                newDestinationX++;
                newDestinationY--;
                break;
        }

        if (board.isCellWithinBoard(newDestinationX, newDestinationY)) {
            destinationX = newDestinationX;
            destinationY = newDestinationY;
            return true;
        } else {
            return false;
        }
    }

    private void resetDestinationToMovePosition() {
        destinationX = movePositionX;
        destinationY = movePositionY;
    }

    public int getMovePositionX() {
        return movePositionX;
    }

    public int getMovePositionY() {
        return movePositionY;
    }
}
