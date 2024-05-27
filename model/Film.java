package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "films")
public class Film {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Название фильма не должно быть пустым")
    private String name;

    @Column(name = "yearOfProduction")
    @NotEmpty(message = "Год выпуска не должен быть пустым")
    private int yearOfProduction;

    @Column(name = "author")
    @NotEmpty(message = "Имя автора не должно быть пустым")
    private String author;

    @Column(name = "picture")
    private String picture;

    @Column(name = "price")
    private int price;

    @Column(name = "description",length =1000)
    private String description;

    @ManyToMany(mappedBy = "films")
    private List<User> users;


    public Film() {
    }

    public Film(Long id, String name, int yearOfProduction, String author, String picture, int price, String description, List<User> users) {
        this.id = id;
        this.name = name;
        this.yearOfProduction = yearOfProduction;
        this.author = author;
        this.picture = picture;
        this.price = price;
        this.description = description;
        this.users = users;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
