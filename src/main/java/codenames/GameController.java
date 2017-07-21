package codenames;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@RestController

public class GameController  implements InitializingBean {
    private HashMap<String, Game> activeGames;

    //@RequestMapping(method = RequestMethod.POST)
    @CrossOrigin
    @RequestMapping(value = "/newgame")
    public Map<String, String> createNewGame() {
        String newID;
        do {
            newID = generateID();
        } while (activeGames.containsKey(newID));
        activeGames.put(newID, new Game());
        return Collections.singletonMap("response", newID);
    }
    @CrossOrigin
    @RequestMapping(value="/game/{path}", method = RequestMethod.GET)
    public Game getGameByID(@PathVariable("path") String path){
        System.out.println("got get request at /game/ " + path);
        if(activeGames.containsKey(path)){
            return activeGames.get(path);
        } else {
            return null;
        }
    }

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
    }
}