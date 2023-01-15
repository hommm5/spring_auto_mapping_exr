package com.example.json_product_shop.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private int age;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private List<User> friends;

    @OneToMany(mappedBy = "buyer")
    private List<Product> bought;

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private List<Product> sold;

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<Product> getBought() {
        return bought;
    }

    public void setBought(List<Product> bought) {
        this.bought = bought;
    }

    public List<Product> getSold() {
        return sold;
    }

    public void setSold(List<Product> sold) {
        this.sold = sold;
    }
}
