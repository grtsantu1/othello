package com.ubs.intrvw;

import org.junit.Before;
import org.junit.Test;

import static com.ubs.intrvw.CellStatus.DARK;
import static com.ubs.intrvw.CellStatus.EMPTY;
import static com.ubs.intrvw.CellStatus.LIGHT;
import static org.junit.Assert.*;

public class MoveTest {

    private Board board;
    private CellStatus moveCellStatus;

    @Before
    public void setUp() {
        board = new Board();
        moveCellStatus = DARK;
    }

    @Test
    public void testIsValidMoveOutOfRangeMove() {
        Move move = new Move(9,10, moveCellStatus, board);
        assertFalse(move.flipPiecesIfValidMove());
        move = new Move(0,8, moveCellStatus, board);
        assertFalse(move.flipPiecesIfValidMove());
        move = new Move(8,0, moveCellStatus, board);
        assertFalse(move.flipPiecesIfValidMove());
        move = new Move(-1,8, moveCellStatus, board);
        assertFalse(move.flipPiecesIfValidMove());
        move = new Move(0,-1, moveCellStatus, board);
        assertFalse(move.flipPiecesIfValidMove());
    }

    @Test
    public void testIsValidMoveWithEmptyCellsAround() {
        Move move = new Move(1,1, moveCellStatus, board);
        assertFalse(move.flipPiecesIfValidMove());
    }

    @Test
    public void testIsValidMoveWithSameCellsAround() {
        //set same color cells around 1,1
        board.setCellStatus(1,0, moveCellStatus);
        board.setCellStatus(0,0, moveCellStatus);
        board.setCellStatus(0,1, moveCellStatus);
        board.setCellStatus(0,2, moveCellStatus);
        board.setCellStatus(1,2, moveCellStatus);
        board.setCellStatus(2,2, moveCellStatus);
        board.setCellStatus(2,1, moveCellStatus);
        board.setCellStatus(2,0, moveCellStatus);

        Move move = new Move(1,1, moveCellStatus, board);
        assertFalse(move.flipPiecesIfValidMove());
    }

    @Test
    public void testIsValidMoveWithOppositeCellsAroundButNoSameColorAtEnd() {
        //set LIGHT color cells around 1,1 but there will be no DARK after the LIGHT cells
        board.setCellStatus(1,0, LIGHT);
        board.setCellStatus(0,0, LIGHT);
        board.setCellStatus(0,1, LIGHT);
        board.setCellStatus(0,2, LIGHT);
        board.setCellStatus(1,2, LIGHT);
        board.setCellStatus(2,2, LIGHT);
        board.setCellStatus(2,1, LIGHT);
        board.setCellStatus(2,0, LIGHT);

        Move move = new Move(1,1, moveCellStatus, board);
        assertFalse(move.flipPiecesIfValidMove());
    }

    @Test
    public void testIsValidMoveWithValidMoveOnLeft() {
        assertEquals(LIGHT, board.getCellStatus(3,3));
        assertEquals(EMPTY, board.getCellStatus(3,2));

        Move move = new Move(3,2, moveCellStatus, board);
        assertTrue(move.flipPiecesIfValidMove());
        assertEquals(DARK, board.getCellStatus(3,3));
        assertEquals(DARK, board.getCellStatus(3,2));
    }

    @Test
    public void testIsValidMoveWithValidMoveOnLeftUpDiagonal() {
        board.setCellStatus(5,5, DARK);
        assertEquals(LIGHT, board.getCellStatus(3,3));
        assertEquals(LIGHT, board.getCellStatus(4,4));
        assertEquals(EMPTY, board.getCellStatus(2,2));

        Move move = new Move(2,2, moveCellStatus, board);
        assertTrue(move.flipPiecesIfValidMove());

        assertEquals(DARK, board.getCellStatus(3,3));
        assertEquals(DARK, board.getCellStatus(4,4));
        assertEquals(DARK, board.getCellStatus(2,2));
    }

    @Test
    public void testIsValidMoveWithValidMoveOnUp() {
        assertEquals(EMPTY, board.getCellStatus(2,3));
        assertEquals(LIGHT, board.getCellStatus(3,3));

        Move move = new Move(2,3, moveCellStatus, board);
        assertTrue(move.flipPiecesIfValidMove());
        assertEquals(DARK, board.getCellStatus(3,3));
        assertEquals(DARK, board.getCellStatus(2,3));
    }

    @Test
    public void testIsValidMoveWithValidMoveOnRightUpDiagonal() {
        board.setCellStatus(4,2, DARK);

        assertEquals(LIGHT, board.getCellStatus(3,3));
        assertEquals(EMPTY, board.getCellStatus(2,4));

        Move move = new Move(2,4, moveCellStatus, board);
        assertTrue(move.flipPiecesIfValidMove());
        assertEquals(DARK, board.getCellStatus(3,3));
        assertEquals(DARK, board.getCellStatus(2,4));
    }

    @Test
    public void testIsValidMoveWithValidMoveOnRight() {
        assertEquals(EMPTY, board.getCellStatus(4,5));
        assertEquals(LIGHT, board.getCellStatus(4,4));

        Move move = new Move(4,5, moveCellStatus, board);
        assertTrue(move.flipPiecesIfValidMove());
        assertEquals(DARK, board.getCellStatus(4,5));
        assertEquals(DARK, board.getCellStatus(4,4));
    }

    @Test
    public void testIsValidMoveWithValidMoveOnRightDownDiagonal() {
        board.setCellStatus(2,2, DARK);
        assertEquals(EMPTY, board.getCellStatus(5,5));
        assertEquals(LIGHT, board.getCellStatus(4,4));
        assertEquals(LIGHT, board.getCellStatus(3,3));

        Move move = new Move(5,5, moveCellStatus, board);
        assertTrue(move.flipPiecesIfValidMove());
        assertEquals(DARK, board.getCellStatus(5,5));
        assertEquals(DARK, board.getCellStatus(4,4));
        assertEquals(DARK, board.getCellStatus(3,3));
    }

    @Test
    public void testIsValidMoveWithValidMoveOnDown() {
        assertEquals(EMPTY, board.getCellStatus(5,4));
        assertEquals(LIGHT, board.getCellStatus(4,4));

        Move move = new Move(5,4, moveCellStatus, board);
        assertTrue(move.flipPiecesIfValidMove());
        assertEquals(DARK, board.getCellStatus(5,4));
        assertEquals(DARK, board.getCellStatus(4,4));
    }

    @Test
    public void testIsValidMoveWithValidMoveOnLeftDownDiagonal() {
        board.setCellStatus(2,4, DARK);
        assertEquals(EMPTY, board.getCellStatus(4,2));
        assertEquals(LIGHT, board.getCellStatus(3,3));
        Move move = new Move(4,2, moveCellStatus, board);
        assertTrue(move.flipPiecesIfValidMove());
        assertEquals(DARK, board.getCellStatus(4,2));
        assertEquals(DARK, board.getCellStatus(3,3));
    }
}