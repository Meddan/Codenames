package codenames;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


@RestController

public class GameController implements InitializingBean {
    private HashMap<String, Game> activeGames;
    private MultiKeyMap sessionGameTeamMap;

    //@RequestMapping(method = RequestMethod.POST)
    @CrossOrigin
    @RequestMapping(value = "/newgame")
    public Map<String, String> createNewGame(HttpServletRequest request) {
        String newID;
        do {
            newID = generateID();
        } while (activeGames.containsKey(newID));
        activeGames.put(newID, new Game());
        return Collections.singletonMap("response", newID);
    }
    @CrossOrigin
    @RequestMapping(value="/game/{path}", method = RequestMethod.GET)
    public Game getGameByID(@PathVariable("path") String path, HttpServletRequest request){
        HttpSession ses = request.getSession();
        System.out.println("session: " + ses);
        System.out.println("ID: " + ses.getId());
        System.out.println("attributes: " + ses.getAttributeNames());
        for (Enumeration<String> e = ses.getAttributeNames(); e.hasMoreElements();) {
            System.out.println(e.nextElement());
        }
        System.out.println("class: " + ses.getClass());
        System.out.println("got get request at /game/ " + path);
        if(activeGames.containsKey(path)){
            System.out.println("lookup: " + sessionGameTeamMap.get(ses, path));
            return activeGames.get(path);
        } else {
            return null;
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="/game/{path}/{team}", method = RequestMethod.POST)
    public void selectTeam(@PathVariable("path") String path, @PathVariable("team") String team, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("game: " +  path + " selecting team: " + team + " for session: " + request.getSession().getId());
        Game.Turn newTeam;
        if (team.equals("redspymaster")) {
            newTeam = Game.Turn.RedSpymaster;
        } else if (team.equals("bluespymaster")) {
            newTeam = Game.Turn.BlueSpymaster;
        } else if (team.equals("redagent")) {
            newTeam = Game.Turn.RedAgent;
        } else if (team.equals("blueagent")){
            newTeam = Game.Turn.BlueAgent;
        } else {
            newTeam = Game.Turn.BlueAgent;
        }
        sessionGameTeamMap.put(request.getSession(), path, newTeam);
    }
    @CrossOrigin
    @RequestMapping(value="/game/{path}/active", method = RequestMethod.GET)
    public Boolean activeID(@PathVariable("path") String path, HttpServletRequest request){
        return activeGames.containsKey(path);
    }
    /*
    @CrossOrigin
    @RequestMapping(value="/game/{path}", method = RequestMethod.GET)
    public Game doGameAction(@PathVariable("path") String path){
        if(activeGames.containsKey(path)){
            return activeGames.get(path);
        } else {
            return null;
        }
    }*/

    private String generateID(){
        StringBuilder sb = new StringBuilder();
        Random r = new Random();

        for(int i = 0 ; i < 5 ; i++){
            char c = (char) (r.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        activeGames = new HashMap<>();
        sessionGameTeamMap = new MultiKeyMap();
    }
}