package app;

import app.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class MainController {
    @GetMapping("/")
    public String view(@RequestParam(value = "name", required = false, defaultValue = "stranger") String name, Model model) {
        model.addAttribute("msg", "Hello, " + name);
        return "index";
    }

    //for pure data (without HTML and other beauty things)
    //there is no any View Resolver
    @GetMapping("/raw")
    @ResponseBody
    public String getRaw() {
        return "Raw data";//that will be returned as simple string (like responseBody)
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        Collection<User> usersList = new ArrayList<>();
        usersList.add(new User("name1", "surname1", "email1"));
        usersList.add(new User("name2", "surname2", "email2"));
        usersList.add(new User("name3", "surname3", "email3"));

        model.addAttribute("usersVar", usersList);
        return "usersView";

    }

}
