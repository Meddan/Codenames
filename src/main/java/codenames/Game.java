package codenames;

public class Game {

    private GameBoard gameBoard;
    public enum Turn {
        RedSpymaster,
        RedAgent,
        BlueSpymaster,
        BlueAgent;

        private static Turn[] vals = values();
        public Turn next()
        {
            return vals[(this.ordinal()+1) % vals.length];
        }
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
    public void nextTurn(){
        currentTurn.next();
    }
}
