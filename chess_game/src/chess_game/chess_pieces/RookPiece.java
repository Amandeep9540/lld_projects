package chess_game.chess_pieces;

import chess_game.Cell;
import chess_game.Piece;
import chess_game.PieceColor;

public class RookPiece implements Piece {
    PieceColor pieceColor;

    @Override
    public PieceColor getColor() {
        return pieceColor;
    }

    @Override
    public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow, Cell[][] board) {
        return false;
    }

    @Override
    public boolean move(int toRow, int toCol, Cell[][] board) {
        return false;
    }
}
