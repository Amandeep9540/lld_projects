package chess_game.chessMoveHelpers;

import chess_game.boardEntities.Board;
import chess_game.boardEntities.Cell;
import chess_game.chessRuleEngine.ChessRuleEngine;
import chess_game.chess_pieces.Piece;

public class MoveExecutor {

    private final Board board;

    public MoveExecutor(Board board) {
        this.board = board;
    }

    public void movePiece(ChessMove chessMove, ChessRuleEngine chessRuleEngine){
        CellCoordinate source = chessMove.getSourceCoordinate();
        CellCoordinate destination = chessMove.getDestinationCoordinate();

        Cell sourceCell = board.getCell(source.getRow(), source.getCol());
        Cell destinationCell = board.getCell(destination.getRow(), destination.getCol());

        Piece movingPiece = chessMove.getMovingPiece();
        Piece capturedPiece = destinationCell.getPiece();

        destinationCell.setPiece(movingPiece);
        sourceCell.setPiece(null);

        chessMove.setCapturedPiece(capturedPiece);


        boolean isCheck = chessRuleEngine.isKingInCheck(chessMove, board);
        boolean isCheckmate = chessRuleEngine.isCheckmate(chessMove, board);

        chessMove.setCheck(isCheck);
        chessMove.setCheckmate(isCheckmate);
    }
}
