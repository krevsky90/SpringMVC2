package app.utils;

import app.dao.UserDAO;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass.getClass());
    }

    @Override
    public void validate(Object o, Errors errors) {
        //let's validate uniqueness of user's email
        User user = (User) o;
        String email = user.getEmail();

        if (userDAO.getUserByEmail(email) != null) {
            errors.rejectValue("email", "", "This email is already in use");
        }
    }
}