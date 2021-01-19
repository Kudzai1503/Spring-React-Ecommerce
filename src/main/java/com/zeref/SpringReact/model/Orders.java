package com.zeref.SpringReact.model;

import javax.persistence.*;



@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId", unique = true, nullable = false)
    private Integer orderId;

   // @ManyToMany
    private User user;

   // @ManyToOne
    private  Category category;

    public Orders(){
    }

    public Orders(Integer orderId, User user, Category category){
        super();
        this.orderId = orderId;
        this.user = user;
        this.category = category;
    }

    //getter and setter methods for id
    public Integer getOrderid(){
        return orderId;
    }
    public void setOrderid(Integer orderId){
        this.orderId = orderId;
    }

    //getter and setter methods for user
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }

    //getter and settter methods for category
    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category){
        this.category = category;
    }
}
