package app.controller;

import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/users";
    }

    @GetMapping("/view")
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
        model.addAttribute("usersVar", userService.getAllUsers());
        return "/usersView";

    }

    //Difficult way - to send each field as separate parameter
//    @PostMapping("/users/new")
//    public String signUp(@RequestParam("name") String name,
//                         @RequestParam("surname") String surname,
//                         @RequestParam("email") String email) {
//        usersList.add(new User(name, surname, email));
//
//        //to return view from post-method is BAD practise -> good way is to redirect to some GET-handler
//        return "redirect:/users";
//    }
}
