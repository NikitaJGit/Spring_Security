package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private static UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users")
    public String printUserList(Model model) {

        model.addAttribute("user", new User());
        List<User> list =  userService.getAllUsers();
        model.addAttribute("listUsers", list);

        return "users";
    }

    @PostMapping (value = "/users/add")
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/users";
    }

    @DeleteMapping("/remove/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PatchMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("listUsers", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/userdata/{id}")
    public String userData(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "userdata";
    }
}






















