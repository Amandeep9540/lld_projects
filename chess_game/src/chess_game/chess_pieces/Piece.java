package chess_game.chess_pieces;

import chess_game.boardEntities.Board;
import chess_game.chessMoveHelpers.BoardPathValidator;
import chess_game.chessMoveHelpers.ChessMove;

public interface Piece {
    PieceColor getColor();
    void setColor(PieceColor pieceColor);
    boolean isValidMove(ChessMove chessMove, Board board, BoardPathValidator boardPathValidator);
    String getPieceEmoji();
    PieceType getPieceType();
}
