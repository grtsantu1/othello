package com.ubs.intrvw;

import static com.ubs.intrvw.CellStatus.*;

public class Board {
    private CellStatus[][] cells;
    private int darkCellCount;
    private int lightCellCount;
    private static final int BOARD_SIZE = 8;
    private static final int BOARD_CELL_COUNT = BOARD_SIZE * BOARD_SIZE;
    private static final String DRAW_STATUS = "DRAW";

    public Board() {
        init();
    }

    private void init() {
        cells = new CellStatus[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = EMPTY;
            }
        }
        cells[3][3] = LIGHT;
        cells[3][4] = DARK;
        cells[4][3] = DARK;
        cells[4][4] = LIGHT;

        darkCellCount = 2;
        lightCellCount = 2;
    }

    public boolean isCellWithinBoard(int posX, int posY) {
        return (posX >= 0 && posX < BOARD_SIZE && posY >= 0 && posY < BOARD_SIZE) ? true : false;
    }

    public void setCellStatus(int posX, int posY, CellStatus targetCellStatus) {
        CellStatus existingCellStatus = cells[posX][posY];
        cells[posX][posY] = targetCellStatus;
        if (EMPTY == existingCellStatus) {
            increaseCellCount(targetCellStatus);
        } else {
            increaseCellCountAndDecreaseOpposite(targetCellStatus);
        }
    }

    private void increaseCellCount(CellStatus cellStatus) {
        if (DARK == cellStatus) {
            darkCellCount += 1;
        } else if (LIGHT == cellStatus) {
            lightCellCount += 1;
        }
    }

    private void increaseCellCountAndDecreaseOpposite(CellStatus cellStatus) {
        if (DARK == cellStatus) {
            darkCellCount += 1;
            lightCellCount--;
        } else if (LIGHT == cellStatus) {
            lightCellCount += 1;
            darkCellCount--;
        }
    }

    public void printBoard() {
        for (int i = 0; i < cells.length; i++) {
            System.out.print(i + 1 + "  ");
            for (int j = 0; j < cells[i].length; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("  a b c d e f g h");
    }

    public int getDarkCellCount() {
        return darkCellCount;
    }

    public int getLightCellCount() {
        return lightCellCount;
    }

    public CellStatus getCellStatus(int posX, int posY) {
        return cells[posX][posY];
    }

    public boolean isFull() {
        return (darkCellCount + lightCellCount) == BOARD_CELL_COUNT;
    }

    public String getWinner() {
        if (darkCellCount == lightCellCount) {
            return DRAW_STATUS;
        }
        return darkCellCount > lightCellCount ? DARK.getValue() : LIGHT.getValue();
    }

    public int getWinnerCount() {
        return darkCellCount > lightCellCount ? darkCellCount : lightCellCount;
    }

    public int getLoserCount() {
        return darkCellCount < lightCellCount ? darkCellCount : lightCellCount;
    }
}
