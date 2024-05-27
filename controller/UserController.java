package com.example.demo.controller;


import com.example.demo.Jwt.JwtService;
import com.example.demo.model.Film;
import com.example.demo.model.User;
import com.example.demo.service.FilmService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private FilmService filmService;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Autowired
    public UserController(UserService userService, FilmService filmService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.filmService = filmService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @GetMapping("/films")
    public String getAllFilms(Model model) {
        model.addAttribute("films", filmService.getAllFilms());
        return "user/index";
    }

    @GetMapping("/films/{id}")
    public String showFilm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("film", filmService.getFilmById(id));
        return "user/show";
    }

    @PostMapping("/films/add-to-basket")
    @Transactional
    public String addToBasket(@RequestParam("name") String name, @RequestParam("price") int price) {
        Film film = filmService.getFilmByNameAndPrice(name, price);
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(principal.getName());
        user.addToBasket(film);
        userService.save(user);
        return "user/successful_page";
    }

    @PostMapping("/payment")
    public String pay(@RequestParam("price") int price,Model model){
        model.addAttribute("price",price);
        return "user/payment";
    }

    @PostMapping("/successful_payment")
    @Transactional
    public String getPayment(){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(principal.getName());
        user.getFilms().clear();
        userService.save(user);
        return "user/successful_payment";
    }

    @GetMapping("/films/clearBasket")
    @Transactional
    public String clearBasket(){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(principal.getName());
        user.getFilms().clear();
        userService.save(user);
        return "redirect:/user/films";
    }

    @GetMapping("/basket")
    public String getBasket(Model model) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(principal.getName());
        if(user.getFilms().size()!=0) {
            model.addAttribute("userBasket", user.getFilms());
            int sum = 0;
            for (Film film : user.getFilms()) {
                sum += film.getPrice();
            }
            model.addAttribute("resultPrice", sum);
            return "user/basket";
        }
        else {
            return "user/clean_basket";
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String
            password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(jwtService.GenerateToken(username));
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }
}