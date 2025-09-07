package chess_game.cellCodeStratergy;

import chess_game.chessMoveHelpers.CellCoordinate;

public class DefaultCellCodeStrategy implements CellCodeStrategy{

    @Override
    public CellCoordinate decodeCellCode(String cellCode,int boardRow,int boardCol) {
        char colChar = Character.toUpperCase(cellCode.charAt(0));
        int col = colChar - 'A';

        int rowFromBottom = Integer.parseInt(cellCode.substring(1));

        int row = boardRow - rowFromBottom;
        return new CellCoordinate(row, col);
    }

    @Override
    public String encodeCellCode(int currentRow, int currentCol, int boardRow, int boardCol) {
        // Column → letter
        char colChar = (char) ('A' + currentCol);

        // Convert back to row number from bottom
        int rowFromBottom = boardRow - currentRow;

        return "" + colChar + rowFromBottom;
    }

    @Override
    public boolean isCellCodeValid(String cellCode, int boardRow, int boardCol) {
        if (cellCode == null || cellCode.length() < 2) {
            return false;
        }

        char colChar = Character.toUpperCase(cellCode.charAt(0));
        int col = colChar - 'A';
        if (col < 0 || col >= boardCol) {
            return false;
        }

        try {
            int rowFromBottom = Integer.parseInt(cellCode.substring(1));
            if (rowFromBottom < 1 || rowFromBottom > boardRow) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
