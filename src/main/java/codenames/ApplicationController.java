package codenames;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by meddan on 29/06/17.
 */

@RestController
public class ApplicationController {
    private String text;
    @RequestMapping("/")
    @CrossOrigin
    public ApplicationController applicationController(){
        return new ApplicationController();
    }
    public ApplicationController(){
        this.text = "HELLO";
    }
    public String getText(){
        return text;
    }
}
