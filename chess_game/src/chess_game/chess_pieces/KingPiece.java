package chess_game.chess_pieces;

import chess_game.*;

public class KingPiece implements Piece {
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

        int rowDiff = Math.abs(destination.getRow() - source.getRow());
        int colDiff = Math.abs(destination.getCol() - source.getCol());

        if (rowDiff > 1 || colDiff > 1) {
            return false;
        }

        Piece destPiece = board.getCell(destination.getRow(), destination.getCol()).getPiece();
        if (destPiece != null && destPiece.getColor() == this.pieceColor) {
            return false;
        }

        return true;
    }

    @Override
    public String getPieceEmoji() {
        return pieceColor == PieceColor.BLACK ? "\u265A" : "\u2654";
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }
}
