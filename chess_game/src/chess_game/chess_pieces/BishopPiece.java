package chess_game.chess_pieces;

import chess_game.boardEntities.Board;
import chess_game.chessMoveHelpers.BoardPathValidator;
import chess_game.chessMoveHelpers.CellCoordinate;
import chess_game.chessMoveHelpers.ChessMove;

public class BishopPiece implements Piece {
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

        Piece destPiece = board.getCell(destination.getRow(), destination.getCol()).getPiece();
                /*if in destination it own color piece*/
        if (destPiece != null && destPiece.getColor() == this.getColor()) {
            return false;
        }
                /*if path is not clear*/
        if (!pathValidator.isDiagonalPathClear(board,source, destination)) {
            return false;
        }
        return true;
    }

    @Override
    public String getPieceEmoji() {
        return pieceColor == PieceColor.BLACK ? "\u265D" : "\u2657";
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }


}
