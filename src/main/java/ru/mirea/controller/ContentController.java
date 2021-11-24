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
public class ContentController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Autowired
    BandRepo bandRepo;

    @Autowired
    SongRepo songRepo;

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = "/delete_song_from_player")
    public String deleteSongFromPlayer(@RequestParam("id") Long id, @RequestParam("url") String url){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        user.getPlayerSongs().remove(songRepo.getSongById(id));
        userRepo.save(user);
        return "redirect:/" + url;
    }

    @RequestMapping(value = "/delete_love_song")
    public String deleteLoveSong(@RequestParam("id") Long id, @RequestParam("url") String url){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        user.getLoveSongs().remove(songRepo.getSongById(id));
        userRepo.save(user);
        return "redirect:/"  + url;
    }

    @RequestMapping(value = "/add_love_song")
    public String addLoveSong(@RequestParam("id") Long id, @RequestParam("url") String url){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        user.getLoveSongs().add(songRepo.getSongById(id));
        userRepo.save(user);
        return "redirect:/" + url;
    }

    @RequestMapping(value = "/add_song_in_playlist")
    public String addSongInPlaylist(@RequestParam("id") Long id, @RequestParam("url") String url){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        user.getPlayerSongs().add(songRepo.getSongById(id));
        userRepo.save(user);
        return "redirect:/" + url;
    }

    @RequestMapping(value = "/delete_love_band")
    public String deleteLoveBand(@RequestParam("id") Long id, @RequestParam("url") String url){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        user.getLoveBands().remove(bandRepo.getBandById(id));
        userRepo.save(user);
        return "redirect:/" + url;
    }

    @RequestMapping(value = "/add_band_in_love")
    public String addBandInLove(@RequestParam("id") Long id, @RequestParam("url") String url){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        user.getLoveBands().add(bandRepo.getBandById(id));
        userRepo.save(user);
        return "redirect:/"+url;
    }
}
