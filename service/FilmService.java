package com.example.demo.service;


import com.example.demo.model.Film;
import com.example.demo.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }

    public Film getFilmById(Long id){
        return filmRepository.findById(id).orElse(null);
    }

    public Film getFilmByNameAndPrice(String name,int price){
        return filmRepository.findByNameAndPrice(name,price);
    }

    public void save(Film film){
        filmRepository.save(film);
    }

    public void delete(Long id){
        filmRepository.deleteById(id);
    }
}
