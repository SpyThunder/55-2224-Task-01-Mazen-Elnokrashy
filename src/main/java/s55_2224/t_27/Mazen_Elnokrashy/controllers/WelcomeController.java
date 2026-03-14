package s55_2224.t_27.Mazen_Elnokrashy.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Value("${USER_NAME}")
    private String userName;
    @Value("${ID}")
    private String userId;

    @GetMapping
    public String welcome() {
        return String.format("Hello %s %s, from Notes API", userName, userId);
    }

}
