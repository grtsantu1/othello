package com.ubs.intrvw;

public enum CellStatus {
    EMPTY("_"), DARK("X"), LIGHT("O");

    private String value;

    CellStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }


    public boolean isOppositeColor(CellStatus cellStatus) {
        if ((LIGHT == this && DARK == cellStatus) || (DARK == this && LIGHT == cellStatus)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSameColor(CellStatus cellStatus) {
        return this != EMPTY && this == cellStatus;
    }

    public String getValue() {
        return value;
    }
}
