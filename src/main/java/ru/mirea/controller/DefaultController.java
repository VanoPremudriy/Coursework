package ru.mirea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.entity.*;
import ru.mirea.repository.*;
import ru.mirea.services.BandService;
import ru.mirea.services.GenreService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
public class DefaultController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    @Value("${upload.path}")
    String uploadPath;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    GenreRepo genreRepo;

    @Autowired
    GenreBandRepo genreBandRepo;

    @Autowired
    BandRepo bandRepo;

    @Autowired
    SongRepo songRepo;

    @Autowired
    VideoRepo videoRepo;

    @Autowired
    ImageRepo imageRepo;

    @Autowired
    UserRepo userRepo;

    GenreService genreService = new GenreService();
    BandService bandService = new BandService();

    @RequestMapping(value = "/GenrePages")
    public String AllGenrePages(@RequestParam("id") Long id,Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        ArrayList<Band> bands = new ArrayList<>();
        ArrayList<GenreBand> genreBands = (ArrayList<GenreBand>) genreBandRepo.findAllByGenre_Id(id);
        genreBands.forEach(genreBand -> bands.add(bandRepo.getBandById(genreBand.getBand().getId())));
        Genre genre = genreRepo.getGenreById(id);
        bands.sort((o1, o2) -> bandService.sortByFirstLetter(o1,o2));
        ArrayList<Character> charsBands = new ArrayList<>();
        bands.forEach(band -> charsBands.add(band.getBandName().charAt(0)));
        Set<Character> set = new HashSet<>(charsBands);
        charsBands.clear();
        charsBands.addAll(set);
        charsBands.sort((o1, o2) -> {
            if (o1 < o2) return -1;
            else if (o1 > o2) return 1;
            else return 0;
        });
        ArrayList<Genre> genres = (ArrayList<Genre>) genreRepo.findAll();
        ArrayList<Character> charsGenres  = new ArrayList<>();
        genres.forEach(genre1 -> charsGenres.add(genre1.getGenreName().charAt(0)));
        genres.sort((o1, o2) -> genreService.sortByFirstLetter(o1,o2));
        model.addAttribute("user", user);
        model.addAttribute("that_bands",bands);
        model.addAttribute("that_genre", genre);
        model.addAttribute("that_chars", charsBands);
        model.addAttribute("genres", genres);
        return "/GenrePages";
    }

    @RequestMapping(value = "/BandsPages")
    public String BandsPages(@RequestParam("id") Long id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        Band band = bandRepo.getBandById(id);
        ArrayList<Song> songs = (ArrayList<Song>) songRepo.findAllByBand_Id(id);
        ArrayList<Video> videos = (ArrayList<Video>) videoRepo.findAllByBand_Id(id);
        ArrayList<Image> images = (ArrayList<Image>) imageRepo.findAllByBand_Id(id);
        ArrayList<Genre> genres = new ArrayList<>();
        ArrayList<GenreBand> genreBands = (ArrayList<GenreBand>) genreBandRepo.findAllByBand_Id(id);
        genreBands.forEach(genreBand -> genres.add(genreRepo.getGenreById(genreBand.getGenre().getId())));
        model.addAttribute("user", user);
        model.addAttribute("that_genres", genres);
        model.addAttribute("that_band", band);
        model.addAttribute("that_songs", songs);
        model.addAttribute("that_videos", videos);
        model.addAttribute("that_images", images);
        return "/BandsPages";
    }

    @RequestMapping(value = "/GenrePage")
    public String GenrePage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        ArrayList<Genre> genres = (ArrayList<Genre>) genreRepo.findAll();
        ArrayList<Character> charsGenre = new ArrayList<>();
        genres.forEach(genre -> charsGenre.add(genre.getGenreName().charAt(0)));
        Set<Character> set = new HashSet<>(charsGenre);
        charsGenre.clear();
        charsGenre.addAll(set);
        charsGenre.sort((o1, o2) -> {
            if (o1 < o2) return -1;
            else if (o1 > o2) return 1;
            else return 0;
        });
        genres.sort((o1, o2) -> genreService.sortByFirstLetter(o1,o2));
        model.addAttribute("user",user);
        model.addAttribute("that_genres", genres);
        model.addAttribute("that_chars", charsGenre);
        return "/GenrePage";
    }

    @GetMapping("/index")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        ArrayList<Genre> genres = (ArrayList<Genre>) genreRepo.findAll();
        model.addAttribute("that_genres", genres);
        model.addAttribute("user", user);
        return "/index";
    }

    @GetMapping("/Profile")
    public String Profile(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        ArrayList<Genre> genres = (ArrayList<Genre>) genreRepo.findAll();
        Set<User> friends = new HashSet<>(user.getFriends());
        model.addAttribute("user", user);
        model.addAttribute("genres", genres);
        model.addAttribute("friends", friends);
        return "/Profile";
    }

    @GetMapping("/EditingProfile")
    public String EditingProfile(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        ArrayList<Genre> genres = (ArrayList<Genre>) genreRepo.findAll();
        model.addAttribute("user", user);
        model.addAttribute("genres", genres);
        return "/EditingProfile";
    }

    @GetMapping("/OtherProfile")
    public String otherProfile(@RequestParam("username") String username, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        User otherUser = userRepo.getUserByUsername(username);
        ArrayList<Genre> genres = (ArrayList<Genre>) genreRepo.findAll();
        model.addAttribute("user", user);
        model.addAttribute("genres", genres);
        model.addAttribute("other_user", otherUser);
        return "/OtherProfile";
    }

    @GetMapping(value = "/UsersPage")
    public String UsersPage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.getUserByUsername(auth.getName());
        ArrayList<Genre> genres = (ArrayList<Genre>) genreRepo.findAll();
        ArrayList<User> users = (ArrayList<User>) userRepo.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        return "/UsersPage";
    }

}
