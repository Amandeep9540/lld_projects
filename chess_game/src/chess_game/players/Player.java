package chess_game.players;

import chess_game.chess_pieces.PieceColor;

public class Player {
    private String name;
    private PieceColor pieceColor;

    public Player(String name, PieceColor pieceColor) {
        this.name = name;
        this.pieceColor = pieceColor;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }
}
