package chess_game.chess_pieces;

import chess_game.*;

public class KnightPiece implements Piece {
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

                /*Knight must move in an L shape*/
        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
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
        return pieceColor == PieceColor.BLACK ? "\u265E" : "\u2658";
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }

}
