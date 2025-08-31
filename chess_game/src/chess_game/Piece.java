package chess_game;

public interface Piece {
    PieceColor getColor();
    boolean isValidMove(int fromCol,int fromRow, int toCol, int toRow, Cell[][] board);
    boolean move(int toRow, int toCol, Cell[][] board);
}
