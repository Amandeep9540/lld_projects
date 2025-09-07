package chess_game.chessMoveHelpers;

import chess_game.chess_pieces.Piece;

public class ChessMove {
    private final String sourceCode;
    private final CellCoordinate sourceCoordinate;
    private final String destinationCode;
    private final CellCoordinate destinationCoordinate;
    private Piece movingPiece;
    private Piece capturedPiece;

    private boolean isCheck;
    private boolean isCheckmate;

    public ChessMove(String sourceCode, String destinationCode, CellCoordinate fromCoordinate, CellCoordinate destinationCoordinate) {
        this.sourceCode = sourceCode;
        this.destinationCode = destinationCode;
        this.sourceCoordinate = fromCoordinate;
        this.destinationCoordinate = destinationCoordinate;

    }

    public String getSourceCode() {
        return sourceCode;
    }

    public CellCoordinate getSourceCoordinate() {
        return sourceCoordinate;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public CellCoordinate getDestinationCoordinate() {
        return destinationCoordinate;
    }

    public Piece getMovingPiece() {
        return movingPiece;
    }

    public void setMovingPiece(Piece movingPiece) {
        this.movingPiece = movingPiece;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    public void setCapturedPiece(Piece capturedPiece) {
        this.capturedPiece = capturedPiece;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isCheckmate() {
        return isCheckmate;
    }

    public void setCheckmate(boolean checkmate) {
        isCheckmate = checkmate;
    }
}
