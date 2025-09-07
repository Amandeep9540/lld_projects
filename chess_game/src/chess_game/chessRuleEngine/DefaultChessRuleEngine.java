package chess_game.chessRuleEngine;

import chess_game.*;
import chess_game.cellCodeStratergy.CellCodeStrategy;
import chess_game.cellCodeStratergy.DefaultCellCodeStrategy;
import chess_game.CellCoordinate;
import chess_game.chess_pieces.PieceType;

public class DefaultChessRuleEngine implements ChessRuleEngine {

    private CellCodeStrategy cellCodeStrategy;
    private BoardPathValidator boardPathValidator;

    public DefaultChessRuleEngine(CellCodeStrategy cellCodeStrategy,BoardPathValidator boardPathValidator) {
        this.cellCodeStrategy = cellCodeStrategy;
        this.boardPathValidator = boardPathValidator;
    }

    public DefaultChessRuleEngine() {
        this.cellCodeStrategy = new DefaultCellCodeStrategy();
        this.boardPathValidator = new BoardPathValidator();
    }

    @Override
    public boolean isKingInCheck( ChessMove lastMove, Board board) {
        Piece movingPiece = lastMove.getMovingPiece();
        if (movingPiece == null) return false;

        // Opponent color
        PieceColor opponentColor = (movingPiece.getColor() == PieceColor.WHITE)
                ? PieceColor.BLACK : PieceColor.WHITE;

            // Find opponent king
        CellCoordinate kingPos = null;
        for (int r = 0; r < board.getTotalRow(); r++) {
            for (int c = 0; c < board.getTotalCol(); c++) {
                Piece piece = board.getCell(r, c).getPiece();
                if (piece != null && piece.getPieceType() == PieceType.KING && piece.getColor() == opponentColor) {
                    kingPos = new CellCoordinate(r, c);
                    break;
                }
            }
        }

        if (kingPos == null) return false;

        ChessMove attackMove = new ChessMove(
                lastMove.getDestinationCode(),
                cellCodeStrategy.encodeCellCode(kingPos.getRow(), kingPos.getCol(), board.getTotalRow(), board.getTotalCol()),
                lastMove.getDestinationCoordinate(),
                kingPos
        );
        attackMove.setMovingPiece(movingPiece);

        return isValidMove(attackMove, board);
    }

    @Override
    public boolean isCheckmate( ChessMove lastMove, Board board) {
        Piece captured = lastMove.getCapturedPiece();
        if (captured != null && captured.getPieceType() == PieceType.KING) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidMove(ChessMove move, Board board) {
        return move.getMovingPiece().isValidMove(move,board,boardPathValidator);
    }

    @Override
    public boolean validateCurrentCell(String cellCode, Player player, Board board) {
            if(!cellCodeStrategy.isCellCodeValid(cellCode,board.getTotalRow(), board.getTotalCol())) return false;
        CellCoordinate coord = cellCodeStrategy.decodeCellCode(cellCode, board.getTotalRow(), board.getTotalCol());
        Cell cell = board.getCell(coord.getRow(), coord.getCol());

        if (cell == null || cell.getPiece() == null) {
            return false;
        }
            //must belong to current player color
        return cell.getPiece().getColor() == player.getPieceColor();
    }

    @Override
    public boolean validateDestinationCell(String cellCode, Player player, Board board) {
        if(!cellCodeStrategy.isCellCodeValid(cellCode,board.getTotalRow(), board.getTotalCol())) return false;
        CellCoordinate coord = cellCodeStrategy.decodeCellCode(cellCode, board.getTotalRow(), board.getTotalCol());
        Cell cell = board.getCell(coord.getRow(), coord.getCol());

        if(cell.getPiece() == null) return true;
        return cell.getPiece().getColor() != player.getPieceColor();
    }
}
