package com.zeref.SpringReact.model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;




@Entity
@Table(name = "users",
         uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
         })

public class User {
    
    //User id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    //username
    @NotBlank
    @Size(max = 20)
    private String username;

    //user email
    @NotBlank
    @Size(max = 30)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name ="user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    //constructors instantiate a new user
    public User(){}

    public User( String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    //getter and setter methods for userid
    public long getUserid(){
        return userid;
    }
    public void setUserid(long userid){
        this.userid = userid;
    }

    //getter and setter method for username
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    //getter and setter method for email
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    //getter and setter method for password
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public Set<Role> getRoles(){
        return roles;
    }
    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }
}
