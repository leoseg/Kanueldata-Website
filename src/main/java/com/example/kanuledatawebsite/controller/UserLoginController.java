package com.example.kanuledatawebsite.controller;

import com.example.kanuledatawebsite.dataaccesslayer.UserRepository;
import com.example.kanuledatawebsite.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling login and registration
 */
@Controller
@RequestMapping("/")
public class UserLoginController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public String viewStartPage() throws Exception {
      return "startpage";
    }

    /**
     * Loads the registration page
     * @param model model which is used for mapping the information to user class
     * @return html name
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "registration_form";
    }

    /**
     * Processes the incoming registration request by encrypting the password and saving it to the database
     * @param user user object with the information for registration
     * @return html name
     * @throws DataAccessException which could occure with the database
     */
    @PostMapping("/process_register")
    public String processRegister(User user) throws DataAccessException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "register_success";
    }

    @GetMapping("/login")
    public String viewLoginPage(){
        return "login";
    }
}
