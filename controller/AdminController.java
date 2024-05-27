package com.example.demo.controller;

import com.example.demo.Jwt.JwtService;
import com.example.demo.model.Film;
import com.example.demo.model.User;
import com.example.demo.service.FilmService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    private FilmService filmService;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AdminController(UserService userService, FilmService filmService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.filmService = filmService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @GetMapping()
    public String admin(){
        return "admin/adminPage";
    }

    @GetMapping("/films")
    public String getFilms(Model model){
        model.addAttribute("films",filmService.getAllFilms());
        return "admin/index";
    }

    @GetMapping("/films/new")
    public String addFilm(Model model){
        model.addAttribute("film",new Film());
        return "admin/new";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("film") Film film){
        filmService.save(film);
        return "redirect:/admin/films";
    }

    @GetMapping("/films/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("film",filmService.getFilmById(id));
        return "admin/show";
    }
    @GetMapping("/users")
    public String getUsers(Model model){
        model.addAttribute("users",userService.getAllUsersByRole("user"));
        return "admin/showUsers";
    }

    @GetMapping("/users/{id}")
    public String showUser(@PathVariable("id") Long id,Model model){
        model.addAttribute("user",userService.getUserById(id));
        return "admin/user";
    }

    @PostMapping("/block")
    @Transactional
    public String blockUser(@RequestParam("id") Long id){
        User user = userService.getUserById(id);
        user.setLocked(false);
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/unblock")
    @Transactional
    public String unblockUser(@RequestParam("id") Long id){
        User user = userService.getUserById(id);
        user.setLocked(true);
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/films/delete")
    public String deleteFilm(Model model,@ModelAttribute("film") Film film){
        model.addAttribute("films",filmService.getAllFilms());
        return "admin/delete";
    }

    @PostMapping("/deleteFilm")
    @Transactional
    public String delete(@ModelAttribute("film") Film film){
        filmService.delete(film.getId());
        return "redirect:/admin/films";
    }
}
