package codenames;

import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Random;
import java.util.Timer;

public class GameBoard {
    private Agent[][] agents = new Agent[5][5];
    private Agent.Team startingTeam;

    public GameBoard(){
        Random r = new Random();
        if( r.nextInt(2) == 1 ){
            startingTeam = Agent.Team.Red;
        } else {
            startingTeam = Agent.Team.Blue;
        }
        List<String> words = GameUtil.get25Words();
        Agent.Team[][] teams = GameUtil.getNewBoardLayout(startingTeam);
        int k = 0;
        for(int i = 0; i<5 ; i++){
            for(int j = 0; j<5; j++){
                agents[i][j] = new Agent(words.get(k), teams[i][j]);
                k++;
            }
        }
    }
    public Agent[][] getAgents(){
        return agents;
    }
    public Agent.Team getStartingTeam() {
        return startingTeam;
    }

}
