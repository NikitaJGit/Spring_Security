package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "users";
    }


    @GetMapping("/remove/{id}")
    public String delete(@PathVariable("id") long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }


    @PostMapping(value = "/users/addUser/{id}")
    public String addUser(@ModelAttribute("user") User user, @PathVariable("id") long id){
        if(id == 0) {
            userService.addUser(user);
        } else {
            user.setId(id);
            userService.updateUser(user);
        }
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("listUsers", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getById(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        return "/userdata";
    }
}






















