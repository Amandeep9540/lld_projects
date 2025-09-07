package chess_game;

import chess_game.chess_pieces.PieceType;

public interface Piece {
    PieceColor getColor();
    void setColor(PieceColor pieceColor);
    boolean isValidMove(ChessMove chessMove, Board board,BoardPathValidator boardPathValidator);
    String getPieceEmoji();
    PieceType getPieceType();
}
