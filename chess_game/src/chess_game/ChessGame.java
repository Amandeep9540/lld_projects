package chess_game;

import chess_game.chessRuleEngine.ChessRuleEngine;
import chess_game.chessRuleEngine.DefaultChessRuleEngine;

import java.util.*;

public class ChessGame {
    private Map<Player, List<Piece>> capturedPieceMap = new HashMap<>();
    private Map<PieceColor, Player> playerColorMap = new HashMap<>();
    private Board board = null;
    private Player playingPlayer = null;
    private Player opponentPlayer = null;
    private Scanner sc;
    private ChessRuleEngine ruleEngine;
    private BoardPathValidator boardValidator;
    private MoveExecutor moveExecutor;

    ChessGame(){
        this.board = new Board();
        ruleEngine = new DefaultChessRuleEngine();
        moveExecutor = new MoveExecutor(this.board);
        sc =  new Scanner(System.in);
        boardValidator = new BoardPathValidator();
    }
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        chessGame.startGame();
    }

    public void initializeGame(){
        initializePlayers();
        board.printBoard();
    }

    private void initializePlayers() {
                //Getting the data from the console
        System.out.println("Please enter player name for white piece");
        String whitePlayerName = sc.nextLine();
        System.out.println("Please enter player name for black piece");
        String blackPlayerName = sc.nextLine();
                //Initializing the players
        Player whitePlayer = new Player(whitePlayerName,PieceColor.WHITE);
        Player blackPlayer = new Player(blackPlayerName,PieceColor.BLACK);
                //initialize the playerColorMap
        playerColorMap.put(PieceColor.WHITE,whitePlayer);
        playerColorMap.put(PieceColor.BLACK,blackPlayer);
                //initialize the player context
        playingPlayer = whitePlayer;
        opponentPlayer = blackPlayer;
                //initialize the capturedPieceMap
        capturedPieceMap.put(whitePlayer,new ArrayList<>());
        capturedPieceMap.put(blackPlayer,new ArrayList<>());
    }

    private void startGame() {
        initializeGame();
        while (true){
            String currentCellCode = getCurrentCellCode();
            String destinationCellCode = getDestinationCellCode();
            ChessMove chessMove = new ChessMove(currentCellCode,destinationCellCode,board.getCellCoordinateByCode(currentCellCode),board.getCellCoordinateByCode(destinationCellCode));
            chessMove.setMovingPiece(board.getPiece(chessMove.getSourceCoordinate()));
            if(!ruleEngine.isValidMove(chessMove,board)){
                System.out.println(playingPlayer.getName()+" this is invalid move");
                continue;
            }
            moveExecutor.movePiece(chessMove,ruleEngine);
            if(chessMove.isCheckmate()){
                System.out.println("Player "+playingPlayer.getName()+" won!");
                break;
            }
            if(chessMove.isCheck()){
                System.out.println("Player "+opponentPlayer.getName() +" your king is in danger");
            }
            if(Objects.nonNull(chessMove.getCapturedPiece())){
                List<Piece> pieces = capturedPieceMap.get(playingPlayer);
                pieces.add(chessMove.getCapturedPiece());
            }
            switchPlayerContext();
            board.printBoard();
        }
    }

    private String getDestinationCellCode() {
        System.out.println(playingPlayer.getName() +" please enter the destination cell code");
        String destinationCellCode = sc.nextLine();
        if(!ruleEngine.validateDestinationCell(destinationCellCode,playingPlayer,board)){
            System.out.println("Entered Cell is invalid or it may piece belong to you!");
            return getDestinationCellCode();
        }
        return destinationCellCode;
    }

    private String getCurrentCellCode() {
        System.out.println(playingPlayer.getName() +" please enter the cell code (from which you want to pick)");
        String currentCellCode = sc.nextLine();
        if(!ruleEngine.validateCurrentCell(currentCellCode,playingPlayer,board)){
            System.out.println("Entered Cell is invalid or it may piece belong to "+opponentPlayer.getName());
            return getCurrentCellCode();
        }
        return currentCellCode;
    }

        /*this method switch the player after the playingPlayer player play their move*/
    private void switchPlayerContext() {
        Player temp = playingPlayer;
        this.playingPlayer = this.opponentPlayer;
        this.opponentPlayer = temp;
    }
}
