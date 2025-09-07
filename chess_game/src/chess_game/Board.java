package chess_game;

import chess_game.cellCodeStratergy.CellCodeStrategy;
import chess_game.cellCodeStratergy.DefaultCellCodeStrategy;
import chess_game.chess_pieces.*;

public class Board {
    private Cell[][] cells;
    private int totalRow;
    private int totalCol;
    private CellCodeStrategy cellCodeStrategy;

    Board(){
        this(8,8);
    }
    Board(int row,int col){
        this.totalCol = col;
        this.totalRow = row;
        intializedBoard(row,col);
        this.cellCodeStrategy = new DefaultCellCodeStrategy();
    }

    private void intializedBoard(int row,int col){
        initializedEmptyBoard(row,col);
        createWhitePieces(row-1,col-1);
        createBlackPieces(row-1,col-1);
    }

    private void createWhitePieces(int row,int col) {
        initializeRowWithPawn(row-1,PieceColor.WHITE);
        initializeKingRow(row,PieceColor.WHITE);
    }

    private void createBlackPieces(int row,int col) {
        initializeRowWithPawn(1,PieceColor.BLACK);
        initializeKingRow(0,PieceColor.BLACK);
    }

    private void initializeRowWithPawn(int row,PieceColor pieceColor){
        for(int i=0;i<cells[row].length;i++){
            Piece pawnPiece = new PawnPiece();
            pawnPiece.setColor(pieceColor);
            cells[row][i].setPiece(pawnPiece);
        }
    }

    private void initializeKingRow(int row, PieceColor pieceColor) {
        initializeRook(row, pieceColor);
        initializeKnight(row, pieceColor);
        initializeBishop(row, pieceColor);
        initializeKing(row,pieceColor);
        initializeQueen(row,pieceColor);
    }

    private void initializeRook(int row, PieceColor pieceColor){
        Piece rook1 = new RookPiece();
        rook1.setColor(pieceColor);
        Piece rook2 = new RookPiece();
        rook2.setColor(pieceColor);
        cells[row][0].setPiece(rook1);
        cells[row][cells[row].length-1].setPiece(rook2);
    }

    private void initializeKnight(int row, PieceColor pieceColor){
        Piece knightPiece1 = new KnightPiece();
        knightPiece1.setColor(pieceColor);
        Piece knightPiece2 = new KnightPiece();
        knightPiece2.setColor(pieceColor);
        cells[row][1].setPiece(knightPiece1);
        cells[row][cells[row].length-2].setPiece(knightPiece2);
    }

    private void initializeBishop(int row, PieceColor pieceColor){
        Piece bishopPiece1 = new BishopPiece();
        bishopPiece1.setColor(pieceColor);
        Piece bishopPiece2 = new BishopPiece();
        bishopPiece2.setColor(pieceColor);
        cells[row][2].setPiece(bishopPiece1);
        cells[row][cells[row].length-3].setPiece(bishopPiece2);
    }

    private void initializeKing(int row, PieceColor pieceColor){
        Piece kingPiece = new KingPiece();
        kingPiece.setColor(pieceColor);
        cells[row][4].setPiece(kingPiece);
    }

    private void initializeQueen(int row, PieceColor pieceColor){
        Piece queenPiece = new QueenPiece();
        queenPiece.setColor(pieceColor);
        cells[row][3].setPiece(queenPiece);
    }

    private void initializedEmptyBoard(int row, int col) {
        cells = new Cell[row][col];
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    cells[i][j] = new Cell(i,j,null);
                }
            }
    }


    public void printBoard() {
        int row = cells.length;
        int col = cells[row - 1].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(" ---");
            }
            System.out.println();

            for (int j = 0; j < col; j++) {
                Cell cell = cells[i][j];
                String printingValue = (cell.getPiece() != null) ? cell.getPiece().getPieceEmoji() : getCellCode(i,j);
                System.out.print("| " + printingValue + " ");
            }
            System.out.println("|"); // End of row
        }

        // Bottom border after last row
        for (int j = 0; j < col; j++) {
            System.out.print(" ---");
        }
        System.out.println();
    }

    public int getTotalRow() {
        return totalRow;
    }

    public int getTotalCol() {
        return totalCol;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public CellCoordinate getCellCoordinateByCode(String cellCode) {
        if(cellCodeStrategy.isCellCodeValid(cellCode,totalRow,totalCol)){
            return cellCodeStrategy.decodeCellCode(cellCode,totalRow,totalCol);
        }
        throw new IllegalArgumentException("Invalid cellCode");
    }

    public Piece getPiece(CellCoordinate cellCoordinate){
        return this.getCell(cellCoordinate.getRow(),cellCoordinate.getCol()).getPiece();
    }

    public String getCellCode(int currRow,int currCol){
        return cellCodeStrategy.encodeCellCode(currRow,currCol,totalRow,totalCol);
    }
}
