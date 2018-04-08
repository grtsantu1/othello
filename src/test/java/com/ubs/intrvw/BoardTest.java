package com.ubs.intrvw;

import org.junit.Before;
import org.junit.Test;

import static com.ubs.intrvw.CellStatus.DARK;
import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @Test
    public void testIsCellWithinBoardWithOutsideRange() {
        assertFalse(board.isCellWithinBoard(-1,-1));
        assertFalse(board.isCellWithinBoard(0,8));
        assertFalse(board.isCellWithinBoard(8,8));
        assertFalse(board.isCellWithinBoard(8,0));
    }

    @Test
    public void testIsCellWithinBoardWithInsideRange() {
        assertTrue(board.isCellWithinBoard(0,0));
        assertTrue(board.isCellWithinBoard(7,7));
        assertTrue(board.isCellWithinBoard(0,7));
        assertTrue(board.isCellWithinBoard(7,0));
        assertTrue(board.isCellWithinBoard(3,4));
    }

    @Test
    public void setCellStatus() {
        assertEquals(2, board.getDarkCellCount());
        assertEquals(2, board.getLightCellCount());

        board.setCellStatus(0,0, DARK);
        assertEquals(3, board.getDarkCellCount());
        assertEquals(2, board.getLightCellCount());

        board.setCellStatus(3,3, DARK);
        assertEquals(4, board.getDarkCellCount());
        assertEquals(1, board.getLightCellCount());
    }

    @Test
    public void isFull() {
        assertFalse(board.isFull());

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.setCellStatus(i,j, DARK);
            }
        }

        assertTrue(board.isFull());
    }

    @Test
    public void testWinner() {
        assertEquals("DRAW", board.getWinner());
        board.setCellStatus(3,3, DARK);
        assertEquals(DARK.getValue(), board.getWinner());
        assertEquals(3, board.getWinnerCount());
        assertEquals(1, board.getLoserCount());
    }
}