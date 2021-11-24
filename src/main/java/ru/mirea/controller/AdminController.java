package ru.mirea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.entity.User;
import ru.mirea.repository.*;

@Controller
public class AdminController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Autowired
    UserRepo userRepo;

    @Autowired
    MessageRepo messageRepo;

    @RequestMapping(value = "/block_user")
    public String blockUser(@RequestParam("id") Long id){
        User user = userRepo.getUserById(id);
        user.setEnabled(false);
        userRepo.save(user);
        return "redirect:/UsersPage";
    }

    @RequestMapping(value = "/unblock_user")
    public String unblockUser(@RequestParam("id") Long id){
        User user = userRepo.getUserById(id);
        user.setEnabled(true);
        userRepo.save(user);
        return "redirect:/UsersPage";
    }

    @RequestMapping(value = "delete_user")
    public String deleteUser(@RequestParam("id") Long id){
        userRepo.getUserById(id).getFriends().clear();
        messageRepo.findAllByRecipient(userRepo.getUserById(id)).clear();
        messageRepo.findAllBySender(userRepo.getUserById(id));
        userRepo.delete(userRepo.getUserById(id));
        return "redirect:/UsersPage";
    }
}
