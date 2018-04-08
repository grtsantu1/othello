package com.ubs.intrvw;

import java.util.Scanner;

import static com.ubs.intrvw.Player.PLAYER_X;
import static com.ubs.intrvw.Player.PLAYER_Y;

public class Game {

    private Board board = new Board();
    private static final String INVALID_MOVE_MESSAGE = "Invalid move. Please try again.";
    private static final String BOARD_FULL_MESSAGE = "No further moves available";

    public static void main(String[] args) {
        Game game = new Game();
        game.startPlay();
    }

    public void startPlay() {
        board.printBoard();
        Player currentPlayer = PLAYER_X;
        Scanner scanner = new Scanner(System.in);
        GameMoveInputParser gameMoveInputParser = new GameMoveInputParser();

        Move nextMove = null;
        while (!board.isFull()) {
            System.out.print(getPlayerMoveCommand(currentPlayer));
            nextMove = gameMoveInputParser.parseToGameMove(scanner.nextLine(), board, currentPlayer);
            if (nextMove == null || !nextMove.flipPiecesIfValidMove()) {
                System.out.println(INVALID_MOVE_MESSAGE);
            } else {
                currentPlayer = getNextPlayer(currentPlayer);
                board.printBoard();
            }
        }
        System.out.println(BOARD_FULL_MESSAGE);
        System.out.println(String.format("Player '%s' wins ( %s vs %s )", board.getWinner(),
                board.getWinnerCount(), board.getLoserCount()));
    }

    private String getPlayerMoveCommand(Player player) {
        return "Player '" + player.getTargetCellStatus().getValue() + "' move:";
    }

    private Player getNextPlayer(Player currentPlayer) {
        return PLAYER_X == currentPlayer ? PLAYER_Y : PLAYER_X;
    }
}
