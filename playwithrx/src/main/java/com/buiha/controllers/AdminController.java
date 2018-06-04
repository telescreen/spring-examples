package com.buiha.controllers;

import com.buiha.models.User;
import com.buiha.models.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Value("${playwithrx.numberUsersPerPage}")
    private int numberUsersPerPage;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "admin_index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(@PathVariable("page") int page,
                            Model model) {
        long numberOfUsers = userRepository.count();
        Page<User> users = userRepository.findAll(PageRequest.of(page, numberUsersPerPage));

        if (logger.isDebugEnabled()) {
            logger.debug("Number of users: " + numberOfUsers);
            for(User user: users) {
                logger.debug(user.toString());
            }
            logger.debug(String.valueOf(users.getTotalPages()));
        }

        model.addAttribute("users", users);
        model.addAttribute("numberOfUsers", numberOfUsers);
        model.addAttribute("page", page);
        return "admin_users";
    }

    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable("id") long userId, Model model) {
        logger.debug("Edit user details for user with userId = " + userId);
        return "none";
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    public String saveUser(@RequestParam(name="userid", required = true) long userid, Model model) {
        return "none";
    }

    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(name="userid", required = true) long userid, Model model) {
        return "none";
    }

}
