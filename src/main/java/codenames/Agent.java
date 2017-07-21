package codenames;

/**
 * Created by meddan on 29/06/17.
 */
public class Agent {
    public enum Team{
        Blue, Red, Civilian, Assassin
    }
    private Team team;
    private String word;
    private boolean revealed = false;
    public Agent(String word, Team team){
        this.word = word;
        this.team = team;
    }
    public String getWord(){
        return word;
    }
    public boolean isRevealed(){
        return revealed;
    }
    public Team getTeam(){
        return team;
    }
}
