package chess_game;

public class Cell {
    private int row;
    private int col;
    private Piece piece;

    Cell(int row, int col, Piece piece){
        this.row = row;
        this.col = col;
        this.piece = piece;
    }
}
