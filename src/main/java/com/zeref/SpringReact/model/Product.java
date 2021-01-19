package com.zeref.SpringReact.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productDesc")
    private String productDesc;

    @Column(name = "weight")
    private double weight;

    @Column(name = "price")
    private double price;

    @Column(name = "picture1")
    private String picture1;

    @Column(name = "picture2")
    private String picture2;

    @Column(name = "picture3")
    private String picture3;

    @ManyToOne
    private Category category;

    public Product(){

    }

    public Product(Integer id, String productName, String productDesc, double weight, double price,
                    String picture1, String picture2, String picture3, Category category){
        super();
        this.id = id;
        this.productName = productName;
        this.productDesc = productDesc;
        this.weight = weight;
        this.price = price;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
        this.category = category;

    }
    // set and get for id
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    //get and set for productname
    public String getproductName(){
        return productName;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }

    //get and set for product description
    public String getProductDesc(){
        return productDesc;
    }
    public void setProductDesc(String productDesc){
        this.productDesc = productDesc;
    }

    //get and set for weight
    public double getWeight(){
        return weight;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }

    //get and set for price
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    //get and set for pictures
    public String getPic1(){
        return picture1;
    }
    public void setPic1(String picture1){
        this.picture1 = picture1;
    }
    public String getPic2(){
        return picture2;
    }
    public void setPic2(String picture2){
        this.picture2 = picture2;
    }
    public String getPic3(){
        return picture3;
    }
    public void setPic3(String picture3){
        this.picture3 = picture3;
    }

    //get and set for the Category
    public Category getCategory(){
        return category;
    }
    public void setCategory( Category category){
        this.category =category;
    }

}
