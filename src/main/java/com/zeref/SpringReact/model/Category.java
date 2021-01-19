package com.zeref.SpringReact.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//The Category class

@Entity
@Table(name ="categories")

public class Category {

    //The categoryId
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categoryId", unique = true, nullable = false)
    private Integer categoryId;

    //the categoryName
    @Column(name="categoryName", nullable=false)
    private String categoryName;

    //The categoryDescription
    @Column(name="categoryDescription")
    private String categoryDescription;

    //The categoryPic
    @Column(name="categoryPic")
    private String categoryPic;

    /** The products. */
   @OneToMany(mappedBy = "category")
   private List<Product> products;

    public Category(){
    }

    public Category(String categoryName, String categoryPic, int categoryId, String categoryDescription, List<Product> children){
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryPic = categoryPic;
    }

    //Gets the id.
	public Integer getId() {
		return categoryId;
	}
	
	public void setId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	//Gets the name.
	 
	public String getName() {
		return categoryName;
	}
	//Sets the name.
	public void setName(String categoryName) {
		this.categoryName = categoryName;
	}
	//Gets the description
	public String getDescription(){
		return categoryDescription;
	}
	//sets the description
	public void setDescription(String categoryDescription){
		this.categoryDescription = categoryDescription;
	}
	 //Gets the picture.
	
	public String getPicture() {
		return categoryPic;
	}

	 //Sets the picture.

	public void setPicture(String categoryPic) {
		this.categoryPic = categoryPic;
	}



}