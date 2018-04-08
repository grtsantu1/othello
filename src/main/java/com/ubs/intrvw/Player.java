package com.ubs.intrvw;

import static com.ubs.intrvw.CellStatus.DARK;
import static com.ubs.intrvw.CellStatus.LIGHT;

public enum Player {
    PLAYER_X(DARK), PLAYER_Y(LIGHT);

    private CellStatus targetCellStatus;

    Player(CellStatus targetCellStatus) {
        this.targetCellStatus = targetCellStatus;
    }

    public CellStatus getTargetCellStatus() {
        return targetCellStatus;
    }
}
