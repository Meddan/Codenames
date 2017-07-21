package codenames;

public class Game {

    private GameBoard gameBoard;
    private enum Turn {
        RedSpymaster,
        RedAgent,
        BlueSpymaster,
        BlueAgent
    }
    private Turn currentTurn;
    public Game(){
        gameBoard = new GameBoard();
        if(gameBoard.getStartingTeam() == Agent.Team.Red){
            currentTurn = Turn.RedSpymaster;
        } else {
            currentTurn = Turn.BlueSpymaster;
        }

    }
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Turn getCurrentTurn() {
        return currentTurn;
    }
}
