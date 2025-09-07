package chess_game.chess_pieces;

import chess_game.*;

public class PawnPiece implements Piece {
     private PieceColor pieceColor;

    @Override
    public PieceColor getColor() {
        return pieceColor;
    }

    @Override
    public void setColor(PieceColor pieceColor){
        this.pieceColor = pieceColor;
    }

    @Override
    public boolean isValidMove(ChessMove chessMove, Board board,BoardPathValidator boardPathValidator) {
        CellCoordinate source = chessMove.getSourceCoordinate();
        CellCoordinate destination = chessMove.getDestinationCoordinate();

        int rowDiff = destination.getRow() - source.getRow();
        int colDiff = Math.abs(destination.getCol() - source.getCol());

        Piece destPiece = board.getCell(destination.getRow(), destination.getCol()).getPiece();

                /* White moves "up" (row decreases), Black moves "down" (row increases)*/
        int direction = (this.pieceColor == PieceColor.WHITE) ? -1 : 1;

        // 1. Normal forward move (1 step)
        if (colDiff == 0 && rowDiff == direction) {
            if (destPiece == null) {
                return true;
            }
        }

        // 2. First move (2 steps forward)
        if (colDiff == 0 && rowDiff == 2 * direction) {
            int startRow = (this.pieceColor == PieceColor.WHITE) ? board.getTotalRow() - 2 : 1;
            if (source.getRow() == startRow) {
                // check both cells empty
                int middleRow = source.getRow() + direction;
                if (board.getCell(middleRow, source.getCol()).getPiece() == null &&
                        destPiece == null) {
                    return true;
                }
            }
        }

        // 3. Diagonal capture (1 step forward, 1 step sideways)
        if (colDiff == 1 && rowDiff == direction) {
            if (destPiece != null && destPiece.getColor() != this.pieceColor) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getPieceEmoji() {
        return pieceColor == PieceColor.BLACK ? "\u265F" : "\u2659";
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }
}
