package chess_game.chessRuleEngine;

import chess_game.boardEntities.Board;
import chess_game.chessMoveHelpers.ChessMove;
import chess_game.players.Player;

public interface ChessRuleEngine {
    boolean isKingInCheck( ChessMove lastMove, Board board);
    boolean isCheckmate( ChessMove lastMove, Board board);
    boolean isValidMove(ChessMove move, Board board);
    boolean validateCurrentCell(String cellCode, Player player, Board board);
    boolean validateDestinationCell(String cellCode, Player player, Board board);
}
