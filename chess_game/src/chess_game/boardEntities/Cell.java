package chess_game.boardEntities;

import chess_game.chessMoveHelpers.CellCoordinate;
import chess_game.chess_pieces.Piece;

public class Cell {
    private int row;
    private int col;
    private CellCoordinate coordinate;
    private Piece piece;

    Cell(int row, int col, Piece piece) {
        coordinate = new CellCoordinate(row, col);
        this.piece = piece;
    }

    Cell(int row, int col) {
        coordinate = new CellCoordinate(row, col);
    }

    public int getRow() {
        return coordinate.getRow();
    }

    public void setRow(int row) {
        this.coordinate.setRow(row);
    }

    public int getCol() {
        return coordinate.getCol();
    }

    public void setCol(int col) {
        this.coordinate.setCol(col);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

}
