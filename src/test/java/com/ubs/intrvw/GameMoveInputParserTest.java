package com.ubs.intrvw;

import org.junit.Test;

import static com.ubs.intrvw.Player.PLAYER_X;
import static org.junit.Assert.*;

public class GameMoveInputParserTest {

    private GameMoveInputParser parser = new GameMoveInputParser();
    private Board board = new Board();
    private Player player = PLAYER_X;

    @Test
    public void testParseToGameMoveWithOutOfRangeMove() {
        assertNull(parser.parseToGameMove("9a", board, player));
        assertNull(parser.parseToGameMove("0a", board, player));
        assertNull(parser.parseToGameMove("8i", board, player));
        assertNull(parser.parseToGameMove("1A", board, player));
    }

    @Test
    public void testParseToGameMoveWithInvalidRowValue() {
        assertNull(parser.parseToGameMove("ba", board, player));
    }

    @Test
    public void testParseToGameMoveWithInvalidColumnValue() {
        assertNull(parser.parseToGameMove("31", board, player));
    }

    @Test
    public void testParseToGameMoveWithMoreThanTwoChars() {
        assertNull(parser.parseToGameMove("abc", board, player));
        assertNull(parser.parseToGameMove("333", board, player));
        assertNull(parser.parseToGameMove("3as", board, player));
        assertNull(parser.parseToGameMove("a3d", board, player));
    }

    @Test
    public void testParseToGameMoveWithRowFirstValidValue() {
        Move move = parser.parseToGameMove("3a", board, player);
        assertNotNull(move);
        assertEquals(2, move.getMovePositionX());
        assertEquals(0, move.getMovePositionY());
    }

    @Test
    public void testParseToGameMoveWithColFirstValidValue() {
        Move move = parser.parseToGameMove("a2", board, player);
        assertNotNull(move);
        assertEquals(1, move.getMovePositionX());
        assertEquals(0, move.getMovePositionY());
    }
}