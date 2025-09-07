package chess_game.cellCodeStratergy;

import chess_game.chessMoveHelpers.CellCoordinate;


public interface CellCodeStrategy {
    CellCoordinate decodeCellCode(String cellCode,int boardRow,int boardCol);
    String encodeCellCode(int currentRow,int currentCol,int boardRow,int boardCol);
    boolean isCellCodeValid(String cellCode, int boardRow, int boardCol);
}
