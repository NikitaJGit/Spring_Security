package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;


@Controller
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "/admin";
    }


    @GetMapping("/admin/remove/{id}")
    public String delete(@PathVariable("id") long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }


    @PostMapping(value = "/admin/addUser/{id}")
    public String addUser(@ModelAttribute("user") User user, @PathVariable("id") long id){
        if(id == 0) {
            userService.addUser(user);
        } else {
            user.setId(id);
            userService.updateUser(user);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("listUsers", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/admin/editRole/{id}")
    public String editRole(@PathVariable("id") long id){
        User user = userService.getUser(id);
        if(user.getId()!=1) {
            Role admin = roleService.getRoleAdmin();
            if (user.getRoles().size() == 1) {
                Set<Role> roles = new HashSet<Role>();
                roles.add(roleService.getRoleUser());
                roles.add(roleService.getRoleAdmin());
                user.setRoles(roles);
            } else {
                Set<Role> roles = new HashSet<Role>();
                roles.add(roleService.getRoleUser());
                user.setRoles(roles);
            }
            userService.updateUser(user);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/users/{id}")
    public String getById(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        return "/userdata";
    }
}






















