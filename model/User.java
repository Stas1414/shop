package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "isLocked")
    private boolean isLocked;


    @ManyToMany(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles")
    private List<Role> roleList;

    @ManyToMany(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_films")
    private List<Film> films;



    public User() {
    }

    public User(String username, String password, String name,List<Role> roleList, List<Film> films) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.isLocked = true;
        this.roleList = roleList;
        this.films = films;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void addToBasket(Film film){
        films.add(film);
    }

}
