package ru.mirea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.entity.User;
import ru.mirea.repository.*;
import ru.mirea.services.BandService;
import ru.mirea.services.GenreService;

@Controller
public class FriendsController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Autowired
    UserRepo userRepo;



    @RequestMapping(value = "/invite_to_friends")
    public String inviteToFriends(@RequestParam("id") Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        User recip = userRepo.getUserById(id);
        recip.getInvites().add(user);
        userRepo.save(recip);
        return "redirect:/UsersPage";
    }

    @RequestMapping(value = "add_to_friends")
    public String addToFriends(@RequestParam("id") Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        User sender = userRepo.getUserById(id);
        user.getFriends().add(sender);
        sender.getFriends().add(user);
        user.getInvites().remove(sender);
        userRepo.save(sender);
        userRepo.save(user);

        return "redirect:/Profile";
    }

    @RequestMapping(value = "dont_add_to_friends")
    public String dontAddToFriends(@RequestParam("id") Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        user.getInvites().remove(userRepo.getUserById(id));
        userRepo.save(user);
        return "redirect:/Profile";
    }

    @RequestMapping(value = "delete_from_friends")
    public String deleteFromFriends(@RequestParam("id") Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        User friend = userRepo.getUserById(id);
        user.getFriends().remove(friend);
        friend.getFriends().remove(user);
        userRepo.save(user);
        userRepo.save(friend);
        return "redirect:/Profile";
    }
}
