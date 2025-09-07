package chess_game.chess_pieces;

import chess_game.*;

public class RookPiece implements Piece {
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
    public boolean isValidMove(ChessMove chessMove, Board board,BoardPathValidator pathValidator) {
        CellCoordinate source = chessMove.getSourceCoordinate();
        CellCoordinate destination = chessMove.getDestinationCoordinate();

        Piece destPiece = board.getCell(destination.getRow(), destination.getCol()).getPiece();

        if (destPiece != null && destPiece.getColor() == this.pieceColor) {
            return false;
        }

        if (source.getCol() == destination.getCol()) {
            return pathValidator.isVerticalPathClear(board,source, destination);
        }

        if (source.getRow() == destination.getRow()) {
            return pathValidator.isHorizontalPathClear(board,source, destination);
        }

        return false;
    }

    @Override
    public String getPieceEmoji() {
        return pieceColor == PieceColor.BLACK ? "\u265C" : "\u2656";
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }
}
