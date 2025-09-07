package chess_game.chessMoveHelpers;

import chess_game.boardEntities.Board;

public class BoardPathValidator {

    public boolean isVerticalPathClear(Board board, CellCoordinate source, CellCoordinate destination) {
        if (source.getCol() != destination.getCol()) return false;

        int startRow = Math.min(source.getRow(), destination.getRow()) + 1;
        int endRow = Math.max(source.getRow(), destination.getRow());

        for (int r = startRow; r < endRow; r++) {
            if (board.getCell(r, source.getCol()).getPiece() != null) {
                return false;
            }
        }

        return true;
    }

    public boolean isHorizontalPathClear(Board board,CellCoordinate source, CellCoordinate destination) {
        if (source.getRow() != destination.getRow()) return false;

        int startCol = Math.min(source.getCol(), destination.getCol()) + 1;
        int endCol = Math.max(source.getCol(), destination.getCol());

        for (int c = startCol; c < endCol; c++) {
            if (board.getCell(source.getRow(), c).getPiece() != null) {
                return false;
            }
        }

        return true;
    }

    public boolean isDiagonalPathClear(Board board,CellCoordinate source, CellCoordinate destination) {
        int rowDiff = destination.getRow() - source.getRow();
        int colDiff = destination.getCol() - source.getCol();

                // Not a diagonal
        if (Math.abs(rowDiff) != Math.abs(colDiff)) return false;

        int steps = Math.abs(rowDiff);
        int rowStep = rowDiff / steps;
        int colStep = colDiff / steps;

        for (int i = 1; i < steps; i++) {
            int r = source.getRow() + i * rowStep;
            int c = source.getCol() + i * colStep;
            if (board.getCell(r, c).getPiece() != null) return false;
        }

        return true; // path is clear
    }
}
