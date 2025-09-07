package chess_game.chess_pieces;

import chess_game.boardEntities.Board;
import chess_game.chessMoveHelpers.BoardPathValidator;
import chess_game.chessMoveHelpers.CellCoordinate;
import chess_game.chessMoveHelpers.ChessMove;

public class QueenPiece implements Piece {
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
    public boolean isValidMove(ChessMove chessMove, Board board, BoardPathValidator pathValidator) {
        CellCoordinate source = chessMove.getSourceCoordinate();
        CellCoordinate destination = chessMove.getDestinationCoordinate();

        int rowDiff = Math.abs(destination.getRow() - source.getRow());
        int colDiff = Math.abs(destination.getCol() - source.getCol());

        Piece destPiece = board.getCell(destination.getRow(), destination.getCol()).getPiece();

        if (destPiece != null && destPiece.getColor() == this.pieceColor) {
            return false;
        }

        if (source.getRow() == destination.getRow()) {
            return pathValidator.isHorizontalPathClear(board,source, destination);
        }

        if (source.getCol() == destination.getCol()) {
            return pathValidator.isVerticalPathClear(board,source, destination);
        }

        if (rowDiff == colDiff) {
            return pathValidator.isDiagonalPathClear(board,source, destination);
        }

        return false;
    }

    @Override
    public String getPieceEmoji() {
        return pieceColor == PieceColor.BLACK ? "\u265B" : "\u2655";
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }
}
