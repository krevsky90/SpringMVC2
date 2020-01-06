package app;

import app.dao.UserDAO;
import app.model.User;
import app.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    @Qualifier("hibernateUserDAO")
    private UserDAO userDAO;
    @Autowired
    private UserValidator userValidator;

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

        model.addAttribute("usersVar", userDAO.getAllUsers());
        return "/usersView";

    }

    @GetMapping("/users/new")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "/sign_up";
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

    @PostMapping("/users/new")
    public String signUp(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/sign_up";
        }
        userDAO.addUser(user);

        //to return view from post-method is BAD practise -> good way is to redirect to some GET-handler
        return "redirect:/users";
    }
}
